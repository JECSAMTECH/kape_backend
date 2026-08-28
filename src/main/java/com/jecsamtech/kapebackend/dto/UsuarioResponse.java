package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private LocalDateTime fechaRegistro;
    private String nombreRol;
    private String numero;
}
