package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.Estatus;
import com.jecsamtech.kapebackend.model.TypoEnvio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private Long idPedido;
    private BigDecimal total;
    private Estatus estatus;
    private String numTelefono;
    private TypoEnvio typoEnvio;
    private Date fechaPedido;
    private Date fechaEnvio;
    private Date fechaRecibido;

    private Long idUsuario;
    private Long idDireccion;
    private Long idMetodoPago;

}
