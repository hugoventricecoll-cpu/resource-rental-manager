package com.HugoVentrice.GestorDeLogistica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String apellido;

    @NotBlank
    @Column(nullable = false)
    private String numeroTel;

    @NotBlank
    @Column(nullable = false)
    private String correo;

    @NotBlank
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario(String nombre, String apellido, String correo, String numeroTel, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numeroTel = numeroTel;
        this.password = password;
    }

    public Usuario(){

    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
