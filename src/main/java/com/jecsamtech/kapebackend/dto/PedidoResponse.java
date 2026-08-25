package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.DetallePedido;
import com.jecsamtech.kapebackend.model.Estatus;
import com.jecsamtech.kapebackend.model.TypoEnvio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private Long idPedido;
    private Long total;
    private Estatus estatus;
    private String numTelefono;
    private TypoEnvio typoEnvio;
    private LocalDateTime fechaPedido;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaRecibido;

    private Long idUsuario;
    private Long idDireccion;
    private Long idMetodoPago;

}
