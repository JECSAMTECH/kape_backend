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
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(nullable = false, length = 45)
    private String nombre;

    @NotBlank(message = "La descripción no puede ir vacia")
    @Column(nullable = false, length = 125)
    private String descripcion;

    //Atributo imagen
    @NotBlank(message = "La imagen es obligatoria")
    @Column(nullable = false, length = 2048)
    private String imagen;

    @NotNull(message = "El percio no puede ir vacio")
    @PositiveOrZero(message = "Debe ser mayor o igual que cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "Stock del producto no puede estar vacio")
    @PositiveOrZero(message = "Debe ser igual o mayor que cero")
    @Column(nullable = false)
    private Integer stock;

    @NotBlank(message = "Campo etiquetas no puede ir vacio o ser nulo")
    @Column(nullable = false, length = 120)
    private String etiquetas;

    @NotBlank(message = "El tueste no puede ir vacio")
    @Column(nullable = false, length = 45)
    private String tueste;

    @NotBlank(message = "La nota_cata no puede ir vacia")
    @Column(name = "notas_de_cata", nullable = false, length = 125)
    private String notasCata;

    @NotNull(message = "La intensidad no puede ser nulo o vacio")
    @Positive(message = "Debe ser mayor que cero la intensidad")
    @Max(value = 3, message = "No puede ser mayor a 3 la intensidad")
    @Column(nullable = false)
    private Integer intensidad;

}
