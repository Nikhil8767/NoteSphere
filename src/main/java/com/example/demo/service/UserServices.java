package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;


@Component
public class UserServices {

    @Autowired
    private UserRepository urs;

     public ResponseEntity<List<UserEntity>>getAll(){
        List<UserEntity>a=urs.findAll();
        if(a.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else{
            return new ResponseEntity<>(a,HttpStatus.OK);
        }
        
    }

}
