package com.jecsamtech.kapebackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="direccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @ManyToOne
    @JoinColumn(name ="id_usuario", nullable = false)
    private Usuario usuario;

    //calle
    @NotBlank(message = "Calle no puede estar vacío")
    @Column
    private String calle;

    //colonia
    @NotBlank(message = "Colonia no puede estar vacío")
    @Column
    private String colonia;

    //numero
    @NotBlank(message = "Número no puede estar vacío")
    @Column
    private String numero;

    //ciudad
    @NotBlank(message = "Ciudad no puede estar vacío")
    @Column
    private String ciudad;

    //estado
    @NotBlank(message = "Estado no puede estar vacío")
    @Column
    private String estado;

    //pais
    @NotBlank(message = "pais no puede estar vacío")
    @Column
    private String pais;

    //codigo postal
    @NotBlank(message = "Código Postal no puede estar vacío")
    @Column
    private String codigoPostal;

}
