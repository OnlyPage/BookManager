package com.example.bookManager.controller;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.DTO.OrderDTO;
import com.example.bookManager.service.OrderService;
import com.example.bookManager.service.response.BookDetailResponse;
import com.example.bookManager.service.response.OrderDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDetailResponse> createNewOrder(@ModelAttribute OrderDTO orderDTO)
    {
        try {
            OrderDetailResponse orderDetailResponse =  orderService.createNewOrder(orderDTO);
            return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.IM_USED,exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/order/{id}")
    public String deleteBookById(@PathVariable("id") Integer id)
    {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDetailResponse> editOrder(@ModelAttribute OrderDTO orderDTO)
    {
        try {
            OrderDetailResponse  orderDetailResponse =  orderService.updateOrder(orderDTO);
            return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.IM_USED,exception.getMessage(), exception);
        }
    }

    @PutMapping("/order/{id}/{state}")
    public ResponseEntity<OrderDetailResponse> updateState(@PathVariable("id") Integer id, @PathVariable("state") Integer state)
    {
        try {
            OrderDetailResponse  orderDetailResponse =  orderService.updateStateById(id, state);
            return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.IM_USED,exception.getMessage(), exception);
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDetailResponse>> getOrders()
    {
        List<OrderDetailResponse> orderDetailResponses = orderService.getAllOrders();
        return new ResponseEntity<>(orderDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDetailResponse> getOrderById(@PathVariable("id") Integer id)
    {
        OrderDetailResponse orderDetailResponse = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/order/customer/{name}")
    public ResponseEntity<List<OrderDetailResponse>> getOrdersByCustomerName(@PathVariable("name") String name)
    {
        List<OrderDetailResponse> orderDetailResponses = orderService.getAllOrdersByCustomerName(name);
        return new ResponseEntity<>(orderDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/order/store/{name}")
    public ResponseEntity<List<OrderDetailResponse>> getOrdersByStoreName(@PathVariable("name") String name)
    {
        List<OrderDetailResponse> orderDetailResponses = orderService.getAllOrdersByStoreName(name);
        return new ResponseEntity<>(orderDetailResponses, HttpStatus.OK);
    }
}
