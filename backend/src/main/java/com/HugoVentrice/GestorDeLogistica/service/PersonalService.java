package com.HugoVentrice.GestorDeLogistica.service;
import com.HugoVentrice.GestorDeLogistica.DTO.PersonalDTO;
import com.HugoVentrice.GestorDeLogistica.model.Alquilacion;
import com.HugoVentrice.GestorDeLogistica.model.Personal;
import com.HugoVentrice.GestorDeLogistica.repository.AlquilacionRepository;
import com.HugoVentrice.GestorDeLogistica.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;
    private final AlquilacionRepository alquilacionRepository;

    public PersonalService(PersonalRepository personalRepository, AlquilacionRepository alquilacionRepository) {
        this.personalRepository = personalRepository;
        this.alquilacionRepository = alquilacionRepository;
    }

    public List<PersonalDTO> getAllPersonal(){
        List<PersonalDTO> list = new ArrayList<>();

        for (Personal p : personalRepository.findAll()){
            list.add(new PersonalDTO(p.getId(), p.getNombre(), p.getTipo()));
        }

        return list;
    }

    public PersonalDTO addPersonal(Personal personal){
        personalRepository.save(personal);

        return new PersonalDTO(personal.getId(), personal.getNombre(), personal.getTipo());
    }


    public void deletePersonal(long id) {

        List<Alquilacion> alquilacionConPersonal = alquilacionRepository.findByPersonal
                (personalRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe")));

        if (!alquilacionConPersonal.isEmpty()){
            throw new RuntimeException("Este empleado esta asignado a Alquilaciones");
        }

        personalRepository.deleteById(id);
    }

    public PersonalDTO actualizarPersonal(long id, Personal personal){
        Personal personal1 = personalRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe usuario con id: " + id));

        personal1.setTipo(personal.getTipo());
        personal1.setNombre(personal.getNombre());

        personalRepository.save(personal);

        return new PersonalDTO(personal1.getId(), personal1.getNombre(), personal1.getTipo());
    }

}