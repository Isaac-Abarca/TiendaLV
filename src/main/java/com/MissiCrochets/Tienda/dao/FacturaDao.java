package com.MissiCrochets.Tienda.dao;

import com.MissiCrochets.Tienda.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Factura, Long> {
    
}