package com.ironhack.KiteProject.controllers.adminRequired;

import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class PersonControllerInsDel {

    @Autowired
    private PersonService personService;

    @PostMapping("/addPerson")
    public ResponseEntity<Person> insertPerson(@Valid @RequestBody Person person){
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @DeleteMapping("/deletePerson/{username}")
    public ResponseEntity<Void> deleteKite(@PathVariable String username){
        personService.deletePerson(username);
        return ResponseEntity.ok().build();
    }




}
