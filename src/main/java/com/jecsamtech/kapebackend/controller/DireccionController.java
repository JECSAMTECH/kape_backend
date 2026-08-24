package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.DireccionRequestDTO;
import com.jecsamtech.kapebackend.dto.DireccionResponseDTO;
import com.jecsamtech.kapebackend.service.DireccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @GetMapping
    public List<DireccionResponseDTO> getAll() {
        return direccionService.findAll();
    }

    @GetMapping("/{id}")
    public DireccionResponseDTO getById(@PathVariable Long id) {
        return direccionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DireccionResponseDTO create(
            @Valid @RequestBody DireccionRequestDTO dto) {
        return direccionService.create(dto);
    }

    @PutMapping("/{id}")
    public DireccionResponseDTO update(@PathVariable Long id,
                           @Valid @RequestBody DireccionRequestDTO dto) {
        return direccionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        direccionService.deleteById(id);
    }
}
