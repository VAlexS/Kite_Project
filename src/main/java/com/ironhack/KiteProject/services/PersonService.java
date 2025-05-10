package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public final class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public Person savePerson(Person person){

        //valido que el username no se repita
        Optional<Person> personToCheck = personRepository.findById(person.getUsername());

        if (personToCheck.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }


    public Person updatePerson(String username, Person person){
        Person personToUpdate = personRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        person.setUsername(personToUpdate.getUsername());

        return person;
    }

    public Optional<Person> getByUserName(String username){
        Optional<Person> foundPerson = personRepository.findById(username);

        if (foundPerson.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return foundPerson;
    }

    public boolean passwordIsValid(Person person, String password) {
        return passwordEncoder.matches(password, person.getPassword()); // compara el password hardcodeado  con el encriptado
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }


}
