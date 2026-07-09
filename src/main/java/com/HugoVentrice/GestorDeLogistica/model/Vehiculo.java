package com.HugoVentrice.GestorDeLogistica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Vehiculo extends Producto {

    @Column(nullable = false)
    private int kilometraje;

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private int plazas;

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }


}
