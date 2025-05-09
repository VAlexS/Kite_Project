package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.models.kite.Kite;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.KiteRepository;
import com.ironhack.KiteProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public final class KiteService {

    @Autowired
    private KiteRepository kiteRepository;

    @Autowired
    private PersonRepository personRepository;

    public Kite saveKite(Kite kite){

        //valido que el viento requerido sea valido
        if (kite.getWindRequired() < 14 || kite.getWindRequired() > 40)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //si esa cometa tiene dueño asignado, valido que exista en la base de datos
        if (kite.getOwner() != null){
            Optional<Person> ownerKite = personRepository.findById(kite.getOwner().getUsername());

            if (ownerKite.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return kiteRepository.save(kite);
    }

    public List<Kite> getAllKites(){
        return kiteRepository.findAll();
    }

    public Kite getKiteById(int id){
        return kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kite not found"));
    }

    public Kite updateKite(int id, Kite kite){


        Kite kiteToUpdate = kiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        kiteToUpdate.setOwner(kite.getOwner());

        return kiteRepository.save(kiteToUpdate);
    }

    public List<Kite> getKitesByLocation(String location){
        return kiteRepository.findKitesByLocation(location);
    }

    public List<Kite> getKitesByOwner(String ownerName) {
        return kiteRepository.findKitesByOwner(ownerName);
    }

    public void deleteKite(Kite kite){

        var kiteToDelete = kiteRepository.findById(kite.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        kiteRepository.delete(kiteToDelete);
    }



}
