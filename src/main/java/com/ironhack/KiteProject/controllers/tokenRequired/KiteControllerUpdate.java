package com.ironhack.KiteProject.controllers.tokenRequired;

import com.ironhack.KiteProject.dto.KiteLocationDTO;
import com.ironhack.KiteProject.dto.KiteWindRequiredDTO;
import com.ironhack.KiteProject.dto.UpdateKiteDTO;
import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.services.KiteService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Kite> updateKite(@PathVariable int id, @RequestBody @Valid UpdateKiteDTO kite){
        try {
            return ResponseEntity.ok(kiteService.updateKite(id, kite));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Mensaje de error", "Esa cometa ya tenia dueño asignado")
                    .build();
        }
    }

    @PatchMapping("/updateLocation/{id}")
    public ResponseEntity<Kite> updateLocationKite(@PathVariable int id, @RequestBody @Valid KiteLocationDTO kite){
        return ResponseEntity.ok(kiteService.updateKite(id, kite));
    }

    @PatchMapping("/updateWindRequired/{id}")
    public ResponseEntity<Kite> updateWindRequiredKite(@PathVariable int id, @RequestBody @Valid KiteWindRequiredDTO kite){
        System.out.println("Modificando el viento requerido...");
        Kite updatedKite = kiteService.updateKite(id, kite);
        System.out.println("Viento requerido modificado");
        return ResponseEntity.ok(updatedKite);
    }



}
