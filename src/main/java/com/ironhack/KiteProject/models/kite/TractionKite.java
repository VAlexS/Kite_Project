package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;
import lombok.ToString;

@Entity
@DiscriminatorValue("parafoil")
@ToString(callSuper = true)
public final class TractionKite extends Kite {

    public TractionKite() {
    }

    public TractionKite(@NonNull int windLimit, @NonNull String location) {
        super(windLimit, location);
    }
}
