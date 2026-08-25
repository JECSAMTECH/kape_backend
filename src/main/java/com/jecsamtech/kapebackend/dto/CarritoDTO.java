package com.jecsamtech.kapebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {

    private Long idCarrito;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Long idUsuario;

}