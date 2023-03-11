package com.example.my_store_spring.services;

import com.example.my_store_spring.dto.CategoryDto;
import com.example.my_store_spring.dto.ProductDetailsDto;
import com.example.my_store_spring.dto.ProductDto;
import com.example.my_store_spring.dto.StockStatusDto;
import com.example.my_store_spring.model.Category;
import com.example.my_store_spring.model.Product;
import com.example.my_store_spring.model.ProductDetails;
import com.example.my_store_spring.model.StockStatus;
import com.example.my_store_spring.repository.CategoryRepository;
import com.example.my_store_spring.repository.ProductDetailsRepository;
import com.example.my_store_spring.repository.ProductRepository;
import com.example.my_store_spring.repository.StockStatusRepository;
import com.example.my_store_spring.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockStatusRepository stockStatusRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final Mapper mapper;

    @Value("${upload.path}")
    private String uploadPath;

    public Page<ProductDto> findAll(Pageable pageable) {
        return mapper.mapPageEntityToDto(productRepository.findAll(pageable), ProductDto.class);
    }

    public ProductDto findById(Integer id) throws EntityNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(product, ProductDto.class);
    }

    public ProductDto deleteById(Integer id) throws EntityNotFoundException, IOException {
        ProductDto productDto = null;
        if (isProductExists(id)) {
            productDto = findById(id);
            String imagePathString = "src/main/resources/static" + productDto.getImage();
            productRepository.removeProductById(id);
            Files.deleteIfExists(Path.of(imagePathString));
            log.info("");
        }
        return productDto;
    }

    public ProductDto addProduct(CategoryDto categoryDto, StockStatusDto stockStatusDto,
                                 ProductDetailsDto productDetailsDto, ProductDto productDto, MultipartFile file) throws IOException {
        String newFile;
        try {
            newFile = uploadFile(file);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        productDto.setImage(newFile);

        productDto.setDateAdded(LocalDate.now());

        Product product = mapper.toEntity(productDto, Product.class);

        productDto.setCategoryDto(setCategoryById(product, categoryDto));
        productDto.setStockStatusDto(setStockStatusById(product, stockStatusDto));

        productRepository.save(product);

        productDto.setProductDetailsDto(setProductDetails(product, productDetailsDto));
        productDto.setProductId(product.getProductId());
        log.info("");
        return productDto;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Path uploadDir = Path.of(uploadPath);
        if (!Files.isDirectory(uploadDir)) {
            Files.createDirectory(uploadDir);
            log.info("DIR CREATED: {}", uploadDir.getFileName());
        }
        String originalFilename = file.getOriginalFilename();
        Path path = Path.of(uploadPath + originalFilename);
        if (Files.exists(path)) {
            throw new IOException("FILE WITH NAME: \"" + originalFilename + "\" IS EXISTS");
        }
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        String pathString = path.toString();
        int indexOf = path.toString().indexOf("\\img");
        log.info("");
        return pathString.substring(indexOf);
    }

    public boolean isProductExists(String productName) {
        return productRepository.existsProductByModel(productName);
    }

    public boolean isProductExists(Integer id) {
        return productRepository.existsProductByProductId(id);
    }

    public List<CategoryDto> findAllCategories() {
        return mapper.iterableToDto(categoryRepository.findAll(), CategoryDto.class);
    }

    public List<StockStatusDto> findAllStockStatuses() {
        return mapper.iterableToDto(stockStatusRepository.findAll(), StockStatusDto.class);
    }

    protected Category findCategoryById(CategoryDto categoryDto) {
        return categoryRepository.findById(categoryDto.getCategoryId()).orElseThrow(EntityNotFoundException::new);
    }

    protected CategoryDto setCategoryById(Product product, CategoryDto categoryDto) throws EntityNotFoundException {
        Integer id = categoryDto.getCategoryId();
        Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setCategory(category);
        return mapper.toDto(category, CategoryDto.class);
    }

    protected StockStatusDto setStockStatusById(Product product, StockStatusDto stockStatusDto) throws EntityNotFoundException {
        Integer id = stockStatusDto.getStatusId();
        StockStatus stockStatus = stockStatusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setStockStatus(stockStatus);
        return mapper.toDto(stockStatus, StockStatusDto.class);
    }

    protected ProductDetailsDto setProductDetails(Product product, ProductDetailsDto productDetailsDto) throws EntityNotFoundException {
        ProductDetails productDetails = mapper.toEntity(productDetailsDto, ProductDetails.class);
        productDetails.setProduct(product);
        productDetailsRepository.save(productDetails);

        productDetailsDto = mapper.toDto(productDetails, ProductDetailsDto.class);
        productDetailsDto.setId(productDetails.getId());
        return productDetailsDto;
    }
}
