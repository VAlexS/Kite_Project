package com.ironhack.KiteProject.controllers.passwordRequired;

import com.ironhack.KiteProject.dto.KiteDTO;
import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.services.KiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/update/kite/")
public class KiteControllerUpdate {

    @Autowired
    private KiteService kiteService;



    @PutMapping("/updateAllFields/{id}")
    public ResponseEntity<Kite> updateKite(@PathVariable int id, @RequestBody KiteDTO kite){
        try {
            Kite updatedKite = kiteService.updateKite(id, kite);
            return ResponseEntity.ok(updatedKite);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Mensaje de error", "Esa cometa ya tenia dueño asignado")
                    .build();
        }
    }



}
