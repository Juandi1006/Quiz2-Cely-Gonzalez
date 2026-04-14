package edu.co.ustavillavicencio.relationmapping.mapper;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.FacultyReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.FacultyResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Faculty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    Faculty toEntity(FacultyReqDTO facultyReqDTO);
    FacultyResDTO toResponseDTO(Faculty faculty);
}
