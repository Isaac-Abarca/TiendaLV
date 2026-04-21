package com.MissiCrochets.Tienda.dao;

import com.MissiCrochets.Tienda.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Integer> {
}