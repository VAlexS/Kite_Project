package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.kite.LineType;
import com.ironhack.KiteProject.models.kite.StuntKite;
import com.ironhack.KiteProject.models.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Kite kite1;

        Optional<Person> personToAssign = personService.getByUserName("adrian");

        assertTrue(personToAssign.isPresent());

        System.out.println("Owner antes de guardar: " + personToAssign.get());

        Person ownerKite = personToAssign.get();

        kite1 = new StuntKite();
        kite1.setWindRequired(24);
        kite1.setLineType(LineType.DUAL_LINE);
        kite1.setLocation("Madrid");
        kite1.setOwner(ownerKite);





        System.out.println("===========================");
        System.out.println("Estas con las cometas que vas a añadir");
        System.out.println("Cometa 1 = "+kite1);
        System.out.println("===========================");

        kiteService.saveKite(kite1);

        List<Kite> hisKites = kiteService.getKitesByOwner("adrian");
        System.out.println("Clase del primer objeto recuperado: " + hisKites.get(0).getClass().getName());

        Kite kite = hisKites.get(0);
        System.out.println("ID: " + kite.getId());
        System.out.println("Ubicación: " + kite.getLocation());
        System.out.println("Tipo de línea: " + kite.getLineType());
        System.out.println("Owner: " + (kite.getOwner() != null ? kite.getOwner().getUsername() : "Sin dueño"));

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
        for (Kite kite : hisKites) {
            System.out.println("Cometa encontrada - ID: " + kite.getId() + ", Ubicación: " + kite.getLocation() + ", Tipo de línea: " + kite.getLineType() + ", Dueño: " + (kite.getOwner() != null ? kite.getOwner().getUsername() : "Sin dueño"));
        }
        System.out.println("==============================");

    }

    @Test
    @DisplayName("Busco todas las cometas que hay en madrid")
    void findKitesByLocation(){
        List<Kite> kitesMadrid = kiteService.getKitesByLocation("Madrid");

        System.out.println("=======================");
        System.out.println("Las cometas en Madrid");
        for (Kite kite : kitesMadrid) {
            System.out.println("Cometa encontrada - ID: " + kite.getId() + ", Ubicación: " + kite.getLocation() + ", Tipo de línea: " + kite.getLineType() + ", Dueño: " + (kite.getOwner() != null ? kite.getOwner().getUsername() : "Sin dueño"));
        }
        System.out.println("==============================");
    }



}