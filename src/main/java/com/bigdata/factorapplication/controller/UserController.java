package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usercheck")
public class UserController {

@Autowired
    UserEntryService userEntryService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {

        return userEntryService.getAllUsers();
    }

    @PostMapping
    public String addUser(@RequestBody UserEntity user) {

        userEntryService.saveUser(user);
        return "User Created Successfully";
    }

     @PutMapping
     public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {

       UserEntity oldUser= userEntryService.getUserByName(user.getUsername());
       if(oldUser!=null){
           oldUser.setUsername(user.getUsername());
           oldUser.setPassword(user.getPassword());
           userEntryService.saveUser(oldUser);
           return  new ResponseEntity<>(HttpStatus.OK);
       }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }



}
