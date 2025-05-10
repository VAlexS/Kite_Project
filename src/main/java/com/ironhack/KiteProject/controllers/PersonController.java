package com.ironhack.KiteProject.controllers;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        return ResponseEntity.ok(personService.getAllPerson());
    }
}
