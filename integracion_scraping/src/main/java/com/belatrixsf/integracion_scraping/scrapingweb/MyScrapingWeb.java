package com.belatrixsf.integracion_scraping.scrapingweb;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MyScrapingWeb {

	// atributos de instancia
	// private StringBuilder contenidoArchivoOutput;

	public static final String url = "https://guiasbus.us.es/citarredessociales/twitter"; 

	public static void main(String args[]) {
		procesarScrapingWeb("", "", url);
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
	public static void procesarScrapingWeb(String tagABuscar, String tipoTag, String url) {
		// Compruebo si me da un 200 al hacer la petición
		if (getStatusConnectionCode(url) == 200) {

			// Obtengo el HTML de la web en un objeto Document
			Document document = getHtmlDocument(url);

			buscarTagPorTipo(tagABuscar, tipoTag, document);

		} else
			System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
	}

	public static void buscarTagPorTipo(String tagABuscar, String tipo, Document document) {

		
		// Busco todas las entradas que estan dentro de:
		Element body = document.body();
		Elements entradas = body.getAllElements();

		Elements hashTags = entradas.select("a[href]"); // Select all links

		
		//objeto para guardar las entradas de las paginas webs
		StringBuilder contenidoArchivoSalida = new StringBuilder();

		// referencias a los hashtags con #
		for (Element elem : hashTags) {
			String hashTagConMichiHTML = elem.attr("a[href*=#]").toString();
			String hashTagConMichi = elem.select("a[href*=#]").text();

			if (!hashTagConMichiHTML.equals("")) {
				System.out.println("hashTagConMichiHTML " + hashTagConMichiHTML + "\n");
				System.out.println("hashTagConMichi " + hashTagConMichi + "\n");
			}
			// Con el método "text()" obtengo el contenido que hay dentro de las etiquetas
			// HTML
			// Con el método "toString()" obtengo todo el HTML con etiquetas incluidas

			contenidoArchivoSalida.append(hashTagConMichi).append("\n");
		}

		for (Element elem : hashTags) {
			String hrefHTML = elem.select("a[href]").toString();
			String href = elem.select("a[href]").text();
			if (!href.equals("")) {
				System.out.println("href " + href + "\n");
				System.out.println("hrefHTML " + hrefHTML + "\n");
			}
			// Con el método "text()" obtengo el contenido que hay dentro de las etiquetas
			// HTML
			// Con el método "toString()" obtengo todo el HTML con etiquetas incluidas

			if (!href.equals("")) {
				contenidoArchivoSalida.append(href).append("\n");
			}

		}

		// Twitter markup documentation:
		// https://dev.twitter.com/cards/markup
		String[] twitterTags = { "twitter:site", "twitter:site:id", "twitter:creator", "twitter:creator:id",
				"twitter:description", "twitter:title", "twitter:image", "twitter:image:alt", "twitter:player",
				"twitter:player:width", "twitter:player:height", "twitter:player:stream", "twitter:app:name:iphone",
				"twitter:app:id:iphone", "twitter:app:url:iphone", "twitter:app:name:ipad", "twitter:app:id:ipad",
				"twitter:app:url:ipadt", "twitter:app:name:googleplay", "twitter:app:id:googleplay",
				"twitter:app:url:googleplay" };

		// Connect to URL and extract source code
		Document doc;
		try {
			doc = Jsoup.connect("http://stackoverflow.com/").get();

			for (String twitterTag : twitterTags) {

				// find a matching meta tag
				Element meta = doc.select("meta[name=" + twitterTag + "]").first();

				// if found, get the value of the content attribute
				String content = meta != null ? meta.attr("content") : "";

				// display results
				System.out.printf("%s = %s%n", twitterTag, content);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static int getStatusConnectionCode(String url) {
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
	public static Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}

}
