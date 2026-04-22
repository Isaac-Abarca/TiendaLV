package com.MissiCrochets.Tienda.controller;

import com.MissiCrochets.Tienda.domain.Usuario;
import com.MissiCrochets.Tienda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario, RedirectAttributes redirectAttributes) {
        usuario.setActivo(true);

        // Generar avatar automático antes de guardar
        String avatarUrl = "https://ui-avatars.com/api/?name=" + usuario.getNombre() + "+" + usuario.getApellidos() + "&background=random&color=fff&rounded=true";
        usuario.setRutaImagen(avatarUrl);

        usuarioService.save(usuario);

        // Enviamos el mensaje de éxito que durará solo una redirección
        redirectAttributes.addFlashAttribute("registroExito", "¡Cuenta creada con éxito! Ya puedes iniciar sesión.");

        return "redirect:/login";
    }
}
