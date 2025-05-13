package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.dto.*;
import com.ironhack.KiteProject.models.kite.*;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class KiteService {

    @Autowired
    private KiteRepository kiteRepository;

    @Autowired
    private PersonRepository personRepository;

    private boolean authorized;

    //este método es para probarlo en los test
    public Kite saveKite(Kite kite){



        //si esa cometa tiene dueño asignado, valido que exista en la base de datos
        if (kite.getOwner() != null){
            Optional<Person> ownerKite = personRepository.findById(kite.getOwner().getUsername());

            if (ownerKite.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return kiteRepository.save(kite);
    }

    public Kite saveKite(KiteDTO kiteDTO){

        final String SHAPE = kiteDTO.getShape().toLowerCase();

        final String USERNAME_OWNER = kiteDTO.getOwner();

        Optional<Person> ownerKite = Optional.empty();

        if (USERNAME_OWNER != null){
            ownerKite = personRepository.findById(USERNAME_OWNER);

            if (ownerKite.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Kite kite;

        switch (SHAPE){
            case "diamond":
                kite = new StaticKite(kiteDTO.getWindRequired(), kiteDTO.getLocation());
                break;
            case "delta":
                kite = new StuntKite(kiteDTO.getWindRequired(), kiteDTO.getLocation());
                break;
            case "parafoil":
                kite = new TractionKite(kiteDTO.getWindRequired(), kiteDTO.getLocation());
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ownerKite.isPresent())
            kite.setOwner(ownerKite.get());

        return kiteRepository.save(kite); //aqui es donde me lleva a la excepcion
    }

    public List<Kite> getAllKites(String username, String location){

        if (username != null && location != null)
            return kiteRepository.findKitesByOwnerAndLocation(username, location);

        if (username != null)
            return kiteRepository.findKitesByOwner(username);

        if (location != null)
            return kiteRepository.findKitesByLocation(location);


        return kiteRepository.findAll();
    }

    public Kite getKiteById(int id){
        return kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kite not found"));
    }


    //sirve para verificar que el que haga la modificación de una cometa sea el dueño.
    private boolean isAuthorized(Kite kiteWithOwner){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        return kiteWithOwner.getOwner().getUsername().equals(authenticatedUsername);
    }


    public Kite updateKite(int id, UpdateKiteDTO kiteDTO){

        Kite foundKite = kiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //verifico que esta autorizado a realizar la modificacion
        authorized = isAuthorized(foundKite);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No puedes modificar la cometa de otro usuario.");


        final Person OWNER = foundKite.getOwner();
        final String OWNER_KITE_DTO = kiteDTO.getOwner();


        if (OWNER != null && !OWNER.getUsername().equals(OWNER_KITE_DTO))
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        Optional<Person> foundOwner = personRepository.findById(OWNER_KITE_DTO);

        if (foundOwner.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        foundKite.setWindRequired(kiteDTO.getWindRequired());
        foundKite.setOwner(foundOwner.get());
        foundKite.setLocation(kiteDTO.getLocation());

        return kiteRepository.save(foundKite);
    }

    public Kite updateKite(int id, KiteLocationDTO kiteDTO){
        Kite kiteToUpdate = kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        authorized = isAuthorized(kiteToUpdate);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No puedes modificar la cometa de otro usuario.");

        kiteToUpdate.setLocation(kiteDTO.getLocation());
        return kiteRepository.save(kiteToUpdate);
    }

    public Kite updateKite(int id, KiteWindRequiredDTO kiteDTO){
        Kite kiteToUpdate = kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        authorized = isAuthorized(kiteToUpdate);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No puedes modificar la cometa de otro usuario.");



        kiteToUpdate.setWindRequired(kiteDTO.getWindRequired());
        return kiteRepository.save(kiteToUpdate);
    }

    //este método solo lo utilizo en los test
    public List<Kite> getKitesByLocation(String location){
        return kiteRepository.findKitesByLocation(location);
    }

    //este método solo lo utilizo en los test
    public List<Kite> getKitesByOwner(String ownerName) {
        return kiteRepository.findKitesByOwner(ownerName);
    }

    //todo:
    public void deleteKite(Kite kite){

        var kiteToDelete = kiteRepository.findById(kite.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        kiteRepository.delete(kiteToDelete);
    }



}
