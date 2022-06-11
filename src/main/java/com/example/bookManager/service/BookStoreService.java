package com.example.bookManager.service;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.DTO.BookStoreDTO;
import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.BookStore;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.repositories.BookStoreRepository;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;
    private final BookService bookService;
    private final StoreService storeService;

    public BookStoreService(BookStoreRepository bookStoreRepository, BookService bookService,
                            StoreService storeService) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookService = bookService;
        this.storeService = storeService;
    }

    public String createBookStore(BookStoreDTO bookStoreDTO)
    {
        StoreDetail storeDetail = storeService.getStoreDetailById(bookStoreDTO.getIdStore());
        for (BookDTO bookDTO: bookStoreDTO.getBookDTOS()) {
            BookStore bookStore = new BookStore();
            BookDetail bookDetail = bookService.getBookById(bookDTO.getId());
            bookStore.setStoreDetail(storeDetail);
            bookStore.setBookDetail(bookDetail);
            bookStore.setNumber(bookDTO.getNumber());
            bookStore.setCanBuy(true);
            bookStoreRepository.save(bookStore);
            storeDetail.getBookStore().add(bookStore);
            bookDetail.getBookStore().add(bookStore);
            bookService.updateBook(bookDetail);
        }

        storeService.updateStore(storeDetail);
        return  "Create Success";
    }

    public String updateBookStore( BookStoreDTO bookStoreDTO)
    {
        StoreDetail storeDetail = storeService.getStoreDetailById(bookStoreDTO.getIdStore());
        for (BookDTO bookDTO: bookStoreDTO.getBookDTOS()) {
            BookStore bookStore = new BookStore();
            BookDetail bookDetail = bookService.getBookById(bookDTO.getId());
            bookStore.setStoreDetail(storeDetail);
            bookStore.setBookDetail(bookDetail);
            bookStore.setNumber(bookDTO.getNumber());
            bookStore.setCanBuy(true);
            bookStoreRepository.save(bookStore);
            storeDetail.getBookStore().add(bookStore);
            bookDetail.getBookStore().add(bookStore);
            bookService.updateBook(bookDetail);
        }
        storeService.updateStore(storeDetail);
        return  "Update Success";
    }
}
