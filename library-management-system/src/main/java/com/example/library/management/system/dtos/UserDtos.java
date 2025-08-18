package com.example.library.management.system.dtos;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserDtos {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Min(message = "Min Length Should be 5", value = 5)
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Min(message = "Min Length Should be 5", value = 5)
    private String password;
    
    private Set<String> roles;
    
    public UserDtos(){}

    public UserDtos(Integer id, String name, String email, String password, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserDtos(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
