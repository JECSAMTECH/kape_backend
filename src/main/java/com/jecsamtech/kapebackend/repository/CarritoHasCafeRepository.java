package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.CarritoHasCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoHasCafeRepository extends JpaRepository<CarritoHasCafe, Long> {
    List<CarritoHasCafe> findByCarritoId(Long carritoId);
    Optional<CarritoHasCafe> findByCarritoIdAndCafeId(Long carritoId, Long cafeId);
    void deleteByCarritoIdAndCafeId(Long carritoId, Long cafeId);
}
