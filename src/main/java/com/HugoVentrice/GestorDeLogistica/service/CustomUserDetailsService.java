package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.Security.UsuarioDetails;
import com.HugoVentrice.GestorDeLogistica.model.Usuario;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(username);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuario '" + username + "' no encontrado en la base de datos");
        }

        return new UsuarioDetails(usuario);
    }
}
