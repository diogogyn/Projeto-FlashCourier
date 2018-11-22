package org.dos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

	public static void Search() throws SQLException {
		Connection con = ConnectionDBF.getConnection();
		try {//futuramente implementar via hibernate. Apenas para proposito de teste
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ECADCLIENTE LIMIT 2");
			//stmt.setInt(1, 1);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("NMCLIENTE"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		//return manager.find(Certificate.class, idCertificate);
	}
	
	public static void main(String[] args) throws SQLException{
		Search();
	}
}