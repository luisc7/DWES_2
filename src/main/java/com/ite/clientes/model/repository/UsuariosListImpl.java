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
	private int refNuevoUser = 1;
	
	public UsuariosListImpl() {
		listUsuarios = new ArrayList<Usuario>();
		cargaUsuarioDefecto();
	}
	
	private void cargaUsuarioDefecto() {
		listUsuarios.add(new Usuario(1, "Cliente1", "passWrong", "emilio@detrigo.es", "Escuchimizao", "Calle o Hable", 1, new Date()));
		listUsuarios.add(new Usuario(2, "Cliente2", "pass", "correo@del.dos", "Segundo", "Avenida o ida", 1, new Date()));
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
		/**
		 * Devuelve el idUsuario si existe (será < 0)
		 * 
		 * Si existe pero el password es incorrecto, devuelve -1
		 * 
		 * Si el username no existe, devuelve 0
		 */
		int usuarioPass = 0;
		for (Usuario usr: listUsuarios) {
			System.out.println(usr.getUsername() + " " + username);
			if (usr.getUsername().equals(username)) {
				if (usr.getPassword().equals(password)) {
					// El password coincide con el del usuario
					usuarioPass = usr.getIdUsuario();
					break;
				} else {
					// Password incorrecto
					usuarioPass = -1;
					break;
				}
			}
		}
		return usuarioPass;
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
