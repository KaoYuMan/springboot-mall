package com.milly.springbootmall.controller;

import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProduceController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public  ResponseEntity<Product> creatProduct(@RequestBody @Valid ProductRequest productRequest){
    Integer productId = productService.creatProduct(productRequest);
    System.out.println(productId);

    Product product =productService.getProductById(productId);

    return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }
}

