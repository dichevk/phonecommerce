package com.phonecommerce.phonestore.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String comment;
    private int rating;
    private Long userId;
    private Long phoneId;
}
