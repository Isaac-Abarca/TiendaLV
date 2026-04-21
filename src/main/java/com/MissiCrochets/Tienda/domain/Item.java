package com.MissiCrochets.Tienda.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Item extends Producto {
    private int cantidad; // Esta es la propiedad que nos dirá cuántos de este producto hay en el carrito

    public Item() {
    }

    public Item(Producto producto) {
        this.setIdProducto(producto.getIdProducto());
        this.setIdCategoria(producto.getIdCategoria());
        this.setDescripcion(producto.getDescripcion());
        this.setDetalle(producto.getDetalle());
        this.setPrecio(producto.getPrecio());
        this.setExistencias(producto.getExistencias());
        this.setActivo(producto.isActivo());
        this.setRutaImagen(producto.getRutaImagen());
        this.cantidad = 0;
    }
}