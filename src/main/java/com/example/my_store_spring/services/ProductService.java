package com.example.my_store_spring.services;

import com.example.my_store_spring.dto.ProductDto;
import com.example.my_store_spring.model.Product;
import com.example.my_store_spring.model.enums.StockStatus;
import com.example.my_store_spring.repository.ProductRepository;
import com.example.my_store_spring.utilities.Mapper;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public Page<ProductDto> findAll(Pageable pageable) {
        return mapper.mapPageEntityToDto(productRepository.findAll(pageable), ProductDto.class);
    }

    public List<ProductDto> findAllProducts(Pageable pageable) {
        List<Product> productList = productRepository.findAll(pageable).stream().collect(Collectors.toList());
        return mapper.collectToDto(productList, ProductDto.class);
    }

    public List<ProductDto> findAllProducts() {
        Stream<Product> productStream = StreamSupport.stream(productRepository.findAll().spliterator(), false);
        List<Product> productList = productStream.collect(Collectors.toList());
        return mapper.collectToDto(productList, ProductDto.class);
    }

    public List<ProductDto> findAllById(List<Integer> ids) throws EntityNotFoundException {
        Iterable<Product> allById = productRepository.findAllById(ids);
        return mapper.iterableToDto(allById, ProductDto.class);
    }

    public List<ProductDto> deleteAllById(List<Integer> ids) throws EntityNotFoundException {
        List<ProductDto> productDtoList = findAllById(ids);
        if (productDtoList.isEmpty()) {
            throw new EntityNotFoundException();
        }
        productRepository.deleteAllById(ids);
        return productDtoList;
    }

    public List<ProductDto> findAllProductsByType(String sortType) {
        List<Product> productList = Streams.stream(productRepository.findAll(Sort.by(sortType)))
                .collect(Collectors.toList());
        return mapper.collectToDto(productList, ProductDto.class);
    }

    public void addProduct(ProductDto productDto) {
        Product product = mapper.toEntity(productDto, Product.class);
        productRepository.save(product);
        productDto.setProductId(product.getProductId());
    }

    public boolean isProductExists(String productName) {
        return productRepository.existsProductByModel(productName);
    }
}
