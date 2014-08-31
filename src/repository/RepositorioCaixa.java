package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import models.Caixa;

public class RepositorioCaixa implements IRepositorioCaixa {

	// ---------------------------------------------------------------------------------
	public boolean inserirCaixa(Caixa caixa) throws SQLException {
		String sql = "insert into caixa values (?,?,?,?,?)";

		try {

			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			stm1.setInt(1, caixa.getId());
			stm1.setString(2, caixa.getCaixa());
			stm1.setString(3, caixa.getAtendente());
			stm1.setString(4, caixa.getDataHoraCadastro());
			stm1.setInt(5, caixa.getAdministradorId());
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
	public boolean removerCaixa(Caixa caixa) throws SQLException {
		String sql = "DELETE FROM caixa WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			stm1.setInt(1, caixa.getId());
			stm1.execute();
			stm1.close();
			conIntranet.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ---------------------------------------------------------------------------------
	public boolean atualizarCaixa(Caixa caixa) throws SQLException {

		String sql = "UPDATE caixa SET "
				+ "numero_caixa=?, atendente=?, data_hora_cadastro=?, administrador_id=? WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			stm1.setInt(1, caixa.getId());
			stm1.setString(2, caixa.getCaixa());
			stm1.setString(3, caixa.getAtendente());
			stm1.setString(4, caixa.getDataHoraCadastro());
			stm1.setInt(5, caixa.getAdministradorId());
			stm1.execute();
			stm1.close();
			conIntranet.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ---------------------------------------------------------------------------------
	public List<Caixa> listarCaixa() throws SQLException {
		
		ArrayList<Caixa> caixaBD = new ArrayList<>();
		Caixa caixa;
		String sql = "SELECT * FROM caixa";

		Connection conIntranet = new ConnectionFactory()
				.getConnectionIntranet();

		PreparedStatement stm1 = (PreparedStatement) conIntranet
				.prepareStatement(sql);

		ResultSet rs1 = stm1.executeQuery();

		while (rs1.next()) {

			caixa = new Caixa();
			caixa.setId(rs1.getInt(1));
			caixa.setCaixa(rs1.getString(2));
			caixa.setAtendente(rs1.getString(3));
			caixa.setDataHoraCadastro(rs1.getString(4));
			caixa.setAdministradorId(rs1.getInt(5));


			caixaBD.add(caixa);

		}
		rs1.close();
		stm1.close();
		conIntranet.close();

		return caixaBD;
	}
	// ---------------------------------------------------------------------------------
	public List<Caixa> buscarPorCaixa(String chave) throws SQLException {
		ArrayList<Caixa> caixaBD = new ArrayList<>();
		Caixa caixa;
		String sql = "SELECT * FROM caixa WHERE nunero_caixa='"+chave+"'";

		Connection conIntranet = new ConnectionFactory()
				.getConnectionIntranet();

		PreparedStatement stm1 = (PreparedStatement) conIntranet
				.prepareStatement(sql);

		ResultSet rs1 = stm1.executeQuery();

		while (rs1.next()) {

			caixa = new Caixa();
			caixa.setId(rs1.getInt(1));
			caixa.setCaixa(rs1.getString(2));
			caixa.setAtendente(rs1.getString(3));
			caixa.setDataHoraCadastro(rs1.getString(4));
			caixa.setAdministradorId(rs1.getInt(5));


			caixaBD.add(caixa);

		}
		rs1.close();
		stm1.close();
		conIntranet.close();

		return caixaBD;
	}
	// ---------------------------------------------------------------------------------
}
