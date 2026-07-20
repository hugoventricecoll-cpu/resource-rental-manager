package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.SalaDTO;
import com.HugoVentrice.GestorDeLogistica.model.Sala;
import com.HugoVentrice.GestorDeLogistica.service.SalaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sala")
public class SalaController {
    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public List<SalaDTO> getAllSalas(){
        return salaService.getAllSalas();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public SalaDTO createSala(@RequestBody Sala sala){
        return salaService.createSala(sala);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSala(@PathVariable long id) {
        salaService.deleteSala(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SalaDTO actualizarSala(@PathVariable long id, @RequestBody Sala sala) {
        return salaService.actualizarSala(id, sala);
    }
}
