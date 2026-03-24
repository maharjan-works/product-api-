package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.ProductDTO;
import com.maharjanworks.product_api.entity.Category;
import com.maharjanworks.product_api.entity.Product;
import com.maharjanworks.product_api.mapper.ProductMapper;
import com.maharjanworks.product_api.repository.CategoryRepository;
import com.maharjanworks.product_api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        /*
         *  what productDTO has?
         *  id, name, description, price, categoryId
         */

        //first, check  if category exists or not,
        //if not, throw Exception
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException(("Category is not found!")));

        //dto to entity
        Product product = ProductMapper.toProduct(productDTO, category);

        //save entity
        product = productRepository.save(product);

        //change saved Product to dto and return dto
        return  ProductMapper.toProductDTO(product);
    }
}
