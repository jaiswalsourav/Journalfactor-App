package com.bigdata.factorapplication.repositry;

import com.bigdata.factorapplication.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository <JournalEntity, ObjectId> {
}
