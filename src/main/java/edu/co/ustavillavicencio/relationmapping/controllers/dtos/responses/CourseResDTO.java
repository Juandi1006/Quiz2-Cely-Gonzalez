package edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResDTO {
    private Long id;
    private String name;
}
