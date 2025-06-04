package com.proyecto.entrena.service;

import com.proyecto.entrena.modelo.Ejercicio;
import com.proyecto.entrena.repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioServiceImpl implements EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Override
    public Ejercicio save(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    @Override
    public Ejercicio findById(Long id) {
        return ejercicioRepository.findById(id).orElseThrow(()->new RuntimeException(("No se encontró")));
    }

    @Override
    public List<Ejercicio> findAll() {
        return ejercicioRepository.findAll();
    }

    @Override
    public Ejercicio update(Long id, Ejercicio ejercicio) {
        Ejercicio existingEjercicio = findById(id);
        existingEjercicio.setNombre(ejercicio.getNombre());
        existingEjercicio.setDescripcion(ejercicio.getDescripcion());
        existingEjercicio.setTipo(ejercicio.getTipo());
        existingEjercicio.setDuracion(ejercicio.getDuracion());
        existingEjercicio.setEnlace(ejercicio.getEnlace());
        return ejercicioRepository.save(existingEjercicio);
    }

    @Override
    public void delete(Long id) {
        ejercicioRepository.deleteById(id);
    }
}
