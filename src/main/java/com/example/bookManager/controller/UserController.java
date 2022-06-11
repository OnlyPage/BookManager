package com.example.bookManager.controller;

import com.example.bookManager.DTO.UserDTO;
import com.example.bookManager.Exception.UserNotFoundException;
import com.example.bookManager.Exception.UsernameHasBeenUsedException;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.repositories.UserRepository;
import com.example.bookManager.service.UserService;
import com.example.bookManager.service.response.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailResponse>> getAllUserDetails()
    {
        List<UserDetailResponse> userDetails = userService.getALlUser();
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDetailResponse> createNewUser(@ModelAttribute("userDetail") UserDTO userDto)
    {
        try {
            UserDetailResponse userDetailResponse =  userService.createUser(userDto);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
        }catch (UsernameHasBeenUsedException exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.IM_USED, "Username has been used", exception);
        }
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserDetailResponse> getUserById(@PathVariable("id") Integer id)
    {
        try {
            UserDetailResponse userDetailResponse = userService.getUserById(id);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
        }catch (UserNotFoundException exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", exception);
        }
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<UserDetailResponse> getUserByUserName(@PathVariable("username")String username)
    {
        UserDetailResponse userDetailResponse = userService.getUserByUserName(username);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

    @PutMapping("/users")
    public String updateNewUser(@ModelAttribute("userDetail") UserDTO userDto)
    {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/users")
    public String deleteAllUser()
    {
        return userService.deleteAllUser();
    }

    @DeleteMapping("/users/{username}")
    public String deleteUserByUsername(@PathVariable("username")String username)
    {
        return userService.deleteUser(username);
    }
}
