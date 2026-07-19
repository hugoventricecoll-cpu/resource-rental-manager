package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.LoginRequestDTO;
import com.HugoVentrice.GestorDeLogistica.model.Usuario;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginRequestDTO loginRequest) {

        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo());

        if (usuario == null) {
            throw new RuntimeException("Correo no encontrado");
        }


        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            return jwtService.generateToken(loginRequest.getCorreo());
        } else {
            throw new RuntimeException("La contraseña no es igual");
        }

    }
}
