package com.jecsamtech.kapebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequest {

    @NotBlank(message = "El nombre no puede ir vacio")
    private String nombre;
    @NotBlank(message = "El correo no puede ir vacio")
    @Email(message = "Se requiere un formato de correo valido")
    private String correo;
    @NotBlank(message = "La contraseña no puede ir vacia")
    private String contrasenia;
    @NotBlank(message = "El telefóno no puede ir vacio")
    @Size(max = 10, message = "El teléfono no puede tener más de 10 dígitos")
    private String numero;
    @NotBlank(message = "La confirmación de contraseña no puede ir vacia")
    private String confirmarContrasenia;
}
