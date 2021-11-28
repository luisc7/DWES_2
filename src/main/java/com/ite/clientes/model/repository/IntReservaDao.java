package com.ite.clientes.model.repository;

import java.util.List;

import com.ite.clientes.model.beans.Evento;
import com.ite.clientes.model.beans.Reserva;
import com.ite.clientes.model.beans.Usuario;

public interface IntReservaDao {
	
	List<Reserva> findReservasUser(Usuario usuario);
	int nuevaReserva(Usuario usuario, Evento evento, int cantidad, String observaciones);
	int reservasEvento(int idEvento);
}
