package com.example.my_store_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Integer orderItemId;

    private Integer count;

    private BigDecimal cost;

    private ProductDto productDto;
}
