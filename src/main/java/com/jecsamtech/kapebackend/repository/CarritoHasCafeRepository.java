package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.CarritoHasCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoHasCafeRepository
        extends JpaRepository<CarritoHasCafe, Long> {

    List<CarritoHasCafe> findByCarrito_IdCarrito(Long carritoId);

    Optional<CarritoHasCafe> findByCarrito_IdCarritoAndCafe_IdCafe(
            Long carritoId,
            Long cafeId
    );

}
