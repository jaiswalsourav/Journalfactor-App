package com.bigdata.factorapplication.controller;

import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publicController")
public class PublicController {


    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/iwiiw")
    public String  healthCheck() {
        return "OK";
    }

    @PostMapping("/createuser")
    public String addUser(@RequestBody UserEntity user) {

        userEntryService.saveNewUser(user);
        return "User Created Successfully";
    }
}
