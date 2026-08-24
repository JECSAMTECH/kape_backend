package com.jecsamtech.kapebackend.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cafe")
@Data //generar los setters, getters, toString, equals
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Constructor con todos los argumentos
@Builder
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cafe")
    private Long idCafe;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombreCafe;

    @Column(name = "descripcion", nullable = false, length = 125)
    private String descripcionCafe;

    //Atributo imagen
    @Column(name = "imagen", nullable = false, length = 2048)
    private String imagenCafe;

    @Column(name = "etiquetas", nullable = false, length = 120)
    private String etiquetasCafe;

    @Column(name = "tueste", nullable = false, length = 45)
    private String tuesteCafe;

    @Column(name = "notas_de_cata", nullable = false, length = 125)
    private String notasCataCafe;

    @Column( name = "intensidad", nullable = false)
    private Integer intensidadCafe;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCafe;

    @Column(name = "stock", nullable = false)
    private Integer stockCafe;
}
