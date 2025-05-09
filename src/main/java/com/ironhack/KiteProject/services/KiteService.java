package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.KiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class KiteService {

    @Autowired
    private KiteRepository kiteRepository;

    public Kite saveKite(Kite kite){
        return kiteRepository.save(kite);
    }

    public List<Kite> getAllKites(){
        return kiteRepository.findAll();
    }

    public Kite getKiteById(int id){
        return kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kite not found"));
    }

    public Kite updateKite(int id, Kite kite){
        var kiteToUpdate = kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        kite.setId(kiteToUpdate.getId());
        kite.setOwner(kiteToUpdate.getOwner());

        return kiteToUpdate;
    }

    public List<Kite> getKitesByLocation(String location){
        return kiteRepository.findKitesByLocation(location);
    }

    public List<Kite> getKitesByOwner(Person owner){
        return kiteRepository.findKitesByOwner(owner);
    }





}
