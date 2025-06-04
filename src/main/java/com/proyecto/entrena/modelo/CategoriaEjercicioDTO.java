package com.proyecto.entrena.modelo;

import jakarta.validation.constraints.NotBlank;

public class CategoriaEjercicioDTO {

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    private String nombreCategoria;
    @NotBlank(message = "La descripción de la categoria es obligatoria")
    private String descripcionCategoria;

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
    
}
