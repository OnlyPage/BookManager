package com.example.bookManager.service;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.domain.*;
import com.example.bookManager.repositories.BookRepository;
import com.example.bookManager.service.response.BookDetailResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;

@Service
public class BookService
{
    private final BookRepository bookRepository;
    private final StoreService storeService;
    private final CategoryService categoryService;
    private final FeedbackService feedbackService;
    private final CustomerService customerService;

    public BookService(BookRepository bookRepository, StoreService storeService, CategoryService categoryService,
                       @Lazy CustomerService customerService,@Lazy FeedbackService feedbackService) {
        this.bookRepository = bookRepository;
        this.storeService = storeService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.feedbackService = feedbackService;
    }

    public BookDetailResponse createNewBook(BookDTO bookDTO)
    {
        try
        {
            StoreDetail storeDetail = storeService.getStoreDetailByUsername(bookDTO.getUserName());
            CategoryDetail categoryDetail = categoryService.getCategoryDetailById(bookDTO.getCategory());
            BookDetail newBookDetail = new BookDetail();
            newBookDetail.setNameBook(bookDTO.getNameBook());
            newBookDetail.setAuthor(bookDTO.getAuthor());
            newBookDetail.setPublicDate(bookDTO.getPublicDate());
            newBookDetail.setNumber(bookDTO.getNumber());
            newBookDetail.setStoreDetail(storeDetail);
            newBookDetail.setCategoryDetail(categoryDetail);
            newBookDetail.setCanBuy(!(bookDTO.getNumber() == 0));
            newBookDetail.setPrice(bookDTO.getPrice());
            bookRepository.save(newBookDetail);
            return new BookDetailResponse(newBookDetail);
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String updateBook(int id, BookDTO bookDTO)
    {
        BookDetail bookDetail = bookRepository.findById(id).get();
        bookDetail.setNameBook(bookDTO.getNameBook());
        bookDetail.setAuthor(bookDTO.getAuthor());
        bookDetail.setPublicDate(bookDTO.getPublicDate());
        bookRepository.save(bookDetail);
        return "update book success";
    }

    public String updateBook(BookDetail bookDetail)
    {
        bookRepository.save(bookDetail);
        return "update book success";
    }

    public String deleteBook(int id)
    {
        bookRepository.deleteById(id);
        return "delete success";
    }

    public BookDetail getBookById(int id)
    {
        return bookRepository.findById(id).get();
    }

    public BookDetailResponse getBookResById(int id)
    {
        return new BookDetailResponse(bookRepository.findById(id).get());
    }

    public List<BookDetailResponse> getAllBook()
    {
        List<BookDetail> bookDetails = bookRepository.findAll();
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        if(bookDetails != null) {
            for (BookDetail bookDetail: bookDetails) {
                BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
                bookDetailResponse.setRating(feedbackService.getRatingByIdBook(bookDetail.getId()));
                bookDetailResponses.add(bookDetailResponse);
            }
        }
        return bookDetailResponses;
    }

    public List<BookDetailResponse> getAllBookByIdStore(int idStore)
    {
        List<BookDetail> bookDetails = bookRepository.findByStoreDetailId(idStore);
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        if(bookDetails != null) {
            for (BookDetail bookDetail: bookDetails) {
                BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
                bookDetailResponse.setRating(feedbackService.getRatingByIdBook(bookDetail.getId()));
                bookDetailResponses.add(bookDetailResponse);
            }
        }
        return bookDetailResponses;
    }

    public List<BookDetailResponse> getAllBookByNameStore(String name)
    {
        StoreDetail storeDetail = storeService.getStoreDetailByUsername(name);
        List<BookDetail> bookDetails = bookRepository.findByStoreDetailId(storeDetail.getId());
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        if(bookDetails != null) {
            for (BookDetail bookDetail: bookDetails) {
                BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
                bookDetailResponse.setRating(feedbackService.getRatingByIdBook(bookDetail.getId()));
                bookDetailResponses.add(bookDetailResponse);
            }
        }
        return bookDetailResponses;
    }

    public List<BookDetail> getAllBookDetailByIdStore(Integer storeId) {
        List<BookDetail> bookDetails = new ArrayList<>();
        bookDetails = bookRepository.findBookByIdStore(storeId);
        return bookDetails;
    }

    public List<BookDetail> getAllBookDetail()
    {
        List<BookDetail> bookDetails = bookRepository.findAll();
        return bookDetails;
    }

    public List<BookDetailResponse> searchBook(String name)
    {
        List<BookDetail> bookDetails = bookRepository.searchByNameBookLike(name);
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        if(bookDetails != null) {
            for (BookDetail bookDetail: bookDetails) {
                BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
                bookDetailResponse.setRating(feedbackService.getRatingByIdBook(bookDetail.getId()));
                bookDetailResponses.add(bookDetailResponse);
            }
        }
        return bookDetailResponses;
    }

    public List<BookDetailResponse> recommendBookByUsernameAndNumber(String username)
    {
        Map<BookDetail, Map<BookDetail, Double>> diff = new HashMap<>();
        Map<BookDetail, Map<BookDetail, Integer>> freq = new HashMap<>();
        Map<String, HashMap<BookDetail, Double>> inputData = new HashMap<>();
        Map<String, HashMap<BookDetail, Double>> outputData = new HashMap<>();

        List<FeedBackDetail> feedBackDetails = feedbackService.getAllFeedbackDetail();
        List<CustomerDetail> customerDetails = customerService.findAll();
        for (CustomerDetail customerDetail : customerDetails)
        {
            inputData.put(customerDetail.getUsername(), new HashMap<>());
        }

        for(FeedBackDetail feedBackDetail : feedBackDetails)
        {
            inputData.get(feedBackDetail.getUsername()).put(feedBackDetail.getBookDetail(), (double)feedBackDetail.getRating());
        }

        buildDifferencesMatrix(inputData, diff, freq);

        predict(inputData, diff, freq, outputData);

        List<BookDetailResponse> value = new ArrayList<>();
        outputData.get(username).entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                forEach(k ->
                {
                    BookDetailResponse bookDetailResponse = new BookDetailResponse(k.getKey());
                    bookDetailResponse.setRating(feedbackService.getRatingByIdBook(bookDetailResponse.getId()));
                    value.add(bookDetailResponse);
                });
        return value;
    }


    private void buildDifferencesMatrix(Map<String, HashMap<BookDetail, Double>> data, Map<BookDetail, Map<BookDetail, Double>> diff,
                                               Map<BookDetail, Map<BookDetail, Integer>> freq)
    {
        for (HashMap<BookDetail, Double> user : data.values()) {
            for (Map.Entry<BookDetail, Double> e : user.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<BookDetail, Double>());
                    freq.put(e.getKey(), new HashMap<BookDetail, Integer>());
                }
                for (Map.Entry<BookDetail, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (BookDetail j : diff.keySet()) {
            for (BookDetail i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i).doubleValue();
                int count = freq.get(j).get(i).intValue();
                diff.get(j).put(i, oldValue / count);
            }
        }
    }

    private void predict(Map<String, HashMap<BookDetail, Double>> data, Map<BookDetail, Map<BookDetail, Double>> diff,
                                Map<BookDetail, Map<BookDetail, Integer>> freq, Map<String, HashMap<BookDetail, Double>> outputData)
    {
        HashMap<BookDetail, Double> uPred = new HashMap<BookDetail, Double>();
        HashMap<BookDetail, Integer> uFreq = new HashMap<BookDetail, Integer>();
        List<BookDetail> bookDetails = new ArrayList<>();
        bookDetails = getAllBookDetail();
        for (BookDetail j : diff.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Map.Entry<String, HashMap<BookDetail, Double>> e : data.entrySet()) {
            for (BookDetail j : e.getValue().keySet()) {
                for (BookDetail k : diff.keySet()) {
                    try {
                        double predictedValue = diff.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * freq.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + freq.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }
            HashMap<BookDetail, Double> clean = new HashMap<BookDetail, Double>();
            for (BookDetail j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
                }
            }

            for (BookDetail j : bookDetails) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0);
                }
            }
            outputData.put(e.getKey(), clean);
        }
    }

}
