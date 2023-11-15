package com.phonecommerce.phonestore.dto;
import lombok.Data;
import java.util.Date;

@Data
public class NotificationDTO {
    private Long id;
    private String message;
    private Date timestamp;
    private Long userId;
}