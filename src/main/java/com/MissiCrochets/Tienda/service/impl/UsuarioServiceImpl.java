package com.MissiCrochets.Tienda.service.impl;

import com.MissiCrochets.Tienda.dao.RolDao;
import com.MissiCrochets.Tienda.dao.UsuarioDao;
import com.MissiCrochets.Tienda.domain.Rol;
import com.MissiCrochets.Tienda.domain.Usuario;
import com.MissiCrochets.Tienda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional
    public void save(Usuario usuario) {
        // 1. Guardamos el usuario
        usuario = usuarioDao.save(usuario);
        
        // 2. Creamos y guardamos el rol
        Rol rol = new Rol();
        rol.setNombre("ROLE_USER");
        rol.setIdUsuario(usuario.getIdUsuario());
        rolDao.save(rol);
    }
}