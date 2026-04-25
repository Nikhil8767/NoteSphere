package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired 
    private UserRepository urs;

    @Test
    public void testadd(){
        assertEquals(4, 2+2);
    }

    @Test
    public void testFindByUser(){
       
        UserEntity user=new UserEntity();
        user.setUsername("nikhill");
        user.setPassword("1234");
        user.setRole("USER");


        urs.save(user);

        UserEntity u=urs.findByUsername("nikhill");

        assertNotNull(u);
        assertEquals("nikhill", u.getUsername());
    }

}
