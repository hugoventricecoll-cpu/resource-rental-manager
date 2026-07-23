package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.UsuarioDTO;
import com.HugoVentrice.GestorDeLogistica.model.Rol;
import com.HugoVentrice.GestorDeLogistica.model.Usuario;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private  UsuarioService usuarioService;

    @Test
    void getAllUsuarios(){

        Usuario user = new Usuario();
        user.setNombre("Miguel");
        user.setApellido("Garcia");
        user.setCorreo("MuiguelGarcia@test.com");
        user.setNumeroTel("111222333444");
        user.setPassword("1234");
        user.setRol(Rol.USER);

        List<Usuario> listaUsuarios = List.of(user);

        Mockito.when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        List<UsuarioDTO> resultado = usuarioService.getAllUsuarios();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Miguel");

    }
}
