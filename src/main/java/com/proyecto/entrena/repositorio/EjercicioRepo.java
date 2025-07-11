package com.proyecto.entrena.repositorio;

import com.proyecto.entrena.modelo.CategoriaEjercicio;
import com.proyecto.entrena.modelo.Ejercicio;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepo extends JpaRepository<Ejercicio, Integer> {
    
    List <Ejercicio> findByCategoria(CategoriaEjercicio  categoria);
    public Ejercicio findByNombre(String nombre);

    public Object findAllById(Set<Long> ejerciciosIds);
    
}
