package edu.co.ustavillavicencio.relationmapping.controllers.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorReqDTO {
    private String dni;
    private String name;
}
