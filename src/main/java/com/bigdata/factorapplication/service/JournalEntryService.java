package com.bigdata.factorapplication.service;

import com.bigdata.factorapplication.entity.JournalEntity;
import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntity   journalEntity,String username) {

        UserEntity user = userEntryService.getUserByName(username);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepository.save(journalEntity);
        user.getJournalEntities().add(saved);
        userEntryService.saveUser(user);

        System.out.println(saved.getId());
    }
    public void saveEntry(JournalEntity   journalEntity) {


        JournalEntity saved = journalEntryRepository.save(journalEntity);

        System.out.println(saved.getId());
    }

    public List<JournalEntity> getAll() {

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getById(ObjectId myId) {

        return journalEntryRepository.findById(myId);
    }
    public JournalEntity deleteByID(ObjectId myId, String username) {

        //2. Delete it
        UserEntity user = userEntryService.getUserByName(username);

        user.getJournalEntities().removeIf(x -> x.getId().equals(myId));
        userEntryService.saveUser(user);
        JournalEntity checKEntry = journalEntryRepository.findById(myId).orElse(null);
        if (checKEntry != null) {
            journalEntryRepository.deleteById(myId);


        }

// 3. Return the object we found
        return checKEntry;
    }





}
