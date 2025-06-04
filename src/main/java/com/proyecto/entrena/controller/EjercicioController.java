package com.proyecto.entrena.controller;

import com.proyecto.entrena.modelo.Ejercicio;
import com.proyecto.entrena.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@Controller
@RequestMapping("/ejercicios")
public class EjercicioController {
    @Autowired
    private EjercicioService EjercicioService;
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("ejercicio", new Ejercicio());
        return "form";
    }

    @PostMapping
    public String saveEjercicio(@ModelAttribute Ejercicio ejercicio) {
        EjercicioService.save(ejercicio);
        return "redirect:/ejercicios";
    }

    @GetMapping("/edit/{id}")
    public String editEjercicio(@PathVariable Long id, Model model) {
        model.addAttribute("ejercicio", EjercicioService.findById(id));
        return "form";
    }
    @PostMapping("/update/{id}")
    public String updateEjercicio(@PathVariable Long id, @ModelAttribute Ejercicio ejercicio) {
        EjercicioService.update(id, ejercicio);
        return "redirect:/ejercicios";
    }
    @GetMapping("/delete/{id}")
    public String deleteEjercicio(@PathVariable Long id) {
        EjercicioService.delete(id);
        return "redirect:/ejercicios";
    }
    @GetMapping
    public String listEjercicios(Model model) {
        model.addAttribute("ejercicios", EjercicioService.findAll());
        return "list";
    }

}
