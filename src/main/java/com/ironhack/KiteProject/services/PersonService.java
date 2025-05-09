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

    public Person getPersonByDni(String dni){
        //no hace falta definir un método para buscar por dni, ya que en el modelo, he indicado que el dni juega el papel del ID
        return personRepository.findById(dni).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Person updatePerson(String username, Person person){
        var personToUpdate = personRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        person.setUsername(personToUpdate.getUsername());

        return person;
    }

    public Optional<Person> getByUserName(String username){
        return personRepository.findById(username);
    }

    public boolean passwordIsValid(Person person, String password) {
        return passwordEncoder.matches(password, person.getPassword()); // compara el password hardcodeado "1234" con el encriptado "$2a$....."
    }

    public void deleteUser(Person person){
        personRepository.delete(person);
    }


}
