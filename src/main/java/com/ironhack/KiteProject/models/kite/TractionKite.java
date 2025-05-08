package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;

@Entity
@DiscriminatorValue("parafoil")
public final class TractionKite extends Kite {

    public TractionKite(@NonNull int windLimit, @NonNull LineType lineType, @NonNull String location) {
        super(windLimit, lineType, location);
    }
}
