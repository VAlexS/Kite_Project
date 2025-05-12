package com.ironhack.KiteProject.services;

import com.ironhack.KiteProject.dto.KiteDTO;
import com.ironhack.KiteProject.dto.KiteLocationDTO;
import com.ironhack.KiteProject.dto.KiteWindRequiredDTO;
import com.ironhack.KiteProject.models.kite.*;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.repositories.KiteRepository;
import com.ironhack.KiteProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private boolean authorized;

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

    //sirve para verificar que el que haga la modificación de una cometa sea el dueño.
    private boolean isAuthorized(Kite kiteWithOwner){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        return kiteWithOwner.getOwner().getUsername().equals(authenticatedUsername);
    }

    //este método lo utilizo en el controller para pasarle todo
    public Kite updateKite(int id, KiteDTO kiteDTO){

        Kite kiteToUpdate = kiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //verifico que esta autorizado a realizar la modificacion
        authorized = isAuthorized(kiteToUpdate);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar la cometa de otro usuario.");


        final Person OWNER = kiteToUpdate.getOwner();
        final String OWNER_KITE_DTO = kiteDTO.getUsername();


        if (OWNER != null && !OWNER.getUsername().equals(OWNER_KITE_DTO))
            throw new ResponseStatusException(HttpStatus.CONFLICT);


        kiteToUpdate.setWindRequired(kiteDTO.getWindRequired());
        kiteToUpdate.setLineType(LineType.valueOf(kiteDTO.getLineType()));
        kiteToUpdate.setLocation(kiteDTO.getLocation());


        return kiteRepository.save(kiteToUpdate);
    }

    public Kite updateKite(int id, KiteLocationDTO kiteDTO){
        Kite kiteToUpdate = kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        authorized = isAuthorized(kiteToUpdate);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar la cometa de otro usuario.");

        kiteToUpdate.setLocation(kiteDTO.getLocation());
        return kiteRepository.save(kiteToUpdate);
    }

    public Kite updateKite(int id, KiteWindRequiredDTO kiteDTO){
        Kite kiteToUpdate = kiteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        authorized = isAuthorized(kiteToUpdate);

        if (!authorized)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar la cometa de otro usuario.");

        if (kiteDTO.getWindRequired() < 14 || kiteDTO.getWindRequired() > 40)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        kiteToUpdate.setWindRequired(kiteDTO.getWindRequired());
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
