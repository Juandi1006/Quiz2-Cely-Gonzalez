package edu.co.ustavillavicencio.relationmapping.controllers.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KitchenResponseDTO {
    private Long id;
    private String restaurant;
    private Long chefId;
    private Integer capacity;
}
