package com.example.bookManager.service.response;

import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.OrderDetail;
import com.example.bookManager.domain.StoreDetail;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailResponse
{
    private String username;
    private List<BookDetailResponse> bookDetailResponses;
    private List<OrderDetailResponse> orderDetailResponses;

    public StoreDetailResponse(String username, List<BookDetailResponse> bookDetailResponses, List<OrderDetailResponse> orderDetailResponses) {
        this.username = username;
        this.bookDetailResponses = bookDetailResponses;
        this.orderDetailResponses = orderDetailResponses;
    }

    public StoreDetailResponse(StoreDetail storeDetail)
    {
        this.username = storeDetail.getUsername();
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        for (BookDetail bookDetail: storeDetail.getBookDetail()) {
            BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
            bookDetailResponses.add(bookDetailResponse);
        }
        this.bookDetailResponses = bookDetailResponses;

        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail: storeDetail.getOrderDetail()) {
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse(orderDetail);
            orderDetailResponses.add(orderDetailResponse);
        }
        this.orderDetailResponses = orderDetailResponses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BookDetailResponse> getBookDetailResponses() {
        return bookDetailResponses;
    }

    public void setBookDetailResponses(List<BookDetailResponse> bookDetailResponses) {
        this.bookDetailResponses = bookDetailResponses;
    }

    public List<OrderDetailResponse> getOrderDetailResponses() {
        return orderDetailResponses;
    }

    public void setOrderDetailResponses(List<OrderDetailResponse> orderDetailResponses) {
        this.orderDetailResponses = orderDetailResponses;
    }
}
