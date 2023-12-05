package com.phonecommerce.phonestore.Tests.Controller;

import com.phonecommerce.phonestore.controller.NotificationController;
import com.phonecommerce.phonestore.dto.NotificationDTO;
import com.phonecommerce.phonestore.service.NotificationService;
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

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllNotifications_ReturnsListOfNotifications() {
        // Arrange
        NotificationDTO notification = new NotificationDTO();
        List<NotificationDTO> notificationList = Collections.singletonList(notification);

        when(notificationService.getAllNotifications()).thenReturn(notificationList);

        // Act
        ResponseEntity<List<NotificationDTO>> response = notificationController.getAllNotifications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificationList, response.getBody());
    }

    @Test
    void getNotificationById_ExistingId_ReturnsNotification() {
        // Arrange
        long notificationId = 1L;
        NotificationDTO notification = new NotificationDTO();
        when(notificationService.getNotificationById(notificationId)).thenReturn(notification);

        // Act
        ResponseEntity<NotificationDTO> response = notificationController.getNotificationById(notificationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notification, response.getBody());
    }

    @Test
    void getNotificationById_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        when(notificationService.getNotificationById(nonExistingId)).thenReturn(null);

        // Act
        ResponseEntity<NotificationDTO> response = notificationController.getNotificationById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void createNotification_ValidNotification_ReturnsCreated() {
        // Arrange
        NotificationDTO notificationDTO = new NotificationDTO(); // Initialize with valid notification data
        when(notificationService.createNotification(any(NotificationDTO.class))).thenReturn(notificationDTO);

        // Act
        ResponseEntity<NotificationDTO> response = notificationController.createNotification(notificationDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(notificationDTO, response.getBody());
    }

    @Test
    void updateNotification_ExistingIdAndValidNotification_ReturnsUpdatedNotification() {
        // Arrange
        long notificationId = 1L;
        NotificationDTO updatedNotificationDTO = new NotificationDTO(); // Initialize with updated notification data
        when(notificationService.updateNotification(anyLong(), any(NotificationDTO.class))).thenReturn(updatedNotificationDTO);

        // Act
        ResponseEntity<NotificationDTO> response = notificationController.updateNotification(notificationId, updatedNotificationDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedNotificationDTO, response.getBody());
    }

    @Test
    void updateNotification_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        NotificationDTO updatedNotificationDTO = new NotificationDTO(); // Initialize with updated notification data
        when(notificationService.updateNotification(anyLong(), any(NotificationDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<NotificationDTO> response = notificationController.updateNotification(nonExistingId, updatedNotificationDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}