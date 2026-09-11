package com.HugoVentrice.GestorDeLogistica.model;

import jakarta.persistence.*;

@Entity
public class Personal {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean disponible;

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


