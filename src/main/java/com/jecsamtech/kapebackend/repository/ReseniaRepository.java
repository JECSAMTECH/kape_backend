package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Cafe;
import com.jecsamtech.kapebackend.model.Resenia;
import com.jecsamtech.kapebackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {


    // List<Resenia> findByIdProducto(Cafe idCafe); // original
    // List<Resenia> findByDetallePedido_Cafe_IdCafe(Long idCafe);
    // List<Resenia> findByIdCliente(Usuario idUsuario); // origin
    // List<Resenia> findByDetallePedido_Pedido_Usuario_IdUsuario(Long idUsuario);
}