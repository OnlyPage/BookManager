package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "storeDetail")
public class StoreDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameStore")
    private String nameStore;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "storeDetail", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "storeDetail")
    private Set<BookStore> bookStore;

    public StoreDetail()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<BookStore> getBookStore() {
        return bookStore;
    }

    public void setBookStore(Set<BookStore> bookStore) {
        this.bookStore = bookStore;
    }
}
