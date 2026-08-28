package com.jecsamtech.kapebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoHasCafeResponseDTO {

    private Long id;
    private Long carritoId;
    private Long cafeId;
    private String nombreCafe;
    private String imagenCafe;
    private String precioCafe;
    private Long cantidad;
}
