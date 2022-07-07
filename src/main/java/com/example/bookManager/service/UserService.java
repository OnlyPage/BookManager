package com.example.bookManager.service;


import com.example.bookManager.DTO.LoginDTO;
import com.example.bookManager.DTO.UserDTO;
import com.example.bookManager.Exception.UserNotFoundException;
import com.example.bookManager.Exception.UsernameHasBeenUsedException;
import com.example.bookManager.domain.RoleDetail;
import com.example.bookManager.domain.UserDetail;
import com.example.bookManager.helper.ListHelper;
import com.example.bookManager.repositories.UserRepository;
import com.example.bookManager.service.response.UserDetailResponse;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;

    private final CustomerService customerService;
    private final RoleService roleService;
    private final StoreService storeService;

    public UserService(UserRepository userRepository, CustomerService customerService, RoleService roleService, StoreService storeService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.roleService = roleService;
        this.storeService = storeService;
    }

    public UserDetailResponse userLogin(LoginDTO loginDTO)
    {
        UserDetail userDetail = userRepository.findUserByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if(userDetail != null)
        {
            return new UserDetailResponse(userDetail);
        }
        throw new UserNotFoundException("Can login user, wrong username or password");
    }

    public String userLogout(String username)
    {
        UserDetail userDetail = userRepository.findUserByUserName(username);
        if(userDetail != null)
        {
            return "Logout success";
        }
        else
            return "Logout failed";
    }

    public UserDetailResponse createUser(UserDTO userDTO)
    {
        UserDetail oldUser = userRepository.findByUsername(userDTO.getUsername());
        if(oldUser != null)
        {
            throw  new UsernameHasBeenUsedException("User name has been used");
        }

        UserDetail newUser = new UserDetail();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        newUser.setAddress(userDTO.getAddress());
        newUser.setEmail(userDTO.getEmail());
        newUser.setCreateTime(new Date());
        RoleDetail roleDetail = roleService.getRoleById(userDTO.getRole());
        newUser.setRoleDetail(roleDetail);
        userRepository.save(newUser);
        if(roleDetail.getRoleName().equals("CUSTOMER"))
        {
            customerService.CreateNewCustomer(newUser.getUsername());
        }
        else
        {
            storeService.createNewStore(newUser.getUsername());
        }
        return new UserDetailResponse(newUser);
    }

    public UserDetailResponse updateUser(UserDTO userDTO)
    {
        UserDetail oldUser = userRepository.findByUsername(userDTO.getUsername());

        oldUser.setPassword(userDTO.getPassword());
        oldUser.setPhoneNumber(userDTO.getPhoneNumber());
        oldUser.setAddress(userDTO.getAddress());
        oldUser.setEmail(userDTO.getEmail());
        userRepository.save(oldUser);
        return new UserDetailResponse(oldUser);
    }

    public List<UserDetailResponse> getALlUser()
    {
        List<UserDetail> userDetails = userRepository.findAll();
        List<UserDetailResponse> userDetailResponses = new ArrayList<>();
        if(userDetails != null) {
            for (UserDetail userDetail: userDetails) {
                UserDetailResponse userDetailResponse = new UserDetailResponse(userDetail);
                userDetailResponses.add(userDetailResponse);
            }
        }
        return userDetailResponses;
    }

    public UserDetailResponse getUserById(int id)
    {
        Optional<UserDetail> userDetail = userRepository.findById(id);
        if(userDetail.isPresent())
        {
            return new UserDetailResponse(userDetail.get());
        }
        throw new UserNotFoundException("Can find user with id " + id);
    }

    public UserDetail getUserDetailById(int id)
    {
        Optional<UserDetail> userDetail = userRepository.findById(id);
        if(userDetail.isPresent())
        {
            return userDetail.get();
        }
        throw new UserNotFoundException("Can find user with id " + id);
    }


    public UserDetailResponse getUserByUserName(String username)
    {
        UserDetail userDetail = userRepository.findUserByUserName(username);
        if(userDetail != null)
        {
            return new UserDetailResponse(userDetail);
        }
        throw new UserNotFoundException("Can find user with username " + username);
    }

    public UserDetail getUserDetailByUserName(String username)
    {
        UserDetail userDetail = userRepository.findUserByUserName(username);
        if(userDetail != null)
        {
            return userDetail;
        }
        throw new UserNotFoundException("Can find user with username " + username);
    }

    public String deleteUser(String username)
    {
        UserDetail userDetail = userRepository.findUserByUserName(username);
        if(userDetail != null)
        {
            userRepository.delete(userDetail);
            return "delete success";
        }
        throw new UserNotFoundException("Can find user with username " + username);
    }

    public String deleteAllUser()
    {
        userRepository.deleteAll();
        return "delete success";
    }
}
