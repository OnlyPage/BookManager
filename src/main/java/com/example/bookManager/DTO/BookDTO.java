package com.example.bookManager.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class BookDTO
{
    private Integer id;
    private String nameBook;
    private String author;
    private String publicDate;
    private Integer number;
    private Integer category;
    private String userName;
    private Integer price;
    private List<Integer> idBooks;

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Integer> getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(List<Integer> idBooks) {
        this.idBooks = idBooks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
