package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
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

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "storeDetail")
    private List<BookDetail> bookDetail;

    public StoreDetail()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BookDetail> getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(List<BookDetail> bookDetail) {
        this.bookDetail = bookDetail;
    }

    @Override
    public String toString()
    {
        return "id " + id + " : " + username;
    }
}
