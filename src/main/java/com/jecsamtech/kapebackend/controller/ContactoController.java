package com.jecsamtech.kapebackend.controller;

import com.jecsamtech.kapebackend.dto.ContactoRequest;
import com.jecsamtech.kapebackend.dto.ContactoResponse;
import com.jecsamtech.kapebackend.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public List<ContactoResponse> findAll() {
        return contactoService.findAll();
    }

    @GetMapping("/{id}")
    public ContactoResponse findById(@PathVariable Long id) {
        return contactoService.findById(id);
    }

    @GetMapping("/usuario/{userId}")
    public List<ContactoResponse> findByUser(@PathVariable Long userId) {
        return contactoService.findByUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoResponse create(@Valid @RequestBody ContactoRequest request) {
        return contactoService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        contactoService.delete(id);
    }
}