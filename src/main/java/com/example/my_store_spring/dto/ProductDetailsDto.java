package com.example.my_store_spring.dto;

import com.example.my_store_spring.model.Product;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

    private Integer id;

    private String name;
}