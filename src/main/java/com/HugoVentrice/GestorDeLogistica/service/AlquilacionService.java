package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.*;
import com.HugoVentrice.GestorDeLogistica.model.*;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.PersonalRepository;
import com.HugoVentrice.GestorDeLogistica.repository.ProductoRepository;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlquilacionService {

    private final AlquilacionRepository alquilacionRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PersonalRepository personalRepository;


    public AlquilacionService(AlquilacionRepository alquilacionRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository, PersonalRepository personalRepository){
        this.alquilacionRepository = alquilacionRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.personalRepository = personalRepository;
    }

    public List<AlquilacionDTO> allAlquilaciones(){

        List<AlquilacionDTO> lista = new ArrayList<>();

        for (Alquilacion a : alquilacionRepository.findAll()){

            List<PersonalDTO> personalDTOs = new ArrayList<>();

            for (Personal p : a.getPersonal()){
                personalDTOs.add(new PersonalDTO(p.getId(), p.getNombre(), p.getTipo()));
            }

            String tipoProducto;
            if (a.getProducto() instanceof Vehiculo) {
                tipoProducto = "VEHICULO";
            } else if (a.getProducto() instanceof Sala) {
                tipoProducto = "SALA";
            } else {
                tipoProducto = "DESCONOCIDO";
            }

            UsuarioDTO usuarioDTO = new UsuarioDTO(a.getUsuario().getNombre(), a.getUsuario().getApellido(),a.getUsuario().getCorreo());
            ProductoDTO productoDTO = new ProductoDTO(a.getProducto().getId(),a.getProducto().getNombre(),a.getProducto().isDisponible(), tipoProducto);

            lista.add(new AlquilacionDTO(a.getId(), productoDTO, usuarioDTO, a.getFechaInicio(), a.getFechaFin(), personalDTOs));
        }

        return lista;
    }

    public AlquilacionDTO crearAlquilacion(CrearAlquilacionDTO dto){

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Personal> personalList = new ArrayList<>();

        for (Long personalId : dto.getPersonalIds()) {

            Personal p = personalRepository.findById(personalId)
                    .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

            personalList.add(p);
        }

        Alquilacion alquilacion = new Alquilacion();
        alquilacion.setProducto(producto);
        alquilacion.setUsuario(usuario);
        alquilacion.setPersonal(personalList);
        alquilacion.setFechaInicio(dto.getFechaInicio());
        alquilacion.setFechaFin(dto.getFechaFin());

        List<Alquilacion> alquilacionesDelProducto = alquilacionRepository.findByProducto(alquilacion.getProducto());

        boolean haySolapacion = false;

        for (Alquilacion a : alquilacionesDelProducto) {
            if (!(alquilacion.getFechaFin().isBefore(a.getFechaInicio()) || alquilacion.getFechaInicio().isAfter(a.getFechaFin()))) {
                haySolapacion = true;
                break;
            }
        }

        if (alquilacion.getFechaFin().isAfter(alquilacion.getFechaInicio()) && !haySolapacion) {

            alquilacionRepository.save(alquilacion);

            List<PersonalDTO> personalDTOs = new ArrayList<>();

            for (Personal p : alquilacion.getPersonal()){
                personalDTOs.add(new PersonalDTO(p.getId(),p.getNombre(),p.getTipo()));
            }

            String tipoProducto;
            if (alquilacion.getProducto() instanceof Vehiculo) {
                tipoProducto = "VEHICULO";
            } else if (alquilacion.getProducto() instanceof Sala) {
                tipoProducto = "SALA";
            } else {
                tipoProducto = "DESCONOCIDO";
            }

            UsuarioDTO usuarioDTO = new UsuarioDTO(alquilacion.getUsuario().getNombre(), alquilacion.getUsuario().getApellido(),alquilacion.getUsuario().getCorreo());
            ProductoDTO productoDTO = new ProductoDTO(alquilacion.getProducto().getId(),alquilacion.getProducto().getNombre(),alquilacion.getProducto().isDisponible(), tipoProducto);

            return new AlquilacionDTO(
                    alquilacion.getId(),
                    productoDTO,
                    usuarioDTO,
                    alquilacion.getFechaInicio(),
                    alquilacion.getFechaFin(),
                    personalDTOs
            );

        } else if (!(alquilacion.getFechaFin().isAfter(alquilacion.getFechaInicio()))) {
            throw new RuntimeException("Fecha de FIN es anterior a la fecha de INICIO");
        } else {
            throw new RuntimeException("Hay solapación con otra alquilación");
        }
    }

    public void deleteAlquilacion(long id){

        Alquilacion alquilacion = alquilacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Alquilación no encontrada"));

        alquilacionRepository.deleteById(id);

    }

    public AlquilacionDTO actualizarAlquilacion(long id, CrearAlquilacionDTO alquilacionUpdated){
        Alquilacion alquilacion = alquilacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Alquilacion con id '" + id + "' no encontrada"));

        alquilacion.setUsuario(usuarioRepository.findById(alquilacionUpdated.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario con id '" + id + "' no encontrada")));
        alquilacion.setProducto(productoRepository.findById(alquilacionUpdated.getProductoId()).orElseThrow(() -> new RuntimeException("Producto con id '" + id + "' no encontrada")));

        boolean haySolapacion = false;
        for (Alquilacion a : alquilacionRepository.findByProducto(alquilacion.getProducto())) {
            if ( a.getId()!= alquilacion.getId() && !(alquilacion.getFechaFin().isBefore(a.getFechaInicio()) || alquilacion.getFechaInicio().isAfter(a.getFechaFin()))) {
                haySolapacion = true;
                break;
            }
        }

        if (alquilacionUpdated.getFechaFin().isAfter(alquilacionUpdated.getFechaInicio()) && !haySolapacion){
             alquilacion.setFechaFin(alquilacionUpdated.getFechaFin());
             alquilacion.setFechaInicio(alquilacionUpdated.getFechaInicio());
        } else if (!alquilacionUpdated.getFechaFin().isAfter(alquilacionUpdated.getFechaInicio())) {
            throw new RuntimeException("Fecha de FIN es anterior a la fecha de INICIO");
        } else {
            throw new RuntimeException("Error actualizando alquilacion: Fecha elegida solapa con otra alquilacion");
        }

        List<Personal> personal = new ArrayList<>();

        for (Long p : alquilacionUpdated.getPersonalIds()){
            personal.add(personalRepository.findById(p).orElseThrow(() -> new RuntimeException("Id no encontrada")));
        }

        alquilacion.setPersonal(personal);

        alquilacionRepository.save(alquilacion);

        String tipo = (alquilacion.getProducto() instanceof  Vehiculo) ? "Vehiculo" : "Sala";

        List<PersonalDTO> personalDTOList = new ArrayList<>();

        for (Personal  p : alquilacion.getPersonal()) {
            personalDTOList.add(new PersonalDTO(p.getId(),p.getNombre(),p.getTipo()));
        }

        return new AlquilacionDTO(alquilacion.getId(), new ProductoDTO(alquilacion.getProducto().getId(), alquilacion.getProducto().getNombre(), alquilacion.getProducto().isDisponible(), tipo), new UsuarioDTO(alquilacion.getUsuario().getNombre(), alquilacion.getUsuario().getApellido(), alquilacion.getUsuario().getCorreo()), alquilacion.getFechaInicio(), alquilacion.getFechaFin(), personalDTOList);
    }
}
