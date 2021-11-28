package com.ite.clientes.model.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ite.clientes.model.beans.Evento;
import com.ite.clientes.model.beans.Reserva;
import com.ite.clientes.model.beans.Usuario;

@Repository
public class ReservasListImpl implements IntReservaDao {
	
	private List<Reserva> listReserv;	
	private static int refNewReserva=1;
	
	public ReservasListImpl() {
		listReserv = new ArrayList<Reserva>();
	}

	@Override
	public List<Reserva> findReservasUser(Usuario usuario) {
		/**
		 * @param usuario Es el usuario del que queremos 
		 * consultar las reservas que tiene
		 * @return List<Reserva> Una lista únicamente con
		 * las reservas del usuario parado en parámetro
		 */
		List<Reserva> reservasUsuario = new ArrayList<Reserva>();
		for (Reserva reserva: listReserv) {
			if (reserva.getIdUsuario() == usuario.getIdUsuario()) {
				reservasUsuario.add(reserva);
			}
		}
		return reservasUsuario;
	}

	@Override
	public int nuevaReserva(Usuario usuario, Evento evento, int cantidad, String observaciones) {
		/**
		 * @param usuario Es el usuario al que se asigna la reserva
		 * @param evento Es el evento del que se realiza la reserva
		 * @param cantidad Es la cantidad de localidades reservadas en este proceso
		 * @param observaciones Son comentarios adicionales
		 * 
		 * @return int, que será 1 si se hace la reserva con éxit, 0 si no se realiza la reserva
		 */
		if (listReserv.add(new Reserva(refNewReserva, evento.getIdEvento(), usuario.getIdUsuario(), evento.getPrecio(), observaciones, cantidad))) {
			refNewReserva++;
			return 1;
		} else
			return 0;
		
	}
	
	@Override
	public int reservasEvento(int idEvento) {
		/**
		 * Método para calcular las localicades disponibles
		 * a partir del aforo máximo y buscando todas las localidades
		 * reservadas de un evento concreto.
		 * @param idEvento El identificador del evento del que calcular
		 * las plazas disponibles
		 * @return int Cantidad de plazas aún disponibles en el evento.
		 * 
		 */
		int cantidadReservadaEvento = 0;
		for (Reserva reserv: listReserv) {
			if (reserv.getIdEvento() == idEvento) {
				cantidadReservadaEvento += reserv.getCantidad();
			}
		}
		return cantidadReservadaEvento;		
	}

}
