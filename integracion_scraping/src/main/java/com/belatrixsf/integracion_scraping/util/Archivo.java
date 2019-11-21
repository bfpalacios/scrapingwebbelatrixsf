package com.belatrixsf.integracion_scraping.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Archivo {

	static private String rutaArchivoUrlS = "/Users/bfpalacios/Documents/BELTRAIXSF/LISTA_URL.txt";
	static private String rutaArchivoUrlOut = "/Users/bfpalacios/Documents/BELTRAIXSF/SCRAPINGWEB_DOCUMENT_OUT_";
	static private ArrayList<String> listaUrl;

	/**
	 * Con esta método se obtiene una lista de strings 
	 * con las urls de las paginas a explotar para 
	 * obtener informacion parametrizable.
	 * 
	 * @param rutaArchivo
	 * @return listaUrl 
	 */
	static public ArrayList<String> obtenerListaURLParaScrapingWeb(String rutaArchivo)
			throws FileNotFoundException, IOException {

		// definicipn de variable e inicializacion
		String cadena = null;
		listaUrl = new ArrayList<String>();

		// lectura de contenido del archivo de urls
		FileReader file = new FileReader(rutaArchivo);
		BufferedReader b = new BufferedReader(file);

		// se obtienen las urls del archivo
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);

			listaUrl.add(cadena);
		}
		b.close();
		
		
		System.out.println("tamaño de la lista " + listaUrl.size());

		return listaUrl;
	}
	
	/**
	 * Con esta método crea archivos de salida 
	 * con la informacion recopilada por cada 
	 * pagina web.
	 * 
	 * @param nombreURL
	 * @param scrapingWebDocument
	 * @return  
	 */
	static void crearArchivoScrapingWeb(String nombreURL, String scrapingWebDocument)
			throws FileNotFoundException, IOException {

		PrintWriter out = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(rutaArchivoUrlOut+"_"+nombreURL+".txt", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(scrapingWebDocument);
		} catch (IOException e) {
			// File writing/opening failed at some stage.
		} finally {
			try {
				if (out != null) {
					out.close(); // Will close bw and fw too
				} else if (bw != null) {
					bw.close(); // Will close fw too
				} else if (fw != null) {
					fw.close();
				} else {
					// Oh boy did it fail hard! :3
				}
			} catch (IOException e) {
				// Closing the file writers failed for some obscure reason
			}
		}

	}

	public static void main(String[] args) throws IOException {
		obtenerListaURLParaScrapingWeb(rutaArchivoUrlS);
		
		
		crearArchivoScrapingWeb("www.yahoo.com", "hola mundo");
	}

}
