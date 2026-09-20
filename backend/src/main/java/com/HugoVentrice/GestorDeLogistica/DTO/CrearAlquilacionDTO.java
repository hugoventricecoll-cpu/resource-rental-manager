package com.HugoVentrice.GestorDeLogistica.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CrearAlquilacionDTO {

    private List<Long> productoIds;
    private List<Long> personalIds;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public CrearAlquilacionDTO(){

    }

    public CrearAlquilacionDTO(List<Long> productoIds, List<Long> personalIds, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.productoIds = productoIds;
        this.personalIds = personalIds;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public List<Long> getProductoIds() {
        return productoIds;
    }

    public void setProductoIds(List<Long> productoIds) {
        this.productoIds = productoIds;
    }

    public List<Long> getPersonalIds() {
        return personalIds;
    }

    public void setPersonalIds(List<Long> personalIds) {
        this.personalIds = personalIds;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
