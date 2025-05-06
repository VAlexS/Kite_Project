package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("diamond")
public class StaticKite extends Kite {
}
