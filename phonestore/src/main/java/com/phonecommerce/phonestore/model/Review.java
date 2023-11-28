package com.phonecommerce.phonestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;


    private double rating;

    @ManyToOne
    private User user; // The user who wrote the review

    @ManyToOne
    private Phone phone; // The phone being reviewed

    public Review(String comment, double rating, User user, Phone phone) {
        this.comment = comment;
        this.rating = rating;
        this.user = user;
        this.phone = phone;
    }
}