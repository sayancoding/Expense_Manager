package com.example.userService.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String fullName;
    private String contactNo;
    private String emailId;
    private String password;
}
