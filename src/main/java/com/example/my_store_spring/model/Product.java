package com.example.my_store_spring.model;

import com.example.my_store_spring.model.enums.StockStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column
    private LocalDate dateAdded;

    @Column
    private String model;

    @Column
    private String measure;

    @Column
    private double price;

    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;
}
