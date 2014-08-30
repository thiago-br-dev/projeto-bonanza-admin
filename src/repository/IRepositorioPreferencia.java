package repository;

import java.sql.SQLException;
import java.util.List;
import models.Preferencia;

public interface IRepositorioPreferencia {

	boolean inserirPreferencia(Preferencia preferencia) throws SQLException;
	
	boolean removerPreferencia(Preferencia preferencia) throws SQLException;
	
	boolean atualizarPreferencia(Preferencia preferencia) throws SQLException;
	
	List<Preferencia> listarPreferencia() throws SQLException;

}
