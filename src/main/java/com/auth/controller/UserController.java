package com.auth.controller;


import com.auth.dto.UserRequest;
import com.auth.entity.User;

import com.auth.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {



    private final UserService service;

    @PostMapping
    public User create(

            @RequestBody UserRequest request){

        return service.createUser(request);
    }

    @GetMapping
    public List<User> all(){

        return service.getAll();

    }
    @GetMapping("/{id}")
    public User find(

            @PathVariable Long id){

        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        service.delete(id);
        return "Deleted";
    }


}