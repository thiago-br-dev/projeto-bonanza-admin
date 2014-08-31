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

		String sql = "insert into administrador values (?,?,?,?,?)";

		try {
			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();

	PreparedStatement stm1 = (PreparedStatement) conIntranet
			.prepareStatement(sql);

		stm1.setInt(1, administrador.getId());
		stm1.setString(2, administrador.getNome());
		stm1.setString(3, administrador.getLogin());
		stm1.setString(4, administrador.getSenha());
		stm1.setString(5, administrador.getDataHoraCadastro());
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
	public boolean removerAdministrador(Administrador administrador)
			throws SQLException {

		String sql = "DELETE FROM administrador WHERE id=?";

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
		String sql = "SELECT * FROM administrador ";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				administrador = new Administrador();
				administrador.setId(rs1.getInt(1));
				administrador.setNome(rs1.getString(2));
				administrador.setLogin(rs1.getString(3));

				AdministradorDB.add(administrador);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return AdministradorDB ;
	}
	// ---------------------------------------------------------------------------------
	public Administrador verificarLogin(Administrador administrador)
			throws SQLException {
		String sql = "SELECT * FROM administrador";

		try {
			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			ResultSet rs1 = stm1.executeQuery();

			while (rs1.next()) {
				
				
				Administrador adm = new Administrador();
				adm.setId(rs1.getInt(1));
				adm.setNome(rs1.getString(2));
				adm.setLogin(rs1.getString(3));
				adm.setSenha(rs1.getString(4));
				adm.setDataHoraCadastro(rs1.getString(5));
				
				if (adm.getLogin().equals(administrador.getLogin()) && adm.getSenha().equals(administrador.getSenha())){
					
					
					return adm;
				}

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// ---------------------------------------------------------------------------------
		public boolean verificarLoginExistente(String login) throws SQLException {

			String sql = "SELECT login FROM administrador";

			try {

				Connection conIntranet = new ConnectionFactory()
						.getConnectionIntranet();

				PreparedStatement stm1 = (PreparedStatement) conIntranet
						.prepareStatement(sql);

				ResultSet rs1 = stm1.executeQuery();

				while (rs1.next()) {

					String loginBD = rs1.getString(1);

					if (loginBD.equals(login)) {
						return true;
					}

				}
				rs1.close();
				stm1.close();
				conIntranet.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;

		}
	
}
