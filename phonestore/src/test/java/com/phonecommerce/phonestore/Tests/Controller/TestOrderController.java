package com.phonecommerce.phonestore.Tests.Controller;

import com.phonecommerce.phonestore.controller.OrderController;
import com.phonecommerce.phonestore.dto.OrderDTO;
import com.phonecommerce.phonestore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllOrders_ReturnsListOfOrders() {
        // Arrange
        OrderDTO order = new OrderDTO();
        List<OrderDTO> orderList = Collections.singletonList(order);

        when(orderService.getAllOrders()).thenReturn(orderList);

        // Act
        ResponseEntity<List<OrderDTO>> response = orderController.getAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList, response.getBody());
    }

    @Test
    void getOrderById_ExistingId_ReturnsOrder() {
        // Arrange
        long orderId = 1L;
        OrderDTO order = new OrderDTO();
        when(orderService.getOrderById(orderId)).thenReturn(order);

        // Act
        ResponseEntity<OrderDTO> response = orderController.getOrderById(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    void getOrderById_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        when(orderService.getOrderById(nonExistingId)).thenReturn(null);

        // Act
        ResponseEntity<OrderDTO> response = orderController.getOrderById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createOrder_ValidOrder_ReturnsCreated() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO(); // Initialize with valid order data
        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(orderDTO);

        // Act
        ResponseEntity<OrderDTO> response = orderController.createOrder(orderDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDTO, response.getBody());
    }

    @Test
    void updateOrder_ExistingIdAndValidOrder_ReturnsUpdatedOrder() {
        // Arrange
        long orderId = 1L;
        OrderDTO updatedOrderDTO = new OrderDTO(); // Initialize with updated order data
        when(orderService.updateOrder(anyLong(), any(OrderDTO.class))).thenReturn(updatedOrderDTO);

        // Act
        ResponseEntity<OrderDTO> response = orderController.updateOrder(orderId, updatedOrderDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedOrderDTO, response.getBody());
    }

    @Test
    void updateOrder_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        OrderDTO updatedOrderDTO = new OrderDTO(); // Initialize with updated order data
        when(orderService.updateOrder(anyLong(), any(OrderDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<OrderDTO> response = orderController.updateOrder(nonExistingId, updatedOrderDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

