package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.ActualizarCantidadDTO;
import com.jecsamtech.kapebackend.dto.CarritoHasCafeDTO;
import com.jecsamtech.kapebackend.dto.CarritoHasCafeResponseDTO;
import com.jecsamtech.kapebackend.service.CarritoHasCafeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-cafe")
public class CarritoHasCafeController {

    private final CarritoHasCafeService carritoHasCafeService;

    public CarritoHasCafeController(CarritoHasCafeService carritoHasCafeService) {
        this.carritoHasCafeService = carritoHasCafeService;
    }

    @GetMapping("/carrito/{carritoId}")
    public List<CarritoHasCafeResponseDTO> getByCarrito(@PathVariable Long carritoId) {
        return carritoHasCafeService.findByCarrito(carritoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarritoHasCafeResponseDTO agregar(
            @Valid @RequestBody CarritoHasCafeDTO dto
    ) {
        return carritoHasCafeService.agregar(dto);
    }

    @PutMapping("/{id}")
    public CarritoHasCafeResponseDTO actualizarCantidad(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarCantidadDTO dto
    ) {
        return carritoHasCafeService.actualizarCantidad(id, dto.getCantidad());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        carritoHasCafeService.eliminar(id);
    }
}