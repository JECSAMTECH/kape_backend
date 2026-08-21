package com.jecsamtech.kapebackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoHasCafeDTO {

    @NotNull(message = "El carrito es obligatorio")
    private Long carritoId;

    @NotNull(message = "El café es obligatorio")
    private Long cafeId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Long cantidad;
}
