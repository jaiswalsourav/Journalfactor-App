package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminuser")
public class AdminController {


    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/alluser")
    public ResponseEntity<?> getAllUser(){
       System.out.println("alluser API HIT ");
        List<UserEntity> all=userEntryService.getAll();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String createAdminuser(@RequestBody UserEntity user){

        userEntryService.saveNewAdminUser(user);
        return "User Created Successfully";
    }


}
