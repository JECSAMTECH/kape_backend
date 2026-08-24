package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.ReseniaDto;

import java.util.List;

public interface ReseniaService {
    List<ReseniaDto> findAll();
    ReseniaDto findById(Long id);
    List<ReseniaDto> findByCafeId(Long idCafe);
    List<ReseniaDto> findByUsuarioId(Long idUsuario);
    ReseniaDto save(ReseniaDto reseniaDto);
    ReseniaDto update(Long id, ReseniaDto reseniaDto);
    void delete(Long id);
}