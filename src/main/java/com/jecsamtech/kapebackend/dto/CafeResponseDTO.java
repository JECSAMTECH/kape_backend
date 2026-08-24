package com.jecsamtech.kapebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CafeResponseDTO {

    private Long idCafe;
    private String nombreCafe;
    private String descripcionCafe;
    private String imagenCafe;
    private String etiquetasCafe;
    private String tuesteCafe;
    private String notasCataCafe;
    private Integer intensidadCafe;
    private BigDecimal precioCafe;
    private Integer stockCafe;

}
