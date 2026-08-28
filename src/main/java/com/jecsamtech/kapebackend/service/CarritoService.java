package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.CarritoDTO;
import com.jecsamtech.kapebackend.model.Carrito;
import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.repository.CarritoRepository;
import com.jecsamtech.kapebackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<CarritoDTO> obtenerPorUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuario_IdUsuario(usuarioId)
                .map(this::convertirADTO);
    }

    public CarritoDTO crearCarrito(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new com.jecsamtech.kapebackend.exception.ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId));

        return carritoRepository.findByUsuario_IdUsuario(usuarioId)
                .map(this::convertirADTO)
                .orElseGet(() -> {
                    Carrito carrito = new Carrito();
                    carrito.setUsuario(usuario);
                    carrito.setFechaCreacion(LocalDateTime.now());
                    carrito.setFechaActualizacion(LocalDateTime.now());
                    Carrito guardado = carritoRepository.save(carrito);
                    return convertirADTO(guardado);
                });
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
        Long idUsuario = null;
        if (carrito.getUsuario() != null) {
            idUsuario = carrito.getUsuario().getIdUsuario();
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setFechaCreacion(carrito.getFechaCreacion());
        dto.setFechaActualizacion(carrito.getFechaActualizacion());
        dto.setIdUsuario(idUsuario);

        return dto;
    }
}