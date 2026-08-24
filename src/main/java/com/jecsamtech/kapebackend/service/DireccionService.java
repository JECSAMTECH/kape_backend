package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.DireccionRequestDTO;
import com.jecsamtech.kapebackend.model.Direccion;
import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioService usuarioService;

    @Transactional(readOnly = true)
    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Direccion findById(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dirección no encontrada"));
    }

    @Transactional
    public Direccion create(DireccionRequestDTO dto) {
        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();

        Direccion direccion = Direccion.builder()
                .usuario(usuario)
                .calle(dto.getCalle())
                .colonia(dto.getColonia())
                .numero(dto.getNumero())
                .ciudad(dto.getCiudad())
                .estado(dto.getEstado())
                .pais(dto.getPais())
                .codigoPostal(dto.getCodigoPostal())
                .build();

        return direccionRepository.save(direccion);
    }

    @Transactional
    public Direccion update(Long id, DireccionRequestDTO dto) {
        Direccion direccion = findById(id);

        direccion.setCalle(dto.getCalle());
        direccion.setColonia(dto.getColonia());
        direccion.setNumero(dto.getNumero());
        direccion.setCiudad(dto.getCiudad());
        direccion.setEstado(dto.getEstado());
        direccion.setPais(dto.getPais());
        direccion.setCodigoPostal(dto.getCodigoPostal());

        return direccionRepository.save(direccion);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!direccionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dirección no encontrada");
        }

        direccionRepository.deleteById(id);
    }
}
