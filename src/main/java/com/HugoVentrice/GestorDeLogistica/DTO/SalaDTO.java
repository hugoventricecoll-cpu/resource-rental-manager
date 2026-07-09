package com.HugoVentrice.GestorDeLogistica.DTO;

import jakarta.persistence.Column;

public class SalaDTO {

    private int aforo;
    private String ubicacion;
    private boolean disponible;
    private long id;
    private String nombre;

    public SalaDTO(int aforo, String ubicacion, boolean disponible, long id, String nombre){
        this.aforo = aforo;
        this.ubicacion = ubicacion;
        this.disponible = disponible;
        this.id = id;
        this.nombre = nombre;
    }

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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
}
