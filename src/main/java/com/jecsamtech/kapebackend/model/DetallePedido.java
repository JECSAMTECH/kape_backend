package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetallePedido;

    @ManyToOne
    @JoinColumn(name = "idCafe", nullable = false)
    private Cafe cafe;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido cafe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResenia;

    @OneToOne
    @JoinColumn(name = "idCafe", nullable = false)
    private Cafe cafe;

    private Cafe cafe;


}
