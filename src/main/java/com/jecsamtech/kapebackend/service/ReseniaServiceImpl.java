package com.jecsamtech.kapebackend.service.impl;

import com.jecsamtech.kapebackend.dto.ReseniaDto;
import com.jecsamtech.kapebackend.model.DetallePedido;
import com.jecsamtech.kapebackend.model.Resenia;
import com.jecsamtech.kapebackend.repository.DetallePedidoRepository;
import com.jecsamtech.kapebackend.repository.ReseniaRepository;
import com.jecsamtech.kapebackend.service.ReseniaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReseniaServiceImpl implements ReseniaService {

    private final ReseniaRepository reseniaRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReseniaDto> findAll() {
        return reseniaRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReseniaDto findById(Long id) {
        Resenia resenia = reseniaRepository.findById(id)
                .orElseThrow(() -> new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Reseña no encontrada con ID: " + id));
        return mapToDto(resenia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReseniaDto> findByCafeId(Long idCafe) {
        return reseniaRepository.findByDetallePedido_Cafe_IdCafe(idCafe).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReseniaDto> findByUsuarioId(Long idUsuario) {
        return reseniaRepository.findByDetallePedido_Pedido_Usuario_IdUsuario(idUsuario).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReseniaDto save(ReseniaDto reseniaDto) {
        Resenia resenia = mapToEntity(reseniaDto);
        if (resenia.getFecha() == null) {
            resenia.setFecha(LocalDateTime.now());
        }
        Resenia savedResenia = reseniaRepository.save(resenia);
        return mapToDto(savedResenia);
    }

    @Override
    @Transactional
    public ReseniaDto update(Long id, ReseniaDto reseniaDto) {
        Resenia existingResenia = reseniaRepository.findById(id)
                .orElseThrow(() -> new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Reseña no encontrada con ID: " + id));

        existingResenia.setCalificacion(reseniaDto.getCalificacion());
        existingResenia.setComentario(reseniaDto.getComentario());

        if (reseniaDto.getIdDetallePedido() != null) {
            DetallePedido detallePedido = detallePedidoRepository.findById(reseniaDto.getIdDetallePedido())
                    .orElseThrow(() -> new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Detalle de pedido no encontrado"));
            existingResenia.setDetallePedido(detallePedido);
        }

        Resenia updatedResenia = reseniaRepository.save(existingResenia);
        return mapToDto(updatedResenia);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!reseniaRepository.existsById(id)) {
            throw new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Reseña no encontrada con ID: " + id);
        }
        reseniaRepository.deleteById(id);
    }

    private ReseniaDto mapToDto(Resenia resenia) {
        return ReseniaDto.builder()
                .idResenia(resenia.getIdResenia())
                .calificacion(resenia.getCalificacion())
                .comentario(resenia.getComentario())
                .fecha(resenia.getFecha())
                .idDetallePedido(resenia.getDetallePedido() != null ? resenia.getDetallePedido().getIdDetallePedido() : null)
                .build();
    }

    private Resenia mapToEntity(ReseniaDto dto) {
        DetallePedido detallePedido = null;
        if (dto.getIdDetallePedido() != null) {
            detallePedido = detallePedidoRepository.findById(dto.getIdDetallePedido())
                    .orElseThrow(() -> new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Detalle de pedido no encontrado"));
        }

        return Resenia.builder()
                .idResenia(dto.getIdResenia())
                .calificacion(dto.getCalificacion())
                .comentario(dto.getComentario())
                .fecha(dto.getFecha())
                .detallePedido(detallePedido)
                .build();
    }
}