package com.proyecto.entrena.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="ejercicio")
public class Ejercicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEjercicio;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int duracion;
    @Column
    private String enlaceVideo;
    @ManyToOne
    @JoinColumn(name="idCategoria")
    private CategoriaEjercicio categoria;
    
    public Ejercicio() {
        
    }

    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
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

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEnlaceVideo() {
        return enlaceVideo;
    }

    public void setEnlaceVideo(String enlaceVideo) {
        this.enlaceVideo = enlaceVideo;
    }

    public CategoriaEjercicio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEjercicio categoria) {
        this.categoria = categoria;
    }
    
}
