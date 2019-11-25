package com.belatrixsf.integracion_scraping.dto.modelo;
 
 
public class ScrapingWeb {
	
	private long id;
	private String tag;
	private String tipo;
	
	
	
	public ScrapingWeb() { 
	}



	public ScrapingWeb(long id, String tag, String tipo) {
		super();
		this.id = id;
		this.tag = tag;
		this.tipo = tipo;
	}

 
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}
 
	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}

 
	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	
	
	
	
	
	
}
