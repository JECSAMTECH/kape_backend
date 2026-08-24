package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.CafeCreateDTO;
import com.jecsamtech.kapebackend.dto.CafeResponseDTO;
import com.jecsamtech.kapebackend.model.Cafe;
import com.jecsamtech.kapebackend.repository.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> findAll() {
        return cafeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CafeResponseDTO findById(Long idCafe) {
        Cafe cafe = cafeRepository.findById(idCafe)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        return convertToResponseDTO(cafe);
    }

    @Transactional
    public CafeResponseDTO create(CafeCreateDTO dto) {

        Cafe cafe = Cafe.builder()
                .nombreCafe(dto.getNombreCafe())
                .descripcionCafe(dto.getDescripcionCafe())
                .imagenCafe(dto.getImagenCafe())
                .etiquetasCafe(dto.getEtiquetasCafe())
                .tuesteCafe(dto.getTuesteCafe())
                .notasCataCafe(dto.getNotasCataCafe())
                .intensidadCafe(dto.getIntensidadCafe())
                .precioCafe(dto.getPrecioCafe())
                .stockCafe(dto.getStockCafe())
                .build();

        Cafe cafeGuardado = cafeRepository.save(cafe);

        return convertToResponseDTO(cafeGuardado);
    }

    @Transactional
    public CafeResponseDTO update(Long id, CafeCreateDTO dto) {

        Cafe cafe = cafeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        cafe.setNombreCafe(dto.getNombreCafe());
        cafe.setDescripcionCafe(dto.getDescripcionCafe());
        cafe.setImagenCafe(dto.getImagenCafe());
        cafe.setEtiquetasCafe(dto.getEtiquetasCafe());
        cafe.setTuesteCafe(dto.getTuesteCafe());
        cafe.setNotasCataCafe(dto.getNotasCataCafe());
        cafe.setIntensidadCafe(dto.getIntensidadCafe());
        cafe.setPrecioCafe(dto.getPrecioCafe());
        cafe.setStockCafe(dto.getStockCafe());

        Cafe cafeActualizado = cafeRepository.save(cafe);

        return convertToResponseDTO(cafeActualizado);
    }

    @Transactional
    public void deleteById(Long id) {

        if (!cafeRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Producto no encontrado"
            );
        }

        cafeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> searchByName(String name) {
        return cafeRepository.findByNombreCafeContainingIgnoreCase(name)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> searchByRoast(String roast) {
        return cafeRepository.findByTuesteCafe(roast)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> searchByIntensity(Integer intensity) {
        return cafeRepository.findByIntensidadCafe(intensity)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> searchByStock(Integer stock) {
        return cafeRepository.findByStockCafeGreaterThan(stock)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDTO> searchByRangePrice(
            BigDecimal priceMin,
            BigDecimal priceMax
    ) {
        return cafeRepository.findByPrecioCafeBetween(priceMin, priceMax)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    private CafeResponseDTO convertToResponseDTO(Cafe cafe) {

        return CafeResponseDTO.builder()
                .idCafe(cafe.getIdCafe())
                .nombreCafe(cafe.getNombreCafe())
                .descripcionCafe(cafe.getDescripcionCafe())
                .imagenCafe(cafe.getImagenCafe())
                .etiquetasCafe(cafe.getEtiquetasCafe())
                .tuesteCafe(cafe.getTuesteCafe())
                .notasCataCafe(cafe.getNotasCataCafe())
                .intensidadCafe(cafe.getIntensidadCafe())
                .precioCafe(cafe.getPrecioCafe())
                .stockCafe(cafe.getStockCafe())
                .build();
    }
}