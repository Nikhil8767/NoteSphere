package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserServices;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired 
    private UserServices urs;

    @GetMapping("/getAllUser")
    public List<UserEntity>getAllUser(){
        return urs.getAllUser();
    }
}
