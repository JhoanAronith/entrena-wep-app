package com.proyecto.entrena.servicio;

import com.proyecto.entrena.modelo.Ejercicio;
import com.proyecto.entrena.repositorio.EjercicioRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjercicioServ {
    
    @Autowired
    private EjercicioRepo repo;
    
    public List<Ejercicio> listarTodos() {
        return repo.findAll();
    }
 
    
    public Ejercicio buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
    
    public Ejercicio guardarEjercicio(Ejercicio ejercicio) {
        return repo.save(ejercicio);
    }
    
    public void eliminarEjercicio(int idEjercicio) {
        repo.deleteById(idEjercicio);
    }
    
}
