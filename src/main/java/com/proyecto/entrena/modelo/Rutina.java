package com.proyecto.entrena.modelo;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "rutina_ejercicio",
            joinColumns = @JoinColumn(name = "rutina_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
    )
    private Set<Ejercicio> ejercicios;

    // Constructor vacío requerido por JPA
    public Rutina() {
    }

    // Constructor con parámetros
    public Rutina(String nombre, Set<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}