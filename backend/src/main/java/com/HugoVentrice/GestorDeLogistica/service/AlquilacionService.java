package com.HugoVentrice.GestorDeLogistica.service;


import com.HugoVentrice.GestorDeLogistica.DTO.*;
import com.HugoVentrice.GestorDeLogistica.model.*;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.PersonalRepository;
import com.HugoVentrice.GestorDeLogistica.repository.ProductoRepository;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static boolean seSolapan(LocalDateTime inicio1, LocalDateTime fin1, LocalDateTime inicio2, LocalDateTime fin2) {
        return !(fin1.isBefore(inicio2) || inicio1.isAfter(fin2));
    }

    private static String tipoDe(Producto producto) {
        if (producto instanceof Vehiculo) {
            return "VEHICULO";
        } else if (producto instanceof Sala) {
            return "SALA";
        } else {
            return "DESCONOCIDO";
        }
    }

    private List<ProductoDTO> toProductoDTOs(List<Producto> productos) {
        List<ProductoDTO> dtos = new ArrayList<>();
        for (Producto producto : productos) {
            dtos.add(new ProductoDTO(producto.getId(), producto.getNombre(), tipoDe(producto)));
        }
        return dtos;
    }

    private List<PersonalDTO> toPersonalDTOs(List<Personal> personal) {
        List<PersonalDTO> dtos = new ArrayList<>();
        for (Personal p : personal) {
            dtos.add(new PersonalDTO(p.getId(), p.getNombre(), p.getTipo()));
        }
        return dtos;
    }

    private AlquilacionDTO toDTO(Alquilacion alquilacion) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                alquilacion.getUsuario().getNombre(),
                alquilacion.getUsuario().getApellido(),
                alquilacion.getUsuario().getCorreo());
        return new AlquilacionDTO(
                alquilacion.getId(),
                toProductoDTOs(alquilacion.getProductos()),
                usuarioDTO,
                alquilacion.getFechaInicio(),
                alquilacion.getFechaFin(),
                toPersonalDTOs(alquilacion.getPersonal()));
    }

    public List<AlquilacionDTO> allAlquilaciones(){
        List<AlquilacionDTO> lista = new ArrayList<>();
        for (Alquilacion a : alquilacionRepository.findAll()){
            lista.add(toDTO(a));
        }
        return lista;
    }

    private void validarFechas(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null) {
            throw new RuntimeException("Fechas de inicio y fin son obligatorias");
        }
        if (!fin.isAfter(inicio)) {
            throw new RuntimeException("Fecha de FIN es anterior a la fecha de INICIO");
        }
    }

    private void comprobarSolapeProductos(List<Producto> productos, LocalDateTime inicio, LocalDateTime fin, long ignorarAlquilacionId) {
        for (Producto producto : productos) {
            for (Alquilacion a : alquilacionRepository.findByProductos(producto)) {
                if (a.getId() != ignorarAlquilacionId && seSolapan(inicio, fin, a.getFechaInicio(), a.getFechaFin())) {
                    throw new RuntimeException("El producto '" + producto.getNombre() + "' ya está reservado en esas fechas");
                }
            }
        }
    }

    private void comprobarSolapePersonal(List<Personal> personal, LocalDateTime inicio, LocalDateTime fin, long ignorarAlquilacionId) {
        for (Personal p : personal) {
            for (Alquilacion a : alquilacionRepository.findByPersonal(p)) {
                if (a.getId() != ignorarAlquilacionId && seSolapan(inicio, fin, a.getFechaInicio(), a.getFechaFin())) {
                    throw new RuntimeException("El empleado '" + p.getNombre() + "' ya está asignado en esas fechas");
                }
            }
        }
    }

    public AlquilacionDTO crearAlquilacion(CrearAlquilacionDTO dto){

        validarFechas(dto.getFechaInicio(), dto.getFechaFin());



        if (dto.getProductoIds() == null || dto.getProductoIds().isEmpty()) {
            throw new RuntimeException("La alquilación debe incluir al menos un producto");
        }

        List<Producto> productos = new ArrayList<>();
        for (Long productoId : dto.getProductoIds()) {
            productos.add(productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        }

        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");

        List<Personal> personalList = new ArrayList<>();
        if (dto.getPersonalIds() != null) {
            for (Long personalId : dto.getPersonalIds()) {
                personalList.add(personalRepository.findById(personalId).orElseThrow(() -> new RuntimeException("Personal no encontrado")));
            }
        }

        comprobarSolapeProductos(productos, dto.getFechaInicio(), dto.getFechaFin(), -1);
        comprobarSolapePersonal(personalList, dto.getFechaInicio(), dto.getFechaFin(), -1);

        Alquilacion alquilacion = new Alquilacion();
        alquilacion.setProductos(productos);
        alquilacion.setUsuario(usuario);
        alquilacion.setPersonal(personalList);
        alquilacion.setFechaInicio(dto.getFechaInicio());
        alquilacion.setFechaFin(dto.getFechaFin());

        alquilacionRepository.save(alquilacion);

        return toDTO(alquilacion);
    }

    public void deleteAlquilacion(long id){

        alquilacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Alquilación no encontrada"));

        alquilacionRepository.deleteById(id);
    }

    public AlquilacionDTO actualizarAlquilacion(long id, CrearAlquilacionDTO alquilacionUpdated){
        Alquilacion alquilacion = alquilacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Alquilacion con id '" + id + "' no encontrada"));

        validarFechas(alquilacionUpdated.getFechaInicio(), alquilacionUpdated.getFechaFin());

        if (alquilacionUpdated.getProductoIds() == null || alquilacionUpdated.getProductoIds().isEmpty()) {
            throw new RuntimeException("La alquilación debe incluir al menos un producto");
        }

        List<Producto> productos = new ArrayList<>();
        for (Long productoId : alquilacionUpdated.getProductoIds()) {
            productos.add(productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto con id '" + productoId + "' no encontrado")));
        }

        List<Personal> personal = new ArrayList<>();
        if (alquilacionUpdated.getPersonalIds() != null) {
            for (Long p : alquilacionUpdated.getPersonalIds()){
                personal.add(personalRepository.findById(p).orElseThrow(() -> new RuntimeException("Personal con id '" + p + "' no encontrado")));
            }
        }

        comprobarSolapeProductos(productos, alquilacionUpdated.getFechaInicio(), alquilacionUpdated.getFechaFin(), alquilacion.getId());
        comprobarSolapePersonal(personal, alquilacionUpdated.getFechaInicio(), alquilacionUpdated.getFechaFin(), alquilacion.getId());

        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");

        alquilacion.setUsuario(usuario);
        alquilacion.setProductos(productos);
        alquilacion.setPersonal(personal);
        alquilacion.setFechaInicio(alquilacionUpdated.getFechaInicio());
        alquilacion.setFechaFin(alquilacionUpdated.getFechaFin());

        alquilacionRepository.save(alquilacion);

        return toDTO(alquilacion);
    }
}
