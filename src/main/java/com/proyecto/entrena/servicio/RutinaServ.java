package com.proyecto.entrena.servicio;


import com.proyecto.entrena.modelo.Ejercicio;
import com.proyecto.entrena.modelo.Rutina;
import com.proyecto.entrena.repositorio.EjercicioRepo;
import com.proyecto.entrena.repositorio.RutinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RutinaServ {

    @Autowired
    private  RutinaRepo rutinaRepo;
    @Autowired
    private  EjercicioRepo ejercicioRepo;

    // Crear rutina a partir de nombre y IDs de ejercicios
    public Rutina crearRutina(String nombre, Set<Long> ejerciciosIds) {
        // Validar que no exista rutina con el mismo nombre
        if (rutinaRepo.existsByNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe una rutina con ese nombre");
        }

        // Buscar ejercicios
        Set<Ejercicio> ejercicios = ejercicioRepo.findAllById(ejerciciosIds)
                .stream()
                .collect(Collectors.toSet());

        if (ejercicios.size() != ejerciciosIds.size()) {
            throw new IllegalArgumentException("Uno o más ejercicios no existen");
        }

        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        rutina.setEjercicios(ejercicios);
        return rutinaRepo.save(rutina);
    }

    // Listar todas las rutinas
    public List<Rutina> listarRutinas() {
        return rutinaRepo.findAll();
    }

    // Obtener rutina por id
    public Optional<Rutina> obtenerRutina(Long id) {
        return rutinaRepo.findById(id);
    }

    // Actualizar rutina por id
    public Rutina actualizarRutina(Long id, String nombre, Set<Long> ejerciciosIds) {
        Rutina rutina = rutinaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rutina no encontrada"));

        // Validar que si cambia el nombre, no exista otra rutina con ese nombre
        if (!rutina.getNombre().equals(nombre) && rutinaRepo.existsByNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe otra rutina con ese nombre");
        }

        Set<Ejercicio> ejercicios = ejercicioRepo.findAllById(ejerciciosIds)
                .stream()
                .collect(Collectors.toSet());

        if (ejercicios.size() != ejerciciosIds.size()) {
            throw new IllegalArgumentException("Uno o más ejercicios no existen");
        }

        rutina.setNombre(nombre);
        rutina.setEjercicios(ejercicios);
        return rutinaRepo.save(rutina);
    }

    // Eliminar rutina por id
    public void eliminarRutina(Long id) {
        if (!rutinaRepo.existsById(id)) {
            throw new IllegalArgumentException("Rutina no encontrada");
        }
        rutinaRepo.deleteById(id);
    }
}