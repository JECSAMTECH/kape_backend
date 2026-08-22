package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {


    List<Resenia> findByIdProducto(Long idProducto);
    List<Resenia> findByIdCliente(Long idCliente);
}