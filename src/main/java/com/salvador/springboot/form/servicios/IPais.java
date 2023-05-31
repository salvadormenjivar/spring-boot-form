package com.salvador.springboot.form.servicios;

import java.util.List;

import com.salvador.springboot.form.entidades.Pais;

public interface IPais {
	public List<Pais> listarPaises();	
	public Pais obtenerPaisPorId(Integer id);

}
