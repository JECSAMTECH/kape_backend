package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long idContacto;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "correo", length = 45, nullable = false)
    private String correo;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "asunto", length = 200, nullable = false)
    private String asunto;

    @Column(name = "mensaje", length = 500, nullable = false)
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "Usuario_rol_id_rol")
    private Long usuarioRolIdRol;

    public Contacto() {
    }

    public Contacto(String nombre, String correo, String telefono, String asunto,
                    String mensaje, Usuario usuario, Long usuarioRolIdRol) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.usuarioRolIdRol = usuarioRolIdRol;
    }

    // Getters & Setters

    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getUsuarioRolIdRol() {
        return usuarioRolIdRol;
    }

    public void setUsuarioRolIdRol(Long usuarioRolIdRol) {
        this.usuarioRolIdRol = usuarioRolIdRol;
    }
}