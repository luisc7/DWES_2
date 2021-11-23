package com.ite.clientes.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ite.clientes.model.beans.Evento;
import com.ite.clientes.model.beans.Usuario;
import com.ite.clientes.model.repository.IntEventoDao;
import com.ite.clientes.model.repository.IntUsuarioDao;
import com.ite.clientes.model.repository.ReservasListImpl;
import com.ite.clientes.model.repository.UsuariosListImpl;

@Controller
@RequestMapping("/clientes")
public class GestionClientes {
	
	@Autowired
	private IntEventoDao ievent;
	
	@Autowired
	private IntUsuarioDao listaUsuarios = new UsuariosListImpl();
	
	
	
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String procesarLogin(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession sesionUsuario){
		
		int idUsuarioActivo = listaUsuarios.loginUsuario(username, password);
		Usuario usuarioActivo = listaUsuarios.findUserbyId(idUsuarioActivo);
		if (idUsuarioActivo > 0) {
			model.addAttribute("mensajeLogin", "Inicio de sesión correcto");
			model.addAttribute("eventosDestacados", ievent.findDestacados());			
			sesionUsuario.setAttribute("usuarioActivo", usuarioActivo);
			return "destacados";
		} else
			model.addAttribute("mensajeLogin", "Inicio de sesión incorrecto");
			return "login";
	}
	
	@GetMapping("/cerrarSesion")
	public String logout(Model model) {
		return "login";
	}
	
	@GetMapping("/activos")
	public String activos(Model model) {
		return "activos";
	}
	
	@GetMapping("/destacados")
	public String destacados(Model model, HttpSession sesionUsuario) {
		if (sesionUsuario.getAttribute("usuarioActivo")==null)
			return "redirect:/clientes/login";
		else {
			model.addAttribute("eventosDestacados", ievent.findDestacados());
			return "destacados";
		}
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable("id") int idEvento) {
		Evento evento = ievent.findById(idEvento);
		model.addAttribute("evento", evento);
		return "detalle";
	}
	
	@GetMapping("/reservar/{id}")
	public String reservar(Model model, @PathVariable("id") int idEvento) {
		Evento evento = ievent.findById(idEvento);
		ReservasListImpl reservas = (ReservasListImpl) model.getAttribute("listaReserv");
		
		return "detalle";
	}


}
