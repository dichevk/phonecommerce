package com.phonecommerce.phonestore.service;

import com.phonecommerce.phonestore.dto.PhoneDTO;
import com.phonecommerce.phonestore.model.Phone;
import com.phonecommerce.phonestore.repository.PhoneRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<PhoneDTO> getAllPhones() {
        List<Phone> phones = phoneRepository.findAll();
        return phones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PhoneDTO getPhoneById(Long id) {
        Optional<Phone> phoneOptional = phoneRepository.findById(id);
        return phoneOptional.map(this::convertToDTO).orElse(null);
    }

    public PhoneDTO createPhone(PhoneDTO phoneDTO) {
        Phone phone = convertToEntity(phoneDTO);
        Phone savedPhone = phoneRepository.save(phone);
        return convertToDTO(savedPhone);
    }

    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }


    private PhoneDTO convertToDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        BeanUtils.copyProperties(phone, phoneDTO);
        return phoneDTO;
    }

    private Phone convertToEntity(PhoneDTO phoneDTO) {
        Phone phone = new Phone(phoneDTO.getName(),phoneDTO.getBrand(),phoneDTO.getPrice());
        BeanUtils.copyProperties(phoneDTO, phone);
        return phone;
    }
    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        
        if (optionalPhone.isPresent()) {
            Phone existingPhone = optionalPhone.get();
            // Update the fields with non-null values from the DTO
            if (phoneDTO.getName() != null) {
                existingPhone.setName(phoneDTO.getName());
            }
            if (phoneDTO.getBrand() != null) {
                existingPhone.setBrand(phoneDTO.getBrand());
            }
            if (phoneDTO.getPrice() != 0) {
                existingPhone.setPrice(phoneDTO.getPrice());
            }
            
            // Save the updated phone using the repository
            Phone updatedPhone = phoneRepository.save(existingPhone);
            return convertToDTO(updatedPhone);
        } else {
            return null;
        }
    }
}
