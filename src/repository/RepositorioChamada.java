package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Chamada;
import util.ConnectionFactory;

public class RepositorioChamada implements IRepositorioChamada {

	// ---------------------------------------------------------------------------------
	public boolean inserirChamada(Chamada chamada) throws SQLException {
		String sql = "insert into chamada values (?,?,?,?)";

		try {

			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			stm1.setInt(1, 0);
			stm1.setString(2, chamada.getData());
			stm1.setString(3, chamada.getHora());
			stm1.setInt(4, chamada.getCaixaId());
			stm1.execute();
			stm1.close();
			conIntranet.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//----------------------------------------------------------------------------------
	public List<Chamada> listarChamada() throws SQLException {
		ArrayList<Chamada> chamadaDB = new ArrayList<>();
		Chamada chamada;
		String sql = "SELECT * FROM chamada";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				chamada = new Chamada();
				chamada.setId(rs1.getInt(1));
				chamada.setData(rs1.getString(2));
				chamada.setHora(rs1.getString(3));
				chamada.setEspera(rs1.getString(4));
				chamada.setCaixaId(rs1.getInt(5));

				chamadaDB.add(chamada);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return chamadaDB ;
	}
	//----------------------------------------------------------------------------------
	public List<Chamada> buscarPorDatas(String dataInicio, String dataFim)
			throws SQLException {
		ArrayList<Chamada> chamadaDB = new ArrayList<>();
		Chamada chamada;
		String sql = "SELECT * FROM chamada WHERE data >='"+dataInicio+"' AND data <= '"+dataFim+"' ORDER BY id DESC";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				chamada = new Chamada();
				chamada.setId(rs1.getInt(1));
				chamada.setData(rs1.getString(2));
				chamada.setHora(rs1.getString(3));
				chamada.setEspera(rs1.getString(4));
				chamada.setCaixaId(rs1.getInt(5));

				chamadaDB.add(chamada);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return chamadaDB ;
	}
	//----------------------------------------------------------------------------------
}
