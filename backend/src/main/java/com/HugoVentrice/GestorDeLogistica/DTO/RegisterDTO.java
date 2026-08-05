package com.HugoVentrice.GestorDeLogistica.DTO;

import com.HugoVentrice.GestorDeLogistica.model.Rol;

public class RegisterDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String numeroTel;
    private String password;
    private Rol rol;

    public RegisterDTO(String nombre, String apellido, String correo, String numeroTel, String password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numeroTel = numeroTel;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}