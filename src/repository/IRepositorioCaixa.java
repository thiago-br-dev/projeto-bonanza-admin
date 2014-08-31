package repository;

import java.sql.SQLException;
import java.util.List;
import models.Caixa;

public interface IRepositorioCaixa {

	
	boolean inserirCaixa(Caixa caixa) throws SQLException;
	
	boolean removerCaixa(Caixa caixa) throws SQLException;
	
	boolean atualizarCaixa(Caixa caixa) throws SQLException;
	
	List<Caixa> listarCaixa() throws SQLException;
	
	List<Caixa> buscarPorCaixa(String chave) throws SQLException;
}
