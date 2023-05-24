package com.salvador.springboot.form.controladores;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.salvador.springboot.form.entidades.Usuario;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormControl {

	@GetMapping("/form")
	public String verForm(Model model) {
		model.addAttribute("titulo", "Formulario inicio de sesión");
		Usuario usuario = new Usuario();
		usuario.setNombre("Salvador");
		usuario.setApellido("Menjivar");
		usuario.setIdentificador("123.456.123-J");
		model.addAttribute("usuario", usuario);
				
		return "form";
	}
	
	@ModelAttribute("paises")
	public List<String> listarPaises(){
		return Arrays.asList("El Salvador", "Mexico", "Guatemala", "Honduras", "Brasil", "Argentina");
		
	}

	/*
	@PostMapping("/form")
	public String procesarFormulario(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
		model.addAttribute("titulo", "Datos recibidos del formulario: ");
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);		
		model.addAttribute("usuario", usuario);		
		return "resultado";	
	}
	*/
	
	/*Recibimos del formulario directamente la clase Usuario, lo unico que deben ser iguales los names
	 * a los atributos de la clase Usuario*/
	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {		
		model.addAttribute("titulo", "Datos recibidos del formulario: ");
		
		if(result.hasErrors()) {
			/*
			Map<String,String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			model.addAttribute("error", errores);
			*/
			return "form";
		}
		model.addAttribute("usuario", usuario);
		status.setComplete();//Elimina el objeto de sesión usuario después de utilizarlo
		return "resultado";	
	}

}
