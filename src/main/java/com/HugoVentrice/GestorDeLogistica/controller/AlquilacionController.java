package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.AlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.DTO.CrearAlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.model.Alquilacion;
import com.HugoVentrice.GestorDeLogistica.service.AlquilacionService;
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

    @PostMapping
    public AlquilacionDTO saveAlquilacion(@RequestBody CrearAlquilacionDTO alquilacion) {
        return alquilacionService.crearAlquilacion(alquilacion);
    }

    @DeleteMapping("/{id}")
    public void deleteAlquilacion(long id) {
        alquilacionService.deleteAlquilacion(id);
    }

    @PutMapping("/{id}")
    public AlquilacionDTO actualizarAlquilacion(@PathVariable long id, @RequestBody CrearAlquilacionDTO alquilacion){
        return alquilacionService.actualizarAlquilacion(id, alquilacion);
    }
}
