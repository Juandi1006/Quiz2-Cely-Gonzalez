package edu.co.ustavillavicencio.relationmapping.services;

import edu.co.ustavillavicencio.relationmapping.controllers.dtos.request.FacultyReqDTO;
import edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses.FacultyResDTO;
import edu.co.ustavillavicencio.relationmapping.entities.Faculty;
import edu.co.ustavillavicencio.relationmapping.mapper.FacultyMapper;
import edu.co.ustavillavicencio.relationmapping.repositories.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyResDTO createFaculty(FacultyReqDTO facultyReqDTO) {
        Faculty faculty = facultyMapper.toEntity(facultyReqDTO);
        Faculty savedFaculty = facultyRepository.save(faculty);
        return facultyMapper.toResponseDTO(savedFaculty);
    }

}
