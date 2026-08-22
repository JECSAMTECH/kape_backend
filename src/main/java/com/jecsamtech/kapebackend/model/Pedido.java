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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "total", nullable = false)
    private Long total;

    @Column(name = "estatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @Column(name = "num_telefono", nullable = false)
    private String numTelefono;

    @Column(name = "typo_envio", nullable = false)
    private TypoEnvio typoEnvio;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @Column(name = "fecha_envio", nullable = true)
    private Date fechaEnvio;

    @Column(name = "fecha_recibido", nullable = true)
    private Date fechaRecibido;

    //lave foranea id_usuario
    @JoinColumn(name = "id_usuario")
    @OneToOne
    private Long idUsuario;

    //id_datos_envio
    @JoinColumn(name = "id_metodoPago")
    @OneToOne
    private Long idMetodoPago;

    @JoinColumn(name = "id_direccion")
    @OneToOne
    private Long idDireccion;

}
