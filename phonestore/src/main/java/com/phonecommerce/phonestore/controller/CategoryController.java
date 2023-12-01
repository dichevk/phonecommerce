package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.CategoryDTO;
import com.phonecommerce.phonestore.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Api(tags = "Category Management")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation(value = "Get all categories", notes = "Retrieve a list of all categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get category by ID", notes = "Retrieve a category by its ID")
    public ResponseEntity<CategoryDTO> getCategoryById(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new category", notes = "Add a new category to the system")
    public ResponseEntity<CategoryDTO> createCategory(
            @ApiParam(value = "Category data", required = true)
            @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a category", notes = "Modify an existing category's information")
    public ResponseEntity<CategoryDTO> updateCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated category data", required = true)
            @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a category", notes = "Remove a category from the system")
    public ResponseEntity<Void> deleteCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
