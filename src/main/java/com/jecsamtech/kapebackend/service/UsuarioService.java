package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.LoginRequest;
import com.jecsamtech.kapebackend.dto.UsuarioAdminRequest;
import com.jecsamtech.kapebackend.dto.UsuarioRequest;
import com.jecsamtech.kapebackend.dto.UsuarioResponse;
import com.jecsamtech.kapebackend.model.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crearCliente(UsuarioRequest usuarioRequest);
    UsuarioResponse crearUsuarioPermisos(UsuarioAdminRequest usuarioAdminRequest);
    UsuarioResponse encontrarUsuarioPorId(Long id);
    List<UsuarioResponse> encontrarTodosLosUsuarios();
    UsuarioResponse actualizarUsuarioPorId(Long id, UsuarioRequest usuarioRequest);
    void eliminarUsuarioPorId(Long id);
    UsuarioResponse iniciarSesion(LoginRequest loginRequest);
}
