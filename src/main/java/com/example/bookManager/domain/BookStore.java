package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "bookStore")
public class BookStore
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "storeDetail_id")
    private StoreDetail storeDetail;

    @ManyToOne
    @JoinColumn(name = "bookDetail_id")
    private BookDetail bookDetail;

    @Column(name = "number")
    private Integer number;

    @Column(name = "isCanBuy")
    private Boolean isCanBuy;

    public BookStore() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getCanBuy() {
        return isCanBuy;
    }

    public void setCanBuy(Boolean canBuy) {
        isCanBuy = canBuy;
    }
}
