package com.jecsamtech.kapebackend.dto;

import com.jecsamtech.kapebackend.model.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    private String numTelefono;
    private TypoEnvio typoEnvio;
    private Long idMetodoPago;
    private Long idDireccion;
    private Long idUsuario; // temporal

}
