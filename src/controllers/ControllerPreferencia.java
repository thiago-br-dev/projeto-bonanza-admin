package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Administrador;
import models.Preferencia;
import repository.IRepositorioAdministrador;
import repository.IRepositorioPreferencia;
import repository.RepositorioAdministrador;
import repository.RepositorioPreferencia;

public class ControllerPreferencia {
	private IRepositorioPreferencia preferenciaRepositorio;

	// GETs e SETs do clienteRepositorio
	// --------------------------------------------------------------------
	public IRepositorioPreferencia preferenciaRepositorio() {
		return preferenciaRepositorio;
	}

	public void setPreferenciaRepositorio(
			IRepositorioPreferencia preferenciaRepositorio) {
		this.preferenciaRepositorio = preferenciaRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerPreferencia(RepositorioPreferencia RepositorioPreferencia) {
		this.setPreferenciaRepositorio(RepositorioPreferencia);
	}

	// -------------------------------------------------------------------
	public boolean inserir(Preferencia preferencia) throws SQLException {
		preferencia.setAdministradorId(1);
		return preferenciaRepositorio.inserirPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public boolean remover(Preferencia preferencia) throws SQLException {
		return preferenciaRepositorio.removerPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public boolean atualizar(Preferencia preferencia) throws SQLException {
		return preferenciaRepositorio.atualizarPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public List<Preferencia> listar() throws SQLException {
		return preferenciaRepositorio.listarPreferencia();
	}

	// -------------------------------------------------------------------
}
