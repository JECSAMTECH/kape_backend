package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.dto.PedidoRequest;
import com.jecsamtech.kapebackend.dto.PedidoResponse;
import com.jecsamtech.kapebackend.model.*;
import com.jecsamtech.kapebackend.repository.DireccionRepository;
import com.jecsamtech.kapebackend.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final DireccionRepository direccionRepository;
    private final MetodoPago metodoPagoRepository;
    private final UsuarioService usuarioService;

    // Buscar todos los pedidos (vista admin???)
    @Transactional(readOnly = true) //duda sobre si choca con detallePedido
    public List<PedidoResponse> findAll(){
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponse) //falta implementar??
                .toList();
    }

    // Buscar por ID
    @Transactional(readOnly = true)
    public PedidoResponse findById(Long idPedido){
        return toResponse(findEntityById(idPedido));
    }

    // POR QUÉ ESTA NO TIENE @TRANSACCIONAL?
    private Pedido findEntityById(Long idPedido){
        return pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido no encontrado."
                ));
    }
    // crear pedido
    @Transactional
    public  PedidoResponse create(PedidoRequest dto){
        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
    }

}
