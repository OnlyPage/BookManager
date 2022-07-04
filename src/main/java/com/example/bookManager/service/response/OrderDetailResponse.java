package com.example.bookManager.service.response;

import com.example.bookManager.domain.OrderDetail;

import java.util.Date;

public class OrderDetailResponse
{
    private Integer id;
    private String customerName;
    private String storeName;
    private Date dateOrder;
    private Integer price;
    private Integer state;
    private String nameBook;
    private Integer number;

    public OrderDetailResponse(Integer id, String customerName, String storeName, Date dateOrder, Integer price, Integer state, String nameBook, Integer number) {
        this.id = id;
        this.customerName = customerName;
        this.storeName = storeName;
        this.dateOrder = dateOrder;
        this.price = price;
        this.state = state;
        this.nameBook = nameBook;
        this.number = number;
    }

    public OrderDetailResponse(OrderDetail orderDetail, String nameBook)
    {
        this.id = orderDetail.getId();
        this.customerName = orderDetail.getCustomerName();
        this.storeName = orderDetail.getStoreName();
        this.dateOrder = orderDetail.getDateOrder();
        this.price = orderDetail.getPrice();
        this.state = orderDetail.getState();
        this.nameBook = nameBook;
        this.number = orderDetail.getNumber();
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

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
