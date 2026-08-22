package com.jecsamtech.kapebackend.repository;

import com.jecsamtech.kapebackend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{


}
