/**
 * interface que contempla todas as funcionalidades do sistema.
 * 
 * @author			Paulo Lima
 * @corporation 	TeckSoft 2014
 * @project 		Araujo Seguros
 * @version 		1.0 
 * @since			Release 1  16/02/2014
 */
package facade;

import java.sql.SQLException;
import java.util.List;

import models.Administrador;
import models.Preferencia;
import repository.RepositorioAdministrador;
import repository.RepositorioPreferencia;
import controllers.ControllerAdministrador;
import controllers.ControllerPreferencia;

public class Fachada {

	private static Fachada instancia;
	private ControllerAdministrador administrador;
	private ControllerPreferencia preferencia;

	// Metodo para iniciar todos os Repositorios e controladores
	// ---------------------------------------------------------------------
	private void iniciarControladores() {
		RepositorioAdministrador ra = new RepositorioAdministrador();
		administrador = new ControllerAdministrador(ra);

		RepositorioPreferencia rp = new RepositorioPreferencia();
		preferencia = new ControllerPreferencia(rp);
		
	}

	// Contrutor da classe
	// ---------------------------------------------------------------------
	private Fachada() {
		iniciarControladores();
	}

	// Metodo para verificar se a fachada ja existe ou instancia o mesmo.
	// ---------------------------------------------------------------------
	public static Fachada getInstance() {

		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	// ************************* Administrador *******************************
	// ---------------------------------------------------------------------
	public boolean inserirAdministrador(Administrador administrador)
			throws SQLException {
		return this.administrador.inserir(administrador);
	}

	// --------------------------------------------------------------------
	public boolean removerAdministrador(Administrador administrador)
			throws SQLException {
		return this.administrador.remover(administrador);
	}

	// ---------------------------------------------------------------------
	public boolean atualizarAdministrador(Administrador administrador)
			throws SQLException {
		return this.administrador.atualizar(administrador);
	}

	// ---------------------------------------------------------------------
	public List<Administrador> listarAdministrador() throws SQLException {
		return this.administrador.listar();
	}

	// ---------------------------------------------------------------------
	public boolean verificarLogin(Administrador administrador) throws SQLException {
		return this.administrador.verificarLogin(administrador);
	}

	// ---------------------------------------------------------------------
	// ************************* Preferencia *******************************
	// ---------------------------------------------------------------------
	public boolean inserirPrefenrencia(Preferencia preferencia)
			throws SQLException {
		return this.preferencia.inserir(preferencia);
	}

	// --------------------------------------------------------------------
	public boolean removerPreferencia(Preferencia prefencia)
			throws SQLException {
		return this.preferencia.remover(prefencia);
	}

	// ---------------------------------------------------------------------
	public boolean atualizarPreferencia(Preferencia preferencia)
			throws SQLException {
		return this.preferencia.atualizar(preferencia);
	}

	// ---------------------------------------------------------------------
	public List<Preferencia> listarPreferencia() throws SQLException {
		return this.preferencia.listar();
	}

	// ---------------------------------------------------------------------
}
