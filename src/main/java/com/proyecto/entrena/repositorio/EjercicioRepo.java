
package com.proyecto.entrena.repositorio;

import com.proyecto.entrena.modelo.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjercicioRepo extends JpaRepository<Ejercicio, Long> {

    // Buscar ejercicios por nombre (ejemplo)
    List<Ejercicio> findByNombreContainingIgnoreCase(String nombre);

    // Verificar si existe un ejercicio por nombre exacto (puede usarse para validar duplicados)
    boolean existsByNombre(String nombre);
}