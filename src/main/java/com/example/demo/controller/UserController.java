package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserServices;

public class UserController {

    @Autowired UserServices urs;

     @GetMapping("/getUsers")
    public ResponseEntity<List<UserEntity>>getAllUser(){
        return urs.getAll();
    }
}
