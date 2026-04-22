package com.MissiCrochets.Tienda.service;

import com.MissiCrochets.Tienda.domain.Factura;
import com.MissiCrochets.Tienda.domain.Item;
import java.util.List;

public interface FacturaService {
    public Factura registrarCompra(List<Item> carrito);
}