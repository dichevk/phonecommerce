package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.CategoryDTO;
import com.phonecommerce.phonestore.model.Category;
import com.phonecommerce.phonestore.repository.CategoryRepository;
import com.phonecommerce.phonestore.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        assertEquals(2, categoryDTOs.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);

        assertEquals(categoryId, categoryDTO.getId());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void testCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Electronics");

        Category category = new Category();
        category.setName("Electronics");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);

        assertNotNull(savedCategoryDTO);
        assertEquals("Electronics", savedCategoryDTO.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");

        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Original Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryId, categoryDTO);

        assertNotNull(updatedCategoryDTO);
        assertEquals("Updated Category", updatedCategoryDTO.getName());
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;

        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }   
}
