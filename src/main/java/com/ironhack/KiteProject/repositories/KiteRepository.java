package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.kite.Kite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KiteRepository extends JpaRepository<Kite, Integer> {

    List<Kite> findKitesByLocation(String location);


    @Query(value = "SELECT * FROM kites WHERE owner = :ownerName", nativeQuery = true)
    List<Kite> findKitesByOwner(@Param("ownerName") String ownerName);



}
