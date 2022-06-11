package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "orderDetail")
public class OrderDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userDetail_id")
    private UserDetail customer;

    @ManyToOne
    @JoinColumn(name = "storeDetail_id")
    private StoreDetail storeDetail;

    @Column(name = "dateOrder")
    private Date dateOrder;

    @Column(name = "price")
    private Integer price;


}
