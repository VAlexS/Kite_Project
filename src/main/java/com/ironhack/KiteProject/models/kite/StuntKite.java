package com.ironhack.KiteProject.models.kite;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@DiscriminatorValue("delta")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public final class StuntKite extends Kite {

}
