package com.jecsamtech.kapebackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CafeCreateDTO {

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombreCafe;

    @NotBlank(message = "La descripción no puede ir vacía")
    private String descripcionCafe;

    @NotBlank(message = "La imagen es obligatoria")
    private String imagenCafe;

    @NotBlank(message = "Campo etiquetas no puede ir vacío o ser nulo")
    private String etiquetasCafe;

    @NotBlank(message = "El tueste no puede ir vacío")
    private String tuesteCafe;

    @NotBlank(message = "La nota de cata no puede ir vacía")
    private String notasCataCafe;

    @NotNull(message = "La intensidad no puede ser nula")
    @Positive(message = "Debe ser mayor que cero la intensidad")
    @Max(value = 3, message = "La intensidad no puede ser mayor a 3")
    private Integer intensidadCafe;

    @NotNull(message = "El precio no puede ir vacío")
    @PositiveOrZero(message = "El precio debe ser mayor o igual que cero")
    private BigDecimal precioCafe;

    @NotNull(message = "El stock no puede estar vacío")
    @PositiveOrZero(message = "El stock debe ser igual o mayor que cero")
    private Integer stockCafe;
}