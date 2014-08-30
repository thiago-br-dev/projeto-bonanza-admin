package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Preferencia;
import util.ConnectionFactory;

public class RepositorioPreferencia implements IRepositorioPreferencia {
	// ---------------------------------------------------------------------------------
	public boolean inserirPreferencia(Preferencia preferencia)
			throws SQLException {
		String sql = "insert into preferencia values (?,?,?,?)";

		try {
			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);
	    stm1.setInt(1, 0);
		stm1.setString(2, preferencia.getTexto());
		stm1.setString(3, preferencia.getDataHoraModificacao());
		stm1.setInt(4, preferencia.getAdministradorId());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	// ---------------------------------------------------------------------------------
	public boolean removerPreferencia(Preferencia preferencia)
			throws SQLException {
		String sql = "DELETE FROM prefencia WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);


		stm1.setInt(1, preferencia.getId());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	// ---------------------------------------------------------------------------------
	public boolean atualizarPreferencia(Preferencia preferencia)
			throws SQLException {

		String sql = "UPDATE preferencia SET texto=?, data_hora_modificacao=?, administrador_id=? WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);


		stm1.setString(1, preferencia.getTexto());
		stm1.setString(2, preferencia.getDataHoraModificacao());
		stm1.setInt(3, preferencia.getAdministradorId());
		stm1.setInt(4, preferencia.getId());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// ---------------------------------------------------------------------------------
	public List<Preferencia> listarPreferencia() throws SQLException {
		
		ArrayList<Preferencia> preferenciaDB = new ArrayList<>();
		Preferencia preferencia;
		String sql = "SELECT * FROM preferencia";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				preferencia = new Preferencia();
				preferencia.setId(rs1.getInt(1));
				preferencia.setTexto(rs1.getString(2));

				preferenciaDB.add(preferencia);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return preferenciaDB ;
	}
	// ---------------------------------------------------------------------------------
}
