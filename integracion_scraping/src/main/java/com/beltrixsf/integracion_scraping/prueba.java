package com.beltrixsf.integracion_scraping;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String url = "https://hollywoodlife.com/2019/11/24/camila-cabello-amas-2019-dress-performance-photos/";
		//String url = "http://stackoverflow.com/";
		
		
		/*Document doc;
		try {
			doc = Jsoup.connect(url).get();
			
			Element tweetText = doc.select("p.js-tweet-text.tweet-text").first();
			System.out.println(tweetText.text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
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
							doc = Jsoup.connect(url).get();
							
							for (String twitterTag : twitterTags) {
								
								// find a matching meta tag
								//Element meta = doc.select("meta[name=" + twitterTag + "]").first();
								Element meta = doc.select("p.js-tweet-text.tweet-text").first();
				
								// if found, get the value of the content attribute
								String content = meta != null ? meta.attr("content") : "";

								//contenidoArchivoSalida.append(content).append(System.getProperty("line.separator"));
								// display results
								System.out.printf("%s = %s%n", twitterTag, content);
								System.out.println("meta " +meta.text());
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
						
		

	}

}
