package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    //create product
    ProductDTO createProduct(ProductDTO productDTO);

    //get all products
    List<ProductDTO> getProducts();

    //get product by id
    ProductDTO getProductById(Long productId);

    //update product

    //delete product

}
