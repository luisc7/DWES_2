package com.ite.clientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/clientes")
	public String bienvenida() {
		return "Test HomeController";
	}

}
