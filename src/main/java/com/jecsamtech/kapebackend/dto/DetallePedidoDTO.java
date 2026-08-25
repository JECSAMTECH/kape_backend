package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.Molienda;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoDTO {

    private Long idDetallePedido;

    @NotNull(message = "El tipo de café es obligatorio")
    private Long cafeId;

    @NotNull(message = "El numero de pedido es obligatorio")
    private Long pedidoId;

    @NotNull(message = "La reseña es obligatoria")
    private Long reseniaId;

    @NotNull(message = "El tipo de molienda es obligatorio")
    private Molienda molienda;
}