package com.MissiCrochets.Tienda.dao;

import com.MissiCrochets.Tienda.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long> {
}