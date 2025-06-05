package com.proyecto.entrena.modelo;


import java.util.Set;

public class RutinaDTO {
    private String nombre;
    private Set<Long> ejerciciosIds;

    public RutinaDTO() {
    }

    public RutinaDTO(String nombre, Set<Long> ejerciciosIds) {
        this.nombre = nombre;
        this.ejerciciosIds = ejerciciosIds;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Long> getEjerciciosIds() {
        return ejerciciosIds;
    }

    public void setEjerciciosIds(Set<Long> ejerciciosIds) {
        this.ejerciciosIds = ejerciciosIds;
    }
}