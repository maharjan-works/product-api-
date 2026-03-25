package com.maharjanworks.product_api.controller;

import com.maharjanworks.product_api.dto.ProductDTO;
import com.maharjanworks.product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(
     name ="Product Endpoints",
     description = "Create, Read, Update, Delete operations"
)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create product
    @Operation(
            summary = "add a product",
            description = "an endpoint to add a product"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    //get all products
    @Operation(
            summary = "fetch all products",
            description = "an endpoint to fetch all products"
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    //get product by id
    @Operation(
            summary = "get product by id",
            description = "an endpoint to get product by id, if not exists throws exception"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    //update product
    @Operation(
            summary = "update a product",
            description = "an endpoint to update product with productId and new product details provided"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long productId,@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(productId,productDTO), HttpStatus.OK);
    }

    //patch Product
    @Operation(
            summary = "patch product",
            description = "an endpoint to patch product with productId and a property of product to update are provided"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable("id") Long productId, @RequestBody  Map<String,Object> patchProductRequest){
        return new ResponseEntity<>(productService.patchProduct(productId, patchProductRequest), HttpStatus.OK);
    }

    //delete product
    @Operation(
            summary = "delete product",
            description = "an endpoint to delete product with productId"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId){
        return new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
    }
 }
