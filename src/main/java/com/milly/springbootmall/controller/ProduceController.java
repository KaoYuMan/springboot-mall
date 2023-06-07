package com.milly.springbootmall.controller;

import com.milly.springbootmall.constant.ProductCategory;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProduceController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
                                                   //@RequestParam(required = false)此設定就算不帶category參數數值依然可以運行
    public  ResponseEntity<List<Product>>getProducts(@RequestParam(required = false) ProductCategory category,
                                                     @RequestParam(required = false) String serch
    ){

        List<Product> productList = productService.getProducts(category,serch);

        return ResponseEntity.status(HttpStatus.OK).body(productList) ;
    }

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
    public ResponseEntity<Product> creatProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.creatProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品的數據
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}

