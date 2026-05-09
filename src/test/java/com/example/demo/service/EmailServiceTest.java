package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;


    @Test
    void testSendMail(){
        emailService.sendMail("nikhilgupta@gmail.com", "testing the filed", "hi how is you day");
    }
}
