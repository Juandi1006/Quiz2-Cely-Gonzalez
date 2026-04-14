package edu.co.ustavillavicencio.relationmapping.mapper;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.CourseReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.CourseResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResDTO toDTO(Course course);
    Course toEntity(CourseReqDTO courseReqDTO);
}
