package com.example.my_store_spring.dto;

import com.example.my_store_spring.model.Order;
import com.example.my_store_spring.model.Product;
import lombok.*;

import javax.persistence.*;
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
