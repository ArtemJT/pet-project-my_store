package com.example.my_store_spring.dto;

import com.example.my_store_spring.model.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer productId;

    private LocalDate dateAdded;

    private String model;

    private String measure;

    private double price;

    private StockStatus stockStatus;
}
