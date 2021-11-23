package com.ite.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ite.clientes.model.repository.IntUsuarioDao;

@Controller
public class HomeController {
	@Autowired
	private IntUsuarioDao iclie;
	
	@GetMapping("/clientes")
	public String inicio(Model model) {
		
		if (model.getAttribute("usuarioActivo") == null)
			return "redirect:/clientes/login";
		else
			return "redirect:/clientes/destacados";
	}

}
