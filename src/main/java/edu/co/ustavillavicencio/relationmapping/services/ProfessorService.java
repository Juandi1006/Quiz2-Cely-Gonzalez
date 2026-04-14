package edu.co.ustavillavicencio.relationmapping.services;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.ProfessorReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.ProfessorResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Professor;
import edu.co.ustavillavicencio.relationmapping.mapper.ProfessorMapper;
import edu.co.ustavillavicencio.relationmapping.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorResDTO createProfessor(ProfessorReqDTO reqDTO) {
        Professor professorToSave = professorMapper.toEntity(reqDTO);
        Professor savedProfessor = professorRepository.save(professorToSave);
        return professorMapper.toDTO(savedProfessor);
    }
}
