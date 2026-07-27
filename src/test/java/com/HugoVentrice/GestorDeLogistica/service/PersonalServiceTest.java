package com.HugoVentrice.GestorDeLogistica.service;


import com.HugoVentrice.GestorDeLogistica.DTO.PersonalDTO;
import com.HugoVentrice.GestorDeLogistica.model.Personal;
import com.HugoVentrice.GestorDeLogistica.model.Tipo;
import com.HugoVentrice.GestorDeLogistica.repository.PersonalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonalServiceTest {

    @InjectMocks
    private PersonalService personalService;
    @Mock
    private PersonalRepository personalRepository;

    private Personal personal;

    @BeforeEach
    void setup(){
        personal.setNombre("Juan");
        personal.setTipo(Tipo.CHOFER);
        personal.setId(1L);
    }

    @Test
    void getAllPersonalTest(){
        List<PersonalDTO> personalList;

        Mockito.when(personalRepository.findAll()).thenReturn(List.of(personal));

        personalList = personalService.getAllPersonal();

        assertThat(personalList).isNotEmpty();
        Mockito.verify(personalRepository).findAll();
    }

    @Test
    void addPersonalTest(){
        personalService.addPersonal(personal);

        Mockito.verify(personalRepository).save(personal);
    }
}
