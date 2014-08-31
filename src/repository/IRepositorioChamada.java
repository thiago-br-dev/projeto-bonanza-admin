package repository;

import java.sql.SQLException;

import java.util.List;

import models.Chamada;

public interface IRepositorioChamada {

	
	boolean inserirChamada(Chamada chamada) throws SQLException;
	
	List<Chamada> listarChamada() throws SQLException;
	
	List<Chamada> buscarPorDatas(String dataInicio, String dataFim) throws SQLException;
}
