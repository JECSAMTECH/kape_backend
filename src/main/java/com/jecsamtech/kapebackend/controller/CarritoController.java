package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.CarritoDTO;
import com.jecsamtech.kapebackend.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerPorUsuarioId(@PathVariable Long usuarioId) {
        return carritoService.obtenerPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> crearCarrito(@PathVariable Long usuarioId) {
        CarritoDTO nuevoCarrito = carritoService.crearCarrito(usuarioId);
        return ResponseEntity.ok(nuevoCarrito);
    }
}