package com.ironhack.KiteProject.models.kite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ironhack.KiteProject.models.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kites")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shape", discriminatorType = DiscriminatorType.STRING)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "owner")
public abstract class Kite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wind_required")
    @NonNull
    private int windRequired;


    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(columnDefinition = "VARCHAR(255)") //me aseguro que me lo genere como varchar, en vez de un enum
    private LineType lineType;

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonBackReference //para evitar la redundancia ciclica, ya que la relación es bidireccional
    private Person owner;








}
