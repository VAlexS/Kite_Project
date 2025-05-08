package com.ironhack.KiteProject.controllers;

import com.ironhack.KiteProject.models.person.ERole;
import com.ironhack.KiteProject.models.person.Person;
import com.ironhack.KiteProject.services.JwtService;
import com.ironhack.KiteProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PersonService personService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Person user) {
        //todo: arreglar este error
        Optional<Person> optionalUser = personService.getByUserName(user.getUsername());

        // miramos que el user existe
        if (optionalUser.isPresent()) {
            // extraemos el objeto User de dentro del Optional
            Person foundPerson = optionalUser.get();

            // comprobamos si el password es correcto
            if (personService.passwordIsValid(foundPerson, user.getPassword())) {
                // generar un listado con el formato correcto de roles

                // generamos un nuevo array usando el método MAP para devolver en cada elemento del array solamente el nombre del ROLE
                List<ERole> roleNames = foundPerson.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList());


                // una vez vemos que el usuario es correcto, generamos el token
                String token = jwtService.generateToken(foundPerson.getUsername(), roleNames.toString());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login incorrecto");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

    }
}
