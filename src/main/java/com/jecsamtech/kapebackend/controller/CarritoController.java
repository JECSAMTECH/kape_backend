package com.jecsamtech.kapebackend.controller;
import com.jecsamtech.kapebackend.dto.CarritoDTO;
import com.jecsamtech.kapebackend.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*") // Permite peticiones frontend
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerPorUsuarioId(@PathVariable Long usuarioId) {
        return carritoService.obtenerPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/usuario/{usuarioId}")

    public ResponseEntity<CarritoDTO> crearCarrito(@PathVariable Long usuarioId) {
        CarritoDTO nuevoCarrito = carritoService.crearCarrito(usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }
}