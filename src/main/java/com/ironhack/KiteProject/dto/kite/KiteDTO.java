package com.ironhack.KiteProject.dto.kite;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KiteDTO {

    @NotBlank(message = "La forma para identificar el tipo no puede estar vacía")
    @Pattern(regexp = "diamond|delta|parafoil", message = "El tipo de forma debe ser 'diamond', 'delta' o 'parafoil'")
    private String shape;


    @NotNull(message = "El viento requerido no puede estar vacio")
    @Min(value = 14, message = "El viento mínimo requerido es 14")
    @Max(value = 40, message = "El viento máximo permitido es 40")
    private int windRequired;

    @NotBlank(message = "Tienes que especificar el dueño")
    private String owner;

    @NotBlank(message = "La ubicación no puede estar vacia")
    private String location;



}
