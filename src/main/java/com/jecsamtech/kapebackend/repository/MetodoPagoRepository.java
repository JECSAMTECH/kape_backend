package com.jecsamtech.kapebackend.repository;
import com.jecsamtech.kapebackend.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetodoPagoRepository extends JpaRepository <MetodoPago, Long> {
    Optional<MetodoPago> findByTipo(String tipo);
}
