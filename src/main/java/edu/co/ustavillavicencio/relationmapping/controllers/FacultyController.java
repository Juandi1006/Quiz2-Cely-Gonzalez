package edu.co.ustavillavicencio.relationmapping.controllers;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.FacultyReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.FacultyResDTO;
import edu.co.ustavillavicencio.relationmapping.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping()
    public ResponseEntity<FacultyResDTO> createFaculty(@RequestBody FacultyReqDTO facultyReqDTO) {
        return ResponseEntity.ok(facultyService.createFaculty(facultyReqDTO));
    }

}
