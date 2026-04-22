package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.JournalEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.JournalEntityService;
import com.example.demo.service.UserServices;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntityService jrs;

    @Autowired
    private UserServices urs;

    @GetMapping("/getAllJournal")
    public ResponseEntity<List<JournalEntity>>getAll(){
        return jrs.getAllJournal();
    }
// *************************************************************************
    // @GetMapping("/getJournal/{username}")
    // public List<JournalEntity>getJournal(String username){
    //     return jrs.getAllJournalOfUsers(username);
    // }
    // =========================after auth =======================
     @GetMapping("/getJournal")
    public List<JournalEntity>getJournal(){
        return jrs.getAllJournalOfUsers();
    }
// **************************************************************

    @GetMapping("/getById/{id}")
    public ResponseEntity<JournalEntity>getByid(@PathVariable String id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=urs.findByUsername(userName);
        List<JournalEntity>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(collect.isEmpty()){
            Optional<JournalEntity>journal=jrs.findById(id);
            if(journal.isPresent()){
                return new ResponseEntity<>(journal.get(),HttpStatus.OK);
            }
        }
       return jrs.getByid(id);
    }

     

   
// ****************************************************
    // @PostMapping("/add/{username}")
    // public ResponseEntity<String> add(@PathVariable String username,@RequestBody JournalEntity j){  
    //    return jrs.add(j,username);
    // }

// =====================after auth=========================
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody JournalEntity j){  
       return jrs.add(j);
    }

// *****************************************


    @PutMapping("/update/{id}")
    public ResponseEntity<String>updateById(@PathVariable String id,@RequestBody JournalEntity journal){
        return jrs.Update(id,journal);
    }

    

}
