package com.jecsamtech.kapebackend.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombreCafe;

    @NotBlank(message = "La descripción no puede ir vacia")
    @Column(name = "descripcion", nullable = false, length = 125)
    private String descripcionCafe;

    //Atributo imagen
    @NotBlank(message = "La imagen es obligatoria")
    @Column(name = "imagen", nullable = false, length = 2048)
    private String imagenCafe;

    @NotBlank(message = "Campo etiquetas no puede ir vacio o ser nulo")
    @Column(name = "etiquetas", nullable = false, length = 120)
    private String etiquetasCafe;

    @NotBlank(message = "El tueste no puede ir vacio")
    @Column(name = "tueste", nullable = false, length = 45)
    private String tuesteCafe;

    @NotBlank(message = "La nota_cata no puede ir vacia")
    @Column(name = "notas_de_cata", nullable = false, length = 125)
    private String notasCataCafe;

    @NotNull(message = "La intensidad no puede ser nulo o vacio")
    @Positive(message = "Debe ser mayor que cero la intensidad")
    @Max(value = 3, message = "No puede ser mayor a 3 la intensidad")
    @Column( name = "intensidad", nullable = false)
    private Integer intensidadCafe;


    @NotNull(message = "El percio no puede ir vacio")
    @PositiveOrZero(message = "Debe ser mayor o igual que cero")
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCafe;

    @NotNull(message = "Stock del producto no puede estar vacio")
    @PositiveOrZero(message = "Debe ser igual o mayor que cero")
    @Column(name = "stock", nullable = false)
    private Integer stockCafe;
}
