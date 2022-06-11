package com.example.bookManager.service;

import com.example.bookManager.DTO.OrderDTO;
import com.example.bookManager.domain.OrderDetail;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreService storeService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, StoreService storeService, UserService userService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
        this.userService = userService;
    }

    public String createNewOrder(OrderDTO orderDTO)
    {
        OrderDetail orderDetail = new OrderDetail();
        UserDetail userDetail = userService.getUserDetailById(orderDTO.getIdCustomer());
        StoreDetail storeDetail = storeService.getStoreDetailById(orderDTO.getIdStore());
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomer(userDetail);
        orderDetail.setStoreDetail(storeDetail);
        orderDetail.setPrice(orderDTO.getPrice());
        orderRepository.save(orderDetail);
        return "Create success!";
    }

    public String updateOrder(int idOrder, OrderDTO orderDTO)
    {
        OrderDetail orderDetail = orderRepository.findById(idOrder).get();
        UserDetail userDetail = userService.getUserDetailById(orderDTO.getIdCustomer());
        StoreDetail storeDetail = storeService.getStoreDetailById(orderDTO.getIdStore());
        orderDetail.setDateOrder(orderDTO.getDateOrder());
        orderDetail.setCustomer(userDetail);
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
