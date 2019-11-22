package com.belatrixsf.integracion_scraping.scrapingweb;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.belatrixsf.integracion_scraping.util.Archivo;

public class MyScrapingWeb {

	// atributos de instancia
	// private StringBuilder contenidoArchivoOutput;

	//public static final String url = "https://guiasbus.us.es/citarredessociales/twitter"; 
	
	public static final String url = "https://www.yahoo.com";
	public static final String CONSTANTE_TIPO_AHREF = "1"; 
	public static final String CONSTANTE_TIPO_AHREF_NO3 = "2"; 
	public static final String CONSTANTE_TIPO_TWITTER = "3"; 
	
	public static final String NOMBRE_ARCHIVO_OUTPUT = "SCRAPINGWEB_"; 

	public String AHREF = "a[href]";
	public String AHREF3 = "a[href*=#]";
	public String TWITTER = "a[href]";

	public static void main(String args[]) {
		//procesarScrapingWeb("", "", url);
	}
	
	
	/**
	 * Con esta método se procesan los tags a buscar por tipo 
	 * en las urls solicitadas. Al finalizar las busquedas 
	 * guarda en un archivo de texto de salida toda la informacion
	 * recopilada.
	 * 
	 * @param tagABuscar
	 * @param tipoTag
	 * @param url 
	 */
	public   void procesarScrapingWeb(String tagABuscar, String tipoTag, String url) {
		// Compruebo si me da un 200 al hacer la petición
		if (getStatusConnectionCode(url) == 200) {

			// Obtengo el HTML de la web en un objeto Document
			Document document = getHtmlDocument(url);

			buscarTagPorTipo(tagABuscar, tipoTag, document, url);

		} else
			System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
	}

	public   void buscarTagPorTipo(String tagABuscar, String tipo, Document document, String url) {

		//objeto para guardar las entradas de las paginas webs
		StringBuilder contenidoArchivoSalida = new StringBuilder();
	
		// Busco todas las entradas que estan dentro de:
		Element body = document.body();
		Elements entradas = body.getAllElements();
		
		Elements hashTags = entradas.select("a[href]"); // Select all links
		Archivo documentoArchivo = new Archivo(); 
		
		
		if(tipo.equals(CONSTANTE_TIPO_AHREF)) {
		

			for (Element elem : hashTags) {
				String hrefHTML = elem.select(tagABuscar).toString();
				String href = elem.select(tagABuscar).text();
				
				if (!href.equals("")) {
					System.out.println("href " + href + "\n");
					System.out.println("hrefHTML " + hrefHTML + "\n");
					// Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
					// Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
	 
					//Se crea el archivo de salida
					try {
						contenidoArchivoSalida.append(href).append(System.getProperty("line.separator"));
						documentoArchivo.crearArchivoScrapingWeb(url, contenidoArchivoSalida);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			 
				

			}
		}
		
		if(tipo.equals(CONSTANTE_TIPO_AHREF)) {
			
			// referencias a los hashtags con #
			for (Element elem : hashTags) {
				String hashTagConMichiHTML = elem.attr("tagABuscar").toString();
				String hashTagConMichi = elem.select("tagABuscar").text();
	
				if (!hashTagConMichiHTML.equals("")) {
					System.out.println("hashTagConMichiHTML " + hashTagConMichiHTML + "\n");
					System.out.println("hashTagConMichi " + hashTagConMichi + "\n");
				}
				// Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
				// Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
	
				if (!hashTagConMichiHTML.equals("")) {
					 
					//Se crea el archivo de salida
					try {
						contenidoArchivoSalida.append(hashTagConMichiHTML).append(System.getProperty("line.separator"));
						documentoArchivo.crearArchivoScrapingWeb(url, contenidoArchivoSalida);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		if(tipo.equals(CONSTANTE_TIPO_TWITTER)) {
			// Twitter markup documentation:
			// https://dev.twitter.com/cards/markup
			String[] twitterTags = { "twitter:site", "twitter:site:id", "twitter:creator", "twitter:creator:id",
					"twitter:description", "twitter:title", "twitter:image", "twitter:image:alt", "twitter:player",
					"twitter:player:width", "twitter:player:height", "twitter:player:stream", "twitter:app:name:iphone",
					"twitter:app:id:iphone", "twitter:app:url:iphone", "twitter:app:name:ipad", "twitter:app:id:ipad",
					"twitter:app:url:ipadt", "twitter:app:name:googleplay", "twitter:app:id:googleplay",
					"twitter:app:url:googleplay" };
	
			// Connect to URL and extract source code
			//Document doc; 
				//doc = Jsoup.connect("http://stackoverflow.com/").get();
	
				for (String twitterTag : twitterTags) {
	
					// find a matching meta tag
					Element meta = document.select("meta[name=" + twitterTag + "]").first();
	
					// if found, get the value of the content attribute
					String content = meta != null ? meta.attr("content") : "";

					contenidoArchivoSalida.append(content).append(System.getProperty("line.separator"));
					try {
						documentoArchivo.crearArchivoScrapingWeb(url, contenidoArchivoSalida);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// display results
					System.out.printf("%s = %s%n", twitterTag, content);
				}
	 
		}
		
	}

	/**
	 * Con esta método compruebo el Status code de la respuesta que recibo al hacer
	 * la petición EJM: 200 OK 300 Multiple Choices 301 Moved Permanently 305 Use
	 * Proxy 400 Bad Request 403 Forbidden 404 Not Found 500 Internal Server Error
	 * 502 Bad Gateway 503 Service Unavailable
	 * 
	 * @param url
	 * @return Status Code
	 */
	public   int getStatusConnectionCode(String url) {
		System.out.println("getStatusConnectionCode");

		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
			 
		}
		return response.statusCode();
	}

	/**
	 * Con este método devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitirá parsearlo con los métodos de la librelia
	 * JSoup
	 * 
	 * @param url
	 * @return Documento con el HTML
	 */
	public   Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}

}
