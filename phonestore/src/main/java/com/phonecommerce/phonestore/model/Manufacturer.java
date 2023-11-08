package com.phonecommerce.phonestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private Long commerceChamberId;
    
    public Manufacturer(String name, String country, Long commerceChamberId){
        this.commerceChamberId = commerceChamberId;
        this.name = name;
        this.country = country;
    }
}
