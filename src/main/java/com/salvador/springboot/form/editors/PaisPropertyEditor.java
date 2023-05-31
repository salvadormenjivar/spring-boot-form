package com.salvador.springboot.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salvador.springboot.form.servicios.PaisServicio;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {
	@Autowired
	private PaisServicio paisServicio;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {		
			try {
				Integer id = Integer.parseInt(idString);
				this.setValue(paisServicio.obtenerPaisPorId(id));
			} catch (NumberFormatException e) {
				setValue(null);
			}		
	}

}
