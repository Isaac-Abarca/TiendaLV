package com.MissiCrochets.Tienda.dao;

import com.MissiCrochets.Tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    // Este nombre de método es vital: Spring genera el SQL automáticamente
    Usuario findByUsername(String username);
    
    Usuario findByUsernameOrCorreo(String username, String correo);
}