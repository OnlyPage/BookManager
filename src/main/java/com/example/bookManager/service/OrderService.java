package com.example.bookManager.service;

import com.example.bookManager.DTO.BookOrderDTO;
import com.example.bookManager.DTO.OrderDTO;
import com.example.bookManager.domain.*;
import com.example.bookManager.repositories.OrderRepository;
import com.example.bookManager.service.response.BookDetailResponse;
import com.example.bookManager.service.response.OrderDetailResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreService storeService;
    private final CustomerService customerService;
    private final BookService bookService;

    public OrderService(OrderRepository orderRepository, StoreService storeService, CustomerService customerService,@Lazy BookService bookService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    public OrderDetailResponse createNewOrder(OrderDTO orderDTO)
    {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomerName(orderDTO.getCustomerName());
        orderDetail.setStoreName(orderDTO.getStoreName());
        orderDetail.setPrice(orderDTO.getNumber());
        orderDetail.setBookId(orderDTO.getIdBook());
        orderDetail.setState(orderDTO.getState());
        orderDetail.setDateOrder(new Date(System.currentTimeMillis()));
        orderRepository.save(orderDetail);
        return new OrderDetailResponse(orderDetail);
    }

    public OrderDetailResponse updateOrder(OrderDTO orderDTO)
    {
        OrderDetail orderDetail = orderRepository.findById(orderDTO.getId()).get();
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomerName(orderDTO.getCustomerName());
        orderDetail.setStoreName(orderDTO.getStoreName());
        orderDetail.setPrice(orderDTO.getNumber());
        orderDetail.setBookId(orderDTO.getIdBook());
        orderDetail.setState(orderDTO.getState());
        orderRepository.save(orderDetail);
        return new OrderDetailResponse(orderDetail);
    }

    public OrderDetailResponse updateStateById(int idOrder, int state)
    {
        OrderDetail orderDetail = orderRepository.findById(idOrder).get();
        orderDetail.setState(state);
        return new OrderDetailResponse(orderDetail);
    }

    public String deleteOrder(int idOrder)
    {
        orderRepository.deleteById(idOrder);
        return "Delete success";
    }

    public List<OrderDetailResponse> getAllOrders()
    {
        List<OrderDetail> orderDetails = orderRepository.findAll();
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails)
        {
            orderDetailResponses.add(new OrderDetailResponse(orderDetail));
        }
        return orderDetailResponses;
    }

    public OrderDetailResponse getOrderById(int id)
    {
        OrderDetail orderDetail = orderRepository.findById(id).get();
        return new OrderDetailResponse(orderDetail);
    }

    public List<OrderDetailResponse> getAllOrdersByCustomerName(String customerNames)
    {
        List<OrderDetail> orderDetails = orderRepository.findOrderByCustomerName(customerNames);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails)
        {
            orderDetailResponses.add(new OrderDetailResponse(orderDetail));
        }
        return orderDetailResponses;
    }

    public List<OrderDetailResponse> getAllOrdersByStoreName(String storeNames)
    {
        List<OrderDetail> orderDetails = orderRepository.findOrderByStoreName(storeNames);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails)
        {
            orderDetailResponses.add(new OrderDetailResponse(orderDetail));
        }
        return orderDetailResponses;
    }
}
