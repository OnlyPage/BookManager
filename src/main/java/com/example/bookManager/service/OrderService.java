package com.example.bookManager.service;

import com.example.bookManager.DTO.OrderDTO;
import com.example.bookManager.domain.CustomerDetail;
import com.example.bookManager.domain.OrderDetail;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreService storeService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, StoreService storeService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
        this.customerService = customerService;
    }

    public String createNewOrder(OrderDTO orderDTO)
    {
        OrderDetail orderDetail = new OrderDetail();
        CustomerDetail customerDetail = customerService.getCustomerDetailByUsername(orderDTO.getCustomerName());
        StoreDetail storeDetail = storeService.getStoreDetailByUsername(orderDTO.getStoreName());
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomerDetail(customerDetail);
        orderDetail.setStoreDetail(storeDetail);
        orderDetail.setPrice(orderDTO.getPrice());
        orderRepository.save(orderDetail);
        return "Create success!";
    }

    public String updateOrder(int idOrder, OrderDTO orderDTO)
    {
        OrderDetail orderDetail = orderRepository.findById(idOrder).get();
        CustomerDetail customerDetail = customerService.getCustomerDetailByUsername(orderDTO.getCustomerName());
        StoreDetail storeDetail = storeService.getStoreDetailByUsername(orderDTO.getStoreName());
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomerDetail(customerDetail);
        orderDetail.setStoreDetail(storeDetail);
        orderDetail.setPrice(orderDTO.getPrice());
        orderRepository.save(orderDetail);
        return "Update success!";
    }

    public String deleteOrder(int idOrder)
    {
        orderRepository.deleteById(idOrder);
        return "Delete success";
    }
}
