package com.belatrixsf.integracion_scraping.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Metodo base para las conexiones a la base de datos
 * 
 */
public class BaseDAO {

	protected static void cerrarConexion(Connection con) throws RuntimeException {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarConexion: " + se);
		}
	}

	protected static void cerrarResultSet(ResultSet rs) throws RuntimeException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarResultSet: " + se);
		}
	}

	protected static void cerrarStatement(PreparedStatement stmt)
			throws RuntimeException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarStatement: " + se);
		}
	}

	protected static void cerrarCallable(CallableStatement callstmt)
			throws RuntimeException {
		try {
			if (callstmt != null) {
				callstmt.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarCallable: " + se);
		}
	}
}
