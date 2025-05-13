package com.ironhack.KiteProject.controllers.adminRequired;

import com.ironhack.KiteProject.dto.KiteDTO;
import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.services.KiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class KitesControllerInsert {

    @Autowired
    private KiteService kiteService;

    @PostMapping("/addKite")
    public ResponseEntity<Kite> addKite(@Valid @RequestBody KiteDTO kite) {
        return ResponseEntity.ok(kiteService.saveKite(kite));
    }
}
