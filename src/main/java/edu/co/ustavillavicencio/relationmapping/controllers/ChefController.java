package edu.co.ustavillavicencio.relationmapping.controllers;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ChefRequestDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ChefUpdateDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.ChefResponseDTO;
import edu.co.ustavillavicencio.relationmapping.services.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chefs")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChefResponseDTO> createChef(@RequestBody ChefRequestDTO request) {
        return ResponseEntity.ok(chefService.createChef(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ChefResponseDTO>> getAllChefs() {
        return ResponseEntity.ok(chefService.getAllChefs());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<ChefResponseDTO> getMyProfile() {
        return ResponseEntity.ok(chefService.getCurrentChefProfile());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChefResponseDTO> getChefById(@PathVariable Long id) {
        return ResponseEntity.ok(chefService.getChefById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChefResponseDTO> updateChef(@PathVariable Long id, @RequestBody ChefUpdateDTO request) {
        return ResponseEntity.ok(chefService.updateChef(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteChef(@PathVariable Long id) {
        chefService.deleteChef(id);
        return ResponseEntity.noContent().build();
    }
}
