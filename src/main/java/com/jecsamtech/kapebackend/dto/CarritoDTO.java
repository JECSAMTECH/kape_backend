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

    // Constructor explícito de 4 parámetros para evitar que marque en rojo la Service
    public CarritoDTO(Long idCarrito, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Long idUsuario) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.idUsuario = idUsuario;
    }
}