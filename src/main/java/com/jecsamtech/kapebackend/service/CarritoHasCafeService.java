package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.CarritoHasCafeDTO;
import com.jecsamtech.kapebackend.dto.CarritoHasCafeResponseDTO;
import com.jecsamtech.kapebackend.model.Cafe;
import com.jecsamtech.kapebackend.model.Carrito;
import com.jecsamtech.kapebackend.model.CarritoHasCafe;
import com.jecsamtech.kapebackend.repository.CafeRepository;
import com.jecsamtech.kapebackend.repository.CarritoHasCafeRepository;
import com.jecsamtech.kapebackend.repository.CarritoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarritoHasCafeService {

    private final CarritoHasCafeRepository carritoHasCafeRepository;
    private final CarritoRepository carritoRepository;
    private final CafeRepository cafeRepository;

    public CarritoHasCafeService(
            CarritoHasCafeRepository carritoHasCafeRepository,
            CarritoRepository carritoRepository,
            CafeRepository cafeRepository
    ) {
        this.carritoHasCafeRepository = carritoHasCafeRepository;
        this.carritoRepository = carritoRepository;
        this.cafeRepository = cafeRepository;
    }

    @Transactional(readOnly = true)
    public List<CarritoHasCafeResponseDTO> findByCarrito(Long carritoId) {
        return carritoHasCafeRepository.findByCarrito_IdCarrito(carritoId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public CarritoHasCafeResponseDTO agregar(CarritoHasCafeDTO dto) {
        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad debe ser mayor que cero"
            );
        }

        Carrito carrito = carritoRepository.findById(dto.getCarritoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Carrito no encontrado"
                ));

        Cafe cafe = cafeRepository.findById(dto.getCafeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Café no encontrado"
                ));

        CarritoHasCafe guardado = carritoHasCafeRepository
                .findByCarrito_IdCarritoAndCafe_IdCafe(
                        dto.getCarritoId(), dto.getCafeId()
                )
                .map(item -> {
                    item.setCantidad(item.getCantidad() + dto.getCantidad());
                    return carritoHasCafeRepository.save(item);
                })
                .orElseGet(() -> {
                    CarritoHasCafe nuevo = new CarritoHasCafe();
                    nuevo.setCarrito(carrito);
                    nuevo.setCafe(cafe);
                    nuevo.setCantidad(dto.getCantidad());
                    return carritoHasCafeRepository.save(nuevo);
                });

        return toResponseDTO(guardado);
    }

    @Transactional
    public CarritoHasCafeResponseDTO actualizarCantidad(Long id, Long cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad debe ser mayor que cero"
            );
        }

        CarritoHasCafe item = carritoHasCafeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item de carrito no encontrado"
                ));

        item.setCantidad(cantidad);
        return toResponseDTO(carritoHasCafeRepository.save(item));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!carritoHasCafeRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Item de carrito no encontrado"
            );
        }

        carritoHasCafeRepository.deleteById(id);
    }

    private CarritoHasCafeResponseDTO toResponseDTO(CarritoHasCafe item) {
        Cafe cafe = item.getCafe();
        return new CarritoHasCafeResponseDTO(
                item.getId(),
                item.getCarrito() != null ? item.getCarrito().getIdCarrito() : null,
                cafe != null ? cafe.getIdCafe() : null,
                cafe != null ? cafe.getNombreCafe() : null,
                cafe != null ? cafe.getPrecioCafe() : null,
                item.getCantidad()
        );
    }
}
