package edu.co.ustavillavicencio.relationmapping.services;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ChefRequestDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ChefUpdateDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.ChefResponseDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.KitchenResponseDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Chef;
import edu.co.ustavillavicencio.relationmapping.entities.Kitchen;
import edu.co.ustavillavicencio.relationmapping.entities.UserApp;
import edu.co.ustavillavicencio.relationmapping.repositories.ChefRepository;
import edu.co.ustavillavicencio.relationmapping.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepository chefRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ChefResponseDTO createChef(ChefRequestDTO request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(existing -> {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        });

        UserApp user = new UserApp();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("CHEF");
        UserApp savedUser = userRepository.save(user);

        Chef chef = new Chef();
        chef.setUser(savedUser);
        chef.setSpecialization(request.getSpecialization());
        chef.setAge(request.getAge());

        return toResponse(chefRepository.save(chef));
    }

    @Transactional(readOnly = true)
    public List<ChefResponseDTO> getAllChefs() {
        return chefRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ChefResponseDTO getChefById(Long id) {
        return toResponse(findChef(id));
    }

    @Transactional(readOnly = true)
    public ChefResponseDTO getCurrentChefProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Chef chef = chefRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("No existe un chef asociado al usuario autenticado"));
        return toResponse(chef);
    }

    @Transactional
    public ChefResponseDTO updateChef(Long id, ChefUpdateDTO request) {
        Chef chef = findChef(id);
        UserApp user = chef.getUser();

        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            userRepository.findByUsername(request.getUsername()).ifPresent(existing -> {
                throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
            });
            user.setUsername(request.getUsername());
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getSpecialization() != null) {
            chef.setSpecialization(request.getSpecialization());
        }
        if (request.getAge() != null) {
            chef.setAge(request.getAge());
        }

        userRepository.save(user);
        return toResponse(chefRepository.save(chef));
    }

    @Transactional
    public void deleteChef(Long id) {
        Chef chef = findChef(id);
        UserApp user = chef.getUser();
        chefRepository.delete(chef);
        userRepository.delete(user);
    }

    private Chef findChef(Long id) {
        return chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef no encontrado"));
    }

    private ChefResponseDTO toResponse(Chef chef) {
        List<KitchenResponseDTO> kitchens = chef.getKitchens().stream()
                .map(this::toKitchenResponse)
                .toList();

        return new ChefResponseDTO(
                chef.getId(),
                chef.getUser().getUsername(),
                chef.getUser().getName(),
                chef.getSpecialization(),
                chef.getAge(),
                kitchens
        );
    }

    private KitchenResponseDTO toKitchenResponse(Kitchen kitchen) {
        return new KitchenResponseDTO(
                kitchen.getId(),
                kitchen.getRestaurant(),
                kitchen.getChef().getId(),
                kitchen.getCapacity()
        );
    }
}
