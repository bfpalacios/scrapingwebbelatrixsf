package com.belatrixsf.integracion_scraping.entity;


@Entity
@Table(name = "CONFIGURACION", schema = "DBSCRAPING")
public class Fabrica {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIGURACION_seq")
	@SequenceGenerator(sequenceName="CONFIGURACION_seq", allocationSize=1, name="FABRICA_seq")
	private int id; 
	public String tag;
	public String tipo;
	public String rutaArchivoInput;
	public String rutaArchivoOutput;
	
	
	
	

}
