package com.maharjanworks.product_api.mapper;

import com.maharjanworks.product_api.dto.ProductDTO;
import com.maharjanworks.product_api.entity.Category;
import com.maharjanworks.product_api.entity.Product;

public class ProductMapper {

    //entity to dto
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory().getId());
    }

    //dto to entity
    public static Product toProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
