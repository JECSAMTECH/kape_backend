package com.jecsamtech.kapebackend.service;

import com.jecsamtech.kapebackend.dto.ContactoRequest;
import com.jecsamtech.kapebackend.dto.ContactoResponse;
import com.jecsamtech.kapebackend.model.Contacto;
import com.jecsamtech.kapebackend.model.Usuario;
import com.jecsamtech.kapebackend.repository.ContactoRepository;
import com.jecsamtech.kapebackend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final UsuarioRepository usuarioRepository;

    public ContactoService(ContactoRepository contactoRepository, UsuarioRepository usuarioRepository) {
        this.contactoRepository = contactoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<ContactoResponse> findAll() {
        return contactoRepository.findAll()
                .stream()
                .map(ContactoResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContactoResponse findById(Long id) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contacto no encontrado"));
        return ContactoResponse.fromEntity(contacto);
    }

    @Transactional(readOnly = true)
    public List<ContactoResponse> findByUser(Long userId) {
        return contactoRepository.findByUsuario_IdUsuario(userId)
                .stream()
                .map(ContactoResponse::fromEntity)
                .toList();
    }

    @Transactional
    public ContactoResponse create(ContactoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Contacto contacto = new Contacto();
        contacto.setNombre(request.getNombre());
        contacto.setCorreo(request.getCorreo());
        contacto.setTelefono(request.getTelefono());
        contacto.setAsunto(request.getAsunto());
        contacto.setMensaje(request.getMensaje());
        contacto.setUsuario(usuario);
        contacto.setUsuarioRolIdRol(request.getUsuarioRolIdRol());

        return ContactoResponse.fromEntity(contactoRepository.save(contacto));
    }

    @Transactional
    public void delete(Long id) {
        if (!contactoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contacto no encontrado");
        }
        contactoRepository.deleteById(id);
    }
}