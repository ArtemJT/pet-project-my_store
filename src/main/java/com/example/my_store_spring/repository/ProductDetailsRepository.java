package com.example.my_store_spring.repository;

import com.example.my_store_spring.model.ProductDetails;
import com.example.my_store_spring.model.StockStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends CrudRepository<ProductDetails, Integer> {

}
