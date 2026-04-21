/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MissiCrochets.Tienda.controller;

import com.MissiCrochets.Tienda.domain.Categoria;
import com.MissiCrochets.Tienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model, @RequestParam(name = "filtro", defaultValue = "todos") String filtro) {
        var categorias = categoriaService.getCategorias(filtro);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        model.addAttribute("filtroActual", filtro);
        model.addAttribute("categoria", new Categoria());
        return "categoria/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String eliminar(Categoria categoria) {
        
    categoria = categoriaService.getCategoria(categoria);
    categoria.setActivo(false);
    categoriaService.save(categoria);
    
    return "redirect:/categoria/listado";
    }
}