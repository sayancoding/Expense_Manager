package com.example.userService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateUserRequest {
    private String fullName;
    private String contactNo;
    private String emailId;
    private String password;
}
