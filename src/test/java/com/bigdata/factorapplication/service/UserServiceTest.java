package com.bigdata.factorapplication.service;

import com.bigdata.factorapplication.entity.JournalEntity;
import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Test
    public void testFindUserByName() {

        UserEntity user = userEntryRepository.findByUsername("sourav11");

        assertNotNull(user);

        
    }
}
