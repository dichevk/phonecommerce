package com.phonecommerce.phonestore.service;

import com.phonecommerce.phonestore.dto.NotificationDTO;
import com.phonecommerce.phonestore.model.Notification;
import com.phonecommerce.phonestore.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(this::convertToDTO).orElse(null);
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();
            // Update the fields with non-null values from the DTO
            if (notificationDTO.getMessage() != null) {
                existingNotification.setMessage(notificationDTO.getMessage());
            }
            // Assuming the timestamp is not updated manually

            // Save the updated notification using the repository
            Notification updatedNotification = notificationRepository.save(existingNotification);
            return convertToDTO(updatedNotification);
        } else {
            return null;
        }
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        List<Notification> userNotifications = notificationRepository.findByUserId(userId);
        return userNotifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        return notificationDTO;
    }

    private Notification convertToEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationDTO, notification);
        return notification;
    }
}
