package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.ManufacturerDTO;
import com.phonecommerce.phonestore.model.Manufacturer;
import com.phonecommerce.phonestore.repository.ManufacturerRepository;
import com.phonecommerce.phonestore.service.ManufacturerService;

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

class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private ManufacturerService manufacturerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllManufacturers() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Samsung", "South Korea", 123L));
        manufacturers.add(new Manufacturer("Apple", "USA", 456L));

        when(manufacturerRepository.findAll()).thenReturn(manufacturers);

        List<ManufacturerDTO> manufacturerDTOs = manufacturerService.getAllManufacturers();

        assertEquals(2, manufacturerDTOs.size());
        verify(manufacturerRepository, times(1)).findAll();
    }

    @Test
    void testGetManufacturerById() {
        Long manufacturerId = 1L;
        Manufacturer manufacturer = new Manufacturer("Samsung", "South Korea", 123L);
        manufacturer.setId(manufacturerId);

        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(manufacturer));

        ManufacturerDTO manufacturerDTO = manufacturerService.getManufacturerById(manufacturerId);

        assertEquals(manufacturerId, manufacturerDTO.getId());
        verify(manufacturerRepository, times(1)).findById(manufacturerId);
    }

    @Test
    void testCreateManufacturer() {
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setName("Samsung");
        manufacturerDTO.setCountry("South Korea");
        manufacturerDTO.setCommerceChamberId(123L);

        Manufacturer manufacturer = new Manufacturer("Samsung", "South Korea", 123L);

        when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(manufacturer);

        ManufacturerDTO savedManufacturerDTO = manufacturerService.createManufacturer(manufacturerDTO);

        assertNotNull(savedManufacturerDTO);
        assertEquals("Samsung", savedManufacturerDTO.getName());
        verify(manufacturerRepository, times(1)).save(any(Manufacturer.class));
    }

    @Test
    void testDeleteManufacturer() {
        Long manufacturerId = 1L;

        doNothing().when(manufacturerRepository).deleteById(manufacturerId);

        manufacturerService.deleteManufacturer(manufacturerId);

        verify(manufacturerRepository, times(1)).deleteById(manufacturerId);
    }
    @Test
    void testUpdateManufacturer() {
        Long manufacturerId = 1L;
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setName("Apple");
        manufacturerDTO.setCountry("USA");
        manufacturerDTO.setCommerceChamberId(789L);

        Manufacturer existingManufacturer = new Manufacturer("Samsung", "South Korea", 123L);
        existingManufacturer.setId(manufacturerId);

        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(existingManufacturer));
        when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(existingManufacturer);

        ManufacturerDTO updatedManufacturerDTO = manufacturerService.updateManufacturer(manufacturerId, manufacturerDTO);

        assertNotNull(updatedManufacturerDTO);
        assertEquals("Apple", updatedManufacturerDTO.getName());
        assertEquals("USA", updatedManufacturerDTO.getCountry());
        assertEquals(789L, updatedManufacturerDTO.getCommerceChamberId());
        verify(manufacturerRepository, times(1)).findById(manufacturerId);
        verify(manufacturerRepository, times(1)).save(any(Manufacturer.class));
    }
}
