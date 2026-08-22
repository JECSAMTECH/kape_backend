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
    private Long idPedido;

    @Column(nullable = false)
    private Long total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @Column(nullable = false)
    private String numTelefono;

    @Column(nullable = false)
    private TypoEnvio typoEnvio;

    @Column(nullable = false)
    private Date fechaPedido;

    @Column(nullable = true)
    private Date fechaEnvio;

    @Column(nullable = true)
    private Date fechaRrecibido;

    //lave foranea id_usuario
    @JoinColumn(name = "idUsuario")
    @OneToOne
    private Long idUsuario;

    //id_datos_envio
    @JoinColumn(name = "idMetodoPago")
    @OneToOne
    private Long idMetodoPago;

    @JoinColumn(name = "idDireccion")
    @OneToOne
    private Long idDireccion;

}
