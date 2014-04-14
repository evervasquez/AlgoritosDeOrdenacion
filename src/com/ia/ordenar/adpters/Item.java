package com.ia.ordenar.adpters;

public class Item {
	private String titulo;
	private String subtitulo;
	
	public Item(String titulo, String subtitulo) {
		this.titulo = titulo;
		this.subtitulo = subtitulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

}
