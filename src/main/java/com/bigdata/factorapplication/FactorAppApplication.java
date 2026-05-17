package com.bigdata.factorapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FactorAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(FactorAppApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory mongoDatabaseFactory) {

        return new MongoTransactionManager(mongoDatabaseFactory);

    }

}
