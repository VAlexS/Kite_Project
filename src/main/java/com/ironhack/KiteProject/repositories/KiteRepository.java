package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.kite.Kite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KiteRepository extends JpaRepository<Kite, Integer> {

    List<Kite> findKitesByLocation(String location);


    /*@Transactional
    //@Query("SELECT k FROM Kite k WHERE k.owner.username = :ownerName")
    @Query("SELECT k FROM Kite k JOIN FETCH k.owner WHERE k.owner.username = :ownerName")
    List<Kite> findKitesByOwner(@Param("ownerName") String ownerName);*/

    @Query("SELECT k FROM Kite k JOIN FETCH k.owner WHERE k.owner.username = :ownerName")
    List<Kite> findKitesByOwner(@Param("ownerName") String ownerName);



}
