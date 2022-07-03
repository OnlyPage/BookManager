package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "bookDetail")
public class BookDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameBook")
    private String nameBook;

    @Column(name = "author")
    private String author;

    @Column(name = "publicDate")
    private String publicDate;

    @ManyToOne
    @JoinColumn(name = "categoryDetail_id")
    private CategoryDetail categoryDetail;

    @ManyToOne
    @JoinColumn(name = "storeDetail_id")
    private StoreDetail storeDetail;

    @Column(name = "number")
    private Integer number;

    @Column(name = "isCanBuy")
    private Boolean isCanBuy;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "bookDetail")
    private List<FeedBackDetail> feedBackDetails;

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

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }

    public CategoryDetail getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(CategoryDetail categoryDetail) {
        this.categoryDetail = categoryDetail;
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

    public List<FeedBackDetail> getFeedBackDetails() {
        return feedBackDetails;
    }

    public void setFeedBackDetails(List<FeedBackDetail> feedBackDetails) {
        this.feedBackDetails = feedBackDetails;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "id " + id + " : " + nameBook;
    }
}
