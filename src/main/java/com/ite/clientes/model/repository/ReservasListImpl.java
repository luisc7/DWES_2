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
		
		if (listReserv.add(new Reserva(refNewReserva, evento.getIdEvento(), usuario.getIdUsuario(), evento.getPrecio(), observaciones, cantidad))) {
			refNewReserva++;
			return 1;
		} else
			return 0;
		
	}

}
