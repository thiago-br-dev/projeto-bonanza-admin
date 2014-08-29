package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Administrador;
import util.ConnectionFactory;

public class RepositorioAdministrador implements IRepositorioAdministrador {

	// ---------------------------------------------------------------------------------
	public boolean inserirAdministrador(Administrador administrador)
			throws SQLException {

		String sql = "insert into bem_segurado values (?,?,?,?,?,?,?)";

		try {
			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);

		stm1.setInt(1, administrador.getId());
		stm1.setString(2, administrador.getNome());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		
		} catch (Exception e) {
			return false;
		}



	}

	// ---------------------------------------------------------------------------------
	public boolean removerAdministrador(Administrador administrador)
			throws SQLException {

		String sql = "DELETE FROM bem_segurado WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);


		stm1.setInt(1, administrador.getId());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	// ---------------------------------------------------------------------------------
	public boolean atualizarAdministrador(Administrador administrador)
			throws SQLException {

		String sql = "UPDATE bem_segurado SET "
				+ "chassi=?, placa=?, veiculo=?, ano_modelo=?, "
				+ "RENAVAN=?, ano_fabricacao=? WHERE id=?";

		try {
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);


		stm1.setString(1, administrador.getNome());
		stm1.setString(2, administrador.getLogin());
		stm1.setInt(3, administrador.getId());
		stm1.execute();
		stm1.close();
		conIntranet.close();
		return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	// ---------------------------------------------------------------------------------
	public List<Administrador> listarAdministrador() throws SQLException {

		ArrayList<Administrador> AdministradorDB = new ArrayList<>();
		Administrador administrador;
		String sql = "SELECT * FROM bem_segurado ";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				administrador = new Administrador();
				administrador.setId(rs1.getInt(1));
				administrador.setNome(rs1.getString(2));

				AdministradorDB.add(administrador);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return AdministradorDB ;
	}
	// ---------------------------------------------------------------------------------
}
