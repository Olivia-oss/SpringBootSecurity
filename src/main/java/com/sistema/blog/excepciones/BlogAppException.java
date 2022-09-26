package com.sistema.blog.excepciones;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus estado;
	private String mensage;

	public BlogAppException(HttpStatus estado, String mensage) {
		super();
		this.estado = estado;
		this.mensage = mensage;
	}

	public BlogAppException(HttpStatus estado, String mensage, String mensage1) {
		super();
		this.estado = estado;
		this.mensage = mensage;
		this.mensage = mensage1;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

}
