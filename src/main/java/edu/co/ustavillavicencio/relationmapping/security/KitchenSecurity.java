package edu.co.ustavillavicencio.relationmapping.security;

import edu.co.ustavillavicencio.relationmapping.entities.Kitchen;
import edu.co.ustavillavicencio.relationmapping.repositories.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("kitchenSecurity")
@RequiredArgsConstructor
public class KitchenSecurity {

    private final KitchenRepository kitchenRepository;
    private final SecurityUtils securityUtils;

    public boolean canAccessKitchen(Long kitchenId) {
        if (securityUtils.isAdmin()) {
            return true;
        }

        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new RuntimeException("Cocina no encontrada"));

        return kitchen.getChef().getId().equals(securityUtils.getCurrentChefId());
    }
}
