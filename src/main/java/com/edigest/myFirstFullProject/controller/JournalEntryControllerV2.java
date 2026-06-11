package com.edigest.myFirstFullProject.controller;

import com.edigest.myFirstFullProject.Entity.JournalEntry;
import com.edigest.myFirstFullProject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;



    // 1. get all the entries
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // 2. post New created entry
    @PostMapping
    public ResponseEntity<JournalEntry>   createEntry(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // 3. get the entry by using ID
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 4. Delete the entry using ID
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // update the Entry using Id
    @PutMapping("id/{Id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry ) {
        JournalEntry old = journalEntryService.findById(Id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
