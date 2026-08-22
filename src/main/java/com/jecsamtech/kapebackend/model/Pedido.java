package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @Column(nullable = false)
    private Long total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @Column(nullable = false)
    private String num_telefono;

    @Column(nullable = false)
    private TypoEnvio typo_envio;

    @Column(nullable = false)
    private Date fecha_pedido;

    @Column(nullable = true)
    private Date fecha_envio;

    @Column(nullable = true)
    private Date fecha_recibido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    public Pedido() {
    }

    public Pedido(Long id_pedido, Long total, Estatus estatus, String num_telefono, TypoEnvio typo_envio,
                  Date fecha_pedido, Date fecha_envio, Date fecha_recibido,
                  Usuario usuario, MetodoPago metodoPago, Direccion direccion) {
        this.id_pedido = id_pedido;
        this.total = total;
        this.estatus = estatus;
        this.num_telefono = num_telefono;
        this.typo_envio = typo_envio;
        this.fecha_pedido = fecha_pedido;
        this.fecha_envio = fecha_envio;
        this.fecha_recibido = fecha_recibido;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
        this.direccion = direccion;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }

    public TypoEnvio getTypo_envio() {
        return typo_envio;
    }

    public void setTypo_envio(TypoEnvio typo_envio) {
        this.typo_envio = typo_envio;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public Date getFecha_recibido() {
        return fecha_recibido;
    }

    public void setFecha_recibido(Date fecha_recibido) {
        this.fecha_recibido = fecha_recibido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_pedido=" + id_pedido +
                ", total=" + total +
                ", estatus=" + estatus +
                ", num_telefono='" + num_telefono + '\'' +
                ", typo_envio=" + typo_envio +
                ", fecha_pedido=" + fecha_pedido +
                ", fecha_envio=" + fecha_envio +
                ", fecha_recibido=" + fecha_recibido +
                ", usuario=" + (usuario != null ? usuario.getIdUsuario() : null) +
                ", metodoPago=" + (metodoPago != null ? metodoPago.getIdMetodoPago() : null) +
                ", direccion=" + (direccion != null ? direccion.getId_direccion() : null) +
                '}';
    }
}