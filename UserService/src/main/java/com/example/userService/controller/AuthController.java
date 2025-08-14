package com.example.userService.controller;

import com.example.userService.dto.LoginRequest;
import com.example.userService.entity.User;
import com.example.userService.utils.AuthUtil;
import com.example.userService.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        if(authentication.isAuthenticated()){
            int userId = ((User)authentication.getPrincipal()).getUserId();
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("userId",userId);
            return jwtUtil.generateToken(request.getUsername(),claims);
        }
        else{
            return "Authentication Failed";
        }

    }
}
