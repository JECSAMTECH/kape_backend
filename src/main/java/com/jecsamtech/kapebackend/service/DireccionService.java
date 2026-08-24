package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.DireccionRequestDTO;
import com.jecsamtech.kapebackend.dto.DireccionResponseDTO;
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
    public List<DireccionResponseDTO> findAll() {
        return direccionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DireccionResponseDTO findById(Long id) {
        return toResponseDTO(findEntityById(id));
    }

    @Transactional
    public DireccionResponseDTO create(DireccionRequestDTO dto) {
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

        return toResponseDTO(direccionRepository.save(direccion));
    }

    @Transactional
    public DireccionResponseDTO update(Long id, DireccionRequestDTO dto) {
        Direccion direccion = findEntityById(id);

        direccion.setCalle(dto.getCalle());
        direccion.setColonia(dto.getColonia());
        direccion.setNumero(dto.getNumero());
        direccion.setCiudad(dto.getCiudad());
        direccion.setEstado(dto.getEstado());
        direccion.setPais(dto.getPais());
        direccion.setCodigoPostal(dto.getCodigoPostal());

        return toResponseDTO(direccionRepository.save(direccion));
    }

    private Direccion findEntityById(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dirección no encontrada"));
    }

    private DireccionResponseDTO toResponseDTO(Direccion direccion) {
        return DireccionResponseDTO.builder()
                .idDireccion(direccion.getIdDireccion())
                .calle(direccion.getCalle())
                .colonia(direccion.getColonia())
                .numero(direccion.getNumero())
                .ciudad(direccion.getCiudad())
                .estado(direccion.getEstado())
                .pais(direccion.getPais())
                .codigoPostal(direccion.getCodigoPostal())
                .build();
    }

    @Transactional
    public void deleteById(Long id) {
        Direccion direccion = findEntityById(id);
        direccionRepository.delete(direccion);
    }
}
