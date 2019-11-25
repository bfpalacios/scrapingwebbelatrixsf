package com.belatrixsf.integracion_scraping.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties;
  
 
public class MyProperties {
	

	private  String rutaInput = "";
	private  String rutaOutPut = "";
	private  InputStream inputStream = null;
	
	
	public   String getPropValueRutaInput() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
  
 
			// get the property value and print it out
			 rutaInput = prop.getProperty("ruta.archivo.input"); 
  
			System.out.println("\nRutas input" + rutaInput );
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return rutaInput;
	}
	
	public   String getPropValueRutaOutput() throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
  
			rutaOutPut = prop.getProperty("ruta.archivo.output"); 
  
			System.out.println(" ruta output" + rutaOutPut);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return rutaInput;
	}
	
	
	 
}
