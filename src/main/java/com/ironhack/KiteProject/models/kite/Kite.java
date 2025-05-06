package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.*;

@Entity
@Table(name = "kites")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shape")
public abstract class Kite {


}
