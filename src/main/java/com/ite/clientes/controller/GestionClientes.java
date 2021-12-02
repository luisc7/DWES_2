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
	
	@Autowired
	private IntReservaDao listaReservas = new ReservasListImpl();
	
	
	@GetMapping("/login")
	public String login(Model model) {
		/**
		 *  Envía al jsp de login
		 */
		return "login";
	}
	
	@PostMapping("/login")
	public String procesarLogin(
			HttpSession sesionUsuario,
			RedirectAttributes attr, 
			Model model, 
			@RequestParam("username") String username, 
			@RequestParam("password") String password ) {
		/**
		 * Al envío del formulario de login se contemplan tres escenarios:
		 * - Usuario y contraseña correctos
		 * - Usuario no existe
		 * - Contraseña incorrecta (usuario existe)
		 */
		int idUsuarioActivo = listaUsuarios.loginUsuario(username, password);
		Usuario usuarioActivo = listaUsuarios.findUserbyId(idUsuarioActivo);
		
		if (idUsuarioActivo > 0) {
			attr.addFlashAttribute("mensajeLogin", "Inicio de sesión correcto");
			attr.addFlashAttribute("eventosDestacados", ievent.findDestacados());			
			sesionUsuario.setAttribute("usuarioActivo", usuarioActivo);
			sesionUsuario.setAttribute("listaReservas", listaReservas.findReservasUser(usuarioActivo));
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
	public String logout(
			HttpSession sesionUsuario,
			Model model	) {
		/**
		 * Cierra la sesión borrando el atributo del usuarioActivo,
		 * y vuelve a login
		 */
		
		sesionUsuario.removeAttribute("usuarioActivo");
		return "redirect:/clientes/login";
	}
	
	@GetMapping("/activos")
	public String activos(
			Model model, 
			HttpSession sesionUsuario) {
		/**
		 * Envía a los eventos activos, sólo si hay usuario logeado
		 * 
		 * Si no, envía al login
		 */
		
		if (sesionUsuario.getAttribute("usuarioActivo")==null)
			return "redirect:/clientes/login";
		else {
			model.addAttribute("eventosActivos", ievent.findActive());
			return "activos";
		}
	}
	
	@GetMapping("/destacados")
	public String destacados(
			Model model, 
			HttpSession sesionUsuario ) {
		/**
		 * Envía a los eventos destacados, sólo si hay usuario logeado
		 * 
		 * Si no, envía al login
		 */
		
		if (sesionUsuario.getAttribute("usuarioActivo")==null)
			return "redirect:/clientes/login";
		else {
			model.addAttribute("eventosDestacados", ievent.findDestacados());
			return "destacados";
		}
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(
			Model model, 
			HttpSession sesionUsuario, 
			@PathVariable("id") int idEvento ) {
		/**
		 * Accede al detalle del evento con path variable
		 * 
		 * Se recoge de sesión la lista de reservas, se elige
		 * el evento correspondiente a ese id.
		 * 
		 * Se obtiene el número de reservas realizadas, y se calcula
		 * la cantidad restante de plazas disponibles.
		 * 
		 * En la petición se envían el Evento, y las plazas disponibles
		 * 
		 * @return dirige al jsp de detalle cargando los datos
		 * correspondientes, habrá actualizado la cantidad disponible
		 */
		
		Evento evento = ievent.findById(idEvento);
		int cantidadReservada = listaReservas.reservasEvento(idEvento);
		model.addAttribute("evento", evento);
		model.addAttribute("cantidadDisponible", (evento.getAforoMaximo()-cantidadReservada));
		return "detalle";
	}
	
	@GetMapping("/reservar/{id}")
	public String reservar(
			HttpSession sesionUsuario, 
			RedirectAttributes attr, 
			@PathVariable("id") int idEvento, 
			@RequestParam("cantidad") int cantidad ) {
		/**
		 * Recoge por path variable el evento, y como parámetro de la petición
		 * viene la cantidad de plazas reservadas.
		 * 
		 * Se busca el evento en la lista de eventos, y se recoge 
		 * la lista de reservas de la sesión
		 * 
		 * Se filtra con un if si las reservas son mayores de 10.
		 * Se devuelve el mensaje correspondiente a los tres casos posibles:
		 * - No se hace por ser mayor de 10 plazas
		 * - No se realiza (por otras causas)
		 * - Se ha hecho la reserva
		 * 
		 * 
		 * @return Tras lo anterior, dirige a los detalles del evento,
		 * se emplean atributos flash porque primero va a la ruta
		 * de detalle y desde allí lo dirige al jsp, y uno de petición
		 * se perdería
		 */
		
		Evento evento = ievent.findById(idEvento);
		int cantidadReservada = listaReservas.reservasEvento(idEvento);
		if (cantidad > 10)
			// Aquí no debería entrar "nunca" por el formulario, 
			// no obstante lo limitamos por si se accediera modificando la URL
			attr.addFlashAttribute("mensajeReserva", "Reserva NO realizada: Ha indicado más de 10 plazas en la reserva");
		else if ((evento.getAforoMaximo()-cantidadReservada-cantidad) < 0)
			attr.addFlashAttribute("mensajeReserva", "Reserva NO realizada: Ha indicado más plazas en la reserva de las disponibles");
		else if (listaReservas.nuevaReserva((Usuario)sesionUsuario.getAttribute("usuarioActivo"), evento, cantidad, "") == 1)
			attr.addFlashAttribute("mensajeReserva", "Reserva realizada");
		else
			attr.addFlashAttribute("mensajeReserva", "Reserva NO realizada");
		return "redirect:/clientes/detalle/"+idEvento;
	}
	
	@GetMapping("/reservas")
	public String reservas(
			HttpSession sesionUsuario,
			Model model ) {
		/**
		 * Este apartado no se pedía en el enunciado, 
		 * pero he considerado práctico incluirlo porque 
		 * me permitía ver con facilidad si las reservas se habían
		 * realizado exitosamente, y no requería mucho trabajo.
		 * 
		 * No obstante solo he utilizado los id de eventos y de reserva
		 * para hacerlo fácil, puesto que era una comprobación simplemente
		 */
				
		model.addAttribute("reservasUsuario", listaReservas.findReservasUser((Usuario)sesionUsuario.getAttribute("usuarioActivo")));
		return "reservas";
	}


}
