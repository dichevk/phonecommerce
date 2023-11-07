package com.phonecommerce.phonestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private double price;

    public Phone() {
    }

    public Phone(String name, String brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }
}