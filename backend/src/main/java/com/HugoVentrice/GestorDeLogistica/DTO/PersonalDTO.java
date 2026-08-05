package com.HugoVentrice.GestorDeLogistica.DTO;

import com.HugoVentrice.GestorDeLogistica.model.Tipo;

public class PersonalDTO {
    private long id;
    private String nombre;
    private Tipo tipo;

    public PersonalDTO(long id, String nombre, Tipo tipo){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


}


