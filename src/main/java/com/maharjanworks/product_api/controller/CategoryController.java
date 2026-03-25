package com.maharjanworks.product_api.controller;

import com.maharjanworks.product_api.dto.CategoryDTO;
import com.maharjanworks.product_api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Tag(
        name ="Category Endpoints",
        description = "Create, Read, Update, Delete operations"
)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create category
    @Operation(
            summary = "add category",
            description = "an api to add category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
    }

    //get all categories
    @Operation(
            summary = "get all categories",
            description = "an api to get all categories with product list"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    //get category by id
    @Operation(
            summary = "get category by id",
            description = "an api to get category by id "
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.FOUND);
    }

    //update category
    @Operation(
            summary = "update category by id",
            description = "an api to update category by new details by id provided"
    )
    @PutMapping()
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO),HttpStatus.OK);
    }

    //delete category
    @Operation(
            summary = "update category by id",
            description = "an api to update category by new details by id provided"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, CategoryDTO>> deleteCategoryById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategoryById(categoryId), HttpStatus.OK);
    }

}
