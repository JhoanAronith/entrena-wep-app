package com.proyecto.entrena.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="Ejercicio")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;

    @Column(name = "duracion")
    private int duracion;  // Sin tilde
    private String enlace;

    public Ejercicio() {
    }

    public Ejercicio(Long id, String nombre, String descripcion, String tipo, int duracion, String enlace) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.duracion = duracion;
        this.enlace = enlace;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracion() {   // Sin tilde
        return duracion;
    }

    public void setDuracion(int duracion) {  // Sin tilde
        this.duracion = duracion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
