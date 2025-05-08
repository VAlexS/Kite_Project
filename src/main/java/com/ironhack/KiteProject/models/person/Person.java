package com.ironhack.KiteProject.models.person;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ironhack.KiteProject.models.kite.Kite;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public final class Person {


    @Id
    @NonNull
    private String dni;

    @NonNull
    private String name;


    //todo: la password hay que guardarla encriptada en la base de datos
    @NonNull
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Kite> kites = new ArrayList<>();

}
