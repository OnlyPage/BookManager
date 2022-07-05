package com.example.bookManager.controller;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.DTO.UserDTO;
import com.example.bookManager.Exception.UsernameHasBeenUsedException;
import com.example.bookManager.service.BookService;
import com.example.bookManager.service.response.BookDetailResponse;
import com.example.bookManager.service.response.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookDetailResponse> createNewBook(@ModelAttribute BookDTO bookDTO)
    {
        try {
            BookDetailResponse bookDetailResponse =  bookService.createNewBook(bookDTO);
            return new ResponseEntity<>(bookDetailResponse, HttpStatus.OK);
        }catch (Exception exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.IM_USED,exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable("id") Integer id)
    {
        return bookService.deleteBook(id);
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDetailResponse>> getBooks()
    {
        List<BookDetailResponse> bookDetailResponses = bookService.getAllBook();
        return new ResponseEntity<>(bookDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDetailResponse> getBookById(@PathVariable("id") Integer id)
    {
        BookDetailResponse bookDetailResponses = bookService.getBookResById(id);
        return new ResponseEntity<>(bookDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/book/store/{name}")
    public ResponseEntity<List<BookDetailResponse>> getBookByNameStore(@PathVariable("name") String name)
    {
        List<BookDetailResponse> bookDetailResponses = bookService.getAllBookByNameStore(name);
        return new ResponseEntity<>(bookDetailResponses, HttpStatus.OK);
    }

    @GetMapping("/book/recommend/{username}")
    public ResponseEntity<List<BookDetailResponse>> getBooksRecommend(@PathVariable("username") String username)
    {
        try {
            List<BookDetailResponse> bookDetailResponses = bookService.recommendBookByUsernameAndNumber(username);
            return new ResponseEntity<>(bookDetailResponses, HttpStatus.OK);
        }catch (Exception e)
        {
            throw  e;
        }
    }

    @GetMapping("/book/search/{name}")
    public ResponseEntity<List<BookDetailResponse>> searchBookByName(@PathVariable("name") String name)
    {
        List<BookDetailResponse> bookDetailResponses = bookService.searchBook(name);
        return new ResponseEntity<>(bookDetailResponses, HttpStatus.OK);
    }
}
