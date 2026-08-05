package com.HugoVentrice.GestorDeLogistica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Sala extends Producto {

    @Column(nullable = false)
    private int aforo;

    @Column(nullable = false)
    private String ubicacion;

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
