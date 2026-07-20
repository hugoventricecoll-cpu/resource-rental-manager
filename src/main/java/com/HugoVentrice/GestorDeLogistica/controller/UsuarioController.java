package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.UsuarioDTO;
import com.HugoVentrice.GestorDeLogistica.model.Usuario;
import com.HugoVentrice.GestorDeLogistica.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable long id){
        usuarioService.borrarUsuario(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UsuarioDTO actualizarUsuario(@RequestBody  Usuario usuario, @PathVariable long id) {
        return usuarioService.actualizarUsuario(usuario, id);
    }
}