package com.example.my_store_spring;

import com.example.my_store_spring.dto.ProductDto;
import com.example.my_store_spring.model.Product;
import com.example.my_store_spring.model.StockStatus;
import com.example.my_store_spring.repository.ProductRepository;
import com.example.my_store_spring.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RequiredArgsConstructor
@Slf4j
public class MyStoreSpringApplication {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyStoreSpringApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        Page<ProductDto> allProducts = productService.findAll(PageRequest.of(0, 5));
        for (ProductDto productDto : allProducts) {
            log.info("{}", productDto);
        }

//        Iterable<Product> all = productRepository.findAll();
//        for (Product product : all) {
//            log.info("");
//        }
    }

}
