package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resenia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resenia")
    private Long idResenia;

    /*
    @ManyToOne
    @JoinColumn(name = "idDetallePedido", nullable = false )
    private Usuario usuario;
    */

    /*
    @OneToOne
    @JoinColumn(name = "id_cafe", nullable = false)
    private Cafe cafe;
    */

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "comentario", length = 200)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @OneToOne
    @JoinColumn(name = "idDetallePedido", nullable = false)
    private DetallePedido detallePedido;

}