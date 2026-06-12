package com.edigest.myFirstFullProject.repository;

import com.edigest.myFirstFullProject.Entity.JournalEntry;
import com.edigest.myFirstFullProject.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository <User, ObjectId>{
     User findByUserName (String username);
}
