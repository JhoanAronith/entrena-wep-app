package com.proyecto.entrena.modelo;

import jakarta.validation.constraints.*;

public class EjercicioDTO {

    @NotBlank(message = "El nombre del ejercicio es obligatorio")
    private String nombre;
    @NotBlank(message = "La descripción del ejercicio es obligatorio")
    private String descripcion;
    @NotNull(message = "La duración es obligatoria")
    private int duracion;
    @NotBlank(message = "El enlace del video es obligatorio")
    private String enlaceVideo;
    @NotNull(message = "La categoría es obligatoria")
    private Integer categoria;

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

    public int getDuracion() {
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

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    
}
