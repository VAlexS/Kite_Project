package com.ironhack.KiteProject.controllers.publicRoutes;

import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        return ResponseEntity.ok(personService.getAllPerson());
    }

    @GetMapping("/person/{username}")
    public ResponseEntity<Optional<Person>> getKiteById(@PathVariable String username){

        try {
            Optional<Person> person = personService.getByUserName(username);
            return ResponseEntity.ok(person);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Mensaje de error", "Persona no encontrada") // Agregamos el mensaje en los headers
                    .build();
        }
    }
}
