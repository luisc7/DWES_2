package com.ite.clientes.model.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ite.clientes.model.beans.Usuario;

@Repository
public class UsuariosListImpl implements IntUsuarioDao{
	
	private List<Usuario> listUsuarios;
	@SuppressWarnings("unused")
	private int refNuevoUser;
	
	public UsuariosListImpl() {
		listUsuarios = new ArrayList<Usuario>();
		cargaUsuarioDefecto();
	}
	
	private void cargaUsuarioDefecto() {
		listUsuarios.add(new Usuario(1, "Cliente1", "passWrong", "emilio@detrigo.es", "Escuchimizao", "Calle o Hable", 1, new Date()));
		refNuevoUser = 2;
	}

	
	@Override
	public int addNewUser(String username, String password, String email, String nombre, String direccion, int enable) {
		if (listUsuarios.add(new Usuario(refNuevoUser, username, password, email, nombre, direccion, enable, new Date()))) {
			refNuevoUser++;
			return 1;
		} else
			return 0;
	}

	@Override
	public int loginUsuario(String username, String password) {
		Usuario userAux = new Usuario();
		userAux.setUsername(username);
		int pos = listUsuarios.indexOf(userAux);
		if (pos == -1)
			// No existe el usuario
			return -1;
		else {
			if (listUsuarios.get(pos).getPassword().equals(password))
				// El password coincide con el del usuario
				return 1;
			else
				// Password incorrecto
				return 0;
		}
			
		
	}
	
	@Override
	public Usuario findUserbyId(int idUsuario) {
		Usuario usuarioAux = new Usuario();
		usuarioAux.setIdUsuario(idUsuario);
		int pos = listUsuarios.indexOf(usuarioAux);
		if (pos == -1)
			return null;
		else
			return listUsuarios.get(pos);
	}

}
