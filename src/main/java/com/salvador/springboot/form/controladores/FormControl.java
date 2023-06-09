package com.salvador.springboot.form.controladores;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.salvador.springboot.form.editors.PaisPropertyEditor;
import com.salvador.springboot.form.editors.RolPropertyEditor;
import com.salvador.springboot.form.entidades.Pais;
import com.salvador.springboot.form.entidades.Rol;
import com.salvador.springboot.form.entidades.Usuario;
import com.salvador.springboot.form.servicios.IPais;
import com.salvador.springboot.form.servicios.IRol;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormControl {

	@Autowired
	private IPais paisServicio;

	@Autowired
	private IRol rolServicio;

	@Autowired
	private RolPropertyEditor rolEditor;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Rol.class, "roles", rolEditor);
	}

	@GetMapping("/form")
	public String verForm(Model model) {
		model.addAttribute("titulo", "Formulario inicio de sesión");
		Usuario usuario = new Usuario();
		usuario.setNombre("Salvador");
		usuario.setApellido("Menjivar");
		usuario.setIdentificador("123.456.123-J");
		usuario.setActivo(true);
		usuario.setPais(new Pais(3, "HN", "Honduras"));
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@ModelAttribute("lstGeneros")
	public List<String> listarGeneros() {
		return Arrays.asList("Masculino", "Femenino");
	}

	@ModelAttribute("paises")
	public List<String> listarPaises() {
		return Arrays.asList("El Salvador", "Mexico", "Guatemala", "Honduras", "Brasil", "Argentina");
	}

	@ModelAttribute("rolesString")
	public List<String> listarRoles() {
		return Arrays.asList("MODERADOR", "ADMINISTRADOR", "INVITADO");
	}

	@ModelAttribute("rolesObjetos")
	public List<Rol> listarRolesObjetos() {
		return rolServicio.listarRoles();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listarPaisesObjetos() {
		return paisServicio.listarPaises();

	}

	/*
	 * @PostMapping("/form") public String procesarFormulario(Model
	 * model, @RequestParam String username, @RequestParam String
	 * password, @RequestParam String email) { model.addAttribute("titulo",
	 * "Datos recibidos del formulario: "); Usuario usuario = new Usuario();
	 * usuario.setUsername(username); usuario.setPassword(password);
	 * usuario.setEmail(email); model.addAttribute("usuario", usuario); return
	 * "resultado"; }
	 */

	/*
	 * Recibimos del formulario directamente la clase Usuario, lo unico que deben
	 * ser iguales los names a los atributos de la clase Usuario
	 */
	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Datos recibidos del formulario: ");
			/*
			 * Map<String,String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> { errores.put(err.getField(),
			 * "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())
			 * )); }); model.addAttribute("error", errores);
			 */
			return "form";
		}
		return "redirect:/verResultados";
	}

	@GetMapping("/verResultados")
	public String verResultados(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		if(usuario==null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado form");		
		status.setComplete();// Elimina el objeto de sesión usuario después de utilizarlo
		return "resultado";
	}

}
