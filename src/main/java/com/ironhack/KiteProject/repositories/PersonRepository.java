package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {


}
