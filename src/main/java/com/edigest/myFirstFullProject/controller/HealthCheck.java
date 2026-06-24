package com.edigest.myFirstFullProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }


    @GetMapping("/db-name")
    public String getDatabaseName(){
        return mongoTemplate.getDb().getName();
    }
}
