package com.phonecommerce.phonestore.dto;
import lombok.Data;

@Data
public class PhoneDTO {
    private Long id;
    private String name;
    private String brand;
    private double price;
}
