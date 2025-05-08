package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    JwtService jwtService;

    Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setUsername("hombre_de_la_rae");
        person.setPassword("CometaOrao2025");
        System.out.println("El usuario inicial es: " + person);

        personService.savePerson(person);
    }

    @Test
    @DisplayName("La encriptación de passwords es correcta")
    public void passwordEncryption() {
        assertTrue(person.getPassword().startsWith("$2a$")); // todas las strings encriptadas con bcrypt usando el algoritmo que estamos usando deberían empezar así
        System.out.println("esta es la person final: " + person);
    }




}