package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.OrderDTO;
import com.phonecommerce.phonestore.model.Order;
import com.phonecommerce.phonestore.repository.OrderRepository;
import com.phonecommerce.phonestore.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        
        when(orderRepository.findAll()).thenReturn(orders);
        
        List<OrderDTO> orderDTOs = orderService.getAllOrders();
        
        assertEquals(2, orderDTOs.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        OrderDTO orderDTO = orderService.getOrderById(orderId);

        assertEquals(orderId, orderDTO.getId());
        verify(orderRepository, times(1)).findById(orderId);
    }

     @Test
    void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus("Pending");

        Order order = new Order();
        order.setOrderStatus("Pending");

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDTO savedOrderDTO = orderService.createOrder(orderDTO);

        assertNotNull(savedOrderDTO);
        assertEquals("Pending", savedOrderDTO.getOrderStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus("Shipped");

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setOrderStatus("Pending");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(existingOrder);

        OrderDTO updatedOrderDTO = orderService.updateOrder(orderId, orderDTO);

        assertNotNull(updatedOrderDTO);
        assertEquals("Shipped", updatedOrderDTO.getOrderStatus());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;

        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
