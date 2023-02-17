package com.example.my_store_spring.controller;

import com.example.my_store_spring.dto.ProductDto;
import com.example.my_store_spring.model.enums.StockStatus;
import com.example.my_store_spring.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.my_store_spring.model.enums.StockStatus.ON_STOCK;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAll(Model model, @RequestParam(required = false) String sort,
                         @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = sort == null
                ? PageRequest.of(page - 1, size)
                : PageRequest.of(page - 1, size, Sort.by(sort));

        Page<ProductDto> productDtoPage = productService.findAll(pageable);

        List<ProductDto> productDtoList = productDtoPage.toList();
        int currentPage = productDtoPage.getNumber() + 1;
        long totalItems = productDtoPage.getTotalElements();
        int totalPages = productDtoPage.getTotalPages();
//        log.info("TOTAL ITEMS: {}", totalItems);
//        log.info("TOTAL PAGES: {}", totalPages);
//        log.info("CURRENT PAGE: {}", currentPage);

        model.addAttribute("allProducts", productDtoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", size);
        return "products";
    }


//    @GetMapping
//    public String getAllProducts(Model model) {
//        log.info("products page");
//        List<ProductDto> products = productService.findAllProducts();
//        model.addAttribute("products", products);
//        return "product/products";
//    }

    @GetMapping("addProduct")
    public String addProduct() {
        log.info("product add page");
        return "product/addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@RequestParam String productName,
                             @RequestParam(defaultValue = "0") String cost,
                             @RequestParam String measure, Model model) {
        log.info("product add post page");
        String msg;
        if (productName.equals("") || measure.equals("")) {
            msg = "All fields must be filled";
        } else if (productService.isProductExists(productName)) {
            msg = "Product exists!";
        } else {
            ProductDto productDto =
                    new ProductDto(null, LocalDate.now(), productName, measure, Double.parseDouble(cost), ON_STOCK);
            productService.addProduct(productDto);
            msg = "Product added successfully!";
            model.addAttribute("productDto", productDto);
        }
        model.addAttribute("message", msg);
        return "product/addProduct";
    }

//    @PostMapping
//    public String findOrDeleteProduct(@RequestParam String action, Model model,
//                                      @RequestParam(required = false) List<Integer> productId) {
//        log.info("product post page");
//        if (productId == null) {
//            model.addAttribute("message", "Please check product first");
//            return getAllProducts(model);
//        }
//
//        if (action.equals("delete")) {
//            log.info("\"DELETE\" CALLED");
//            return deleteProduct(model, productId);
//        }
//
//        log.info("\"FIND\" CALLED");
//        boolean isDeleted = false;
//        List<ProductDto> productDtoList = productService.findAllById(productId);
//        model.addAttribute("productList", productDtoList);
//        model.addAttribute("deleted", isDeleted);
//        return "product/product";
//    }
//
//    @DeleteMapping
//    public String deleteProduct(Model model, @RequestParam List<Integer> productId) {
//        log.info("product delete page");
//        List<ProductDto> productDtoList = productService.deleteAllById(productId);
//        boolean isDeleted = true;
//        model.addAttribute("productList", productDtoList);
//        model.addAttribute("deleted", isDeleted);
//        return "product/product";
//    }
}
