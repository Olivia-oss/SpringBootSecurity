package com.sistema.blog.dto;

import java.util.Date;

public class ErrorDetalles {

	private Date MarcaDeTiempo;
	private String mensage;
	private String detalles;

	public ErrorDetalles(Date marcaDeTiempo, String mensage, String detalles) {
		super();
		MarcaDeTiempo = marcaDeTiempo;
		this.mensage = mensage;
		this.detalles = detalles;
	}

	public ErrorDetalles() {
		super();
	}

	public Date getMarcaDeTiempo() {
		return MarcaDeTiempo;
	}

	public void setMarcaDeTiempo(Date marcaDeTiempo) {
		MarcaDeTiempo = marcaDeTiempo;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

}
