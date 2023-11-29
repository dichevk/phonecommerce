package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.UserDTO;
import com.phonecommerce.phonestore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User Management")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Get all users", notes = "Retrieve a list of all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by ID", notes = "Retrieve a user by their ID")
    public ResponseEntity<UserDTO> getUserById(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new user", notes = "Add a new user to the system")
    public ResponseEntity<UserDTO> createUser(
            @ApiParam(value = "User data", required = true)
            @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user", notes = "Modify an existing user's information")
    public ResponseEntity<UserDTO> updateUser(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated user data", required = true)
            @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user", notes = "Remove a user from the system")
    public ResponseEntity<Void> deleteUser(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
