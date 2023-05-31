package com.salvador.springboot.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salvador.springboot.form.servicios.RolServicio;

@Component
public class RolPropertyEditor  extends PropertyEditorSupport{
	@Autowired
	private RolServicio rolServicio;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {		
			try {		
				Integer id = Integer.parseInt(idString);
				this.setValue(rolServicio.buscarPorIdRol(id));
			} catch (NumberFormatException e) {
				setValue(null);
			}		
	}
}
