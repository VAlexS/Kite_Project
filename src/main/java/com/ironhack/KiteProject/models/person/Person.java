package com.ironhack.KiteProject.models.person;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ironhack.KiteProject.models.kite.Kite;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "persons")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class Person {

    @Id
    @NonNull
    private String username; //el username es unico




    @NonNull
    private String password;


    @OneToMany(mappedBy = "owner", fetch = EAGER)
    @JsonManagedReference
    private List<Kite> kites = new ArrayList<>();


    @ManyToMany(fetch = EAGER) // to load roles when loading the user
    private Collection<Role> roles = new ArrayList<>();



}
