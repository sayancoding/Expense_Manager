package com.example.userService.controller;

import com.example.userService.dto.CreateUserRequest;
import com.example.userService.dto.UserDto;
import com.example.userService.exception.AlreadyExistException;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String createUser(@RequestBody CreateUserRequest userRequest) throws AlreadyExistException {
        userService.createUser(userRequest);
        return "New User is created..";
    }

    @GetMapping("/query")
    public UserDto findByEmail(@RequestParam("email") String emailId){
        return userService.findByEmail(emailId);
    }

}
