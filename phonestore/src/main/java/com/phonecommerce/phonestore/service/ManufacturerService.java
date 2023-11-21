package com.phonecommerce.phonestore.service;


import com.phonecommerce.phonestore.dto.ManufacturerDTO;
import com.phonecommerce.phonestore.model.Manufacturer;
import com.phonecommerce.phonestore.repository.ManufacturerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<ManufacturerDTO> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ManufacturerDTO getManufacturerById(Long id) {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);
        return manufacturerOptional.map(this::convertToDTO).orElse(null);
    }

    public ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = convertToEntity(manufacturerDTO);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return convertToDTO(savedManufacturer);
    }

    public ManufacturerDTO updateManufacturer(Long id, ManufacturerDTO manufacturerDTO) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if (optionalManufacturer.isPresent()) {
            Manufacturer existingManufacturer = optionalManufacturer.get();
            // Update the fields with non-null values from the DTO
            if (manufacturerDTO.getName() != null) {
                existingManufacturer.setName(manufacturerDTO.getName());
            }
            if (manufacturerDTO.getCountry() != null) {
                existingManufacturer.setCountry(manufacturerDTO.getCountry());
            }

            // Save the updated manufacturer using the repository
            Manufacturer updatedManufacturer = manufacturerRepository.save(existingManufacturer);
            return convertToDTO(updatedManufacturer);
        } else {
            return null;
        }
    }

    public void deleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }

    private ManufacturerDTO convertToDTO(Manufacturer manufacturer) {
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        BeanUtils.copyProperties(manufacturer, manufacturerDTO);
        return manufacturerDTO;
    }

    private Manufacturer convertToEntity(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = new Manufacturer(manufacturerDTO.getName(), manufacturerDTO.getCountry(), manufacturerDTO.getCommerceChamberId());
        BeanUtils.copyProperties(manufacturerDTO, manufacturer);
        return manufacturer;
    }
}
