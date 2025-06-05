package com.proyecto.entrena.repositorio;


import com.proyecto.entrena.modelo.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutinaRepo extends JpaRepository<Rutina, Long> {

    // Buscar rutina por nombre exacto (para evitar duplicados)
    Optional<Rutina> findByNombre(String nombre);

    // Verificar si existe rutina por nombre
    boolean existsByNombre(String nombre);
}