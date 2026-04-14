package edu.co.ustavillavicencio.relationmapping.controllers.dtos.request;

import lombok.Data;

@Data
public class ChefUpdateDTO {
    private String username;
    private String name;
    private String specialization;
    private Integer age;
    private String password;
}
