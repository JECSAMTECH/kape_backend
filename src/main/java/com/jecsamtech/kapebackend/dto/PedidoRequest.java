package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    private String numTelefono;
    private TypoEnvio typoEnvio;
    private Long metodoPago;
    private Long direccion;
    //private Long idUsuario;

}
