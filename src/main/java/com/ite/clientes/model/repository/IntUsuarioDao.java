package com.ite.clientes.model.repository;

import com.ite.clientes.model.beans.Usuario;

public interface IntUsuarioDao {
	
	int loginUsuario(String username, String password);
	int addNewUser(String username, String password, String email, String nombre, String direccion, int enable);
	Usuario findUserbyId(int idUsuario);
}
