package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;
import lombok.ToString;

@Entity
@DiscriminatorValue("diamond")
@ToString(callSuper = true)
public final class StaticKite extends Kite {

    public StaticKite() {
    }


    public StaticKite(@NonNull int windLimit, @NonNull LineType lineType, @NonNull String location) {
        super(windLimit, lineType, location);
    }


}
