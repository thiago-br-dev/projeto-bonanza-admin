package exceptions;

import javax.swing.JOptionPane;

public class ExceptionPDF extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionPDF() {
		JOptionPane.showMessageDialog(null,
				"Ops ! Ocorreu um erro ao gerar o seu PDF. Tente novamente.",
				"Erro de Conexão", JOptionPane.ERROR_MESSAGE);
	}

}