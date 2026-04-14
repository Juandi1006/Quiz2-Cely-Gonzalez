package edu.co.ustavillavicencio.relationmapping.mapper;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ProfessorReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.ProfessorResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    Professor toEntity(ProfessorReqDTO professorReqDTO);
    ProfessorResDTO toDTO(Professor professor);
}
