package com.MissiCrochets.Tienda.controller;

import com.MissiCrochets.Tienda.domain.Item;
import com.MissiCrochets.Tienda.domain.Producto;
import com.MissiCrochets.Tienda.service.ItemService;
import com.MissiCrochets.Tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        var totalCarrito = 0.0;
        for (Item i : items) {
            totalCarrito += (i.getPrecio() * i.getCantidad());
        }
        model.addAttribute("items", items);
        model.addAttribute("carritoTotal", totalCarrito);
        return "carrito/listado";
    }

    @GetMapping("/carrito/agregar/{idProducto}")
    public String agregarItem(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        if (producto != null && producto.getExistencias() > 0) {
            itemService.save(new Item(producto));
        }
        return "redirect:/";
    }

    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/modificar/{idProducto}/{cantidad}")
    public String modificarItem(Item item) {
        // Buscamos el item en el servicio
        Item actual = itemService.get(item);

        // Si el item existe y la nueva cantidad es válida (mayor a 0 y menor al stock)
        if (actual != null && item.getCantidad() > 0 && item.getCantidad() <= actual.getExistencias()) {
            itemService.update(item);
        }
        return "redirect:/carrito/listado";
    }
}
