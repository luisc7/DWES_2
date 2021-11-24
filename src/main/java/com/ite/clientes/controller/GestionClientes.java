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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.clientes.model.beans.Evento;
import com.ite.clientes.model.beans.Usuario;
import com.ite.clientes.model.repository.IntEventoDao;
import com.ite.clientes.model.repository.IntReservaDao;
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
	public String procesarLogin(RedirectAttributes attr, Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession sesionUsuario){
		
		int idUsuarioActivo = listaUsuarios.loginUsuario(username, password);
		Usuario usuarioActivo = listaUsuarios.findUserbyId(idUsuarioActivo);
		if (idUsuarioActivo > 0) {
			attr.addFlashAttribute("mensajeLogin", "Inicio de sesión correcto");
			attr.addFlashAttribute("eventosDestacados", ievent.findDestacados());			
			sesionUsuario.setAttribute("usuarioActivo", usuarioActivo);
			IntReservaDao reservas = new ReservasListImpl();
			sesionUsuario.setAttribute("listaReservas", reservas);
			return "redirect:/clientes/destacados";
		} else if (idUsuarioActivo == 0) {
			model.addAttribute("mensajeLogin", "El usuario no existe");
			return "login";
		} else {
			model.addAttribute("mensajeLogin", "La contraseña no es correcta");
			return "login";
		}
	}
	
	@GetMapping("/cerrarSesion")
	public String logout(Model model, HttpSession sesionUsuario) {
		sesionUsuario.removeAttribute("usuarioActivo");
		return "redirect:/clientes/login";
	}
	
	@GetMapping("/activos")
	public String activos(Model model, HttpSession sesionUsuario) {
		if (sesionUsuario.getAttribute("usuarioActivo")==null)
			return "redirect:/clientes/login";
		else {
			model.addAttribute("eventosActivos", ievent.findActive());
			return "activos";
		}
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
	public String reservar(HttpSession sesionUsuario, RedirectAttributes attr, @PathVariable("id") int idEvento, @RequestParam("cantidad") int cantidad) {
		Evento evento = ievent.findById(idEvento);
		IntReservaDao reservas = (IntReservaDao) sesionUsuario.getAttribute("listaReservas");
		if (reservas.nuevaReserva((Usuario)sesionUsuario.getAttribute("usuarioActivo"), evento, cantidad, "") == 1)
			attr.addFlashAttribute("mensajeReserva", "Reserva realizada");
		else
			attr.addFlashAttribute("mensajeReserva", "Reserva NO realizada");
		return "redirect:/clientes/detalle/"+idEvento;
	}
	
	@GetMapping("/reservas")
	public String reservas(Model model, HttpSession sesionUsuario) {
		IntReservaDao reservas = (IntReservaDao) sesionUsuario.getAttribute("listaReservas");
		model.addAttribute("reservasUsuario", reservas.findReservasUser((Usuario)sesionUsuario.getAttribute("usuarioActivo")));
		return "reservas";
	}


}
