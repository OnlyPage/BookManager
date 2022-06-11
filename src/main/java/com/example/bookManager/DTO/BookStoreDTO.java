package com.example.bookManager.DTO;

import com.example.bookManager.domain.BookDetail;

import java.util.List;

public class BookStoreDTO
{
    private Integer idStore;
    private List<BookDTO> bookDTOS;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }

    public List<BookDTO> getBookDTOS() {
        return bookDTOS;
    }

    public void setBookDTOS(List<BookDTO> bookDTOS) {
        this.bookDTOS = bookDTOS;
    }
}
