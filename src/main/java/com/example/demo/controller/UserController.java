package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserServices urs;

     @GetMapping("/getUsers")
    public ResponseEntity<List<UserEntity>>getAllUser(){
        return urs.getAll();
    }

   

    @PutMapping("/updateUser")
    public ResponseEntity<String>update(@RequestBody UserEntity user){

        return urs.update(user);

    }

    @DeleteMapping("/deleteJournal/{id}/{userid}")
    public ResponseEntity<String>delete(@PathVariable String id,@PathVariable int userid){
        return urs.delete(id, userid);
    }
}
