package repository;

import java.sql.SQLException;
import java.util.List;

import models.Administrador;

public interface IRepositorioAdministrador {

	boolean inserirAdministrador(Administrador administrador) throws SQLException;
	
	boolean removerAdministrador(Administrador administrador) throws SQLException;
	
	boolean atualizarAdministrador(Administrador administrador) throws SQLException;
	
	List<Administrador> listarAdministrador() throws SQLException;
	
	Administrador verificarLogin(Administrador administrador) throws SQLException;
	
}
