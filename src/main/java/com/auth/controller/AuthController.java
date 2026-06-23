package com.auth.controller;


import com.auth.dto.*;

import com.auth.service.AuthService;
import com.auth.service.BlacklistService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {



    private final AuthService service;
    
    private final BlacklistService blacklistService;




    @PostMapping("/login")
    public AuthResponse login(

            @RequestBody LoginRequest request){

        return service.login(request);

    }





    @PostMapping("/refresh")
    public AuthResponse refresh(

            @RequestBody RefreshRequest request){

        return service.refresh(request);

    }
    
    @PostMapping("/logout")
    public String logout(

    @RequestHeader("Authorization")
    String header){


    String token =
    header.substring(7);



    blacklistService.blacklist(token);



    return "Logged out successfully";


    }



}