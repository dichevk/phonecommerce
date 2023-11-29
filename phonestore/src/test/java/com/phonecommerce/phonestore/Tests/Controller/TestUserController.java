package com.phonecommerce.phonestore.Tests.Controller;

import com.phonecommerce.phonestore.controller.UserController;
import com.phonecommerce.phonestore.dto.UserDTO;
import com.phonecommerce.phonestore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.phonecommerce.phonestore.utils.RandomGeneratorUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestUserController {
    RandomGeneratorUtil randomGeneratorUtil;
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        randomGeneratorUtil = new RandomGeneratorUtil();
    }

    @Test
    void testGetAllUsers() {
        UserDTO user1 = new UserDTO();
        user1.setUsername("john_doe");
        user1.setPassword("password123");
        user1.setEmail("john@example.com");
        UserDTO user2 = new UserDTO();
        user2.setUsername("maria_doe");
        user2.setPassword("password123");
        user2.setEmail("maria_doe@example.com");
        List<UserDTO> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDTO>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UserDTO user = new UserDTO();
        user.setId(userId);
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john@example.com");

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testCreateUser() {
       
        Long userId = randomGeneratorUtil.generateRandomLongId();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername("john_doe");
        userDTO.setPassword("password123");
        userDTO.setEmail("john@example.com");

        UserDTO createdUser = new UserDTO();
        createdUser.setUsername("john_doe");
        createdUser.setPassword("password123");
        createdUser.setEmail("john@example.com");


        when(userService.createUser(userDTO)).thenReturn(createdUser);

        ResponseEntity<UserDTO> responseEntity = userController.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdUser, responseEntity.getBody());
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void testUpdateUser() {
        Long userId = randomGeneratorUtil.generateRandomLongId();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername("john_doe");
        userDTO.setPassword("password123");
        userDTO.setEmail("john@example.com");
        userService.createUser(userDTO);

        UserDTO updatedUser = new UserDTO();
        updatedUser.setUsername("john_doe_updated");
        updatedUser.setPassword("password123_updated");
        updatedUser.setEmail("john_updated@example.com");

        when(userService.updateUser(userId, updatedUser)).thenReturn(updatedUser);

        ResponseEntity<UserDTO> responseEntity = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());
        verify(userService, times(1)).updateUser(userId, userDTO);
    }

    @Test
    void testDeleteUser() {
        Long userId = randomGeneratorUtil.generateRandomLongId();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername("john_doe");
        userDTO.setPassword("password123");
        userDTO.setEmail("john@example.com");
        userService.createUser(userDTO);

        ResponseEntity<Void> responseEntity = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }
}
