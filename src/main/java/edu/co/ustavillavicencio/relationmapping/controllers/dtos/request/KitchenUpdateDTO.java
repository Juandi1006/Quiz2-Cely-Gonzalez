package edu.co.ustavillavicencio.relationmapping.controllers.dtos.request;

import lombok.Data;

@Data
public class KitchenUpdateDTO {
    private String restaurant;
    private Long chefId;
    private Integer capacity;
}
