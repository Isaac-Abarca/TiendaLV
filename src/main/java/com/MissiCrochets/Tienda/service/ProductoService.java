/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MissiCrochets.Tienda.service;

import com.MissiCrochets.Tienda.domain.Producto;
import java.util.List;

public interface ProductoService {
    public List<Producto> getProductos(boolean activosOnly);
    public Producto getProducto(Producto producto);
    public void save(Producto producto);
    public void delete(Producto producto);
}