package com.salvador.springboot.form.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salvador.springboot.form.entidades.Pais;

@Service
public class PaisServicio implements IPais {
	private List<Pais> lstPaises;

	public PaisServicio(List<Pais> lstPaises) {
		this.lstPaises = Arrays.asList(new Pais(1, "SV", "El Salvador"), new Pais(2, "GT", "Guatemala"),
				new Pais(3, "HN", "Honduras"), new Pais(4, "CR", "Costa Rica"), new Pais(5, "BEL", "Belice"));
	}

	@Override
	public List<Pais> listarPaises() {
		return lstPaises;
	}

	@Override
	public Pais obtenerPaisPorId(Integer id) {
		Pais paisEncontrado = null;
		for (Pais pais : lstPaises) {
			if (id == pais.getId()) {
				paisEncontrado = pais;
				break;
			}
		}
		return paisEncontrado;

	}

}
