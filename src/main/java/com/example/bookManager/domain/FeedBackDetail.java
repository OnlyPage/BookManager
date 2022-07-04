package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FeedbackDetail")
public class FeedBackDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "bookDetail_id")
    private BookDetail bookDetail;

    public FeedBackDetail(Integer id, Integer rating, String comment, String username, BookDetail bookDetail) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.bookDetail = bookDetail;
    }

    public FeedBackDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }
}
