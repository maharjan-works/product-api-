package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    //create product
    ProductDTO createProduct(ProductDTO productDTO);

    //get all products
    List<ProductDTO> getProducts();

    //get product by id
    ProductDTO getProductById(Long productId);

    //update product
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    //patch product

    //delete product
    String deleteProduct(Long productId);


}
