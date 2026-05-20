package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.JournalEntity;
import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.service.JournalEntryService;
import com.bigdata.factorapplication.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserEntryService userEntryService;

   @GetMapping
   public  ResponseEntity<?> getJournalEntriesAll() {

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();
       UserEntity user = userEntryService.getUserByName(username);
      if (user == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      List<JournalEntity> all=user.getJournalEntities();
       System.out.println("API HIT GET CALL");

      if(all == null){
          return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
      }

      return new ResponseEntity<>(all, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<JournalEntity>  createEntry(@RequestBody JournalEntity newJournalEntry) {

        

       try{

           System.out.println("API HIT");
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String username = authentication.getName();
           journalEntryService.saveNewEntry(newJournalEntry,username);
           System.out.println("API HIT after save entry");
           return new ResponseEntity<>(newJournalEntry,HttpStatus.CREATED);
       }
       catch (Exception e) {

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }


    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntryService.getUserByName(username);
        if (user == null || user.getJournalEntities().stream().noneMatch(journal -> journal.getId().equals(myId))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<JournalEntity> journalEntity= journalEntryService.getById(myId);
        if(journalEntity.isPresent())  {return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);}

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> deleteJournalEntryById(@PathVariable ObjectId myId) {
        System.out.println("DELETE API  HIT");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
         journalEntryService.deleteByID(myId,username);

       /* if (deletedEntry != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }*/

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> updateEntry(@PathVariable ObjectId myId,
                                                     @RequestBody JournalEntity newJournalEntry) {

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();

       UserEntity user = userEntryService.getUserByName(username);
       if (user == null || user.getJournalEntities().stream().noneMatch(journal -> journal.getId().equals(myId))) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       JournalEntity oldEntry = journalEntryService.getById(myId).orElse(null);

       if(oldEntry != null) {
           oldEntry.setTitle(newJournalEntry.getTitle()!=null ? newJournalEntry.getTitle():oldEntry.getTitle());
           oldEntry.setContent(newJournalEntry.getContent()!=null ? newJournalEntry.getContent():oldEntry.getContent());
           journalEntryService.saveEntry(oldEntry);
           return new ResponseEntity<>(oldEntry,HttpStatus.OK);

       }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }





}
