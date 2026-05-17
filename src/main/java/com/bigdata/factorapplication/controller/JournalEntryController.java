package com.bigdata.factorapplication.controller;


import com.bigdata.factorapplication.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

   private Map<Long,JournalEntity> journalEntries=new HashMap<>();


   @GetMapping("/valueAll")
   public List<JournalEntity> getJournalEntries() {


    return new ArrayList<>(journalEntries.values());
    }
    @PostMapping("/postValue")
    public Boolean createEntry(@RequestBody JournalEntity newJournalEntry) {

        journalEntries.put(newJournalEntry.getId(), newJournalEntry);
        return true;

    }

    @GetMapping("/id/{myId}")
    public JournalEntity getJournalEntryById(@PathVariable Long myId) {
       return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntity deleteJournalEntryById(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }
    @PutMapping("/id/{myId}")
    public JournalEntity updateEntry(@PathVariable Long myId, @RequestBody JournalEntity newJournalEntry) {
       journalEntries.put(myId, newJournalEntry);
       return newJournalEntry;
    }





}

 */
