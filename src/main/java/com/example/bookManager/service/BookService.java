package com.example.bookManager.service;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.CategoryDetail;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.repositories.BookRepository;
import com.example.bookManager.service.response.BookDetailResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService
{
    private final BookRepository bookRepository;
    private final StoreService storeService;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, StoreService storeService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.storeService = storeService;
        this.categoryService = categoryService;
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

    public List<BookDetailResponse> getAllBook()
    {
        List<BookDetail> bookDetails = bookRepository.findAll();
        List<BookDetailResponse> bookDetailResponses = new ArrayList<>();
        if(bookDetails != null) {
            for (BookDetail bookDetail: bookDetails) {
                BookDetailResponse bookDetailResponse = new BookDetailResponse(bookDetail);
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
}
