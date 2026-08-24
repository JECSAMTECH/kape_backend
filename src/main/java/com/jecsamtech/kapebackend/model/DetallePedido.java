package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detallePedido")
    private Long idDetallePedido;

    @ManyToOne
    @JoinColumn(name = "id_cafe", nullable = false)
    private Cafe cafe;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "id_resenias", nullable = false)
    private Resenia resenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "molienda", nullable = false)
    private Molienda molienda;

  
    }
}