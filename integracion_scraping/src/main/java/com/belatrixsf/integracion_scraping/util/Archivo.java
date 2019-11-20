package com.belatrixsf.integracion_scraping.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
	
	static private String rutaArchivoUrlS = "/Users/bfpalacios/Documents/BELTRAIXSF/LISTA_URL.txt";
	private static ArrayList<String> listaUrl;

	

	/**
	 * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
	 * EJM:
	 * 		200 OK			300 Multiple Choices
	 * 		301 Moved Permanently	305 Use Proxy
	 * 		400 Bad Request		403 Forbidden
	 * 		404 Not Found		500 Internal Server Error
	 * 		502 Bad Gateway		503 Service Unavailable
	 * @param url
	 * @return Status Code
	 */
	static public ArrayList<String> muestraContenido(String archivo) throws FileNotFoundException, IOException {
		
		//definicipn de variable e inicializacion
		String cadena = null;
		listaUrl = null;
		
		
		
		//lectura de contenido del archivo de urls
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		
		
		//se obtienen las urls del archivo
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
			
			listaUrl.add(cadena);
		}
		b.close();
		
		
		return listaUrl;
	}
	
	
	
	public static void main(String[] args) throws IOException {
        muestraContenido(rutaArchivoUrlS);
    }
	
	

}
