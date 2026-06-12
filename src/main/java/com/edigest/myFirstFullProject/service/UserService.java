package com.edigest.myFirstFullProject.service;

import com.edigest.myFirstFullProject.Entity.JournalEntry;
import com.edigest.myFirstFullProject.Entity.User;
import com.edigest.myFirstFullProject.repository.JournalEntryRepository;
import com.edigest.myFirstFullProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. method to save the User
    public void saveEntry(User user){
        userRepository.save(user);
    }


    // 2. method to Get ALL Users
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // 3. method to find User by ID
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }



    // 4. delete the User using the ID
    public void deleteById(ObjectId Id) {
        userRepository.deleteById(Id);
    }

    // 5 method to find user by username
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);

    }
}
