package com.ironhack.KiteProject.repositories;

import com.ironhack.KiteProject.models.kite.Kite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KiteRepository extends JpaRepository<Kite, Integer> {
}
