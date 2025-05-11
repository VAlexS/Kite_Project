package com.ironhack.KiteProject.models.kite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shape")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StaticKite.class, name = "diamond"),
        @JsonSubTypes.Type(value = StuntKite.class, name = "delta"),
        @JsonSubTypes.Type(value = TractionKite.class, name = "parafoil")
})
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

    @NonNull
    private String location; //hace referencia al sitio en el que la persona suele volar la cometa

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner")
    @JsonBackReference //para evitar la redundancia ciclica, ya que la relación es bidireccional
    private Person owner;

}
