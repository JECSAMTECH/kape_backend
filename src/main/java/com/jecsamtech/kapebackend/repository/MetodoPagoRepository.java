package com.jecsamtech.kapebackend.repository;
import com.jecsamtech.kapebackend.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetodoPagoRepository extends JpaRepository <MetodoPago, Long> {
    Optional<MetodoPago> findByTipo(String tipo);
}
