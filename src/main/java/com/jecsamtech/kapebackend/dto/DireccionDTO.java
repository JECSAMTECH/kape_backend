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

public class DireccionDTO {
    private Long idDireccion;

    @NotBlank(message = "Calle no puede estar vacía")
    private String calle;

    @NotBlank(message = "Colonia no puede estar vacía")
    private String colonia;

    @NotBlank(message = "Número no puede estar vacío")
    private String numero;

    @NotBlank(message = "Ciudad no puede estar vacía")
    private String ciudad;

    @NotBlank(message = "Estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "País no puede estar vacío")
    private String pais;

    @NotBlank(message = "Código postal no puede estar vacío")
    private String codigoPostal;

}
