package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.CategoryDTO;
import com.maharjanworks.product_api.entity.Category;
import com.maharjanworks.product_api.exception.CategoryAlreadyExistsException;
import com.maharjanworks.product_api.exception.CategoryNotFoundException;
import com.maharjanworks.product_api.mapper.CategoryMapper;
import com.maharjanworks.product_api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());

        if(optionalCategory.isPresent()){
           throw new CategoryAlreadyExistsException("Category "+ categoryDTO.getName() + " already exists.");
        }

        Category category = CategoryMapper.toCategory(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category id: "+ categoryId + " not found!") );

        return CategoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category=categoryRepository.findById(categoryDTO.getId()).orElseThrow(()->  new CategoryNotFoundException("Category id: "+ categoryDTO.getId() + " not found!"));
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    @Override
    public Map<String, CategoryDTO> deleteCategoryById(Long categoryId) {

        //checking if category exists, if not throwing exception
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: "+ categoryId + " not found!"));

        //if exists, convert to dto
        CategoryDTO categoryDTO =CategoryMapper.toCategoryDTO(category);

        //delete existed category
        categoryRepository.delete(category);

        //returning deleted resource
        return Map.of("deleted",categoryDTO);
    }
}
