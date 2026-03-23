package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.CategoryDTO;
import com.maharjanworks.product_api.entity.Category;
import com.maharjanworks.product_api.mapper.CategoryMapper;
import com.maharjanworks.product_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategory(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
}
