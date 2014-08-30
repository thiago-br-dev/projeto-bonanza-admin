package controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Preferencia;
import repository.IRepositorioPreferencia;
import repository.RepositorioPreferencia;
import util.Sessao;

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
		
		String dataSistema = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		String horaSistema = new SimpleDateFormat("HH:mm").format(new Date());

		preferencia.setDataHoraModificacao(dataSistema + " " + horaSistema);
		preferencia.setAdministradorId(Sessao.idAdministrador);
		return preferenciaRepositorio.inserirPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public boolean remover(Preferencia preferencia) throws SQLException {
		return preferenciaRepositorio.removerPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public boolean atualizar(Preferencia preferencia) throws SQLException {

		String dataSistema = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		String horaSistema = new SimpleDateFormat("HH:mm").format(new Date());

		preferencia.setDataHoraModificacao(dataSistema + " " + horaSistema);
		preferencia.setAdministradorId(Sessao.idAdministrador);
		return preferenciaRepositorio.atualizarPreferencia(preferencia);
	}

	// -------------------------------------------------------------------
	public List<Preferencia> listar() throws SQLException {
		return preferenciaRepositorio.listarPreferencia();
	}

	// -------------------------------------------------------------------
}
