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

import java.util.List;
import java.util.Map;

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
         *  what productDTO has? all coming from controller, even categoryId
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
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }

    @Override
    public ProductDTO getProductById(Long productId) {

        Product product = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        //convert to dto and return
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        //fetch product by id
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        // check if category existed,
        var category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found."));

        //update fetched product with new dataset
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        //save product
        product = productRepository.save(product);

        //convert to dto and return
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public String deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return "Product Id: "+ productId + " deleted successfully from db";
    }


}
