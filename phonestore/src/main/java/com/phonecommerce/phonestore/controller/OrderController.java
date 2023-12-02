package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.OrderDTO;
import com.phonecommerce.phonestore.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Api(tags = "Order Management")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ApiOperation(value = "Get all orders", notes = "Retrieve a list of all orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get order by ID", notes = "Retrieve an order by its ID")
    public ResponseEntity<OrderDTO> getOrderById(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a new order", notes = "Add a new order to the system")
    public ResponseEntity<OrderDTO> createOrder(
            @ApiParam(value = "Order data", required = true)
            @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an order", notes = "Modify an existing order's information")
    public ResponseEntity<OrderDTO> updateOrder(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated order data", required = true)
            @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an order", notes = "Remove an order from the system")
    public ResponseEntity<Void> deleteOrder(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
