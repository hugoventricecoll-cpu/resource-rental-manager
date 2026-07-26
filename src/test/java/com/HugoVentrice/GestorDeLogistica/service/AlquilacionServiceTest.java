package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.AlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.DTO.CrearAlquilacionDTO;
import com.HugoVentrice.GestorDeLogistica.model.*;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.PersonalRepository;
import com.HugoVentrice.GestorDeLogistica.repository.ProductoRepository;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AlquilacionServiceTest {

    @InjectMocks
    private AlquilacionService alquilacionService;
    @Mock
    private AlquilacionRepository alquilacionRepository;
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PersonalRepository personalRepository;

    private Alquilacion alquilacionDePrueba;

    @BeforeEach
    void setUp() {
        Sala sala = new Sala();
        sala.setId(1L);
        sala.setNombre("Sala A");
        sala.setDisponible(true);

        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setApellido("Garcia");
        usuario.setCorreo("miguel@test.com");

        alquilacionDePrueba = new Alquilacion();
        alquilacionDePrueba.setId(1L);
        alquilacionDePrueba.setProducto(sala);
        alquilacionDePrueba.setUsuario(usuario);
        alquilacionDePrueba.setFechaInicio(LocalDateTime.of(2026, 8, 10, 10, 0));
        alquilacionDePrueba.setFechaFin(LocalDateTime.of(2026, 8, 15, 18, 0));
        alquilacionDePrueba.setPersonal(new ArrayList<>());
    }

    @Test
    void findAll(){
        List<Alquilacion> alquilacionList = List.of(alquilacionDePrueba);

        Mockito.when(alquilacionRepository.findAll()).thenReturn(alquilacionList);

        List<AlquilacionDTO> alquilacionListDTO = alquilacionService.allAlquilaciones();

        assertThat(alquilacionListDTO).isNotEmpty();
        assertThat(alquilacionListDTO.getFirst().getUsuario().getNombre()).isEqualTo("Miguel");
        Mockito.verify(alquilacionRepository).findAll();
    }

    @Test
    void crearAlquilacion(){

        CrearAlquilacionDTO dto = new CrearAlquilacionDTO();
        dto.setFechaFin(alquilacionDePrueba.getFechaFin());
        dto.setFechaInicio(alquilacionDePrueba.getFechaInicio());
        dto.setProductoId(alquilacionDePrueba.getProducto().getId());
        dto.setUsuarioId(alquilacionDePrueba.getUsuario().getId());

        dto.setPersonalIds(new ArrayList<>());

        Mockito.when(productoRepository.findById(dto.getProductoId()))
                .thenReturn(Optional.of(alquilacionDePrueba.getProducto()));

        Mockito.when(usuarioRepository.findById(dto.getUsuarioId()))
                .thenReturn(Optional.of(alquilacionDePrueba.getUsuario()));

        Mockito.when(alquilacionRepository.findByProducto(alquilacionDePrueba.getProducto()))
                .thenReturn(new ArrayList<>());

        Mockito.when(alquilacionRepository.save(Mockito.any(Alquilacion.class)))
                .thenReturn(alquilacionDePrueba);

        AlquilacionDTO resultado = alquilacionService.crearAlquilacion(dto);

        assertThat(resultado).isNotNull();
        Mockito.verify(alquilacionRepository).save(Mockito.any(Alquilacion.class));
    }

}
