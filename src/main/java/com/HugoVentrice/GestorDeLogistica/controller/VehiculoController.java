package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.VehiculoDTO;
import com.HugoVentrice.GestorDeLogistica.model.Vehiculo;
import com.HugoVentrice.GestorDeLogistica.service.VehiculoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<VehiculoDTO> getAllVehiculos(){
        return vehiculoService.getAllVehiculos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public VehiculoDTO crearVehiculo(@RequestBody Vehiculo vehiculo){
        return vehiculoService.createVehiculo(vehiculo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void borrarVehiculo(@PathVariable long id) {
        vehiculoService.deleteVehiculo(id);
    }
}
