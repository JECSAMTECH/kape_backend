package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.CarritoDTO;
import com.jecsamtech.kapebackend.model.Carrito;
import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public Optional<CarritoDTO> obtenerPorUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuario_IdUsuario(usuarioId)
                .map(this::convertirADTO);
    }

    public CarritoDTO crearCarrito(Long usuarioId) {
        Carrito carrito = new Carrito();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        carrito.setUsuario(usuario);

        carrito.setFechaCreacion(LocalDateTime.now());
        carrito.setFechaActualizacion(LocalDateTime.now());

        Carrito guardado = carritoRepository.save(carrito);
        return convertirADTO(guardado);
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
        Long idUsuario = (carrito.getUsuario() != null) ? carrito.getUsuario().getIdUsuario() : null;
        return new CarritoDTO(
                carrito.getIdCarrito(),
                carrito.getFechaCreacion(),
                carrito.getFechaActualizacion(),
                idUsuario
        );
    }
}