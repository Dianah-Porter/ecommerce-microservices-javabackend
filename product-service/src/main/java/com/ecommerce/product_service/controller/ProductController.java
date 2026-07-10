package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product) {


        return new ResponseEntity<>(
                productService.createProduct(product),
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @PathVariable Long id){

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }



    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product){


        return ResponseEntity.ok(
                productService.updateProduct(id, product)
        );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id){

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                "Product deleted successfully"
        );
    }

}