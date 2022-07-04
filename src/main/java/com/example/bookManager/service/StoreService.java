package com.example.bookManager.service;

import com.example.bookManager.Exception.StoreNotFoundException;
import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.StoreRepository;
import com.example.bookManager.service.response.StoreDetailResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StoreService
{
    private final StoreRepository storeRepository;
    private final BookService bookService;

    public StoreService(StoreRepository storeRepository, @Lazy BookService bookService) {
        this.storeRepository = storeRepository;
        this.bookService = bookService;
    }

    public String createNewStore(String username)
    {
        StoreDetail storeDetail = new StoreDetail();
        storeDetail.setUsername(username);
        storeRepository.save(storeDetail);
        return "create store success";
    }

    public String updateStore(String username)
    {
        StoreDetail storeDetail = new StoreDetail();
        storeDetail.setUsername(username);
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

    public StoreDetail getStoreDetailByUsername(String userName)
    {
        StoreDetail storeDetail = storeRepository.findStoreByUserName(userName);
        if(storeDetail == null)
        {
            throw  new StoreNotFoundException("Store not found by name: " + userName);
        }
        return storeDetail;
    }

    public StoreDetailResponse getStoreResponseByUsername(String userName)
    {
        StoreDetail storeDetail = storeRepository.findStoreByUserName(userName);
        if(storeDetail == null)
        {
            throw  new StoreNotFoundException("Store not found by name: " + userName);
        }
        return new StoreDetailResponse(storeDetail);
    }

    public List<StoreDetailResponse> getAllStoreResponse()
    {
        List<StoreDetail> storeDetails = storeRepository.findAll();
        List<StoreDetailResponse> storeDetailResponses = new ArrayList<>();
        for (StoreDetail storeDetail: storeDetails)
        {
            StoreDetailResponse storeDetailResponse = new StoreDetailResponse(storeDetail);
            storeDetailResponses.add(storeDetailResponse);
        }
        return storeDetailResponses;
    }
}
