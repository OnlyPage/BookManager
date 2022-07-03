package com.example.bookManager.service;

import com.example.bookManager.DTO.FeedbackDTO;
import com.example.bookManager.domain.FeedBackDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.FeedbackRepository;
import com.example.bookManager.service.response.FeedbackDetailResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService
{
    private final FeedbackRepository feedbackRepository;
    private final BookService bookService;
    private final UserService userService;

    public FeedbackService(FeedbackRepository feedbackRepository,@Lazy BookService bookService,@Lazy UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<FeedbackDetailResponse> getAllFeedback()
    {
        List<FeedbackDetailResponse> data = new ArrayList<>();
        List<FeedBackDetail> feedBackDetails = feedbackRepository.findAll();
        for (FeedBackDetail feedBackDetail : feedBackDetails)
        {
            try {
                UserDetail userDetail = userService.getUserDetailByUserName(feedBackDetail.getUsername());
                data.add(new FeedbackDetailResponse(feedBackDetail, userDetail));
            }
            catch (Exception e)
            {
                continue;
            }

        }
        return data;
    }

    public List<FeedBackDetail> getAllFeedbackDetail()
    {
        List<FeedBackDetail> feedBackDetails = feedbackRepository.findAll();
        return feedBackDetails;
    }

    public FeedbackDetailResponse getFeedbackById(int id)
    {
        FeedBackDetail feedBackDetail = feedbackRepository.findById(id).get();
        UserDetail userDetail = userService.getUserDetailByUserName(feedBackDetail.getUsername());
        FeedbackDetailResponse feedbackDetailResponse = new FeedbackDetailResponse(feedBackDetail, userDetail);
        return feedbackDetailResponse;
    }

    public FeedbackDetailResponse createNewFeedBack(FeedbackDTO feedbackDTO)
    {
        FeedBackDetail feedBackDetail = new FeedBackDetail();
        feedBackDetail.setComment(feedbackDTO.getComment());
        feedBackDetail.setBookDetail(bookService.getBookById(feedbackDTO.getIdBook()));
        feedBackDetail.setRating(feedbackDTO.getRating());
        feedBackDetail.setUsername(feedbackDTO.getUsername());
        UserDetail userDetail = userService.getUserDetailByUserName(feedBackDetail.getUsername());
        feedbackRepository.save(feedBackDetail);
        return new FeedbackDetailResponse(feedBackDetail, userDetail);
    }

    public FeedbackDetailResponse updateNewFeedBack(FeedbackDTO feedbackDTO)
    {
        FeedBackDetail feedBackDetail = feedbackRepository.findById(feedbackDTO.getIdFeedback()).get();
        feedBackDetail.setComment(feedbackDTO.getComment());
        feedBackDetail.setBookDetail(bookService.getBookById(feedbackDTO.getIdBook()));
        feedBackDetail.setRating(feedbackDTO.getRating());
        feedBackDetail.setUsername(feedBackDetail.getUsername());
        UserDetail userDetail = userService.getUserDetailByUserName(feedbackDTO.getUsername());
        feedbackRepository.save(feedBackDetail);
        return new FeedbackDetailResponse(feedBackDetail, userDetail);
    }

    public List<FeedBackDetail> getFeedbackDetailByIDBook(int idBook)
    {
        return feedbackRepository.findFeedBackByBookId(idBook);
    }

    public Float getRatingByIdBook(int idBook)
    {
        try {
            float value = 0f;
            List<FeedBackDetail> feedBackDetails = feedbackRepository.findFeedBackByBookId(idBook);
            for (FeedBackDetail feedBackDetail : feedBackDetails)
            {
                value += ((float)feedBackDetail.getRating() / feedBackDetails.size());
            }

            return value;
        }
        catch (Exception e)
        {
            return 0f;
        }
    }

    public List<FeedbackDetailResponse> getFeedbackResponseByIdBook(int idBook)
    {
        List<FeedbackDetailResponse> data = new ArrayList<>();
        List<FeedBackDetail> feedBackDetails = feedbackRepository.findFeedBackByBookId(idBook);
        for (FeedBackDetail feedBackDetail : feedBackDetails)
        {
            UserDetail userDetail = userService.getUserDetailByUserName(feedBackDetail.getUsername());
            data.add(new FeedbackDetailResponse(feedBackDetail, userDetail));
        }
        return data;
    }

    public boolean deleteFeedBackById(int id)
    {
        feedbackRepository.deleteById(id);
        return true;
    }

    public boolean deleteFeedbackByIdBook(int idBook)
    {
        List<FeedBackDetail> feedBackDetails = feedbackRepository.findFeedBackByBookId(idBook);
        for (FeedBackDetail feedBackDetail : feedBackDetails)
        {
            feedbackRepository.delete(feedBackDetail);
        }
        return true;
    }
}
