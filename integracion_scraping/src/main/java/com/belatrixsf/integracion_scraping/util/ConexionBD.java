package com.belatrixsf.integracion_scraping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	 
	public static Connection obtenerConexion() throws SQLException {
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/dbbelatrixsf?autoReconnect=true&useSSL=false",
						"root", "root.2019");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	} 


} 
