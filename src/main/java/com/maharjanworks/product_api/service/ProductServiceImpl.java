package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.ProductDTO;
import com.maharjanworks.product_api.entity.Category;
import com.maharjanworks.product_api.entity.Product;
import com.maharjanworks.product_api.exception.CategoryNotFoundException;
import com.maharjanworks.product_api.exception.NoFieldFoundException;
import com.maharjanworks.product_api.exception.ProductNotFoundException;
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
                .orElseThrow(() -> new CategoryNotFoundException("Category id: "+productDTO.getCategoryId() + " not found!"));

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
                orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        //convert to dto and return
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        //fetch product by id
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // check if category existed,
        var category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + productDTO.getCategoryId() + " not found"));

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
    public ProductDTO patchProduct(Long productId, Map<String, Object> patchProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        Product finalProduct = product;
        patchProductRequest.forEach((key, value) ->{
            switch(key) {
                case "name":
                    finalProduct.setName((String) value);
                    break;
                case "description":
                    finalProduct.setDescription((String) value);
                    break;
                case "price":
                    finalProduct.setPrice((Double) value);
                    break;
                case "categoryId":
                    Long categoryId = ((Integer) value).longValue();
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Category not found."));
                    finalProduct.setCategory(category);
                    break;
                default:
                    throw new NoFieldFoundException("invalid field: "+ key);
            }
        });
        //save patched resource
        product = productRepository.save(finalProduct);

        //convert to dto and return
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public String deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product id: "+ productId + " not found!"));
        productRepository.deleteById(productId);
        return "Product Id: "+ productId + " deleted successfully from db";
    }


}
