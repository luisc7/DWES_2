package test;

import com.ite.clientes.model.repository.EventoListImpl;
import com.ite.clientes.model.repository.IntEventoDao;
import com.ite.clientes.model.repository.IntReservaDao;
import com.ite.clientes.model.repository.IntUsuarioDao;
import com.ite.clientes.model.repository.ReservasListImpl;
import com.ite.clientes.model.repository.UsuariosListImpl;

public class TestReservaListImpl {
	
	public static void main(String[] args) throws Throwable {
	
		IntReservaDao listaReservas = new ReservasListImpl();
		
		IntEventoDao ieven = new EventoListImpl();
		
		IntUsuarioDao listaUsuarios = new UsuariosListImpl();
		
		/*Usuario usuarioPrueba = new Usuario(123, "user1", "miclave", "miemail", "nombrecito", "micalle", 1, new Date());
		Tipo tipoEventoPrueba = new Tipo(546, "Eventito", "Evento de test");
		Evento eventoPrueba = new Evento(11, "Evelin y Jaime", "Enlace vespertino", new Date(), 8, "C/Olmo", "Activo", "", 115, 10, 65.7f, tipoEventoPrueba);
		
		System.out.print("Si se añade el evento, saldrá 1: ");
		System.out.println(listaReservas.nuevaReserva(usuarioPrueba, eventoPrueba, 1, "Observaciones de nueva reserva"));
		System.out.println(listaReservas.findReservasUser(usuarioPrueba).toString());
		

		Usuario usuarioPrueba2 = new Usuario(124, "user2", "miclave2", "miemail2", "nombrecito2", "micalle2", 1, new Date());
		Tipo tipoEventoPrueba2 = new Tipo(547, "Eventonto2", "Evento de test 2");
		Evento eventoPrueba2 = new Evento(2, "Jeremiah", "Bautizo almuerzo", new Date(1000000), 3, "C/Abeto", "Activo", "s", 50, 12, 32.5f, tipoEventoPrueba2);
		
		System.out.print("Si se añade el evento, saldrá 1: ");
		System.out.println(listaReservas.nuevaReserva(usuarioPrueba2, eventoPrueba2, 3, "Observaciones de nueva reserva 2 a user2"));
		System.out.println(listaReservas.findReservasUser(usuarioPrueba2).toString());
		
		Tipo tipoEventoPrueba3 = new Tipo(548, "Eventonto3", "Evento de test 3");
		Evento eventoPrueba3 = new Evento(3, "Carlos Alberto", "Comunión almuerzo", new Date(), 3, "C/Pino", "Activo", "", 40, 10, 25.35f, tipoEventoPrueba3);
		
		System.out.print("Si se añade el evento, saldrá 1: ");
		System.out.println(listaReservas.nuevaReserva(usuarioPrueba2, eventoPrueba3, 2, "Observaciones de nueva reserva 3 a user2"));
		System.out.println(listaReservas.findReservasUser(usuarioPrueba2).toString());*/
		
		listaUsuarios.addNewUser("user2", "miclave2", "miemail2", "nombrecito2", "micalle2", 1);
		
		System.out.print("Si se añade el evento, saldrá 1: ");
		System.out.println(listaReservas.nuevaReserva(listaUsuarios.findUserbyId(1), ieven.findById(1), 1, "Una reserva para user1"));
		System.out.println(listaReservas.nuevaReserva(listaUsuarios.findUserbyId(1), ieven.findById(2), 1, "Segunda reserva para user 1"));
		System.out.println(listaReservas.findReservasUser(listaUsuarios.findUserbyId(1)).toString());
		
		/*System.out.println(listaUsuarios.findUserbyId(1).toString());
		System.out.println(listaUsuarios.findUserbyId(2).toString());
		
		System.out.println(listaReservas.findReservasUser(listaUsuarios.findUserbyId(1)));*/
		
	
	}

}
