package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.PhoneDTO;
import com.phonecommerce.phonestore.model.Phone;
import com.phonecommerce.phonestore.repository.PhoneRepository;
import com.phonecommerce.phonestore.service.PhoneService;

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

class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPhones() {
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("iphone6","iphone",400));
        phones.add(new Phone("iphone7","iphone",600));

        when(phoneRepository.findAll()).thenReturn(phones);

        List<PhoneDTO> phoneDTOs = phoneService.getAllPhones();

        assertEquals(2, phoneDTOs.size());
        verify(phoneRepository, times(1)).findAll();
    }

    @Test
    void testGetPhoneById() {
        Long phoneId = 1L;
        Phone phone = new Phone("Samsung_S23","Samsung",800);
        phone.setId(phoneId);

        when(phoneRepository.findById(phoneId)).thenReturn(Optional.of(phone));

        PhoneDTO phoneDTO = phoneService.getPhoneById(phoneId);

        assertEquals(phoneId, phoneDTO.getId());
        verify(phoneRepository, times(1)).findById(phoneId);
    }

    @Test
    void testCreatePhone() {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setName("iPhoneX");
        phoneDTO.setBrand("iPhone");
        phoneDTO.setPrice(1000);

        Phone phone = new Phone(phoneDTO.getName(),phoneDTO.getBrand(),phoneDTO.getPrice());

        when(phoneRepository.save(any(Phone.class))).thenReturn(phone);

        PhoneDTO savedPhoneDTO = phoneService.createPhone(phoneDTO);

        assertNotNull(savedPhoneDTO);
        assertEquals("iPhone", savedPhoneDTO.getName());
        verify(phoneRepository, times(1)).save(any(Phone.class));
    }

    @Test
    void testUpdatePhone() {
        Long phoneId = 1L;
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setName("iphone14");

        Phone existingPhone = new Phone("iphoneX", "iphone", 1000);
        existingPhone.setId(phoneId);
        existingPhone.setName("iPhone");

        when(phoneRepository.findById(phoneId)).thenReturn(Optional.of(existingPhone));
        when(phoneRepository.save(any(Phone.class))).thenReturn(existingPhone);

        PhoneDTO updatedPhoneDTO = phoneService.updatePhone(phoneId, phoneDTO);

        assertNotNull(updatedPhoneDTO);
        assertEquals("Galaxy", updatedPhoneDTO.getName());
        verify(phoneRepository, times(1)).findById(phoneId);
        verify(phoneRepository, times(1)).save(any(Phone.class));
    }

    @Test
    void testDeletePhone() {
        Long phoneId = 1L;

        doNothing().when(phoneRepository).deleteById(phoneId);

        phoneService.deletePhone(phoneId);

        verify(phoneRepository, times(1)).deleteById(phoneId);
    }
}
