package com.HugoVentrice.GestorDeLogistica.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class AlquilacionDTO {

    private long id;
    private List<ProductoDTO> productos;
    private UsuarioDTO usuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<PersonalDTO> personal;

    public AlquilacionDTO(long id, List<ProductoDTO> productos, UsuarioDTO usuario, LocalDateTime fechaInicio, LocalDateTime fechaFin, List<PersonalDTO> personal) {
        this.id = id;
        this.productos = productos;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.personal = personal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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

    public List<PersonalDTO> getPersonal() {
        return personal;
    }

    public void setPersonal(List<PersonalDTO> personal) {
        this.personal = personal;
    }


}
