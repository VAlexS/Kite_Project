package com.ironhack.KiteProject;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.kite.LineType;
import com.ironhack.KiteProject.models.kite.StuntKite;
import com.ironhack.KiteProject.models.kite.TractionKite;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.models.person.ERole;
import com.ironhack.KiteProject.services.KiteService;
import com.ironhack.KiteProject.services.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KiteProjectApplicationTests {

	/*@Autowired
	KiteService kiteService;

	@Autowired
	PersonService personService;

	@Test
	@DisplayName("Crear una persona en la base de datos")
	void savePerson(){
		Person person = new Person("06593826W", "Victor", ERole.USER, "admin");
		personService.createPerson(person);
	}

	@Test
	@DisplayName("Guardar otra persona")
	void saveOtherPerson(){
		Person person = new Person("65291847F", "Antonio", ERole.USER, "root");
		personService.createPerson(person);
	}

	@Test
	@DisplayName("Ahora una persona va a tener 3 cometas en distintas ubicaciones")
	void save3KitesToPerson(){

		final String DNI = "06593826W";
		final int WIND_LIMIT = 24;

		Kite kite1 = new StuntKite(WIND_LIMIT, LineType.DUAL_LINE, "Madrid");
		kite1.setOwner(personService.getPersonByDni(DNI));

		Kite kite2 = new StuntKite(WIND_LIMIT, LineType.DUAL_LINE, "Caraquiz, Uceda (Guadalajara)");
		kite2.setOwner(personService.getPersonByDni(DNI));

		Kite kite3 = new StuntKite(WIND_LIMIT, LineType.DUAL_LINE, "Torrevieja (Alicante)");
		kite3.setOwner(personService.getPersonByDni(DNI));

		kiteService.createKite(kite1);
		kiteService.createKite(kite2);
		kiteService.createKite(kite3);
	}

	@Test
	@DisplayName("Antonio adquiere 2 cometas de distinto tipo")
	void save2KitesToPerson(){
		final String DNI = "65291847F";

		Kite kite1 = new TractionKite(30, LineType.DUAL_LINE, "Madrid");
		kite1.setOwner(personService.getPersonByDni(DNI));

		Kite kite2 = new StuntKite(24, LineType.DUAL_LINE, "Madrid");
		kite2.setOwner(personService.getPersonByDni(DNI));

		kiteService.createKite(kite1);
		kiteService.createKite(kite2);
	}*/

}
