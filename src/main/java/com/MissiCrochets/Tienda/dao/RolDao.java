package com.MissiCrochets.Tienda.dao;

import com.MissiCrochets.Tienda.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RolDao extends JpaRepository<Rol, Long> {
}