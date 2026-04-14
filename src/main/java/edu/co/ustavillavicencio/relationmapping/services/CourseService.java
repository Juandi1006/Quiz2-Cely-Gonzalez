package edu.co.ustavillavicencio.relationmapping.services;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.CourseReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.CourseResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Course;
import edu.co.ustavillavicencio.relationmapping.mapper.CourseMapper;
import edu.co.ustavillavicencio.relationmapping.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResDTO create(CourseReqDTO reqDTO) {
        Course course = courseMapper.toEntity(reqDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }
}
