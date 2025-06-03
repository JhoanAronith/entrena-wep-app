package com.proyecto.entrena.repositorio;

import com.proyecto.entrena.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
    
    public Usuario findByEmail(String email);
    List<Usuario> findByRol(String rol);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
}
