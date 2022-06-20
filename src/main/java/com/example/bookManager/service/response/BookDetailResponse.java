package com.example.bookManager.service.response;

import com.example.bookManager.domain.BookDetail;

public class BookDetailResponse
{
    private int id;
    private String nameBook;
    private String author;
    private String publicDate;
    private Integer number;
    private String category;
    private String userName;
    private boolean isCanBuy;

    public BookDetailResponse(String nameBook, String author, String publicDate, Integer number, String category, String userName, boolean isCanBuy) {
        this.nameBook = nameBook;
        this.author = author;
        this.publicDate = publicDate;
        this.number = number;
        this.category = category;
        this.userName = userName;
        this.isCanBuy = isCanBuy;
    }

    public BookDetailResponse(BookDetail bookDetail)
    {
        this.id = bookDetail.getId();
        this.nameBook = bookDetail.getNameBook();
        this.author = bookDetail.getAuthor();
        this.publicDate = bookDetail.getPublicDate();
        this.number = bookDetail.getNumber();
        this.category = bookDetail.getCategoryDetail().getName();
        this.userName = bookDetail.getStoreDetail().getUsername();
        this.isCanBuy = bookDetail.getCanBuy();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isCanBuy() {
        return isCanBuy;
    }

    public void setCanBuy(boolean canBuy) {
        isCanBuy = canBuy;
    }
}
