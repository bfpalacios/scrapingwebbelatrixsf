package com.belatrixsf.integracion_scraping.negocio;

import java.io.IOException;
import java.util.ArrayList;

import com.belatrixsf.integracion_scraping.scrapingweb.MyScrapingWeb;
import com.belatrixsf.integracion_scraping.util.Archivo;

	
public class ScrapingWebNegocio {
	
	private Archivo archivo = new Archivo();
	private String RUTA_ARCHIVO_INPUT 	= "C:\\SCRAPINGWEB\\LISTA_URL.txt"; 
	private String RUTA_ARCHIVO_OUTPUT = "C:\\SCRAPINGWEB\\";  
	private static final String ARCHIVO_NAME_OUTPUT = "SCRAPINGWEB_DOCUMENT_OUT_";	
	private static ArrayList<String> listaUrl;
	 
	
 
	public String consultarScrapingWeb() {
		//variables
		ArrayList<String> listaUrlS = null;
		
		MyScrapingWeb myScrapingWeb = new MyScrapingWeb();
		//1.- Se obtiene las urls a buscar scrapingweb
		try {
			listaUrlS = archivo.obtenerListaURLParaScrapingWeb(RUTA_ARCHIVO_INPUT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.- Se obtiene la lista de los tags por tipo a buscar en el scrapingweb
		
		
		
		//3.- Iterar a la invocacion del scraping y la generacion de archivo de salida
		for (String url : listaUrlS) {
			
			myScrapingWeb.procesarScrapingWeb("tag", "tipo", url );
		}
		return "exito";
		
	}

}
