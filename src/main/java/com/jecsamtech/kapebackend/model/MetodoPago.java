package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;

@Entity
@Table (name = "metodo_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long idMetodoPago;

    @Column(name = "tipo", length = 45, nullable = false)
    private String tipo;

    public MetodoPago() {

    }

    public MetodoPago(Long idMetodoPago, String tipo) {
        this.idMetodoPago = idMetodoPago;
        this.tipo = tipo;
    }

    public Long getIdMetodoPago() {
        return idMetodoPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdMetodoPago(Long idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idMetodoPago=" + idMetodoPago +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
