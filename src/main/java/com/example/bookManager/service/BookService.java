package com.example.bookManager.service;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.repositories.BookRepository;
import com.example.bookManager.repositories.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String createNewBook(BookDTO bookDTO)
    {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setNameBook(bookDTO.getNameBook());
        bookDetail.setAuthor(bookDTO.getAuthor());
        bookDetail.setPublicDate(bookDTO.getPublicDate());
        bookRepository.save(bookDetail);
        return "create new book success";
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
}
