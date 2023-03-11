package com.example.my_store_spring.dto;

import com.example.my_store_spring.model.Category;
import com.example.my_store_spring.model.ProductDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer productId;

    private String model;

    private BigDecimal price;

    private LocalDate dateAdded;

    private String image;

    private StockStatusDto stockStatusDto;

    private CategoryDto categoryDto;

    private ProductDetailsDto productDetailsDto;
}
