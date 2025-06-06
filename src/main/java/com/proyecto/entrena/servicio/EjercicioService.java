package com.proyecto.entrena.service;

import com.proyecto.entrena.modelo.Ejercicio;

import java.util.List;

public interface EjercicioService {
    Ejercicio save(Ejercicio ejercicio);
    Ejercicio findById(Long id);
    List<Ejercicio> findAll();
    Ejercicio update(Long id, Ejercicio ejercicio);
    void delete(Long id);
}
