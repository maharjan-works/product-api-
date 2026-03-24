package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    //create Category
    CategoryDTO createCategory(CategoryDTO dto);

    //get all categories
    List<CategoryDTO> getCategories();

    //get category by id
    CategoryDTO getCategoryById(Long categoryId);

    //update category
    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    //delete category
    Map<String, CategoryDTO> deleteCategoryById(Long categoryId);

}
