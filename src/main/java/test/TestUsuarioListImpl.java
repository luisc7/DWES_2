package test;

import com.ite.clientes.model.repository.IntUsuarioDao;
import com.ite.clientes.model.repository.UsuariosListImpl;

public class TestUsuarioListImpl {
	
	public static void main(String[] args) {
	
		IntUsuarioDao listaUsuarios = new UsuariosListImpl();
		
		System.out.print("No existe, tiene que dar 0: ");
		System.out.println(listaUsuarios.loginUsuario("pepe", "calabaza"));

		System.out.print("Existe y contraseña bien, tiene que dar 1: ");
		System.out.println(listaUsuarios.loginUsuario("Cliente1", "passWrong"));

		System.out.print("Existe pero contraseña mal, tiene que dar -1: ");
		System.out.println(listaUsuarios.loginUsuario("Cliente1", "santoyseña"));
	
	}

}
