package com.HugoVentrice.GestorDeLogistica.service;

import com.HugoVentrice.GestorDeLogistica.DTO.VehiculoDTO;

import com.HugoVentrice.GestorDeLogistica.model.Alquilacion;
import com.HugoVentrice.GestorDeLogistica.model.Vehiculo;

import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final AlquilacionRepository alquilacionRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, AlquilacionRepository alquilacionRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.alquilacionRepository = alquilacionRepository;
    }

    public List<VehiculoDTO> getAllVehiculos(){
        List<VehiculoDTO> lista = new ArrayList<>();

        for (Vehiculo v : vehiculoRepository.findAll()){
            lista.add(new VehiculoDTO(v.getKilometraje(), v.getMatricula(), v.getPlazas(), v.isDisponible(), v.getId(), v.getNombre()));
        }

        return lista;
    }

    public VehiculoDTO createVehiculo(Vehiculo v){
        vehiculoRepository.save(v);

        return new VehiculoDTO(v.getKilometraje(), v.getMatricula(), v.getPlazas(), v.isDisponible(), v.getId(), v.getNombre());
    }

    public void deleteVehiculo(long id) {

        List<Alquilacion> alquilacionList = alquilacionRepository.findByProducto(vehiculoRepository.findById(id).orElseThrow
                (()-> new RuntimeException("Error al borrar de la lista: Coche con id '" + id + "' no encontrado en Alquilaciones")));

        if (!alquilacionList.isEmpty()) {
            throw new RuntimeException("Error al borrar de la lista: Coche con id '"  + id + "' pertenece a una Alquilación");
        }

        vehiculoRepository.deleteById(id);
    }

    public VehiculoDTO actualizarVehiculo(long id, Vehiculo vehiculo) {
        Vehiculo vehiculo1 = vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehiculo con id '" + id + "' no encontrado"));

        vehiculo1.setKilometraje(vehiculo.getKilometraje());
        vehiculo1.setMatricula(vehiculo.getMatricula());
        vehiculo1.setPlazas(vehiculo.getPlazas());
        vehiculo1.setNombre(vehiculo.getNombre());
        vehiculo1.setDisponible(vehiculo.isDisponible());
        
        vehiculoRepository.save(vehiculo1);

        return new VehiculoDTO(vehiculo1.getKilometraje(), vehiculo1.getMatricula(), vehiculo1.getPlazas(), vehiculo1.isDisponible(), vehiculo1.getId(), vehiculo1.getNombre());
    }
}
