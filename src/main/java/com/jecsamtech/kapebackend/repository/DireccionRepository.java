package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    // Buscar todas las direcciones de un usuario específico
    List<Direccion> findByUsuario_IdUsuario(Long idUsuario);

    // Buscar direcciones por ciudad
    List<Direccion> findByCiudad(String ciudad);

    // Buscar direcciones por estado
    List<Direccion> findByEstado(String estado);

    // Buscar direcciones por código postal
    List<Direccion> findByCodigoPostal(String codigoPostal);
}
