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
import repository.RepositorioAdministrador;
import controllers.ControllerAdministrador;

public class Fachada {

	private static Fachada instancia;
	private ControllerAdministrador administrador;

	// Metodo para iniciar todos os Repositorios e controladores
	// ---------------------------------------------------------------------
	private void iniciarControladores() {
		RepositorioAdministrador ra = new RepositorioAdministrador();
		administrador = new ControllerAdministrador(ra);

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


}
