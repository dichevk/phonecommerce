package com.phonecommerce.phonestore.Tests.Controller;
import com.phonecommerce.phonestore.controller.CategoryController;
import com.phonecommerce.phonestore.dto.CategoryDTO;
import com.phonecommerce.phonestore.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

 @Test
    void getAllCategories_ReturnsListOfCategories() {
        // Arrange
        CategoryDTO category = new CategoryDTO();
        List<CategoryDTO> categoryList = Collections.singletonList(category);

        when(categoryService.getAllCategories()).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> response = categoryController.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryList, response.getBody());
    }

    @Test
    void getCategoryById_ExistingId_ReturnsCategory() {
        // Arrange
        long categoryId = 1L;
        CategoryDTO category = new CategoryDTO();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.getCategoryById(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    void getCategoryById_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        when(categoryService.getCategoryById(nonExistingId)).thenReturn(null);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.getCategoryById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    void createCategory_ValidCategory_ReturnsCreated() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(); // Initialize with valid category data
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.createCategory(categoryDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void updateCategory_ExistingIdAndValidCategory_ReturnsUpdatedCategory() {
        // Arrange
        long categoryId = 1L;
        CategoryDTO updatedCategoryDTO = new CategoryDTO(); // Initialize with updated category data
        when(categoryService.updateCategory(anyLong(), any(CategoryDTO.class))).thenReturn(updatedCategoryDTO);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.updateCategory(categoryId, updatedCategoryDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCategoryDTO, response.getBody());
    }

    @Test
    void updateCategory_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        CategoryDTO updatedCategoryDTO = new CategoryDTO(); // Initialize with updated category data
        when(categoryService.updateCategory(anyLong(), any(CategoryDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.updateCategory(nonExistingId, updatedCategoryDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}