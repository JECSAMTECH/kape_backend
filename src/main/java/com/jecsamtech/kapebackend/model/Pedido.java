package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    // id_pedido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    // total
    @Column(name = "total", nullable = false)
    private Long total;

    // estatus (enum)
    @Column(name = "estatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    // num_telefono
    @Column(name = "num_telefono", nullable = false)
    private String numTelefono;

    // typo_envio (enum)
    @Column(name = "typo_envio", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypoEnvio typoEnvio;

    // fecha_pedido
    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    // fecha_envio
    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    // fecha_recibido
    @Column(name = "fecha_recibido")
    private Date fechaRecibido;

    // llave foranea id_usuario
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    // llave foranea id_datos_envio
    @OneToOne
    @JoinColumn(name = "id_metodoPago")
    private MetodoPago idMetodoPago;

    // llave foranea id_direccion
    @OneToOne
    @JoinColumn(name = "id_direccion")
    private Direccion idDireccion;

}
