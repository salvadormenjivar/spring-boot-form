package com.salvador.springboot.form.entidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-[A-Za-z]")
	private String identificador;
	
	@NotBlank
	private String nombre;	
	
	@NotEmpty
	@Pattern(regexp = "[A-Za-z]+", message="El apellido solo debe llevar letras")
	private String apellido;	
	
	@NotEmpty(message = "El username no puede ser vacío")
	@Size(min = 3, max=15)
	private String username;
	
	@NotEmpty(message = "El password no puede ser vacío")
	private String password;
	
	@NotEmpty(message = "El correo no puede ser vacío")
	@Email(message="Revise el formato del correo")
	private String email;
	
	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date fechaNacimiento;	
	
	@NotNull
	private Pais pais;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	

}
