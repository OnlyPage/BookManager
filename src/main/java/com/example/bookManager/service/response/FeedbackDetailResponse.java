package com.example.bookManager.service.response;

import com.example.bookManager.domain.FeedBackDetail;
import com.example.bookManager.domain.UserDetail;

public class FeedbackDetailResponse
{
    private int id;
    private BookDetailResponse bookDetailResponse;
    private UserDetailResponse userDetailResponse;
    private String comment;
    private int rating;

    public FeedbackDetailResponse() {
    }

    public FeedbackDetailResponse(FeedBackDetail feedBackDetail, UserDetail userDetail)
    {
        this.id = feedBackDetail.getId();
        this.comment = feedBackDetail.getComment();
        this.rating = feedBackDetail.getRating();
        this.bookDetailResponse = new BookDetailResponse(feedBackDetail.getBookDetail());
        this.userDetailResponse = new UserDetailResponse(userDetail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookDetailResponse getBookDetailResponse() {
        return bookDetailResponse;
    }

    public void setBookDetailResponse(BookDetailResponse bookDetailResponse) {
        this.bookDetailResponse = bookDetailResponse;
    }

    public UserDetailResponse getUserDetailResponse() {
        return userDetailResponse;
    }

    public void setUserDetailResponse(UserDetailResponse userDetailResponse) {
        this.userDetailResponse = userDetailResponse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
