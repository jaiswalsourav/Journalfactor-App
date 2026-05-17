package com.bigdata.factorapplication.service;

import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryService {


    @Autowired
    UserEntryRepository userEntryRepository;



    public List<UserEntity> getAllUsers() {
        return userEntryRepository.findAll();
    }

    public String saveUser(UserEntity user) {

        UserEntity saved = userEntryRepository.save(user);
        return "User Created Successfully";
    }

    public UserEntity getUserByName(String userName) {

        return userEntryRepository.findByUsername(userName);
    }







}
