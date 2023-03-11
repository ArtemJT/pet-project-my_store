package com.example.my_store_spring.repository;

import com.example.my_store_spring.model.Category;
import com.example.my_store_spring.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
