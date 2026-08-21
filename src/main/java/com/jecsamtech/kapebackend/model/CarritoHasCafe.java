package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carrito_has_cafe", uniqueConstraints = @UniqueConstraint(columnNames = {"carrito_id", "cafe_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarritoHasCafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrito_id", nullable = false)
    private Long carritoId;

    @Column(name = "cafe_id", nullable = false)
    private Long cafeId;

    @Column(nullable = false)
    private Integer cantidad;
}
