package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.JournalEntity;
import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.service.JournalEntryService;
import com.bigdata.factorapplication.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserEntryService userEntryService;

   @GetMapping("{username}")
   public  ResponseEntity<?> getJournalEntriesAll(@PathVariable String username) {

       UserEntity user = userEntryService.getUserByName(username);
      List<JournalEntity> all=user.getJournalEntities();
       System.out.println("API HIT GET CALL");

      if(all != null && !all.isEmpty()){

          return new ResponseEntity<>(all, HttpStatus.OK);
      }


         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntity>  createEntry(@RequestBody JournalEntity newJournalEntry,@PathVariable String username) {

        

       try{

           System.out.println("API HIT");

           journalEntryService.saveEntry(newJournalEntry,username);

           return new ResponseEntity<>(newJournalEntry,HttpStatus.CREATED);
       }
       catch (Exception e) {

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }


    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myId) {

        Optional<JournalEntity> journalEntity= journalEntryService.getById(myId);
        if(journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(),HttpStatus.OK);
        }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<JournalEntity> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String username) {

        JournalEntity deletedEntry = journalEntryService.deleteByID(myId,username);
        if (deletedEntry != null) {
            return new ResponseEntity<>(deletedEntry, HttpStatus.OK);
        }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<JournalEntity> updateEntry(@PathVariable ObjectId myId,
                                                     @RequestBody JournalEntity newJournalEntry,
                                                     @PathVariable String username) {

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
