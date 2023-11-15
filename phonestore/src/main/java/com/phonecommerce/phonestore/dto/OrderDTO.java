package com.phonecommerce.phonestore.dto;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private String orderStatus;
    private double totalAmount;
    private Long userId;
    private List<Long> phoneIds;
}
