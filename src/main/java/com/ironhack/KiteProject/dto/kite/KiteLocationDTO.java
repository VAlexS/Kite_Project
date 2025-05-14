package com.ironhack.KiteProject.dto.kite;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KiteLocationDTO {

    @NotBlank(message = "La ubicación no puede estar vacía")
    private String location;
}
