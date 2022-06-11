package com.example.bookManager.controller;


import com.example.bookManager.DTO.LoginDTO;
import com.example.bookManager.Exception.UserNotFoundException;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.service.UserService;
import com.example.bookManager.service.response.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController
{
    @Autowired
    private UserService userService;

    public LoginController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailResponse> userLogin(@ModelAttribute LoginDTO loginDTO)
    {
        try {
            UserDetailResponse userDetailResponse = userService.userLogin(loginDTO);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
        }catch (UserNotFoundException exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Wrong username or password!", exception);
        }
    }

    @PostMapping("/logout/{username}")
    public String userLogout(@PathVariable String userName)
    {
        return userService.userLogout(userName);
    }
}
