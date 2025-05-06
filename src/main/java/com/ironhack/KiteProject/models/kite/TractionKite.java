package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("parafoil")
public class TractionKite extends Kite {
}
