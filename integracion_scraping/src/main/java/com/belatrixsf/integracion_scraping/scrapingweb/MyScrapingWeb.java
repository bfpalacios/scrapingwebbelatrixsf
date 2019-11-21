package com.belatrixsf.integracion_scraping.scrapingweb;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MyScrapingWeb {
	
	
public static final String url = "http://www.yahoo.com";
	
    public static void main (String args[]) {
		
    	System.out.println("Inicio");
		
        // Compruebo si me da un 200 al hacer la petición
        if (getStatusConnectionCode(url) == 200) {

        	System.out.println("entro");
            // Obtengo el HTML de la web en un objeto Document
            Document document = getHtmlDocument(url);
			
            
            Element body = document.body();
            
            
            // Busco todas las entradas que estan dentro de: 
            Elements entradas = body.getAllElements();
            
            System.out.println("Número de entradas en la página inicial de Jarroba: "+entradas.size()+"\n");
			
            
            StringBuilder tmp = new StringBuilder();
            // Paseo cada una de las entradas
            for (Element elem : entradas) {
                //String hashTag = elem.getElementsByClass("#").text();
                //String twitter = elem.getElementsByClass("@").toString();
                String fecha = elem.getElementsByClass("fecha").toString();

                //System.out.println(hashTag+"\n"+twitter+"\n"+fecha+"\n\n");
                System.out.println("Fecha "+fecha+"\n\n");
				
                // Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
                // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
                

                //tmp.append(hashTag).append("\n");
                //tmp.append(twitter).append("\n");
                tmp.append(fecha).append("\n");
            }
				
        }else
            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
    }
 
	
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
	 * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
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
