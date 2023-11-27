package com.phonecommerce.phonestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date timestamp;

    @ManyToOne
    private User user; // The user to whom the notification is sent
    
    public Notification(){}
    public Notification (String message, Date timestamp, User user){
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
    }

}
