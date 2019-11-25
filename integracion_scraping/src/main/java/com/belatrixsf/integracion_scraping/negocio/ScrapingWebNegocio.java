package com.belatrixsf.integracion_scraping.negocio;

import java.io.IOException;  
import java.util.ArrayList;  
import com.belatrixsf.integracion_scraping.dao.ScrapingWebDAO;
import com.belatrixsf.integracion_scraping.dto.modelo.ScrapingWeb;
import com.belatrixsf.integracion_scraping.excepcion.DAOExcepcion;
import com.belatrixsf.integracion_scraping.scrapingweb.MyScrapingWeb;
import com.belatrixsf.integracion_scraping.util.Archivo;
import com.belatrixsf.integracion_scraping.util.MyProperties; 

public class ScrapingWebNegocio extends Thread {
	
	
	//variables de instancia
	private String nombre;
	private static Archivo archivo;
	private ArrayList<ScrapingWeb> listaTagsRepository;
	private long initialTime; 
    private static ScrapingWebDAO scrapingWebDAO;
    
    //metodo constructos con parametros
	public ScrapingWebNegocio(String nombre, ArrayList<ScrapingWeb> listaTagsRepository, long initialTime) {
		super();
		this.nombre = nombre;
		this.listaTagsRepository = listaTagsRepository;
		this.initialTime = initialTime;
	}
	
	
	/**
	 * Con esta método se realiza el proceso de forma paralela y mas rapida 
	 * que una funciona secuencial.
	 *
	 */
	@Override
	public void run() {
		MyProperties myProperties = new MyProperties();
		ArrayList<String> listaUrlS = new ArrayList<String>();
		
	
		 
		// 1.- Se obtiene las urls del archivo de entrada a buscar scrapingweb
		try {

			listaUrlS = archivo.obtenerListaURLParaScrapingWeb(myProperties.getPropValueRutaInput());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2.- Se obtiene la lista de los tags por tipo a buscar en el scrapingweb
		//los taggs se obtienen de una tabla de base de datos parametrizable
		for (ScrapingWeb scrapingWeb : listaTagsRepository) {

			//System.out.println("BUSCAR POR TIPO TAGS " + scrapingWeb.getTipo());
			//System.out.println("BUSCAR POR  TAGS " + scrapingWeb.getTag());

			// 3.- Iterar a la invocacion del scraping y la generacion de archivo de salida
			for (String url : listaUrlS) {

				//System.out.println("URLS " + url);

				MyScrapingWeb.procesarScrapingWeb(scrapingWeb.getTag(), scrapingWeb.getTipo(), url);
			}

		}

		System.out.println("El scraping web " + this.nombre + " HA TERMINADO DE PROCESAR " + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

	} 
	

	 
	public static void main(String args[]) {

		  
		//se inicia conexion 
		ArrayList<ScrapingWeb> listaTagsRepository = null;
		try {
			listaTagsRepository = (ArrayList<ScrapingWeb>) scrapingWebDAO.listar();
		} catch (DAOExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Tiempo inicial de referencia
		long initialTime = System.currentTimeMillis();

		ScrapingWebNegocio scrapingWebNegocio = new ScrapingWebNegocio("Invocacion al scrapingweb hilos", listaTagsRepository,
				initialTime);

		scrapingWebNegocio.start();
	 
		//consultarScrapingWeb();
	}
	
	/**
	 * Con esta método se ejecuta de forma secuencial la creacion de archivos de salida
	 * despues de realizar la busqueda por scraping web.
	 *  
	 */ 
	public static String consultarScrapingWeb() {

		// variables ArrayList<String>
		ArrayList<String> listaUrlS2 = new ArrayList<String>();
		 
		// Tiempo inicial de referencia
		long initialTime = System.currentTimeMillis();
		MyProperties myProperties = new MyProperties();

		ArrayList<ScrapingWeb> listaTags2 =  new ArrayList<ScrapingWeb>();

		ScrapingWeb scrapingWeb1 = new ScrapingWeb();
		ScrapingWeb scrapingWeb2 = new ScrapingWeb();
		ScrapingWeb scrapingWeb3 = new ScrapingWeb();

		scrapingWeb1.setTipo("1");
		scrapingWeb1.setTag("a[href]");
		listaTags2.add(scrapingWeb1);

		scrapingWeb2.setTipo("2");
		scrapingWeb2.setTag("a[href*=#]");
		listaTags2.add(scrapingWeb2);
		
		scrapingWeb3.setTipo("3");
		scrapingWeb3.setTag("title");
		listaTags2.add(scrapingWeb3);

		// 1.- Se obtiene las urls a buscar scrapingweb
		try {

			listaUrlS2 = archivo.obtenerListaURLParaScrapingWeb(myProperties.getPropValueRutaInput());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2.- Se obtiene la lista de los tags por tipo a buscar en el scrapingweb

		for (ScrapingWeb scrapingWeb : listaTags2) {

			System.out.println("TIPOS " + scrapingWeb.getTipo());

			// 3.- Iterar a la invocacion del scraping y la generacion de archivo de salida
			for (String url : listaUrlS2) {

				//System.out.println("URLS " + url);

				MyScrapingWeb.procesarScrapingWeb(scrapingWeb.getTag(), scrapingWeb.getTipo(), url);
			}

		}

		System.out.println("El scraping web  HA TERMINADO DE PROCESAR " + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - initialTime) / 1000 + "seg");

		return "exito";
	}

}
