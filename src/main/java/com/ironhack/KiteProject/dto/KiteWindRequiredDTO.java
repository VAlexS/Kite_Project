package com.ironhack.KiteProject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KiteWindRequiredDTO {

    @Min(value = 14, message = "El viento mínimo requerido es 14")
    @Max(value = 40, message = "El viento máximo permitido es 40")
    @NotNull(message = "El viento requerido no puede estar vacio")
    private int windRequired;
}
