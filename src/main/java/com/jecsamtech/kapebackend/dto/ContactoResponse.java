package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.Contacto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactoResponse {

    private Long idContacto;
    private String nombre;
    private String correo;
    private String telefono;
    private String asunto;
    private String mensaje;
    private Long usuarioId;
    private Long usuarioRolIdRol;

    public static ContactoResponse fromEntity(Contacto contacto) {
        return new ContactoResponse(
                contacto.getIdContacto(),
                contacto.getNombre(),
                contacto.getCorreo(),
                contacto.getTelefono(),
                contacto.getAsunto(),
                contacto.getMensaje(),
                contacto.getUsuario() != null ? contacto.getUsuario().getIdUsuario() : null,
                contacto.getUsuarioRolIdRol()
        );
    }
}