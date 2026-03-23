package com.maharjanworks.product_api.service;

import com.maharjanworks.product_api.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //create Category
    CategoryDTO createCategory(CategoryDTO dto);

    //get all categories
    List<CategoryDTO> getCategories();

    //get category by id

    //update category

    //delete category

}
