package com.HugoVentrice.GestorDeLogistica.DTO;



public class VehiculoDTO {
    private int kilometraje;
    private String matricula;
    private int plazas;
    private boolean disponible;
    private long id;
    private String nombre;

    public VehiculoDTO(int kilometraje, String matricula, int plazas, boolean disponible, long id, String nombre){
        this.kilometraje = kilometraje;
        this.matricula = matricula;
        this.plazas = plazas;
        this.disponible = disponible;
        this.id = id;
        this.nombre = nombre;
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
