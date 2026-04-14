package edu.co.ustavillavicencio.relationmapping.controllers.dtos.request;

import lombok.Data;

@Data
public class ChefRequestDTO {
    private String username;
    private String password;
    private String name;
    private String specialization;
    private Integer age;
}
