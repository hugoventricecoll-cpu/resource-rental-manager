package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.AlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.DTO.CrearAlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.service.AlquilacionService;
import com.HugoVentrice.GestorDeLogistica.service.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquilacion")
public class AlquilacionController {

    private final AlquilacionService alquilacionService;

    public AlquilacionController(AlquilacionService alquilacionService){
        this.alquilacionService = alquilacionService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<AlquilacionDTO> getAllAlquilaciones(){
        return alquilacionService.allAlquilaciones();
    }

    // El endpoint de "getAllAlquilaciones" es un riesgo de privacidad. Cualquier persona puede acceder a TODAS las alquilaciones de la bd.
    // Aquí abajo pongo el ejemplo de como sería el endpoint para los usuarios, de forma segura evitando compartir todas las alquilaciones de la bd.

    // Actualización: Me he dado cuenta de que lo de abajo tampoco es seguro xdddd, cualquiera puede poner el ID que les dé la gana y ver la alquilación de quien le dé la gana 🗣 🗣
    // !!!!!!!!! ARREGLAR EN ALGÚN MOMENTO !!!!!!!!!!!! (Por ahora se queda así)

    /*
        @GetMapping("/{id}")
        public List<AlquilacionDTO> getAllAlquilacionesUser(long id){
            alquilacionService.allAlquilacionesUSer(id);
        }
    */

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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