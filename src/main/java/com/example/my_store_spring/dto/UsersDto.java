package com.example.my_store_spring.dto;

import com.example.my_store_spring.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Integer userId;

    private LocalDateTime dateAdded;

    private String name;

    private String password;

    private String email;

    private UserRole role;
}
