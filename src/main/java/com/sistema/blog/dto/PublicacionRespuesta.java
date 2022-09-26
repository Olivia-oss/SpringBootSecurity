package com.sistema.blog.dto;

import java.util.List;

public class PublicacionRespuesta {

	private List<PublicacionDTO> contenido;
	private int numeroPagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;

	public PublicacionRespuesta() {
		super();
	}

	public PublicacionRespuesta(List<PublicacionDTO> contenido, int numeroPagina, int medidaPagina, long totalElementos,
			int totalPaginas, boolean ultima) {
		super();
		this.contenido = contenido;
		this.numeroPagina = numeroPagina;
		this.medidaPagina = medidaPagina;
		this.totalElementos = totalElementos;
		this.totalPaginas = totalPaginas;
		this.ultima = ultima;
	}

	public List<PublicacionDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<PublicacionDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getMedidaPagina() {
		return medidaPagina;
	}

	public void setMedidaPagina(int medidaPagina) {
		this.medidaPagina = medidaPagina;
	}

	public long getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(long totalElementos) {
		this.totalElementos = totalElementos;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public boolean isUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

}
