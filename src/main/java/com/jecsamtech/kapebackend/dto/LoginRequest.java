package com.jecsamtech.kapebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class LoginRequest {

    @NotBlank(message = "El correo no puede ir vacio")
    @Email(message = "Se requiere un formato de correo valido")
    private String correo;

    @NotBlank(message = "La contraseña no puede ir vacia")
    private String contrasenia;
}
