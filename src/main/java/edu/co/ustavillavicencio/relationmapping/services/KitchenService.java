package edu.co.ustavillavicencio.relationmapping.services;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.KitchenRequestDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.KitchenUpdateDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.KitchenResponseDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Chef;
import edu.co.ustavillavicencio.relationmapping.entities.Kitchen;
import edu.co.ustavillavicencio.relationmapping.repositories.ChefRepository;
import edu.co.ustavillavicencio.relationmapping.repositories.KitchenRepository;
import edu.co.ustavillavicencio.relationmapping.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final ChefRepository chefRepository;
    private final SecurityUtils securityUtils;

    @Transactional
    public KitchenResponseDTO createKitchen(KitchenRequestDTO request) {
        Kitchen kitchen = new Kitchen();
        kitchen.setRestaurant(request.getRestaurant());
        kitchen.setCapacity(request.getCapacity());
        kitchen.setChef(findChef(request.getChefId()));
        return toResponse(kitchenRepository.save(kitchen));
    }

    @Transactional(readOnly = true)
    public List<KitchenResponseDTO> getAllKitchens() {
        if (securityUtils.isAdmin()) {
            return kitchenRepository.findAll().stream().map(this::toResponse).toList();
        }

        return kitchenRepository.findByChefId(securityUtils.getCurrentChefId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public KitchenResponseDTO getKitchenById(Long id) {
        return toResponse(findKitchen(id));
    }

    @Transactional
    public KitchenResponseDTO updateKitchen(Long id, KitchenUpdateDTO request) {
        Kitchen kitchen = findKitchen(id);

        if (request.getRestaurant() != null) {
            kitchen.setRestaurant(request.getRestaurant());
        }
        if (request.getCapacity() != null) {
            kitchen.setCapacity(request.getCapacity());
        }
        if (request.getChefId() != null) {
            kitchen.setChef(findChef(request.getChefId()));
        }

        return toResponse(kitchenRepository.save(kitchen));
    }

    @Transactional
    public void deleteKitchen(Long id) {
        kitchenRepository.delete(findKitchen(id));
    }

    private Kitchen findKitchen(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocina no encontrada"));
    }

    private Chef findChef(Long chefId) {
        return chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef no encontrado"));
    }

    private KitchenResponseDTO toResponse(Kitchen kitchen) {
        return new KitchenResponseDTO(
                kitchen.getId(),
                kitchen.getRestaurant(),
                kitchen.getChef().getId(),
                kitchen.getCapacity()
        );
    }
}
