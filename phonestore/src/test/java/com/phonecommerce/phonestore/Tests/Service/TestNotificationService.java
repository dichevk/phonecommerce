package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.NotificationDTO;
import com.phonecommerce.phonestore.model.Notification;
import com.phonecommerce.phonestore.model.User;
import com.phonecommerce.phonestore.repository.NotificationRepository;
import com.phonecommerce.phonestore.service.NotificationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Message 1", new Date(), new User("user1","passuser1","username1@gmail.com")));
        notifications.add(new Notification("Message 2", new Date(), new User("user2","passuser2","username2@gmail.com")));

        when(notificationRepository.findAll()).thenReturn(notifications);

        List<NotificationDTO> notificationDTOs = notificationService.getAllNotifications();

        assertEquals(2, notificationDTOs.size());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void testGetNotificationById() {
        Long notificationId = 1L;
        Notification notification = new Notification("Message", new Date(), new User("user1","passuser1","username1@gmail.com"));
        notification.setId(notificationId);

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));

        NotificationDTO notificationDTO = notificationService.getNotificationById(notificationId);

        assertEquals(notificationId, notificationDTO.getId());
        verify(notificationRepository, times(1)).findById(notificationId);
    }

    @Test
    void testCreateNotification() {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("New Message");
        notificationDTO.setTimestamp(new Date());

        Notification notification = new Notification("New Message", new Date(), new User("user1","passuser1","username1@gmail.com"));

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        NotificationDTO savedNotificationDTO = notificationService.createNotification(notificationDTO);

        assertNotNull(savedNotificationDTO);
        assertEquals("New Message", savedNotificationDTO.getMessage());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void testUpdateNotification() {
        Long notificationId = 1L;
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("Updated Message");
        notificationDTO.setTimestamp(new Date());

        Notification existingNotification = new Notification("Original Message", new Date(), new User("user1","passuser1","username1@gmail.com"));
        existingNotification.setId(notificationId);

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(existingNotification));
        when(notificationRepository.save(any(Notification.class))).thenReturn(existingNotification);

        NotificationDTO updatedNotificationDTO = notificationService.updateNotification(notificationId, notificationDTO);

        assertNotNull(updatedNotificationDTO);
        assertEquals("Updated Message", updatedNotificationDTO.getMessage());
        verify(notificationRepository, times(1)).findById(notificationId);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void testDeleteNotification() {
        Long notificationId = 1L;

        doNothing().when(notificationRepository).deleteById(notificationId);

        notificationService.deleteNotification(notificationId);

        verify(notificationRepository, times(1)).deleteById(notificationId);
    }

}
