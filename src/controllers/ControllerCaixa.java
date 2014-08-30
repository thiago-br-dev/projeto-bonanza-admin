package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Caixa;
import repository.IRepositorioCaixa;
import repository.RepositorioCaixa;

public class ControllerCaixa {
	
	private IRepositorioCaixa caixaRepositorio;

	// GETs e SETs do clienteRepositorio
	// --------------------------------------------------------------------
	public IRepositorioCaixa caixaRepositorio() {
		return caixaRepositorio;
	}

	public void setCaixaRepositorio(
			IRepositorioCaixa caixaRepositorio) {
		this.caixaRepositorio = caixaRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerCaixa(RepositorioCaixa RepositorioCaixa) {
		this.setCaixaRepositorio(RepositorioCaixa);
	}

	// -------------------------------------------------------------------
	public boolean inserir(Caixa caixa) throws SQLException {
		caixa.setAdministradorId(1);
		return caixaRepositorio.inserirCaixa(caixa);
	}

	// -------------------------------------------------------------------
	public boolean remover(Caixa caixa) throws SQLException {
		return caixaRepositorio.removerCaixa(caixa);
	}

	// -------------------------------------------------------------------
	public boolean atualizar(Caixa caixa) throws SQLException {
		return caixaRepositorio.atualizarCaixa(caixa);
	}

	// -------------------------------------------------------------------
	public List<Caixa> listar() throws SQLException {
		return caixaRepositorio.listarCaixa();
	}

	// -------------------------------------------------------------------

}
