package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KiteRepository extends JpaRepository<Kite, Integer> {

    List<Kite> findKitesByLocation(String location);


    List<Kite> findKitesByOwner(Person owner);



}
