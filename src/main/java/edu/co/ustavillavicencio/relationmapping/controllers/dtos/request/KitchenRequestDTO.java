package edu.co.ustavillavicencio.relationmapping.controllers.dtos.request;

import lombok.Data;

@Data
public class KitchenRequestDTO {
    private String restaurant;
    private Long chefId;
    private Integer capacity;
}
