package com.example.bookManager.service.response;

import com.example.bookManager.domain.OrderDetail;

import java.util.Date;
import java.util.List;

public class OrderDetailResponse
{
    private Integer id;
    private String customerName;
    private String storeName;
    private Date dateOrder;
    private Integer price;
    private Integer state;
    private Integer idBook;

    public OrderDetailResponse(String customerName, String storeName, Date dateOrder, Integer price, Integer state) {
        this.customerName = customerName;
        this.storeName = storeName;
        this.dateOrder = dateOrder;
        this.price = price;
        this.state = state;
    }

    public OrderDetailResponse(OrderDetail orderDetail)
    {
        this.id = orderDetail.getId();
        this.customerName = orderDetail.getCustomerName();
        this.storeName = orderDetail.getStoreName();
        this.dateOrder = orderDetail.getDateOrder();
        this.price = orderDetail.getPrice();
        this.state = orderDetail.getState();
        this.idBook = orderDetail.getBookId();
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }
}
