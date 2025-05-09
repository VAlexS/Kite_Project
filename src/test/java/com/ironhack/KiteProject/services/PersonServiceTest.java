package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

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
        person = new Person("auronplay", "abcd1234");
        System.out.println("=================");
        System.out.println("Persona inicial: ");
        System.out.println(person);
        System.out.println("=================");
        personService.savePerson(person);
    }

    @Test
    @DisplayName("Genera un token correctamente")
    void generateToken() {
        String token = jwtService.generateToken("auronplay", "[ROLE_USER]");

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


    //para probar este test, comentar el método que tiene el beforeEach para no añadir el usuario 2 veces
    @Test
    @DisplayName("Crear un usuario que ya existe en la base de datos")
    void duplicateUser(){
        person = new Person("hombre_de_la_rae", "admin");

        assertThrows(ResponseStatusException.class, () -> personService.savePerson(person));
    }




}