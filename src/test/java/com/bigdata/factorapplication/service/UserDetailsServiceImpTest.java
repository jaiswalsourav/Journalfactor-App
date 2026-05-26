package com.bigdata.factorapplication.service;

import com.bigdata.factorapplication.entity.UserEntity;
import com.bigdata.factorapplication.repositry.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
//@SpringBootTest(classes = UserDetailsServiceImpTest.class)
public class UserDetailsServiceImpTest {



    //@Autowired
    @InjectMocks
    UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private UserEntryRepository userEntryRepository;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTest()
    {
        UserEntity user = new UserEntity();

        user.setUsername("ram");
        user.setPassword("srfwewrt");
        user.setRoles(Arrays.asList("USER"));

        when(userEntryRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(user);

        UserDetails userDetails =
                userDetailsServiceImp.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }
}
