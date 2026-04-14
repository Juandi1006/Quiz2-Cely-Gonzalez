package edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChefResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String specialization;
    private Integer age;
    private List<KitchenResponseDTO> kitchens;
}
