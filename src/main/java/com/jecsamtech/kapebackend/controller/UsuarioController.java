package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.UsuarioAdminRequest;
import com.jecsamtech.kapebackend.dto.UsuarioRequest;
import com.jecsamtech.kapebackend.dto.UsuarioResponse;
import com.jecsamtech.kapebackend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearCliente(@RequestBody @Valid UsuarioRequest usuarioRequest){

        UsuarioResponse usuarioResponse = usuarioService.crearCliente(usuarioRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioResponse.getIdUsuario())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @PostMapping("/admin")
    public ResponseEntity<UsuarioResponse> crearUsuarioPermisos(@RequestBody @Valid UsuarioAdminRequest usuarioAdminRequest){

        UsuarioResponse usuarioResponse = usuarioService.crearUsuarioPermisos(usuarioAdminRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioResponse.getIdUsuario())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> encontrarTodosLosUsuarios(){

        List<UsuarioResponse> usuarioResponses = usuarioService.encontrarTodosLosUsuarios();

        return ResponseEntity.ok(usuarioResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> encontrarUsuarioPorId(@PathVariable Long id){

        UsuarioResponse usuarioResponse = usuarioService.encontrarUsuarioPorId(id);

        return ResponseEntity.ok(usuarioResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id){

        usuarioService.eliminarUsuarioPorId(id);

        return ResponseEntity.noContent().build();
    }
}
