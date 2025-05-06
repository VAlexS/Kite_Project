package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("delta")
public class StuntKite extends Kite {
}
