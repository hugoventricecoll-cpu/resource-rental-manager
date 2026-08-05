package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.SalaDTO;
import com.HugoVentrice.GestorDeLogistica.model.Alquilacion;
import com.HugoVentrice.GestorDeLogistica.model.Sala;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final AlquilacionRepository alquilacionRepository;

    public SalaService(SalaRepository salaRepository, AlquilacionRepository alquilacionRepository) {
        this.salaRepository = salaRepository;
        this.alquilacionRepository = alquilacionRepository;
    }

    public List<SalaDTO> getAllSalas(){

        List<SalaDTO> lista = new ArrayList<>();

        for (Sala s : salaRepository.findAll()){
            lista.add(new SalaDTO(s.getAforo(), s.getUbicacion(), s.isDisponible(), s.getId(), s.getNombre()));
        }

        return lista;
    }

    public SalaDTO createSala(Sala sala){
        salaRepository.save(sala);

        return new SalaDTO(sala.getAforo(), sala.getUbicacion(), sala.isDisponible(), sala.getId(), sala.getNombre());
    }

    public void deleteSala(long id){
        List<Alquilacion> alquilacionList = alquilacionRepository.findByProducto(salaRepository.findById(id).orElseThrow
                (()-> new RuntimeException("Sala con id '" + id + "' no encontrada")));

        if (!alquilacionList.isEmpty()) {
            throw new RuntimeException("Sala con id '" + id + "' pertenece a una Alquilación, cancelar alquilación primero");
        }

        alquilacionRepository.deleteById(id);
    }

    public SalaDTO actualizarSala(long id, Sala sala){
        Sala sala1 = salaRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala con id '" + id + "' no encontrada"));

        sala1.setAforo(sala.getAforo());
        sala1.setDisponible(sala.isDisponible());
        sala1.setNombre(sala.getNombre());
        sala1.setUbicacion(sala.getUbicacion());

        salaRepository.save(sala1);

        return new SalaDTO(sala1.getAforo(), sala1.getUbicacion(), sala1.isDisponible(), sala1.getId(), sala1.getNombre());
    }
}
