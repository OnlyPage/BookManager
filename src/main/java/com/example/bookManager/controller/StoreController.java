package com.example.bookManager.controller;

import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.service.BookService;
import com.example.bookManager.service.StoreService;
import com.example.bookManager.service.response.BookDetailResponse;
import com.example.bookManager.service.response.StoreDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController
{
    @Autowired
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/store/{username}")
    public ResponseEntity<StoreDetailResponse> getStoreByUsername(@PathVariable("username")String username)
    {
        StoreDetailResponse storeDetails = storeService.getStoreResponseByUsername(username);
        return new ResponseEntity<>(storeDetails, HttpStatus.OK);
    }

    @GetMapping("/store")
    public ResponseEntity<List<StoreDetailResponse>> getAllStore()
    {
        List<StoreDetailResponse> storeDetails = storeService.getAllStoreResponse();
        return new ResponseEntity<>(storeDetails, HttpStatus.OK);
    }
}
