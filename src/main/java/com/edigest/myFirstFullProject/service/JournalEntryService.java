package com.edigest.myFirstFullProject.service;

import com.edigest.myFirstFullProject.Entity.JournalEntry;
import com.edigest.myFirstFullProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // 1. method to save the JournalEntry
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }


    // 2. method to Get ALL Journal Entries
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    // 3. method to find journal entry by ID
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }



    // 4. delete the entry using the ID
    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);

    }
}
