package com.HugoVentrice.GestorDeLogistica.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CrearAlquilacionDTO {

    private Long productoId;
    private Long usuarioId;
    private List<Long> personalIds;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public CrearAlquilacionDTO(){

    }


    public CrearAlquilacionDTO(Long productoId, Long usuarioId, List<Long> personalIds, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.productoId = productoId;
        this.usuarioId = usuarioId;
        this.personalIds = personalIds;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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
