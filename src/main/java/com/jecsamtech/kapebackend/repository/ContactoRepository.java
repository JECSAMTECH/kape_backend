package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    // Buscar todos los contactos hechos por un usuario específico
    List<Contacto> findByUsuario_IdUsuario(Integer idUsuario);

    // Buscar contactos por correo
    List<Contacto> findByCorreo(String correo);

    // Buscar contactos por asunto
    List<Contacto> findByAsuntoContainingIgnoreCase(String asunto);

}