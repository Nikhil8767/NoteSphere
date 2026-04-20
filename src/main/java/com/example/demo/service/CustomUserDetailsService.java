package com.example.demo.service;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServices userServices;

    public CustomUserDetailsService(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userServices.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_", ""))
                .build();
    }
}