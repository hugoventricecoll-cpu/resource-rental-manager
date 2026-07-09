package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.SalaDTO;
import com.HugoVentrice.GestorDeLogistica.model.Sala;
import com.HugoVentrice.GestorDeLogistica.service.SalaService;
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

    @PostMapping
    public SalaDTO createSala(@RequestBody Sala sala){
        return salaService.createSala(sala);
    }

    @DeleteMapping("/{id}")
    public void deleteSala(long id) {
        salaService.deleteSala(id);
    }

    @PutMapping("/{id}")
    public SalaDTO actualizarSala(@PathVariable long id, @RequestBody Sala sala) {
        return salaService.actualizarSala(id, sala);
    }
}
