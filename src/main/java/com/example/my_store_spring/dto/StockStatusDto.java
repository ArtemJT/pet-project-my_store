package com.example.my_store_spring.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockStatusDto {

    private Integer statusId;

    private String status;
}