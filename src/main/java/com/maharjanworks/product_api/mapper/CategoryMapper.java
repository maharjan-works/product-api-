package com.maharjanworks.product_api.mapper;

import com.maharjanworks.product_api.dto.CategoryDTO;
import com.maharjanworks.product_api.entity.Category;

public class CategoryMapper {


    //dto to entity
    public static Category toCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    //entity to dto
    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProductDTOs(
                category.getProducts().stream()
                        .map(ProductMapper::toProductDTO)
                        .toList()
        );
        return categoryDTO;
    }
}
