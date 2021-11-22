package com.ite.clientes.model.repository;

import java.util.List;

import com.ite.clientes.model.beans.Tipo;


public interface IntTipoDao {
	
	Tipo findById(int idTipo);
	List<Tipo> findAll();

}
