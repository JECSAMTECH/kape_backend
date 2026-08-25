package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.DetallePedidoDTO;
import com.jecsamtech.kapebackend.model.Cafe;
import com.jecsamtech.kapebackend.model.DetallePedido;
import com.jecsamtech.kapebackend.model.Pedido;
import com.jecsamtech.kapebackend.model.Resenia;
import com.jecsamtech.kapebackend.repository.CafeRepository;
import com.jecsamtech.kapebackend.repository.DetallePedidoRepository;
import com.jecsamtech.kapebackend.repository.PedidoRepository;
import com.jecsamtech.kapebackend.repository.ReseniaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final CafeRepository cafeRepository;
    private final PedidoRepository pedidoRepository;
    private final ReseniaRepository reseniaRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository,
                                CafeRepository cafeRepository,
                                PedidoRepository pedidoRepository,
                                ReseniaRepository reseniaRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.cafeRepository = cafeRepository;
        this.pedidoRepository = pedidoRepository;
        this.reseniaRepository = reseniaRepository;
    }

    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> findAll() {
        return detallePedidoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DetallePedidoDTO findById(Long id) {
        DetallePedido entity = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de pedido no encontrado"));
        return toDTO(entity);
    }

    public DetallePedidoDTO save(DetallePedidoDTO dto) {
        Cafe cafe = cafeRepository.findById(dto.getCafeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Café no encontrado"));
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        Resenia resenia = reseniaRepository.findById(dto.getReseniaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));


        DetallePedido entity = new DetallePedido();
        entity.setCafe(cafe);
        entity.setPedido(pedido);
        entity.setResenia(resenia);
        entity.setMolienda(dto.getMolienda());

        DetallePedido saved = detallePedidoRepository.save(entity);
        return toDTO(saved);
    }

    public DetallePedidoDTO update(Long id, DetallePedidoDTO dto) {

        DetallePedido entity = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de pedido no encontrado"));

        if (dto.getCafeId() != null && !entity.getCafe().getIdCafe().equals(dto.getCafeId())) {
            Cafe cafe = cafeRepository.findById(dto.getCafeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Café no encontrado"));
            entity.setCafe(cafe);
        }
        if (dto.getPedidoId() != null && !entity.getPedido().getIdPedido().equals(dto.getPedidoId())) {
            Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
            entity.setPedido(pedido);
        }
        if (dto.getReseniaId() != null && !entity.getResenia().getIdResenia().equals(dto.getReseniaId())) {
            Resenia resenia = reseniaRepository.findById(dto.getReseniaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));
            entity.setResenia(resenia);
        }

        if (dto.getMolienda() != null) {
            entity.setMolienda(dto.getMolienda());
        }

        DetallePedido updated = detallePedidoRepository.save(entity);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de pedido no encontrado");
        }
        detallePedidoRepository.deleteById(id);
    }

    private DetallePedidoDTO toDTO(DetallePedido entity) {
        return DetallePedidoDTO.builder()
                .idDetallePedido(entity.getIdDetallePedido())
                .cafeId(entity.getCafe().getIdCafe())
                .pedidoId(entity.getPedido().getIdPedido())
                .reseniaId(entity.getResenia().getIdResenia())
                .molienda(entity.getMolienda())
                .build();
    }
}