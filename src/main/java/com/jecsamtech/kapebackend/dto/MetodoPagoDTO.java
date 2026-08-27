package com.jecsamtech.kapebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoPagoDTO {

    private Long idMetodoPago;

    @NotBlank(message = "El tipo de método de pago es obligatorio.")
    private String tipo;

}
