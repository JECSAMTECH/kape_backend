package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.MetodoPagoDTO;
import com.jecsamtech.kapebackend.service.MetodoPagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> findAll() {
        return ResponseEntity.ok(metodoPagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(metodoPagoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> create(@Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPagoDTO saved = metodoPagoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody MetodoPagoDTO dto) {
        return ResponseEntity.ok(metodoPagoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        metodoPagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}