package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    @NotNull(message = "El tipo de envío es obligatorio")
    private TipoEnvio tipoEnvio;

    @NotNull(message = "El método de pago es obligatorio")
    private Long idMetodoPago;

    @NotNull(message = "La dirección es obligatoria")
    private Long idDireccion;

    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario; // temporal
}
