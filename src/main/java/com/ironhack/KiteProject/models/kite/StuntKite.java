package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;
import lombok.ToString;

@Entity
@DiscriminatorValue("delta")
//esto sirve para que a la hora de imprimir, al utilizar lombock, llame al toString de la clase superior
@ToString(callSuper = true)
public final class StuntKite extends Kite {

    public StuntKite() {
    }

    public StuntKite(@NonNull int windRequired, @NonNull String location) {
        super(windRequired, location);
    }
}
