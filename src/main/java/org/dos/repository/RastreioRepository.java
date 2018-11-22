package org.dos.repository;

import org.dos.config.ConnectionDB;
import org.dos.model.Rastreio;
import org.dos.model.Status;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *          CLASSE QUE VAI REALIZAR A PERSISTeNCIA DO NOSSO OBJETO RastreioModel
 *          NO BANCO DE DADOS.
 *
 *
 */
@Repository
public class RastreioRepository {

	/**
	 * @PersistenceContext é o local onde ficam armazenados as entidades que
	 *                     estao sendo manipuladas pelo EntityManager
	 *
	 *
	 * @PersistenceContext(type = PersistenceContextType.EXTENDED) assim o
	 *                          servidor vai gerenciar para nos as entidades.
	 *
	 **/
	//@PersistenceContext(type = PersistenceContextType.EXTENDED)
	//private EntityManager manager;
	private Connection con;

	public RastreioRepository() throws SQLException, ClassNotFoundException {
		this.con =  ConnectionDB.getConnection();
	}

	/**
	 *
	 * @param idRastreio
	 * @return Rastreio
	 */
	public Rastreio buscarDetalhesRastreio (int idRastreio) {
		Rastreio r = null;

		try {//futuramente implementar via hibernate. Apenas para proposito de teste
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM rastreios WHERE id=? OR cod_rastreio=? ORDER BY id");
			stmt.setInt(1, idRastreio);
			stmt.setInt(2, idRastreio);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				r = new Rastreio(rs.getInt("id"),
						rs.getString("cod_rastreio"),
						rs.getString("nome"),
						this.buscarStatusRastreio(idRastreio),
						rs.getString("origem"),
						rs.getString("destino")
						);
			}
			rs.close();
			stmt.close();
			return r;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return r;
		}
		//return manager.find(Certificate.class, idCertificate);
	}

	public ArrayList<Status> buscarStatusRastreio (int idRastreio) {
		ArrayList<Status> list = new ArrayList<>();
		try {//futuramente implementar via hibernate. Apenas para proposito de teste
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM status WHERE id_rastreio=? ORDER BY id");
			stmt.setInt(1, idRastreio);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Status(rs.getInt("id"),
						rs.getInt("id_rastreio"),
						rs.getTimestamp("horario"),
						rs.getString("descricao"),
						rs.getString("localizacao")
						));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @return
	 */
	public List<Rastreio> listaRastreios() {
		ArrayList<Rastreio> list = new ArrayList<>();
		try {//futuramente implementar via hibernate. Apenas para proposito de teste
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM rastreios ORDER BY id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Rastreio(rs.getInt("id"),
						rs.getString("cod_rastreio"),
						rs.getString("nome"),
						null,
						rs.getString("origem"),
						rs.getString("destino")
						));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return list;
	}

}
