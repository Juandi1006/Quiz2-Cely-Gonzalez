package edu.co.ustavillavicencio.relationmapping.controllers.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupResponse {
    private Long id;
    private String username;
    private String name;
    private String role;
}
