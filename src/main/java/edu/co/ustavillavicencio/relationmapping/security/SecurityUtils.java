package edu.co.ustavillavicencio.relationmapping.security;

import edu.co.ustavillavicencio.relationmapping.entities.Chef;
import edu.co.ustavillavicencio.relationmapping.repositories.ChefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final ChefRepository chefRepository;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    public Long getCurrentChefId() {
        Chef chef = chefRepository.findByUserUsername(getCurrentUsername())
                .orElseThrow(() -> new RuntimeException("No existe un chef asociado al usuario autenticado"));
        return chef.getId();
    }
}
