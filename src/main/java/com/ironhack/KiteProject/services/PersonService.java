package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Person createPerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person getPersonByDni(String dni){
        //no hace falta definir un método para buscar por dni, ya que en el modelo, he indicado que el dni juega el papel del ID
        return personRepository.findById(dni).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Person updatePerson(String dni, Person person){
        var personToUpdate = personRepository.findById(dni).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        person.setDni(personToUpdate.getDni());

        return person;
    }


}
