package view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroAdministrador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel erro, fundoMensagemErro, fundoMensagemSalvo, sucesso;
	private JTextField campoNome;
	private JTextField campoLogin;
	private JTextField campoConfirmarSenha;
	private JTextField campoSenha;

	public static void main(String[] args) {
		
		try {
			
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			CadastroAdministrador dialog = new CadastroAdministrador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public CadastroAdministrador() {
		
		setTitle("Cadastro de Administrador - Bonanza Supermercados");
		setBounds(100, 100, 638, 356);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setBounds(34, 171, 561, 29);
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNome.setColumns(10);
		
		campoNome.setBorder(BorderFactory.createCompoundBorder(
				campoNome.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		contentPanel.add(campoNome);
		
		JLabel nome = new JLabel("Nome Completo");
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Cambria", Font.PLAIN, 17));
		nome.setBounds(34, 144, 117, 22);
		contentPanel.add(nome);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.DARK_GRAY);
		lblLogin.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblLogin.setBounds(34, 207, 40, 22);
		contentPanel.add(lblLogin);
		
		campoLogin = new JTextField();
		campoLogin.setColumns(10);
		campoLogin.setBounds(34, 234, 189, 29);
		contentPanel.add(campoLogin);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setForeground(Color.DARK_GRAY);
		lblConfirmarSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblConfirmarSenha.setBounds(419, 207, 176, 22);
		contentPanel.add(lblConfirmarSenha);
		
		campoConfirmarSenha = new JTextField();
		campoConfirmarSenha.setColumns(10);
		campoConfirmarSenha.setBounds(419, 234, 176, 29);
		contentPanel.add(campoConfirmarSenha);
		
		campoSenha = new JTextField();
		campoSenha.setColumns(10);
		campoSenha.setBounds(233, 234, 176, 29);
		contentPanel.add(campoSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.DARK_GRAY);
		lblSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblSenha.setBounds(233, 207, 47, 22);
		contentPanel.add(lblSenha);
		
		JButton botaoSalvar = new JButton("Salvar");
		
		botaoSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				validarInformacoes();
				
			}
		
		});
		
		botaoSalvar.setBounds(460, 274, 136, 30);
		contentPanel.add(botaoSalvar);
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setBounds(314, 274, 136, 30);
		contentPanel.add(botaoCancelar);
		
		erro = new JLabel("Erro");
		erro.setHorizontalAlignment(SwingConstants.CENTER);
		erro.setForeground(Color.GRAY);
		erro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erro.setVisible(false);
		erro.setBounds(168, 97, 427, 24);
		
		contentPanel.add(erro);
		
		fundoMensagemErro = new JLabel("New label");
		fundoMensagemErro.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_erro.png")));
		fundoMensagemErro.setBounds(167, 93, 428, 34);
		fundoMensagemErro.setVisible(false);
		contentPanel.add(fundoMensagemErro);
		
		sucesso = new JLabel("Usuário cadastrado com sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(168, 97, 427, 24);
		
		contentPanel.add(sucesso);
		
		fundoMensagemSalvo = new JLabel("New label");
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_erro.png")));
		fundoMensagemSalvo.setBounds(167, 93, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 304);
		lblNewLabel.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/novo_administrador.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
	
	public void validarInformacoes() {
		
		if (campoNome.getText().trim().isEmpty()) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("O campo (nome completo) ainda não foi preenchido.");
			erro.setVisible(true);

			campoNome.requestFocus();

		}

		else if (campoLogin.getText().trim().isEmpty()) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("O campo (login) ainda não foi preenchido.");
			erro.setVisible(true);

			campoLogin.requestFocus();

		}

		else if (campoSenha.getText().trim().isEmpty()) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("O campo (senha) ainda não foi preenchido.");
			erro.setVisible(true);

			campoSenha.requestFocus();

		}

		else if (campoConfirmarSenha.getText().trim().isEmpty()) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("O campo (confirmar senha) ainda não foi preenchido.");
			erro.setVisible(true);

			campoConfirmarSenha.requestFocus();

		}

		else if (!(campoSenha.getText().equals(campoConfirmarSenha.getText()))) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("Campos (senha e confirmar senha) não estão iguais.");
			erro.setVisible(true);

			campoSenha.setText("");
			campoConfirmarSenha.setText("");

			campoSenha.requestFocus();

		}
		
		else {
			
			
			
		}
		
	}

}