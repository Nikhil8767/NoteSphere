package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.JournalEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.JournalEntityService;
import com.example.demo.service.UserServices;


@RestController
public class JournalEntryController {

    @Autowired
    private JournalEntityService jrs;

    // delete function is remaining **************************************************************
    // when we delete this it should also get deleted from user or user delete that then it should be deleted 

    @GetMapping("/getAllJournal")
    public ResponseEntity<List<JournalEntity>>getAll(){
        return jrs.getAllJournal();
    }

    @GetMapping("/getJournal/{username}")
    public List<JournalEntity>getJournal(String username){
        return jrs.getAllJournalOfUsers(username);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<JournalEntity>getByid(@PathVariable String id){
       return jrs.getByid(id);
    }

     

   

    @PostMapping("/add/{username}")
    public ResponseEntity<String> add(@PathVariable String username,@RequestBody JournalEntity j){  
       return jrs.add(j,username);
    }

    

}
