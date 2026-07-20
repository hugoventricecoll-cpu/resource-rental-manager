package com.HugoVentrice.GestorDeLogistica.controller;

import com.HugoVentrice.GestorDeLogistica.DTO.PersonalDTO;
import com.HugoVentrice.GestorDeLogistica.model.Personal;
import com.HugoVentrice.GestorDeLogistica.service.PersonalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public List<PersonalDTO> getAllPersonal() {
        return personalService.getAllPersonal();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public PersonalDTO addPersonal(@RequestBody Personal personal){
        return personalService.addPersonal(personal);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePersonal(@PathVariable long id){
        personalService.deletePersonal(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public PersonalDTO updatePersonal(@PathVariable long id, @RequestBody Personal personal){
        return personalService.actualizarPersonal(id, personal);
    }

}
