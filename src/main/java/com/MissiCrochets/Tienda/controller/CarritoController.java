package com.MissiCrochets.Tienda.controller;

import com.MissiCrochets.Tienda.domain.Factura;
import com.MissiCrochets.Tienda.domain.Item;
import com.MissiCrochets.Tienda.domain.Producto;
import com.MissiCrochets.Tienda.service.FacturaService;
import com.MissiCrochets.Tienda.service.ItemService;
import com.MissiCrochets.Tienda.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private HttpSession session;

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
    public String agregarItem(Producto producto) {
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

    // Cambiado para recibir la cantidad por URL (Path Variable)
    @GetMapping("/carrito/modificar/{idProducto}/{cantidad}")
    public String modificarItem(Item item, @PathVariable("cantidad") int cantidad) {
        item.setCantidad(cantidad);
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    // Nuevo: Método para vaciar el carrito manualmente
    @GetMapping("/carrito/vaciar")
    public String vaciar() {
        itemService.gets().clear();
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/pagar")
    public String pagar() {
        // Esta vista mostrará el spinner y redirigirá a /finalizar tras 3 segundos
        return "carrito/pagar"; 
    }

    @GetMapping("/carrito/finalizar")
    public String finalizarCompra(Model model) {
        var carrito = itemService.gets();
        
        if (carrito != null && !carrito.isEmpty()) {
            Factura factura = facturaService.registrarCompra(carrito);
            model.addAttribute("factura", factura);
            model.addAttribute("ventas", new ArrayList<>(carrito)); // Copia para la vista
            
            carrito.clear();
            
            session.setAttribute("cartTotal", 0.0);
            
            return "carrito/factura"; 
        }
        return "redirect:/";
    }
}