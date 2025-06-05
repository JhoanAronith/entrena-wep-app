package com.proyecto.entrena.controlador;

import com.proyecto.entrena.modelo.Ejercicio;
import com.proyecto.entrena.modelo.Rutina;
import com.proyecto.entrena.modelo.RutinaDTO;
import com.proyecto.entrena.repositorio.EjercicioRepo;
import com.proyecto.entrena.servicio.RutinaServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RutinaControl {

    private final RutinaServ rutinaServ;
    private final EjercicioRepo ejercicioRepo;

    public RutinaControl(RutinaServ rutinaServ, EjercicioRepo ejercicioRepo) {
        this.rutinaServ = rutinaServ;
        this.ejercicioRepo = ejercicioRepo;
    }

    @GetMapping("/rutina")
    public String mostrarFormulario(Model model) {
        model.addAttribute("rutinas", rutinaServ.listarRutinas());
        model.addAttribute("rutinaForm", new RutinaDTO());
        model.addAttribute("ejercicios", ejercicioRepo.findAll()); // Carga ejercicios desde repo
        return "rutina";
    }

    @PostMapping("/rutina")
    public String guardarRutina(@ModelAttribute("rutinaForm") RutinaDTO dto, Model model) {
        try {
            rutinaServ.crearRutina(dto.getNombre(), dto.getEjerciciosIds());
            return "redirect:/rutina";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("rutinas", rutinaServ.listarRutinas());
            model.addAttribute("ejercicios", ejercicioRepo.findAll());
            return "rutina";
        }
    }

    @GetMapping("/rutina/editar/{id}")
    public String editarRutina(@PathVariable Long id, Model model) {
        try {
            Rutina rutina = rutinaServ.obtenerRutina(id)
                    .orElseThrow(() -> new IllegalArgumentException("Rutina no encontrada"));
            RutinaDTO dto = new RutinaDTO();
            dto.setNombre(rutina.getNombre());
            Set<Long> ejerciciosIds = rutina.getEjercicios().stream()
                    .map(e -> e.getId())
                    .collect(Collectors.toSet());
            dto.setEjerciciosIds(ejerciciosIds);

            model.addAttribute("rutinaForm", dto);
            model.addAttribute("id", id);
            model.addAttribute("rutinas", rutinaServ.listarRutinas());
            model.addAttribute("ejercicios", ejercicioRepo.findAll());
            return "rutina";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("rutinas", rutinaServ.listarRutinas());
            model.addAttribute("ejercicios", ejercicioRepo.findAll());
            model.addAttribute("rutinaForm", new RutinaDTO());
            return "rutina";
        }
    }

    @PostMapping("/rutina/actualizar/{id}")
    public String actualizarRutina(@PathVariable Long id, @ModelAttribute("rutinaForm") RutinaDTO dto, Model model) {
        try {
            rutinaServ.actualizarRutina(id, dto.getNombre(), dto.getEjerciciosIds());
            return "redirect:/rutina";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("rutinas", rutinaServ.listarRutinas());
            model.addAttribute("ejercicios", ejercicioRepo.findAll());
            return "rutina";
        }
    }

    @PostMapping("/rutina/eliminar/{id}")
    public String eliminarRutina(@PathVariable Long id, Model model) {
        try {
            rutinaServ.eliminarRutina(id);
            return "redirect:/rutina";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("rutinas", rutinaServ.listarRutinas());
            model.addAttribute("ejercicios", ejercicioRepo.findAll());
            model.addAttribute("rutinaForm", new RutinaDTO());
            return "rutina";
        }
    }
}
