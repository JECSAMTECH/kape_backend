package com.jecsamtech.kapebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoHasCafeResponseDTO {

    private Long id;
    private Long carritoId;
    private Long cafeId;
    private String nombreCafe;
    private BigDecimal precioCafe;
    private Long cantidad;
}
