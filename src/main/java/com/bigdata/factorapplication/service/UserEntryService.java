package com.bigdata.factorapplication.service;

import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserEntryService {


    @Autowired
    UserEntryRepository userEntryRepository;
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public List<UserEntity> getAllUsers() {
        return userEntryRepository.findAll();
    }

    public void saveNewUser(UserEntity user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList("USER"));
        UserEntity saved = userEntryRepository.save(user);
        //return "User Created Successfully";
    }
    public String saveUser(UserEntity user) {

        UserEntity saved = userEntryRepository.save(user);
        return "User Created Successfully";
    }

    public UserEntity getUserByName(String userName) {

        return userEntryRepository.findByUsername(userName);
    }







}
