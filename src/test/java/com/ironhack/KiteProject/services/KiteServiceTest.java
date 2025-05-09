package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.kite.LineType;
import com.ironhack.KiteProject.models.kite.StuntKite;
import com.ironhack.KiteProject.models.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KiteServiceTest {

    @Autowired
    KiteService kiteService;

    @Autowired
    PersonService personService;


    @Test
    @DisplayName("Asigno 3 cometas acrobáticas para hombre_de_la_rae")
    void setUpStuntKite(){

        Kite kite1, kite2, kite3;

        Optional<Person> personToAssign = personService.getByUserName("hombre_de_la_rae");

        assertTrue(personToAssign.isPresent());

        System.out.println("Owner antes de guardar: " + personToAssign.get());

        Person ownerKite = personToAssign.get();

        kite1 = new StuntKite();
        kite1.setWindRequired(24);
        kite1.setLineType(LineType.DUAL_LINE);
        kite1.setLocation("Madrid");
        kite1.setOwner(ownerKite);

        kite2 = new StuntKite();
        kite2.setWindRequired(24);
        kite2.setLineType(LineType.DUAL_LINE);
        kite2.setLocation("Caraquiz, Uceda (Guadalajara)");
        kite2.setOwner(ownerKite);

        kite3 = new StuntKite();
        kite3.setWindRequired(24);
        kite3.setLineType(LineType.DUAL_LINE);
        kite3.setLocation("Torrevieja (Alicante)");
        kite3.setOwner(ownerKite);




        System.out.println("===========================");
        System.out.println("Estas con las cometas que vas a añadir");
        System.out.println("Cometa 1 = "+kite1);
        System.out.println("Cometa 2 = "+kite2);
        System.out.println("Cometa 3 = "+kite3);
        System.out.println("===========================");

        kiteService.saveKite(kite1);
        kiteService.saveKite(kite2);
        kiteService.saveKite(kite3);

    }

    //todo: investigar por qué falla este test
    @Test
    @DisplayName("Busco las cometas que tiene hombre_de_la_rae")
    void findKiteByOwner(){
        Optional<Person> foundPerson = personService.getByUserName("hombre_de_la_rae");

        assertTrue(foundPerson.isPresent());

        Person owner = foundPerson.get();

        List<Kite> hisKites = kiteService.getKitesByOwner(owner.getUsername());

        System.out.println("=======================");
        System.out.println("Las cometas de "+owner.getUsername());
        System.out.println(hisKites);
        System.out.println("==============================");

    }

    @Test
    @DisplayName("Busco todas las cometas que hay en madrid")
    void findKitesByLocation(){
        List<Kite> kitesMadrid = kiteService.getKitesByLocation("Madrid");

        System.out.println("=======================");
        System.out.println("Las cometas en Madrid");
        System.out.println(kitesMadrid);
        System.out.println("==============================");
    }

    @Test
    @DisplayName("Crear una cometa con un viento requerido fuera de rango")
    void setUpKiteWithImpossibleWindRequired(){
        Kite kite = new StuntKite();
        kite.setWindRequired(8);
        kite.setLineType(LineType.DUAL_LINE);
        kite.setLocation("Segovia");

        assertThrows(ResponseStatusException.class, () -> kiteService.saveKite(kite));

    }

    @Test
    @DisplayName("Crear una cometa con un propietario que no existe en la base de datos")
    void saveKiteWithUnexistingOwner(){
        Kite kite = new StuntKite();

        Person person = new Person("adrian", "key");

        kite.setWindRequired(18);
        kite.setLineType(LineType.DUAL_LINE);
        kite.setLocation("Alcala de Henares, (Madrid)");
        kite.setOwner(person);

        assertThrows(ResponseStatusException.class, () -> kiteService.saveKite(kite));
    }

    @Test
    @DisplayName("Crear una cometa sin dueño asignado")
    void saveKiteWithoutOwner(){
        Kite kite = new StuntKite();
        kite.setWindRequired(20);
        kite.setLocation("Burgos");
        kite.setLineType(LineType.DUAL_LINE);

        kiteService.saveKite(kite);
    }


    @Test
    @DisplayName("Asignar a una persona una cometa ya existente")
    void aPersonAdquireKite(){
        Optional<Person> foundPerson = personService.getByUserName("auronplay");

        assertTrue(foundPerson.isPresent());

        Person person = foundPerson.get();

        Kite kite = kiteService.getKiteById(4);

        assertNull(kite.getOwner());

        kite.setOwner(person);

        kiteService.updateKite(4, kite);
    }

    @Test
    @DisplayName("Asigno una cometa con dueño a otra persona, debe de lanzar un error")
    void aPersonAdquireUnexistingKite(){
        Kite kite = kiteService.getKiteById(1);

        Optional<Person> foundPerson = personService.getByUserName("auronplay");

        assertTrue(foundPerson.isPresent());

        kite.setOwner(foundPerson.get());

        assertThrows(ResponseStatusException.class, () -> kiteService.updateKite(kite.getId(), kite));



    }



}