/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MissiCrochets.Tienda.controller;

import com.MissiCrochets.Tienda.service.CategoriaService;
import com.MissiCrochets.Tienda.service.ItemService;
import com.MissiCrochets.Tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String inicio(Model model,
            @RequestParam(name = "idCategoria", required = false) Long idCategoria,
            @RequestParam(name = "precioMin", required = false) Double precioMin,
            @RequestParam(name = "precioMax", required = false) Double precioMax) {

        final var productosBase = productoService.getProductos(true);

        var categoriasMenu = categoriaService.getCategorias("todos").stream()
                .filter(cat -> productosBase.stream().anyMatch(p -> p.getIdCategoria().equals(cat.getIdCategoria())))
                .toList();

        var productosAMostrar = productosBase.stream()
                .filter(p -> (idCategoria == null || p.getIdCategoria().longValue() == idCategoria.longValue()))
                .filter(p -> (precioMin == null || p.getPrecio() >= precioMin))
                .filter(p -> (precioMax == null || p.getPrecio() <= precioMax))
                .toList();

        model.addAttribute("productos", productosAMostrar);
        model.addAttribute("categorias", categoriasMenu);
        model.addAttribute("idCategoriaSelected", idCategoria);
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);

        return "index";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
    }
}
