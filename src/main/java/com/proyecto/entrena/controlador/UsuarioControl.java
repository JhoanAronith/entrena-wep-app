package com.proyecto.entrena.controlador;

import org.springframework.ui.Model;
import com.proyecto.entrena.servicio.UsuarioServ;
import com.proyecto.entrena.modelo.RegistroDTO;
import com.proyecto.entrena.modelo.Usuario;
import com.proyecto.entrena.repositorio.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControl {

    @Autowired
    private UsuarioRepo repo;

    @Autowired
    private UsuarioServ ser;

    //Método GET para mostrar la página de registro
    @GetMapping("/registro")
    public String mostraPaginaRegistro(Model model) {
        RegistroDTO registroDTO = new RegistroDTO();
        model.addAttribute(registroDTO);
        model.addAttribute("success", false);
        return "registro";
    }

    //Método POST para registrar un nuevo cliente
    @PostMapping("/registro")
    public String registrarUsuario(Model model, @Valid @ModelAttribute RegistroDTO registroDTO, BindingResult result) {
        if (!registroDTO.getContrasena().equals(registroDTO.getConfirmarContrasena())) {
            result.addError(new FieldError("registroDTO", "confirmarContrasena", "Las contraseñas no son iguales"));
        }
        Usuario usuario = repo.findByEmail(registroDTO.getEmail());
        if (usuario != null) {
            result.addError(new FieldError("registroDTO", "email", "El correo ya está en uso"));
        }
        if (result.hasErrors()) {
            model.addAttribute("registroDTO", registroDTO);
            return "registro";
        }
        try {
            var bCryptEncoder = new BCryptPasswordEncoder();
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreCompleto(registroDTO.getNombreCompleto());
            nuevoUsuario.setNombreUsuario(registroDTO.getNombreUsuario());
            nuevoUsuario.setDni(registroDTO.getDni());
            nuevoUsuario.setTelefono(registroDTO.getTelefono());
            nuevoUsuario.setEmail(registroDTO.getEmail());
            nuevoUsuario.setRol(registroDTO.getRol());
            nuevoUsuario.setContrasena(bCryptEncoder.encode(registroDTO.getContrasena()));
            repo.save(nuevoUsuario);
            model.addAttribute("registroDTO", new RegistroDTO());
            model.addAttribute("success", true);
        } catch (Exception ex) {
            result.addError(new ObjectError("globalError", "Ocurrió un error inesperado, intenta nuevamente"));
            return "registro";
        }
        return "registro";
    }

//Mostrar página del login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    //Mostrar página de perfil
    @GetMapping("/perfil")
    public String mostrarPerfil() {
        return "perfil";
    }

    //Mostrar página de inicio del administrador
    @GetMapping("/admin/inicio")
    public String mostrarInicioAdmin() {
        return "admin/inicio";
    }

    //Mostar página de inicio de la web
    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio";
    }

}
