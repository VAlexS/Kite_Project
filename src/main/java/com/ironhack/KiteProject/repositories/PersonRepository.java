package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {


}
