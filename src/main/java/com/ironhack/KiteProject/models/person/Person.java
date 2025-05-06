package com.ironhack.KiteProject.models.person;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ironhack.KiteProject.models.kite.Kite;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private String dni;

    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(columnDefinition = "VARCHAR(255)")
    private Rol rol;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Kite> kites = new ArrayList<>();



}
