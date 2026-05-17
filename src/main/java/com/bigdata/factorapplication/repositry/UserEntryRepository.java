package com.bigdata.factorapplication.repositry;

import com.bigdata.factorapplication.entity.JournalEntity;
import com.bigdata.factorapplication.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository <UserEntity, ObjectId> {

    UserEntity findByUsername(String username);
}
