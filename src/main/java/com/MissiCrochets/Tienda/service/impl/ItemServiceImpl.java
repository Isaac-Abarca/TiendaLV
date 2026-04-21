package com.MissiCrochets.Tienda.service.impl;

import com.MissiCrochets.Tienda.domain.Item;
import com.MissiCrochets.Tienda.service.ItemService;
import java.util.ArrayList; // No olvides este import
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    // DECLARA LA VARIABLE AQUÍ PARA QUE LA CLASE LA RECONOZCA
    private static final List<Item> listaItems = new ArrayList<>();

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void delete(Item item) {
        listaItems.removeIf(i -> Objects.equals(i.getIdProducto(), item.getIdProducto()));
    }

    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) return i;
        }
        return null;
    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }
}