package edu.co.ustavillavicencio.relationmapping.controllers;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.KitchenRequestDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.KitchenUpdateDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.KitchenResponseDTO;
import edu.co.ustavillavicencio.relationmapping.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KitchenResponseDTO> createKitchen(@RequestBody KitchenRequestDTO request) {
        return ResponseEntity.ok(kitchenService.createKitchen(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CHEF')")
    public ResponseEntity<List<KitchenResponseDTO>> getAllKitchens() {
        return ResponseEntity.ok(kitchenService.getAllKitchens());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @kitchenSecurity.canAccessKitchen(#id)")
    public ResponseEntity<KitchenResponseDTO> getKitchenById(@PathVariable Long id) {
        return ResponseEntity.ok(kitchenService.getKitchenById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KitchenResponseDTO> updateKitchen(@PathVariable Long id, @RequestBody KitchenUpdateDTO request) {
        return ResponseEntity.ok(kitchenService.updateKitchen(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteKitchen(@PathVariable Long id) {
        kitchenService.deleteKitchen(id);
        return ResponseEntity.noContent().build();
    }
}
