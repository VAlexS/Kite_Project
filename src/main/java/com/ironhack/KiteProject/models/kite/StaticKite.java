package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;

@Entity
@DiscriminatorValue("diamond")
public final class StaticKite extends Kite {

    public StaticKite(@NonNull int windLimit, @NonNull LineType lineType, @NonNull String location) {
        super(windLimit, lineType, location);
    }
}
