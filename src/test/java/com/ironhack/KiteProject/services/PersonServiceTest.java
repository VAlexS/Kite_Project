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
        person = new Person("06593826W", "hombre_de_la_rae", "CometaOrao2025");
        personService.savePerson(person);
    }

    @Test
    @DisplayName("Genera un token correctamente")
    void generateToken() {
        String token = jwtService.generateToken("John", "[ROLE_ADMIN]");

        System.out.println("======================================");
        System.out.println("ESTO ES EL TOKEN: " + token);
        System.out.println("======================================");
    }

    @Test
    @DisplayName("La encriptación de passwords es correcta")
    public void passwordEncryption() {
        // todas las strings encriptadas con bcrypt usando el algoritmo que estamos usando deberían empezar así
        assertTrue(person.getPassword().startsWith("$2a$"));
        System.out.println("======================================");
        System.out.println("esta es la person final: " + person);
        System.out.println("======================================");
    }




}