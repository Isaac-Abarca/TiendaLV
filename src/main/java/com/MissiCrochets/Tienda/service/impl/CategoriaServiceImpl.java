/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MissiCrochets.Tienda.service.impl;

import com.MissiCrochets.Tienda.dao.CategoriaDao;
import com.MissiCrochets.Tienda.domain.Categoria;
import com.MissiCrochets.Tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(String filtro) {
        var lista = categoriaDao.findAll();

        if ("activos".equalsIgnoreCase(filtro)) {
            lista.removeIf(cat -> !cat.isActivo());
        } else if ("inactivos".equalsIgnoreCase(filtro)) {
            lista.removeIf(cat -> cat.isActivo());
        }

        return lista;

    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
}
