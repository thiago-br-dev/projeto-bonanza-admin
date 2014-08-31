package controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Caixa;
import repository.IRepositorioCaixa;
import repository.RepositorioCaixa;
import util.Sessao;

public class ControllerCaixa {

	private IRepositorioCaixa caixaRepositorio;

	// GETs e SETs do clienteRepositorio
	// --------------------------------------------------------------------
	public IRepositorioCaixa caixaRepositorio() {
		return caixaRepositorio;
	}

	public void setCaixaRepositorio(IRepositorioCaixa caixaRepositorio) {
		this.caixaRepositorio = caixaRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerCaixa(RepositorioCaixa RepositorioCaixa) {
		this.setCaixaRepositorio(RepositorioCaixa);
	}

	// -------------------------------------------------------------------
	public boolean inserir(Caixa caixa) throws SQLException {

		String dataSistema = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		String horaSistema = new SimpleDateFormat("HH:mm").format(new Date());

		caixa.setDataHoraCadastro(dataSistema + " " + horaSistema);
		caixa.setAdministradorId(Sessao.idAdministrador);
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
	public List<Caixa> buscarPorCaixa(String chave) throws SQLException {
		return caixaRepositorio.buscarPorCaixa(chave);
	}

	// -------------------------------------------------------------------

}
