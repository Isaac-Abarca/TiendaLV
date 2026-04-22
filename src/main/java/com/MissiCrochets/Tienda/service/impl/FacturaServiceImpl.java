package com.MissiCrochets.Tienda.service.impl;

import com.MissiCrochets.Tienda.dao.FacturaDao;
import com.MissiCrochets.Tienda.dao.UsuarioDao;
import com.MissiCrochets.Tienda.dao.VentaDao;
import com.MissiCrochets.Tienda.domain.Factura;
import com.MissiCrochets.Tienda.domain.Item;
import com.MissiCrochets.Tienda.domain.Producto;
import com.MissiCrochets.Tienda.domain.Usuario;
import com.MissiCrochets.Tienda.domain.Venta;
import com.MissiCrochets.Tienda.service.FacturaService;
import com.MissiCrochets.Tienda.service.ProductoService;
import com.MissiCrochets.Tienda.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private UsuarioDao usuarioDao; // Asegúrate de tener este Autowired
    @Autowired
    private ProductoService productoService;
    @Autowired
    private HttpSession session;

    @Transactional
    public Factura registrarCompra(List<Item> carrito) {
        // 1. Obtener el usuario actual logueado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioDao.findByUsername(username);

        // 2. Calcular el total usando 'cantidad'
        double total = 0;
        for (Item item : carrito) {
            total += (item.getPrecio() * item.getCantidad());
        }

        // 3. Guardar la Factura (Encabezado)
        Factura factura = new Factura();
        factura.setIdUsuario(usuario.getIdUsuario());
        factura.setFecha(LocalDate.now());
        factura.setTotal(total);
        factura.setEstado(1);
        factura = facturaDao.save(factura);

        // 4. Guardar cada Venta (Detalle) y descontar Stock
        for (Item item : carrito) {
            Venta venta = new Venta();
            venta.setIdFactura(factura.getIdFactura());

            // USAMOS EL ID DIRECTAMENTE DEL ITEM
            // Si item.getIdProducto() es null, aquí está el fallo.
            Integer idProd = item.getIdProducto();
            if (idProd == null) {
                // Log de emergencia para que veas en consola qué producto falla
                System.out.println("ERROR: El producto " + item.getDescripcion() + " tiene ID nulo");
            }

            venta.setIdProducto((long) idProd);
            venta.setPrecio(item.getPrecio());
            venta.setCantidad(item.getCantidad());
            ventaDao.save(venta);
            // Actualizar existencias del producto
            Producto producto = productoService.getProducto(item);
            if (producto != null) {
                producto.setExistencias(producto.getExistencias() - item.getCantidad());
                productoService.save(producto);
            }
        }
        return factura;
    }
}
