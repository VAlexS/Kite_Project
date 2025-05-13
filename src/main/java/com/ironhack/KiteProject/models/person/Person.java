package com.ironhack.KiteProject.models.person;

import com.ironhack.KiteProject.models.kite.Kite;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public final class Person {

    @Id
    @NonNull
    @NotBlank(message = "hay que introducir un username")
    private String username; //el username es unico




    @NonNull
    @NotBlank(message = "tienes que meter una password")
    private String password;


    @OneToMany(mappedBy = "owner", fetch = EAGER)
    private List<Kite> kites = new ArrayList<>();


    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
