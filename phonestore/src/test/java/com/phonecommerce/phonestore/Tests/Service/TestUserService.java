package com.phonecommerce.phonestore.Tests.Service;


import com.phonecommerce.phonestore.dto.UserDTO;
import com.phonecommerce.phonestore.model.User;
import com.phonecommerce.phonestore.repository.UserRepository;
import com.phonecommerce.phonestore.service.UserService;

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

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("john_doe", "password123", "john@example.com"));
        users.add(new User("jane_smith", "pass456", "jane@example.com"));

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOs = userService.getAllUsers();

        assertEquals(2, userDTOs.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User("john_doe", "password123", "john@example.com");
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById(userId);

        assertEquals(userId, userDTO.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");
        userDTO.setPassword("password123");
        userDTO.setEmail("john@example.com");

        User user = new User("john_doe", "password123", "john@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO savedUserDTO = userService.createUser(userDTO);

        assertNotNull(savedUserDTO);
        assertEquals("john_doe", savedUserDTO.getUsername());
        assertEquals("password123", savedUserDTO.getPassword());
        assertEquals("john@example.com", savedUserDTO.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("jane_smith");
        userDTO.setPassword("newPass789");
        userDTO.setEmail("jane_updated@example.com");

        User existingUser = new User("jane_doe", "password123", "jane@example.com");
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);

        assertNotNull(updatedUserDTO);
        assertEquals("jane_smith", updatedUserDTO.getUsername());
        assertEquals("newPass789", updatedUserDTO.getPassword());
        assertEquals("jane_updated@example.com", updatedUserDTO.getEmail());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void testDeleteUser() {
        Long userId = 1L;
        
        doNothing().when(userRepository).deleteById(userId);
        
        userService.deleteUser(userId);
        
        verify(userRepository, times(1)).deleteById(userId);
}

}