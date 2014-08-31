package controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public ControllerAdministrador(
			RepositorioAdministrador RepositorioAdministrador) {
		this.setAdministradorRepositorio(RepositorioAdministrador);
	}

	// -------------------------------------------------------------------
	public boolean inserir(Administrador administrador) throws SQLException {
		String dataSistema = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		String horaSistema = new SimpleDateFormat("HH:mm").format(new Date());

		administrador.setDataHoraCadastro(dataSistema + " " + horaSistema);

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
	public Administrador verificarLogin(Administrador administrador)
			throws SQLException {
		return administradorRepositorio.verificarLogin(administrador);
	}

	// -------------------------------------------------------------------
	public boolean verificarLoginExistente(String login) throws SQLException, Exception {
		return administradorRepositorio.verificarLoginExistente(login);
	}
	// -------------------------------------------------------------------
}