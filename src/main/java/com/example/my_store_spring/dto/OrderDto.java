package com.example.my_store_spring.dto;

import com.example.my_store_spring.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer orderId;

    private BigDecimal amount;

    private LocalDate dateAdded;

    private OrderStatus status;

    private List<OrderItemDto> orderItemDtoList;
}
