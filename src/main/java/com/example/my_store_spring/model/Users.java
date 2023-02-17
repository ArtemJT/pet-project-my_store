package com.example.my_store_spring.model;

import com.example.my_store_spring.model.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private LocalDateTime dateAdded;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}


