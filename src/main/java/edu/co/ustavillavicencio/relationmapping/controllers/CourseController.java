package edu.co.ustavillavicencio.relationmapping.controllers;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.CourseReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.CourseResDTO;
import edu.co.ustavillavicencio.relationmapping.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResDTO> createCourse(@RequestBody CourseReqDTO reqDTO) {
        return ResponseEntity.ok(courseService.create(reqDTO));
    }


}
