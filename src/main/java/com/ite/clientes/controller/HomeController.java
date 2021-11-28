package com.ite.clientes.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/**
	 *  Aquí decido redirigir todo a /clientes entrando por la ruta principal
	 * 
	 *  En el controlador de clientes están el resto de rutas
	 */
	
	@GetMapping("/")
	public String rutaPpal() {
		return "redirect:/clientes";
	}
	
	@GetMapping("/clientes")
	/**
	 * Al acceder a /clientes, dirijo a login si no hay sesión de usuario, 
	 * Si el usuario ha iniciado sesión, redirijo a la lista de destacados 
	 */
	
	public String inicio(HttpSession sesionUsuario) {
		
		if (sesionUsuario.getAttribute("usuarioActivo") == null)
			return "redirect:/clientes/login";
		else
			return "redirect:/clientes/destacados";
	}

}
