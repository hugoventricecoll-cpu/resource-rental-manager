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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    static Usuario user = new Usuario();
    static {
        user.setId(99999999);
        user.setNombre("Miguel");
        user.setApellido("Garcia");
        user.setCorreo("MuiguelGarcia@test.com");
        user.setNumeroTel("111222333444");
        user.setPassword("1234");
    }

    @Mock // Una clase falsa, VACÍA, es solo para que el programa no suelte errores
    private UsuarioRepository usuarioRepository;

    @InjectMocks // Es la clase REAL, con todos los métodos que tiene dentro la clase real [ej: usuarioService.getAllUsuarios()]
    private  UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    /*

    Mock lo usamos en la "cosa" que obviamos que funciona,
    es decir Mockeamos las dependencias de la clase que queremos probar.
         (ej: si queremos probar usuarioService, obviamos que UsuarioRepository funciona, asi que creamos uno falso un "mock" vacío,
          para que nosotros nos "inventemos" que hace cada método).

    Ya que si usásemos @InjectMocks con todas las cosas (que se PUEDE, excepto con interfaces/objetos que no sean clases)
    tendrías que también añadir su SUBCAPA... ¡Superineficiente!

     */

    @Test
    void getAllUsuarios(){

        List<Usuario> listaUsuarios = List.of(user);

        Mockito.when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        List<UsuarioDTO> resultado = usuarioService.getAllUsuarios();

        assertThat(resultado).isNotEmpty();
    }

    @Test
    void borrarUsuario(){
        usuarioService.borrarUsuario(user.getId());

        /*
         el métodо que pruebo usa lo que le devuelve el repositorio para producir un resultado? -> entonces when + assertThat sobre ese resultado
         o simplemente le pasa datos al repositorio sin esperar nada de vuelta? -> entonces verify
         */
        Mockito.verify(usuarioRepository).deleteById(user.getId());
    }

}
