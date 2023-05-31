package com.salvador.springboot.form.servicios;

import java.util.List;

import com.salvador.springboot.form.entidades.Rol;

public interface IRol {

	public List<Rol> listarRoles();

	public Rol buscarPorIdRol(Integer idRol);

}
