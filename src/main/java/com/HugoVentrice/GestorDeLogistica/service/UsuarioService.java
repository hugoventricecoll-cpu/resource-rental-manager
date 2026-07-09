package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.UsuarioDTO;
import com.HugoVentrice.GestorDeLogistica.model.Alquilacion;
import com.HugoVentrice.GestorDeLogistica.model.Usuario;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final AlquilacionRepository alquilacionRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, AlquilacionRepository alquilacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alquilacionRepository = alquilacionRepository;
    }

    public List<UsuarioDTO> getAllUsuarios(){

        List<UsuarioDTO> listaDeUsuariosDTO = new ArrayList<>();

        for (Usuario u : usuarioRepository.findAll()){
            listaDeUsuariosDTO.add(new UsuarioDTO(u.getNombre(), u.getApellido(), u.getCorreo()));
        }

        return listaDeUsuariosDTO;
    }

    public UsuarioDTO crearUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo());
    }

    public void borrarUsuario(long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Alquilacion> alquilacionesDelUsuario = alquilacionRepository.findByUsuario(usuario);

        if (!alquilacionesDelUsuario.isEmpty()) {
            throw new RuntimeException("Usuario con Alquilación activa, cancelar alquilación primero");
        }

        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO actualizarUsuario(Usuario usuario, long id){

        Usuario usuarioOriginal = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioOriginal.setNombre(usuario.getNombre());
        usuarioOriginal.setNumeroTel(usuario.getNumeroTel());
        usuarioOriginal.setCorreo(usuario.getCorreo());
        usuarioOriginal.setApellido(usuario.getApellido());

        usuarioRepository.save(usuarioOriginal);

        return new UsuarioDTO(usuarioOriginal.getNombre(), usuarioOriginal.getApellido(), usuarioOriginal.getCorreo());
    }
}
