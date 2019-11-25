package com.belatrixsf.integracion_scraping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scraping_web")
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


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}


	@Column(name = "tag", nullable = false)
	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}


	@Column(name = "tipo", nullable = false)
	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	
	
	
	
	
	
}
