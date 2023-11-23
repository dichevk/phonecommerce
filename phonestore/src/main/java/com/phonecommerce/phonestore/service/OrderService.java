package com.phonecommerce.phonestore.service;

import com.phonecommerce.phonestore.dto.OrderDTO;
import com.phonecommerce.phonestore.model.Order;
import com.phonecommerce.phonestore.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.map(this::convertToDTO).orElse(null);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            // Update the fields with non-null values from the DTO
            if (orderDTO.getOrderStatus() != null) {
                existingOrder.setOrderStatus(orderDTO.getOrderStatus());
            }
            // Assuming other fields can be updated similarly

            // Save the updated order using the repository
            Order updatedOrder = orderRepository.save(existingOrder);
            return convertToDTO(updatedOrder);
        } else {
            return null;
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return order;
    }
}

