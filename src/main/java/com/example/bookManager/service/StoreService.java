package com.example.bookManager.service;

import com.example.bookManager.DTO.BookDTO;
import com.example.bookManager.DTO.StoreDTO;
import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.BookStore;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.StoreRepository;
import com.example.bookManager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService
{
    private final StoreRepository storeRepository;
    private final BookService bookService;
    private final UserService userService;

    public StoreService(StoreRepository storeRepository, BookService bookService, UserService userService) {
        this.storeRepository = storeRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public String createNewStore(String username, StoreDTO storeDTO)
    {
        UserDetail userDetail = userService.getUserDetailByUserName(username);
        if(userDetail!= null)
        {
            StoreDetail storeDetail = new StoreDetail();
            storeDetail.setNameStore(storeDTO.getNameStore());
            storeDetail.setUsername(username);
            storeRepository.save(storeDetail);
            return "create store success";
        }
        return "create store fail";
    }

    public String updateStore(int id, StoreDTO storeDTO)
    {
        StoreDetail storeDetail = storeRepository.findById(id).get();
        storeDetail.setNameStore(storeDTO.getNameStore());
        storeRepository.save(storeDetail);
        return "update store success";
    }

    public String updateStore(StoreDetail storeDetail)
    {
        storeRepository.save(storeDetail);
        return "update store success";
    }

    public String deleteStore(int id)
    {
        storeRepository.deleteById(id);
        return "delete store success";
    }

    public StoreDetail getStoreDetailById(int id)
    {
        return storeRepository.findById(id).get();
    }


}
