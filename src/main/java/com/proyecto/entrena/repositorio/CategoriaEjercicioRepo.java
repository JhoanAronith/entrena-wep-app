package com.proyecto.entrena.repositorio;

import com.proyecto.entrena.modelo.CategoriaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEjercicioRepo extends JpaRepository<CategoriaEjercicio, Integer> {
    
}
