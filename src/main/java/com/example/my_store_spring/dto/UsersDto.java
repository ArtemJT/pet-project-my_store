package com.example.my_store_spring.dto;

import com.example.hw_31_spring_security.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    private Integer id;

    private String name;

    private String password;

    private UserRole role;
}
