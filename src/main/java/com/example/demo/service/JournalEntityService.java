package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.entity.JournalEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.JournalEntityRepository;
import com.example.demo.repository.UserRepository;

@Component
public class JournalEntityService {

    @Autowired
    private JournalEntityRepository jrs;

    @Autowired 
    private UserRepository urs;


    public ResponseEntity<List<JournalEntity>>getAllJournal(){

        return new ResponseEntity<>(jrs.findAll(),HttpStatus.FOUND);
    }

    public Optional<JournalEntity>findById(String id){
        return jrs.findById(id);
    }
    

    public List<JournalEntity>getAllJournalOfUsers(String Username){
        UserEntity user=urs.findByUsername(Username);

        return user.getJournalEntries();
    }

    public ResponseEntity<JournalEntity>getByid(String id){
        Optional<JournalEntity>a=jrs.findById(id);
        if(a.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<JournalEntity>(a.get(),HttpStatus.FOUND);
        }
    }


   

    public ResponseEntity<String> add(JournalEntity j,String username){
        j.setDate(LocalDate.now());
        UserEntity user=urs.findByUsername(username);
        if(user==null){
            return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
        }
        else{
            j.setUser(user);
            JournalEntity saved=jrs.save(j);
             user.getJournalEntries().add(saved);
            return new ResponseEntity<>("added to userprofile and journal",HttpStatus.CREATED);
        }
    }










    
}
