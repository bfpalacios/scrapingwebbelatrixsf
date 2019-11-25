package com.belatrixsf.integracion_scraping.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.belatrixsf.integracion_scraping.dto.modelo.ScrapingWeb;
import com.belatrixsf.integracion_scraping.excepcion.DAOExcepcion;
import com.belatrixsf.integracion_scraping.util.ConexionBD; 
 

public class ScrapingWebDAO extends BaseDAO {

	
	/**
	 * Con esta método lista la informacion de la tabla parametrizable de tags 
	 * a buscar en las paginas web solicitadas.
	 *  
	 * @param scrapingWeb   
	 */
	public static Collection<ScrapingWeb> listar() throws DAOExcepcion {
		Collection<ScrapingWeb> scrapingWeb = new ArrayList<ScrapingWeb>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			String query = "select id, tag,tipo from scraping_web";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ScrapingWeb vo = new ScrapingWeb();
				vo.setId(rs.getInt("id"));
				vo.setTag(rs.getString("tag"));
				vo.setTipo(rs.getString("tipo"));
				scrapingWeb.add(vo);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			cerrarResultSet(rs);
			cerrarStatement(stmt);
			cerrarConexion(con);
		}
		return scrapingWeb;
	}

} 
