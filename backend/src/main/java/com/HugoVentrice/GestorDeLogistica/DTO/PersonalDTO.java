package com.HugoVentrice.GestorDeLogistica.DTO;

import com.HugoVentrice.GestorDeLogistica.model.Tipo;

public class PersonalDTO {
    private long id;
    private String nombre;
    private Tipo tipo;
    private boolean disponible;

    public PersonalDTO(long id, String nombre, Tipo tipo, boolean disponible){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponible = disponible;
    }


    public boolean isDisponible() {return disponible;}

    public void setDisponible(boolean disponible) {this.disponible = disponible;}

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


