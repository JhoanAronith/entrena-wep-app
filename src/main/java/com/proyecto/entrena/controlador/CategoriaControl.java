package com.proyecto.entrena.controlador;

import com.proyecto.entrena.modelo.*;
import com.proyecto.entrena.repositorio.CategoriaEjercicioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoriaControl {

    @Autowired
    CategoriaEjercicioRepo repo;

    @PostMapping("/admin/categorias")
    public String registrarCategoria(@Valid @ModelAttribute CategoriaEjercicioDTO categoriaEjercicioDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ejercicioDTO", new EjercicioDTO());
            model.addAttribute("categoriaEjercicioDTO", categoriaEjercicioDTO);
            model.addAttribute("categorias", repo.findAll());
            return "admin/ejercicios";
        }

        CategoriaEjercicio categoria = new CategoriaEjercicio();
        categoria.setNombreCategoria(categoriaEjercicioDTO.getNombreCategoria());
        categoria.setDescripcionCategoria(categoriaEjercicioDTO.getDescripcionCategoria());
        repo.save(categoria);

        return "redirect:/admin/ejercicios";
    }

}
