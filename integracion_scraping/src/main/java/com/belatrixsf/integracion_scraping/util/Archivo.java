package com.belatrixsf.integracion_scraping.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.ArrayList; 

public class Archivo  {

	//private static final String RUTA_ARCHIVO_INPUT 	= "C:\\SCRAPINGWEB\\LISTA_URL.txt"; 
	private static final String RUTA_ARCHIVO_INPUT 	= "/Users/bfpalacios/Documents/BELTRAIXSF/LISTA_URL.txt"; 
	//private static final String RUTA_ARCHIVO_OUTPUT = "C:\\SCRAPINGWEB\\";  
	private static final String RUTA_ARCHIVO_OUTPUT = "/Users/bfpalacios/Documents/BELATRIXSF/";  
	private static final String ARCHIVO_NAME_OUTPUT = "SCRAPINGWEB_DOCUMENT_OUT_";	
	private static ArrayList<String> listaUrl;
	
	
	
	/**
	 * Con esta método se obtiene una lista de strings 
	 * con las urls de las paginas a explotar para 
	 * obtener informacion parametrizable.
	 * 
	 * @param rutaArchivo
	 * @return listaUrl 
	 */
	public static   ArrayList<String> obtenerListaURLParaScrapingWeb(String rutaArchivo)
			throws FileNotFoundException, IOException {

		// definicipn de variable e inicializacion
		String cadena = null;
		listaUrl = new ArrayList<String>();

		// lectura de contenido del archivo de urls
		FileReader file = new FileReader(rutaArchivo);
		BufferedReader b = new BufferedReader(file);

		// se obtienen las urls del archivo
		while ((cadena = b.readLine()) != null) {
			//System.out.println(cadena);

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
	 * @param contenidoArchivoSalida
	 * @return  
	 */
	public    void crearArchivoScrapingWeb(String nombreURL, StringBuilder contenidoArchivoSalida)
			throws FileNotFoundException, IOException {

			/*String ruta = RUTA_ARCHIVO_OUTPUT+ARCHIVO_NAME_OUTPUT+nombreURL.substring(7, nombreURL.length()).replaceAll("/", "")+".txt";
	       
	        File archivo = new File(ruta);
	        BufferedWriter bw = null;
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contenidoArchivoSalida.toString());
	        } else {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contenidoArchivoSalida.toString());
	        }
	        bw.close();*/
		
		
		BufferedWriter bw = null;
	    FileWriter fw = null;

	    try {
	    	String ruta = RUTA_ARCHIVO_OUTPUT+ARCHIVO_NAME_OUTPUT+nombreURL.substring(7, nombreURL.length()).replaceAll("/", "")+".txt";
	        File file = new File(ruta);
	        
	        // Si el archivo no existe, se crea!
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        // flag true, indica adjuntar información al archivo.
	        fw = new FileWriter(file.getAbsoluteFile(), true);
	        bw = new BufferedWriter(fw);
	        bw.write(contenidoArchivoSalida.toString());
	      //  System.out.println("información agregada!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	                        //Cierra instancias de FileWriter y BufferedWriter
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }   
	    
	}
 

}
