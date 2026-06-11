package com.edigest.myFirstFullProject.controller;

import com.edigest.myFirstFullProject.Entity.JournalEntry;
import com.edigest.myFirstFullProject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;



    // 1. get all the entries
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }


    // 2. post New created entry
    @PostMapping
    public JournalEntry  createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    // 3. get the entry by using ID
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null) ;
    }
    // 4. Delete the entry using ID
    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }


    // update the Entry using Id
    @PutMapping("id/{Id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry ) {
        JournalEntry old = journalEntryService.findById(Id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

}
