package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserServices;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices urs;

     @PostMapping("/addUser")
    public ResponseEntity<String>Add(@RequestBody UserEntity user){
        return urs.addUser(user);
    }
}
