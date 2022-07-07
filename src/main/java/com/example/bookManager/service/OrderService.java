package com.example.bookManager.service;

import com.example.bookManager.DTO.OrderDTO;
import com.example.bookManager.domain.*;
import com.example.bookManager.repositories.OrderRepository;
import com.example.bookManager.service.response.OrderDetailResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
        orderDetail.setPrice(orderDTO.getPrice());
        orderDetail.setBookId(orderDTO.getIdBook());
        orderDetail.setState(orderDTO.getState());
        orderDetail.setDateOrder(new Date(System.currentTimeMillis()));
        orderDetail.setNumber(orderDTO.getNumber());
        orderRepository.save(orderDetail);
        BookDetail bookDetail = bookService.getBookById(orderDTO.getIdBook());
        return new OrderDetailResponse(orderDetail, bookDetail.getNameBook());
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
        orderDetail.setNumber(orderDTO.getNumber());
        orderRepository.save(orderDetail);
        BookDetail bookDetail = bookService.getBookById(orderDTO.getIdBook());
        bookService.updateBook(bookDetail);
        return new OrderDetailResponse(orderDetail, bookDetail.getNameBook());
    }

    public OrderDetailResponse updateStateById(int idOrder, int state)
    {
        OrderDetail orderDetail = orderRepository.findById(idOrder).get();
        orderDetail.setState(state);
        BookDetail bookDetail = bookService.getBookById(orderDetail.getBookId());
        bookDetail.setNumber(bookDetail.getNumber() - orderDetail.getNumber());

        return new OrderDetailResponse(orderDetail, bookDetail.getNameBook());
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
            BookDetail bookDetail = bookService.getBookById(orderDetail.getBookId());
            orderDetailResponses.add(new OrderDetailResponse(orderDetail, bookDetail.getNameBook()));
        }
        return orderDetailResponses;
    }

    public OrderDetailResponse getOrderById(int id)
    {
        OrderDetail orderDetail = orderRepository.findById(id).get();
        BookDetail bookDetail = bookService.getBookById(orderDetail.getBookId());
        return new OrderDetailResponse(orderDetail, bookDetail.getNameBook());
    }

    public List<OrderDetailResponse> getAllOrdersByCustomerName(String customerNames)
    {
        List<OrderDetail> orderDetails = orderRepository.findOrderByCustomerName(customerNames);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails)
        {
            BookDetail bookDetail = bookService.getBookById(orderDetail.getBookId());
            orderDetailResponses.add(new OrderDetailResponse(orderDetail, bookDetail.getNameBook()));
        }
        return orderDetailResponses;
    }

    public List<OrderDetailResponse> getAllOrdersByStoreName(String storeNames)
    {
        List<OrderDetail> orderDetails = orderRepository.findOrderByStoreName(storeNames);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails)
        {
            BookDetail bookDetail = bookService.getBookById(orderDetail.getBookId());
            orderDetailResponses.add(new OrderDetailResponse(orderDetail, bookDetail.getNameBook()));
        }
        return orderDetailResponses;
    }

}
