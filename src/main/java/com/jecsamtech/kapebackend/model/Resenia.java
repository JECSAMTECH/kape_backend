package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resenia")
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resenias")
    private Long idResenias;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "comentario", length = 200)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Relación con DetallePedido
    @OneToOne(mappedBy = "resenia")
    private DetallePedido detallePedido;


    // Constructor vacío
    public Resenia() {
    }

    // Constructor
    public Resenia(Long idCliente, Long idProducto, Integer calificacion, String comentario, LocalDateTime fecha) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdReseñas() {
        return idResenias;
    }

    public void setIdResenias(Long idResenias) {
        this.idResenias = idResenias;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }
}