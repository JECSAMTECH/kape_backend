package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.LoginRequest;
import com.jecsamtech.kapebackend.dto.UsuarioAdminRequest;
import com.jecsamtech.kapebackend.dto.UsuarioRequest;
import com.jecsamtech.kapebackend.dto.UsuarioResponse;
import com.jecsamtech.kapebackend.exception.PasswordMismatchException;
import com.jecsamtech.kapebackend.exception.ResourceAlreadyExistsException;
import com.jecsamtech.kapebackend.exception.ResourceNotFoundException;
import com.jecsamtech.kapebackend.model.Rol;
import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.repository.RolRepository;
import com.jecsamtech.kapebackend.repository.UsuarioRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    //private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UsuarioResponse crearCliente(UsuarioRequest usuarioRequest) {

        System.out.println("REQUEST RECIBIDO");
        if (usuarioRepository.existsByCorreo(usuarioRequest.getCorreo())){
            throw new ResourceAlreadyExistsException("El email ya existe, pruebe uno distinto");
        }

        //String encodedPassword = passwordEncoder.encode(usuarioRequest.getContrasenia());

        if (!usuarioRequest.getContrasenia()
                .equals(usuarioRequest.getConfirmarContrasenia())) {

            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Rol clientRole = rolRepository.findByNombreRol("CLIENTE")
                .orElseThrow(()-> new PasswordMismatchException("No se encontro el rol \"Cliente\""));

        LocalDateTime registrationDate = LocalDateTime.now();

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setCorreo(usuarioRequest.getCorreo());
        usuario.setRol(clientRole);
        usuario.setContrasenia(usuarioRequest.getContrasenia());
        usuario.setFechaRegistro(registrationDate);
        usuario.setTelefono(usuarioRequest.getNumero());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return convertToUsuarioResponse(savedUsuario);
    }

    @Override
    public UsuarioResponse crearUsuarioPermisos(UsuarioAdminRequest usuarioAdminRequest) {
        if (usuarioRepository.existsByCorreo(usuarioAdminRequest.getCorreo())){
            throw new ResourceAlreadyExistsException("El email ya existe, pruebe uno distinto");
        }

        //String encodedPassword = passwordEncoder.encode(usuarioAdminRequest.getContrasenia());

        Rol clientRole = rolRepository.findByNombreRol(usuarioAdminRequest.getNombreRol())
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el rol "+usuarioAdminRequest.getNombreRol()));

        LocalDateTime registrationDate = LocalDateTime.now();

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioAdminRequest.getNombre());
        usuario.setCorreo(usuarioAdminRequest.getCorreo());
        usuario.setRol(clientRole);
        usuario.setContrasenia(usuarioAdminRequest.getContrasenia());
        usuario.setFechaRegistro(registrationDate);


        Usuario savedUsuario = usuarioRepository.save(usuario);

        return convertToUsuarioResponse(savedUsuario);
    }

    @Override
    public UsuarioResponse encontrarUsuarioPorId(Long id) {
        Usuario usuarioEncontrado = encontrarEntidadUsuarioPorId(id);
        return convertToUsuarioResponse(usuarioEncontrado);
    }

    @Override
    public List<UsuarioResponse> encontrarTodosLosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertToUsuarioResponse)
                .toList();
    }

    @Override
    public UsuarioResponse actualizarUsuarioPorId(Long id, UsuarioRequest usuarioRequest) {
        return null;
    }

    @Override
    public void eliminarUsuarioPorId(Long id) {
        encontrarEntidadUsuarioPorId(id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponse iniciarSesion(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Correo o contraseña incorrectos"));

        if (!usuario.getContrasenia().equals(loginRequest.getContrasenia())) {
            throw new IllegalArgumentException("Correo o contraseña incorrectos");
        }

        return convertToUsuarioResponse(usuario);
    }

    private UsuarioResponse convertToUsuarioResponse(Usuario usuario){
        return new UsuarioResponse(usuario.getIdUsuario(), usuario.getNombre(),
                usuario.getCorreo(), usuario.getFechaRegistro(), usuario.getRol().getNombreRol(), usuario.getTelefono());
    }

    private Usuario encontrarEntidadUsuarioPorId(Long usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow(()-> new ResourceNotFoundException("No se encontró el usuario con id "+usuarioId));
    }
}
