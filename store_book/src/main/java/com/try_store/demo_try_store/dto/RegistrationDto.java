package com.try_store.demo_try_store.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
