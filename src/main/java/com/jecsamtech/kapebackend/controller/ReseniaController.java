package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.ReseniaDto;
import com.jecsamtech.kapebackend.service.ReseniaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenias")
@RequiredArgsConstructor
public class ReseniaController {

    private final ReseniaService reseniaService;

    @GetMapping
    public ResponseEntity<List<ReseniaDto>> getAllResenias() {
        return ResponseEntity.ok(reseniaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseniaDto> getReseniaById(@PathVariable Long id) {
        return ResponseEntity.ok(reseniaService.findById(id));
    }

    @GetMapping("/cafe/{idCafe}")
    public ResponseEntity<List<ReseniaDto>> getReseniasByCafe(@PathVariable Long idCafe) {
        return ResponseEntity.ok(reseniaService.findByCafeId(idCafe));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReseniaDto>> getReseniasByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(reseniaService.findByUsuarioId(idUsuario));
    }

    @PostMapping
    public ResponseEntity<ReseniaDto> createResenia(@Valid @RequestBody ReseniaDto reseniaDto) {
        ReseniaDto created = reseniaService.save(reseniaDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReseniaDto> updateResenia(@PathVariable Long id, @Valid @RequestBody ReseniaDto reseniaDto) {
        ReseniaDto updated = reseniaService.update(id, reseniaDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResenia(@PathVariable Long id) {
        reseniaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
