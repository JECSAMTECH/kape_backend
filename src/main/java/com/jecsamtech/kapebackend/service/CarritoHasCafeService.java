package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.CarritoHasCafeDTO;
import com.jecsamtech.kapebackend.model.CarritoHasCafe;
import com.jecsamtech.kapebackend.repository.CarritoHasCafeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarritoHasCafeService {

    private final CarritoHasCafeRepository carritoHasCafeRepository;

    public CarritoHasCafeService(CarritoHasCafeRepository carritoHasCafeRepository) {
        this.carritoHasCafeRepository = carritoHasCafeRepository;
    }

    @Transactional(readOnly = true)
    public List<CarritoHasCafe> findByCarrito(Long carritoId) {
        return carritoHasCafeRepository.findByCarritoId(carritoId);
    }

    @Transactional
    public CarritoHasCafe agregar(CarritoHasCafeDTO dto) {
        return carritoHasCafeRepository.findByCarritoIdAndCafeId(dto.getCarritoId(), dto.getCafeId())
                .map(item -> {
                    item.setCantidad(item.getCantidad() + dto.getCantidad());
                    return carritoHasCafeRepository.save(item);
                })
                .orElseGet(() -> {
                    CarritoHasCafe nuevo = new CarritoHasCafe();
                    nuevo.setCarritoId(dto.getCarritoId());
                    nuevo.setCafeId(dto.getCafeId());
                    nuevo.setCantidad(dto.getCantidad());
                    return carritoHasCafeRepository.save(nuevo);
                });
    }

    @Transactional
    public CarritoHasCafe actualizarCantidad(Long id, Long cantidad) {
        CarritoHasCafe item = carritoHasCafeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de carrito no encontrado"));
        item.setCantidad(cantidad);
        return carritoHasCafeRepository.save(item);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!carritoHasCafeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de carrito no encontrado");
        }
        carritoHasCafeRepository.deleteById(id);
    }
}
