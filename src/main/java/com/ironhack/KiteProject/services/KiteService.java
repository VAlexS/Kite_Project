package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.dto.KiteDTO;
import com.ironhack.KiteProject.models.kite.*;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.KiteRepository;
import com.ironhack.KiteProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public final class KiteService {

    @Autowired
    private KiteRepository kiteRepository;

    @Autowired
    private PersonRepository personRepository;

    //todo:
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

    public List<Kite> getAllKites(String username, String location){

        if (username != null && location != null) {
            // Buscar cometas por dueño y ubicación simultáneamente
            System.out.println("mostrando todo");
            return kiteRepository.findKitesByOwnerAndLocation(username, location);
        } else if (username != null) {
            // Filtrar solo por dueño
            System.out.println("Mostrando solo el dueño");
            return kiteRepository.findKitesByOwner(username);
        } else if (location != null) {
            // Filtrar solo por ubicación
            System.out.println("mostrando por location");
            return kiteRepository.findKitesByLocation(location);
        }
        // Si no se pasan parámetros, devolver todas las cometas
        return kiteRepository.findAll();
    }

    public Kite getKiteById(int id){
        return kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kite not found"));
    }


    //este método lo utilizo en los test
    public Kite updateKite(int id, Kite kite){
        System.out.println("Modificando la cometa");
        Kite kiteToUpdate = kiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        Person owner = kiteToUpdate.getOwner();

        Person ownerKiteParam = kite.getOwner();

        if (owner != null && !owner.getUsername().equals(ownerKiteParam.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        kiteToUpdate.setOwner(kite.getOwner());

        return kiteRepository.save(kiteToUpdate);
    }

    //este método lo utilizo en el controller
    public Kite updateKite(int id, KiteDTO kiteDTO){

        Kite kiteToUpdate = kiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Person OWNER = kiteToUpdate.getOwner();
        final String OWNER_KITE_DTO = kiteDTO.getUsername();


        if (OWNER != null && !OWNER.getUsername().equals(OWNER_KITE_DTO))
            throw new ResponseStatusException(HttpStatus.CONFLICT);


        kiteToUpdate.setWindRequired(kiteDTO.getWindRequired());
        kiteToUpdate.setLineType(LineType.valueOf(kiteDTO.getLineType()));
        kiteToUpdate.setLocation(kiteDTO.getLocation());


        return kiteRepository.save(kiteToUpdate);
    }

    //todo:
    public List<Kite> getKitesByLocation(String location){
        return kiteRepository.findKitesByLocation(location);
    }

    //todo:
    public List<Kite> getKitesByOwner(String ownerName) {
        return kiteRepository.findKitesByOwner(ownerName);
    }

    //todo:
    public void deleteKite(Kite kite){

        var kiteToDelete = kiteRepository.findById(kite.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        kiteRepository.delete(kiteToDelete);
    }



}
