package com.example.bookManager.controller;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.DTO.FeedbackDTO;
import com.example.bookManager.service.FeedbackService;
import com.example.bookManager.service.response.BookDetailResponse;
import com.example.bookManager.service.response.FeedbackDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FeedbackController
{
    @Autowired
    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackDetailResponse> createFeedBack(@ModelAttribute FeedbackDTO feedbackDTO)
    {
        try {
            FeedbackDetailResponse feedbackDetailResponse =  feedbackService.createNewFeedBack(feedbackDTO);
            return new ResponseEntity<>(feedbackDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw exception;
        }
    }

    @PutMapping("/feedback")
    public ResponseEntity<FeedbackDetailResponse> updateFeedBack(@ModelAttribute FeedbackDTO feedbackDTO)
    {
        try {
            FeedbackDetailResponse feedbackDetailResponse =  feedbackService.updateNewFeedBack(feedbackDTO);
            return new ResponseEntity<>(feedbackDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw exception;
        }
    }

    @DeleteMapping("/feedback/{id}")
    public Boolean deleteBookById(@PathVariable("id") Integer id)
    {
        return feedbackService.deleteFeedBackById(id);
    }

    @GetMapping("/feedback")
    public ResponseEntity<List<FeedbackDetailResponse>> getALlFeedBack()
    {
        List<FeedbackDetailResponse> feedbackDetailResponses = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbackDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<List<FeedbackDetailResponse>> getFeedbacksById(@PathVariable("id") Integer id)
    {
        List<FeedbackDetailResponse> feedbackDetailResponses = feedbackService.getFeedbackResponseByIdBook(id);
        return new ResponseEntity<>(feedbackDetailResponses, HttpStatus.OK);
    }
}
