package edu.co.ustavillavicencio.relationmapping.controllers;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ProfessorReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.ProfessorResDTO;
import edu.co.ustavillavicencio.relationmapping.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorResDTO> createProfessor(@RequestBody ProfessorReqDTO reqDTO) {
        return ResponseEntity.ok(professorService.createProfessor(reqDTO));
    }
}
