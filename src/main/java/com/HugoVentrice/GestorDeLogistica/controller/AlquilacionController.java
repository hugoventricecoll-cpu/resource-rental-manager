package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.AlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.DTO.CrearAlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.service.AlquilacionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquilacion")
public class AlquilacionController {

    private final AlquilacionService alquilacionService;

    public AlquilacionController(AlquilacionService alquilacionService){
        this.alquilacionService = alquilacionService;
    }

    @GetMapping
    public List<AlquilacionDTO> getAllAlquilaciones(){
        return alquilacionService.allAlquilaciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public AlquilacionDTO saveAlquilacion(@RequestBody CrearAlquilacionDTO alquilacion) {
        return alquilacionService.crearAlquilacion(alquilacion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAlquilacion(@PathVariable long id) {
        alquilacionService.deleteAlquilacion(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AlquilacionDTO actualizarAlquilacion(@PathVariable long id, @RequestBody CrearAlquilacionDTO alquilacion){
        return alquilacionService.actualizarAlquilacion(id, alquilacion);
    }
}