package com.example.demo.service;

import java.net.Authenticator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.JournalEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.JournalEntityRepository;
import com.example.demo.repository.UserRepository;


@Component
public class UserServices {

    @Autowired
    private UserRepository urs;

    // private static final PasswordEncoder passwordencoder=new BCryptPasswordEncoder();/
    @Autowired
private PasswordEncoder passwordEncoder;

    @Autowired JournalEntityRepository jrs;

     public ResponseEntity<List<UserEntity>>getAll(){
        List<UserEntity>a=urs.findAll();
        if(a.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else{
            return new ResponseEntity<>(a,HttpStatus.OK);
        }
        
    }

        public ResponseEntity<String>addUser(UserEntity user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
         
            
            urs.save(user);
            return new ResponseEntity<>("users added successfully",HttpStatus.CREATED);
        }

        
       @Transactional
        public ResponseEntity<String>delete(String id,int userId){
            Optional<UserEntity>u=urs.findById(userId);
             Optional<JournalEntity>j=jrs.findById(id);
            if(u.isEmpty()){
                return new ResponseEntity<>("user not found ",HttpStatus.NOT_FOUND);
            }

            if(j.isEmpty()){
                 return new ResponseEntity<>("user not found ",HttpStatus.NOT_FOUND);
            }
            
            UserEntity user=u.get();
            JournalEntity journal=j.get();
            user.getJournalEntries().remove(journal);
            jrs.delete(journal);

            return new ResponseEntity<>("deleted succesfully",HttpStatus.OK);

        }

        public UserEntity findByUsername(String username){
            return urs.findByUsername(username);
        }


        public ResponseEntity<String>update(UserEntity user){

            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            UserEntity userInDb=urs.findByUsername(username);

            userInDb.setUsername(user.getUsername());
            // userInDb.setPassword(user.getPassword());
            // urs.save(userInDb);
            // we are not saving it directly ... instead of that we are converting the password to hash form
             userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
            urs.save(userInDb);
            return new ResponseEntity<>("updated successfullty",HttpStatus.OK);

        }

}
