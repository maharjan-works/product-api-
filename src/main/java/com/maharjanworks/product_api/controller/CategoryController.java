package com.maharjanworks.product_api.controller;

import com.maharjanworks.product_api.dto.CategoryDTO;
import com.maharjanworks.product_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.FOUND);
    }

    //update category
    @PutMapping()
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO),HttpStatus.OK);
    }

    //delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, CategoryDTO>> deleteCategoryById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategoryById(categoryId), HttpStatus.OK);
    }

}
