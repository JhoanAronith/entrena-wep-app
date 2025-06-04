package com.proyecto.entrena.controlador;

import com.proyecto.entrena.modelo.*;
import com.proyecto.entrena.repositorio.*;
import com.proyecto.entrena.servicio.EjercicioServ;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class EjercicioControl {

    @Autowired
    private EjercicioRepo repo;

    @Autowired
    private CategoriaEjercicioRepo categoriaRepo;

    @Autowired
    private EjercicioServ ejercicioServ;

    //Método GET para mostrar la pagina de registro de ejercicio
    @GetMapping("/admin/ejercicios")
    public String mostrarPaginaEjercicios(Model model) {
        List<Ejercicio> ejercicios = ejercicioServ.listarTodos();
        EjercicioDTO ejercicioDTO = new EjercicioDTO();
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("ejercicioDTO", ejercicioDTO);
        model.addAttribute("categoriaEjercicioDTO", new CategoriaEjercicioDTO());
        model.addAttribute("success", false);
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "admin/ejercicios";
    }

    //Método POST para registrar un nuevo ejercicio
    @PostMapping("/admin/ejercicios")
    public String registrarEjercicio(Model model, @Valid @ModelAttribute EjercicioDTO ejercicioDTO, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaRepo.findAll());
            model.addAttribute("ejercicioDTO", ejercicioDTO);
            return "admin/ejercicios";
        }
        try {
            CategoriaEjercicio categoria = categoriaRepo.findById(ejercicioDTO.getCategoria()).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            Ejercicio nuevoEjercicio = new Ejercicio();
            nuevoEjercicio.setNombre(ejercicioDTO.getNombre());
            nuevoEjercicio.setDescripcion(ejercicioDTO.getDescripcion());
            nuevoEjercicio.setDuracion(ejercicioDTO.getDuracion());
            nuevoEjercicio.setEnlaceVideo(ejercicioDTO.getEnlaceVideo());
            nuevoEjercicio.setCategoria(categoria);
            repo.save(nuevoEjercicio);
            model.addAttribute("ejercicioDTO", ejercicioDTO);
            model.addAttribute("categorias", categoriaRepo.findAll());
            model.addAttribute("success", true);
        } catch (Exception ex) {
            result.addError(new ObjectError("globalError", "Ocurrió un error inseperado, intenta nuevamente"));
            model.addAttribute("categorias", categoriaRepo.findAll());
            return "admin/ejercicios";
        }
        return "redirect:/admin/ejercicios";
    }

    @GetMapping("/admin/ejercicios/eliminar")
    public String eliminarEjercicios(@RequestParam int idEjercicio) {
        ejercicioServ.eliminarEjercicio(idEjercicio);
        return "redirect:/admin/ejercicios";
    }

    @PostMapping("/admin/ejercicios/editar")
    public String editarEjercicio(@RequestParam Integer idEjercicio,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam int duracion,
            @RequestParam String enlaceVideo,
            @RequestParam Integer categoria,
            Model model) {
        try {
            Ejercicio ejercicio = repo.findById(idEjercicio)
                    .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

            CategoriaEjercicio cat = categoriaRepo.findById(categoria)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            ejercicio.setNombre(nombre);
            ejercicio.setDescripcion(descripcion);
            ejercicio.setDuracion(duracion);
            ejercicio.setEnlaceVideo(enlaceVideo);
            ejercicio.setCategoria(cat);

            repo.save(ejercicio);

            model.addAttribute("successEdit", true);
        } catch (Exception e) {
            model.addAttribute("errorEdit", "Ocurrió un error al editar el ejercicio.");
        }
        return "redirect:/admin/ejercicios";
    }

}
