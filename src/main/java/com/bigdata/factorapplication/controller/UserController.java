package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.UserEntryRepository;
import com.bigdata.factorapplication.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usercheck")
public class UserController {

    @Autowired
    UserEntryService userEntryService;
    @Autowired
    UserEntryRepository userEntryRepository;

   /* @GetMapping("/all")
    public List<UserEntity> getAllUsers() {

        return userEntryService.getAllUsers();
    }*/

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) {

        if (userEntryService.getUserByName(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        userEntryService.saveNewUser(user);
        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

     @PutMapping
     public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String username = authentication.getName();
            System.out.println("dwiiwiiw");
       UserEntity oldUser= userEntryService.getUserByName(user.getUsername());
       if(oldUser!=null){
           oldUser.setUsername(user.getUsername());
           oldUser.setPassword(user.getPassword());
           userEntryService.saveNewUser(oldUser);
           return  new ResponseEntity<>(HttpStatus.OK);
       }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     @DeleteMapping
     public ResponseEntity<?> deleteUser(@RequestBody UserEntity user) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         userEntryRepository.deleteByUsername(username);

         return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }



}
