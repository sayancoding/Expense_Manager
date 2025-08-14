package com.example.userService.service;

import com.example.userService.dao.UserDao;
import com.example.userService.dto.CreateUserRequest;
import com.example.userService.dto.UserDto;
import com.example.userService.entity.User;
import com.example.userService.exception.AlreadyExistException;
import com.example.userService.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequest userRequest) throws AlreadyExistException {
        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setContactNo(userRequest.getContactNo());
        user.setEmailId(userRequest.getEmailId());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        if(userDao.findByEmailId(userRequest.getEmailId()).isPresent()){
            throw new AlreadyExistException(userRequest.getEmailId() + " : User is already exist..");
        }
        userDao.save(user);
    }
    public UserDto findByEmail(String emailId){

        User user = userDao.findByEmailId(emailId)
                .orElseThrow(()->new NotFoundException(emailId +" : User Not found"));
        if(!user.getUsername().equals(getCurrentUsername())){
            throw new NotFoundException("Not found");
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    private String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }
}
