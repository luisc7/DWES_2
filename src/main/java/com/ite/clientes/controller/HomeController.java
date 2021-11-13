package com.ite.clientes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/saludo")
	public String bienvenida() {
		return "Prueba inicial del HomeController";
	}

}
