package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.kite.LineType;
import com.ironhack.KiteProject.models.kite.StuntKite;
import com.ironhack.KiteProject.models.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Person ownerKite = personToAssign.get();

        kite1 = new StuntKite(24, LineType.DUAL_LINE, "Madrid");
        kite1.setOwner(ownerKite);

        kite2 = new StuntKite(24, LineType.DUAL_LINE, "Caraquiz, Uceda (Guadalajara)");
        kite2.setOwner(ownerKite);

        kite3 = new StuntKite(24, LineType.DUAL_LINE, "Torrevieja (Alicante)");
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

}