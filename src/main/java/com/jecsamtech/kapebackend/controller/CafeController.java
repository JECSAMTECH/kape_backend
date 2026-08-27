package com.jecsamtech.kapebackend.controller;


import com.jecsamtech.kapebackend.dto.CafeCreateDTO;
import com.jecsamtech.kapebackend.dto.CafeResponseDTO;
import com.jecsamtech.kapebackend.service.CafeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping
    public ResponseEntity<List<CafeResponseDTO>>  getAll(){
        return ResponseEntity.ok(cafeService.findAll());
    }

    @GetMapping("/{id}")
    public CafeResponseDTO getById(@PathVariable Long id) {
        return cafeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CafeResponseDTO create(@Valid @RequestBody CafeCreateDTO dto) {
        return cafeService.create(dto);
    }

    @PutMapping("/{id}")
    public CafeResponseDTO update(@PathVariable Long id, @RequestBody CafeCreateDTO dto){
        return cafeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cafeService.deleteById(id);
    }

    @GetMapping("/search/name")
    public List<CafeResponseDTO> searchByName(
            @RequestParam String name
    ) {
        return cafeService.searchByName(name);
    }

    @GetMapping("/search/roast")
    public List<CafeResponseDTO> searchByRoast(
            @RequestParam String roast
    ) {
        return cafeService.searchByRoast(roast);
    }

    @GetMapping("/search/intensity")
    public List<CafeResponseDTO> searchByIntensity(
            @RequestParam Integer intensity
    ) {
        return cafeService.searchByIntensity(intensity);
    }

    @GetMapping("/search/stock")
    public List<CafeResponseDTO> searchByStock(
            @RequestParam Integer stock
    ) {
        return cafeService.searchByStock(stock);
    }

    @GetMapping("/search/price")
    public List<CafeResponseDTO> searchByPriceRange(
            @RequestParam("min") BigDecimal priceMin,
            @RequestParam("max") BigDecimal priceMax
            ) {
        return cafeService.searchByPriceRange(priceMin, priceMax);
    }

}
