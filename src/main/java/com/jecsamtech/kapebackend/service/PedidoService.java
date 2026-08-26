package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.dto.PedidoRequest;
import com.jecsamtech.kapebackend.dto.PedidoResponse;
import com.jecsamtech.kapebackend.model.*;
import com.jecsamtech.kapebackend.repository.DireccionRepository;
import com.jecsamtech.kapebackend.repository.PedidoRepository;
import com.jecsamtech.kapebackend.repository.MetodoPagoRepository;
import com.jecsamtech.kapebackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final DireccionRepository direccionRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final UsuarioRepository usuarioRepository; // temporal

    // Buscar todos los pedidos (posible vista admin)
    @Transactional(readOnly = true)
    public List<PedidoResponse> findAll(){
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Buscar pedido por usuario (mis pedidos)
    @Transactional(readOnly = true)
    public List<PedidoResponse> findMisPedidos(Long idUsuario) { // temporal
        return pedidoRepository
                .findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Buscar pedido por Id (para admin agregar autorización)
    @Transactional(readOnly = true)
    public PedidoResponse findById(Long idPedido){
        return toResponseDTO(findEntityById(idPedido));
    }

    // por que es un metodo auxiliar privado
    private Pedido findEntityById(Long idPedido){
        return pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido no encontrado."
                ));
    }
    // crear pedido
    @Transactional
    public  PedidoResponse create(PedidoRequest dto) {
        //Usuario usuario = usuarioService...;
        Usuario usuario = usuarioRepository
                .findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));

        Direccion direccion = direccionRepository
                .findById(dto.getIdDireccion())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dirección no encontrada"
                ));
        MetodoPago metodoPago = metodoPagoRepository
                .findById(dto.getIdMetodoPago())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Método de pago no encontrado"
                ));
        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .direccion(direccion)
                .metodoPago(metodoPago)
                .numTelefono(dto.getNumTelefono())
                .typoEnvio(dto.getTypoEnvio())
                // pone la fecha actual
                .fechaPedido(new Date()) //verificar tipo de dato Date
                //manda a pendiente por default
                .estatus(Estatus.PENDIENTE)
                .total(BigDecimal.ZERO) //pendiente cálculo
                .build();
        return toResponseDTO(
                pedidoRepository.save(pedido)
        );
    }

    // actualizar estatus de pedido
    @Transactional
    public PedidoResponse updateEstatus(Long idPedido, Estatus nuevoEstatus){
        Pedido pedido = findEntityById(idPedido);
        pedido.setEstatus(nuevoEstatus);

        return toResponseDTO(
                pedidoRepository.save(pedido)
        );
    }

    //Actualizar fecha por estatus

    // Econtrar pedidos por usuario, para historial de compra
    @Transactional(readOnly = true)
    public List<PedidoResponse> findByUsuario(Long idUsuario){
        return pedidoRepository
                .findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private PedidoResponse toResponseDTO(Pedido pedido) {
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .total(pedido.getTotal())
                .estatus(pedido.getEstatus())
                .numTelefono(pedido.getNumTelefono())
                .typoEnvio(pedido.getTypoEnvio())
                .fechaPedido(pedido.getFechaPedido())
                .fechaEnvio(pedido.getFechaEnvio())
                .fechaRecibido(pedido.getFechaRecibido())
                .idUsuario( // temporal
                        pedido.getUsuario() != null
                                ? pedido.getUsuario().getIdUsuario()
                                : null
                )
                .idDireccion(
                        pedido.getDireccion() != null
                                ? pedido.getDireccion().getIdDireccion()
                                : null
                )
                .idMetodoPago(
                        pedido.getMetodoPago() != null
                                ? pedido.getMetodoPago().getIdMetodoPago()
                                : null
                )
                .build();
    }
}
