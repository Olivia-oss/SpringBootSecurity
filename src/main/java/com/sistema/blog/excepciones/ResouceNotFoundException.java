package com.sistema.blog.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String nombreDelRcurso;
	private String nombreDelCampo;
	private long valorDelCampo;

	public ResouceNotFoundException(String nombreDelRcurso, String nombreDelCampo, long valorDelCampo) {
		super(String.format("%s No encontrado con: %s : '%s'", nombreDelRcurso, nombreDelCampo, valorDelCampo));
		this.nombreDelRcurso = nombreDelRcurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}

	public String getNombreDelRcurso() {
		return nombreDelRcurso;
	}

	public void setNombreDelRcurso(String nombreDelRcurso) {
		this.nombreDelRcurso = nombreDelRcurso;
	}

	public String getNombreDelCampo() {
		return nombreDelCampo;
	}

	public void setNombreDelCampo(String nombreDelCampo) {
		this.nombreDelCampo = nombreDelCampo;
	}

	public long getValorDelCampo() {
		return valorDelCampo;
	}

	public void setValorDelCampo(long valorDelCampo) {
		this.valorDelCampo = valorDelCampo;
	}
	
}
