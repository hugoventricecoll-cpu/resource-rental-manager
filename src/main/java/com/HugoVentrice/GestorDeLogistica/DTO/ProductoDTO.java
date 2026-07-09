package com.HugoVentrice.GestorDeLogistica.DTO;

import com.HugoVentrice.GestorDeLogistica.model.Producto;

public class ProductoDTO {
    private long id;
    private String nombre;
    private boolean disponible;
    private String tipo;

    public ProductoDTO( long id, String nombre, boolean disponible, String tipo){
        this.id = id;
        this.nombre = nombre;
        this.disponible = disponible;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
