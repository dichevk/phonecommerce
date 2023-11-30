package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.PhoneDTO;
import com.phonecommerce.phonestore.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
@Api(tags = "Phone Management")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    @ApiOperation(value = "Get all phones", notes = "Retrieve a list of all phones")
    public ResponseEntity<List<PhoneDTO>> getAllPhones() {
        List<PhoneDTO> phones = phoneService.getAllPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get phone by ID", notes = "Retrieve a phone by its ID")
    public ResponseEntity<PhoneDTO> getPhoneById(
            @ApiParam(value = "Phone ID", required = true)
            @PathVariable Long id) {
        PhoneDTO phone = phoneService.getPhoneById(id);
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new phone", notes = "Add a new phone to the system")
    public ResponseEntity<PhoneDTO> createPhone(
            @ApiParam(value = "Phone data", required = true)
            @RequestBody PhoneDTO phoneDTO) {
        PhoneDTO createdPhone = phoneService.createPhone(phoneDTO);
        return new ResponseEntity<>(createdPhone, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a phone", notes = "Modify an existing phone's information")
    public ResponseEntity<PhoneDTO> updatePhone(
            @ApiParam(value = "Phone ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated phone data", required = true)
            @RequestBody PhoneDTO phoneDTO) {
        PhoneDTO updatedPhone = phoneService.updatePhone(id, phoneDTO);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a phone", notes = "Remove a phone from the system")
    public ResponseEntity<Void> deletePhone(
            @ApiParam(value = "Phone ID", required = true)
            @PathVariable Long id) {
        phoneService.deletePhone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

