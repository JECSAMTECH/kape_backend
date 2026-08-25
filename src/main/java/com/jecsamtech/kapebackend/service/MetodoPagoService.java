package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.MetodoPagoDTO;
import com.jecsamtech.kapebackend.model.MetodoPago;
import com.jecsamtech.kapebackend.repository.MetodoPagoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoService(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }


    @Transactional(readOnly = true)
    public List<MetodoPagoDTO> findAll() {
        return metodoPagoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MetodoPagoDTO findById(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pago no encontrado"));
        return toDTO(metodoPago);
    }

    @Transactional
    public MetodoPagoDTO save(MetodoPagoDTO dto) {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setTipo(dto.getTipo());
        return toDTO(metodoPagoRepository.save(metodoPago));
    }

    @Transactional
    public MetodoPagoDTO update(Long id, MetodoPagoDTO dto) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pago no encontrado"));
        metodoPago.setTipo(dto.getTipo());
        return toDTO(metodoPagoRepository.save(metodoPago));
    }

    @Transactional
    public void delete(Long id) {
        if (!metodoPagoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pago no encontrado");
        }
        metodoPagoRepository.deleteById(id);
    }

    private MetodoPagoDTO toDTO(MetodoPago metodoPago) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setIdMetodoPago(metodoPago.getIdMetodoPago());
        dto.setTipo(metodoPago.getTipo());
        return dto;
    }
}