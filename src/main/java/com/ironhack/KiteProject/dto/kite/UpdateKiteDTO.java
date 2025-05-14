package com.ironhack.KiteProject.dto.kite;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKiteDTO {

    @NotNull(message = "El viento requerido no puede estar vacio")
    @Min(value = 14, message = "El viento mínimo requerido es 14")
    @Max(value = 40, message = "El viento máximo permitido es 40")
    private int windRequired;

    @NotBlank(message = "Tienes que especificar el dueño")
    private String owner;

    @NotBlank(message = "La ubicación no puede estar vacia")
    private String location;
}
