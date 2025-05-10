package com.ironhack.KiteProject.controllers;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.services.KiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/")
public class KiteController {

    @Autowired
    private KiteService kiteService;

    @GetMapping("/kites")
    public ResponseEntity<List<Kite>> getAllKites(@RequestParam(required = false) String owner,
                                                  @RequestParam(required = false) String location){

        return ResponseEntity.ok(kiteService.getAllKites(owner, location));
    }

    @GetMapping("/kite/{id}")
    public ResponseEntity<Kite> getKiteById(@PathVariable int id){
        try {
            Kite kite = kiteService.getKiteById(id);
            return ResponseEntity.ok(kite);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Mensaje de error", "Cometa no encontrada") // Agregamos el mensaje en los headers
                    .build();
        }
    }









}
