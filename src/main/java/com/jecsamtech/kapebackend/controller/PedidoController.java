package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.PedidoRequest;
import com.jecsamtech.kapebackend.dto.PedidoResponse;
import com.jecsamtech.kapebackend.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    // admin (implementar lo de los roles en security)
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<PedidoResponse> getAll(){
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public PedidoResponse getById(@PathVariable Long idPedido){
        return pedidoService.findById(idPedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse create(
            @Valid @RequestBody PedidoRequest dto){
        return pedidoService.create(dto);
    }

    //@PreAuthorize("hasRole('CLIENTE')")
    /*@GetMapping("/mis-pedidos")
    public List<PedidoResponse> getMisPedidos() {
        return pedidoService.findMisPedidos();
    }*/

    //@PreAuthorize("hasRole('ADMIN')") //
    @GetMapping("/usuario/{idUsuario}")
    public List<PedidoResponse> getByUsuario(
            @PathVariable Long idUsuario) {

        return pedidoService.findByUsuario(idUsuario);
    }

}
