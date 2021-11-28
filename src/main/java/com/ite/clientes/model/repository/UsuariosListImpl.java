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
		/**
		 * Realiza una carga de dos usuarios preexistentes. Se asignan como "enable"
		 */
		addNewUser("cliente1", "pass1", "primitivo@anticuado.es", "Primitivo", "Calle o Hable", 1);
		addNewUser("cliente2", "pass2", "correo@del.dos", "Segundo", "Avenida o ida", 1);
	}

	
	@Override
	public int addNewUser(
			String username, 
			String password, 
			String email, 
			String nombre, 
			String direccion, 
			int enable) {
		/**
		 * @param Datos del nuevo usuario
		 * 
		 * Método para añadir nuevos usuarios. 
		 * Va incrementando una variable para el id de usuario con cada adición
		 * para crear un id único a cada usuario.
		 * 
		 * @return 1 si se ha añadido correctamente, 0 si no se añade
		 */
		if (listUsuarios.add(new Usuario(refNuevoUser, username, password, email, nombre, direccion, enable, new Date()))) {
			refNuevoUser++;
			return 1;
		} else
			return 0;
	}

	@Override
	public int loginUsuario(String username, String password) {
		/**
		 * @param username Es el nombre de usuario (no confundir con el nombre)
		 * @param password Es la contraseña del usuario
		 * 
		 * @return idUsuario si existe (será > 0)
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
		/**
		 * @param idUsuario Es el identificador del objeto Usuario
		 * @return Usuario de la lista al que corresponde 
		 * el idUsuario que pasamos como parámetro.
		 */
		Usuario usuarioAux = new Usuario();
		usuarioAux.setIdUsuario(idUsuario);
		int pos = listUsuarios.indexOf(usuarioAux);
		if (pos == -1)
			return null;
		else
			return listUsuarios.get(pos);
	}

}
