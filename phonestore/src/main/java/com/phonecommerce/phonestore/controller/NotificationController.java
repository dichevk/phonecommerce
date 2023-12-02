package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.NotificationDTO;
import com.phonecommerce.phonestore.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Api(tags = "Notification Management")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @ApiOperation(value = "Get all notifications", notes = "Retrieve a list of all notifications")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get notification by ID", notes = "Retrieve a notification by its ID")
    public ResponseEntity<NotificationDTO> getNotificationById(
            @ApiParam(value = "Notification ID", required = true)
            @PathVariable Long id) {
        NotificationDTO notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new notification", notes = "Add a new notification to the system")
    public ResponseEntity<NotificationDTO> createNotification(
            @ApiParam(value = "Notification data", required = true)
            @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a notification", notes = "Modify an existing notification's information")
    public ResponseEntity<NotificationDTO> updateNotification(
            @ApiParam(value = "Notification ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated notification data", required = true)
            @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO updatedNotification = notificationService.updateNotification(id, notificationDTO);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a notification", notes = "Remove a notification from the system")
    public ResponseEntity<Void> deleteNotification(
            @ApiParam(value = "Notification ID", required = true)
            @PathVariable Long id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}