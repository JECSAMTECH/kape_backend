package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Cafe;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    //Para buscar con nombres especificos
    //Optional<Cafe> findByNombreCafe(String nombreCafe);
    //PAra tener felixbilidad en busqueda
    List<Cafe> findByNombreCafeContainingIgnoreCase(String nombreCafe);
    List<Cafe> findByTuesteCafe(String tuesteCafe);

    List<Cafe> findByIntensidadCafe(Integer intensidadCafe);

    List<Cafe> findByPrecioCafeBetween(
            BigDecimal precioMin,
            BigDecimal precioMax
    );

    List<Cafe> findByStockCafeGreaterThan(Integer stock);
}
