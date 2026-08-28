package com.jecsamtech.kapebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionResponseDTO {
    private Long idDireccion;
    private Long usuarioId;
    private String calle;
    private String colonia;
    private String numero;
    private String ciudad;
    private String estado;
    private String pais;
    private String codigoPostal;
}
