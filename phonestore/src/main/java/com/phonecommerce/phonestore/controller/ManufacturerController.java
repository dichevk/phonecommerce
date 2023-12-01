package com.phonecommerce.phonestore.controller;
import com.phonecommerce.phonestore.dto.ManufacturerDTO;
import com.phonecommerce.phonestore.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@Api(tags = "Manufacturer Management")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    @ApiOperation(value = "Get all manufacturers", notes = "Retrieve a list of all manufacturers")
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        List<ManufacturerDTO> manufacturers = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get manufacturer by ID", notes = "Retrieve a manufacturer by its ID")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(
            @ApiParam(value = "Manufacturer ID", required = true)
            @PathVariable Long id) {
        ManufacturerDTO manufacturer = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new manufacturer", notes = "Add a new manufacturer to the system")
    public ResponseEntity<ManufacturerDTO> createManufacturer(
            @ApiParam(value = "Manufacturer data", required = true)
            @RequestBody ManufacturerDTO manufacturerDTO) {
        ManufacturerDTO createdManufacturer = manufacturerService.createManufacturer(manufacturerDTO);
        return new ResponseEntity<>(createdManufacturer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a manufacturer", notes = "Modify an existing manufacturer's information")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(
            @ApiParam(value = "Manufacturer ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated manufacturer data", required = true)
            @RequestBody ManufacturerDTO manufacturerDTO) {
        ManufacturerDTO updatedManufacturer = manufacturerService.updateManufacturer(id, manufacturerDTO);
        return new ResponseEntity<>(updatedManufacturer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a manufacturer", notes = "Remove a manufacturer from the system")
    public ResponseEntity<Void> deleteManufacturer(
            @ApiParam(value = "Manufacturer ID", required = true)
            @PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
