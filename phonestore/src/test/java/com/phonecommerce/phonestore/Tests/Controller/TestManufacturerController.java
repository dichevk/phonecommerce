package com.phonecommerce.phonestore.Tests.Controller;

import com.phonecommerce.phonestore.controller.ManufacturerController;
import com.phonecommerce.phonestore.dto.ManufacturerDTO;
import com.phonecommerce.phonestore.service.ManufacturerService;
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

class ManufacturerControllerTest {

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private ManufacturerController manufacturerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllManufacturers_ReturnsListOfManufacturers() {
        // Arrange
        ManufacturerDTO manufacturer = new ManufacturerDTO();
        List<ManufacturerDTO> manufacturerList = Collections.singletonList(manufacturer);

        when(manufacturerService.getAllManufacturers()).thenReturn(manufacturerList);

        // Act
        ResponseEntity<List<ManufacturerDTO>> response = manufacturerController.getAllManufacturers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manufacturerList, response.getBody());
    }

    @Test
    void getManufacturerById_ExistingId_ReturnsManufacturer() {
        // Arrange
        long manufacturerId = 1L;
        ManufacturerDTO manufacturer = new ManufacturerDTO();
        when(manufacturerService.getManufacturerById(manufacturerId)).thenReturn(manufacturer);

        // Act
        ResponseEntity<ManufacturerDTO> response = manufacturerController.getManufacturerById(manufacturerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manufacturer, response.getBody());
    }

    @Test
    void getManufacturerById_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        when(manufacturerService.getManufacturerById(nonExistingId)).thenReturn(null);

        // Act
        ResponseEntity<ManufacturerDTO> response = manufacturerController.getManufacturerById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

   
    @Test
    void createManufacturer_ValidManufacturer_ReturnsCreated() {
        // Arrange
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO(); // Initialize with valid manufacturer data
        when(manufacturerService.createManufacturer(any(ManufacturerDTO.class))).thenReturn(manufacturerDTO);

        // Act
        ResponseEntity<ManufacturerDTO> response = manufacturerController.createManufacturer(manufacturerDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(manufacturerDTO, response.getBody());
    }

    @Test
    void updateManufacturer_ExistingIdAndValidManufacturer_ReturnsUpdatedManufacturer() {
        // Arrange
        long manufacturerId = 1L;
        ManufacturerDTO updatedManufacturerDTO = new ManufacturerDTO(); // Initialize with updated manufacturer data
        when(manufacturerService.updateManufacturer(anyLong(), any(ManufacturerDTO.class))).thenReturn(updatedManufacturerDTO);

        // Act
        ResponseEntity<ManufacturerDTO> response = manufacturerController.updateManufacturer(manufacturerId, updatedManufacturerDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedManufacturerDTO, response.getBody());
    }

    @Test
    void updateManufacturer_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        ManufacturerDTO updatedManufacturerDTO = new ManufacturerDTO(); // Initialize with updated manufacturer data
        when(manufacturerService.updateManufacturer(anyLong(), any(ManufacturerDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<ManufacturerDTO> response = manufacturerController.updateManufacturer(nonExistingId, updatedManufacturerDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

