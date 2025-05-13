package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;
import lombok.ToString;

@Entity
@DiscriminatorValue("diamond")
@ToString(callSuper = true) //esto sirve para que a la hora de imprimir, al utilizar lombock, llame al toString de la clase superior
public final class StaticKite extends Kite {

    public StaticKite() {
    }


    public StaticKite(@NonNull int windLimit, @NonNull String location) {
        super(windLimit, location);
    }


}
