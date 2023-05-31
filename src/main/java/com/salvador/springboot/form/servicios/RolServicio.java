package com.salvador.springboot.form.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salvador.springboot.form.entidades.Rol;

@Service
public class RolServicio implements IRol {
	private List<Rol> lstRoles;

	public RolServicio(List<Rol> lstRoles) {
		this.lstRoles = Arrays.asList(new Rol(1, "Admin", "Administrador"), new Rol(2, "Mod", "Moderador"),
				new Rol(3, "Inv", "Invitado"));
	}

	@Override
	public List<Rol> listarRoles() {
		return lstRoles;
	}

	@Override
	public Rol buscarPorIdRol(Integer idRol) {
		Rol resultado = null;
		for (Rol rol: lstRoles) {
			if(rol.getId() == idRol) {
				resultado = rol;
				break;
			}						
		}
		return resultado;
	}

}
