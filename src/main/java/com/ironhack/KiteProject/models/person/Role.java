package com.ironhack.KiteProject.models.person;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public final class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(columnDefinition = "VARCHAR(255)") //me aseguro que me lo genere como varchar, en vez de un enum
    private ERole name;
}
