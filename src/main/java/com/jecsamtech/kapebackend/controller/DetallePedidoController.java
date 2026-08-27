package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.DetallePedidoDTO;
import com.jecsamtech.kapebackend.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoDTO>> findAll() {
        return ResponseEntity.ok(detallePedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(detallePedidoService.findById(id));
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedidoDTO>> getByPedidoId(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detallePedidoService.findByPedidoId(pedidoId));
    }

    @PostMapping
    public ResponseEntity<DetallePedidoDTO> create(@Valid @RequestBody DetallePedidoDTO dto) {
        DetallePedidoDTO created = detallePedidoService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> update(@PathVariable Long id, @Valid @RequestBody DetallePedidoDTO dto) {
        return ResponseEntity.ok(detallePedidoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}