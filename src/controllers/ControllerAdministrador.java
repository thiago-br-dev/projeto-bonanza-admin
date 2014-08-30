package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Administrador;
import repository.IRepositorioAdministrador;
import repository.RepositorioAdministrador;

public class ControllerAdministrador {

	private IRepositorioAdministrador administradorRepositorio;

	// GETs e SETs do clienteRepositorio
	// --------------------------------------------------------------------
	public IRepositorioAdministrador administradorRepositorio() {
		return administradorRepositorio;
	}

	public void setAdministradorRepositorio(
			IRepositorioAdministrador administradorRepositorio) {
		this.administradorRepositorio = administradorRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerAdministrador(RepositorioAdministrador RepositorioAdministrador) {
		this.setAdministradorRepositorio(RepositorioAdministrador);
	}

	// -------------------------------------------------------------------
	public boolean inserir(Administrador administrador) throws SQLException {
		return administradorRepositorio.inserirAdministrador(administrador);
	}

	// -------------------------------------------------------------------
	public boolean remover(Administrador administrador) throws SQLException {
		return administradorRepositorio.removerAdministrador(administrador);
	}

	// -------------------------------------------------------------------
	public boolean atualizar(Administrador administrador) throws SQLException {
		return administradorRepositorio.atualizarAdministrador(administrador);
	}

	// -------------------------------------------------------------------
	public List<Administrador> listar() throws SQLException {
		return administradorRepositorio.listarAdministrador();
	}

	// -------------------------------------------------------------------
	public boolean verificarLogin(Administrador administrador) throws SQLException {
		return administradorRepositorio.verificarLogin(administrador);
	}

	// -------------------------------------------------------------------
}