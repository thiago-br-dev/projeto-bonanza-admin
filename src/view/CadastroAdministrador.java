package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import util.TamanhoMaximo;
import models.Administrador;
import facade.Fachada;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CadastroAdministrador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel erro, fundoMensagemErro, fundoMensagemSalvo, sucesso;
	private JTextField campoNome;
	private JTextField campoLogin;
	private JPasswordField campoConfirmarSenha;
	private JPasswordField campoSenha;
	
	Fachada fachada;

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
		campoNome.setDocument(new TamanhoMaximo(100));
		campoNome.setBounds(33, 165, 561, 29);
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNome.setColumns(10);
		
		campoNome.setBorder(BorderFactory.createCompoundBorder(
				campoNome.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		contentPanel.add(campoNome);
		
		JLabel nome = new JLabel("Nome Completo");
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Cambria", Font.PLAIN, 17));
		nome.setBounds(33, 138, 117, 22);
		contentPanel.add(nome);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.DARK_GRAY);
		lblLogin.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblLogin.setBounds(33, 201, 40, 22);
		contentPanel.add(lblLogin);
		
		campoLogin = new JTextField();
		campoLogin.setDocument(new TamanhoMaximo(20));
		campoLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoLogin.setColumns(10);
		
		campoLogin.setBorder(BorderFactory.createCompoundBorder(
				campoLogin.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		campoLogin.setBounds(33, 228, 189, 29);
		contentPanel.add(campoLogin);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setForeground(Color.DARK_GRAY);
		lblConfirmarSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblConfirmarSenha.setBounds(418, 201, 176, 22);
		contentPanel.add(lblConfirmarSenha);
		
		campoConfirmarSenha = new JPasswordField();
		campoConfirmarSenha.setDocument(new TamanhoMaximo(20));
		campoConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoConfirmarSenha.setColumns(10);
		
		campoConfirmarSenha.setBorder(BorderFactory.createCompoundBorder(
				campoConfirmarSenha.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		campoConfirmarSenha.setBounds(418, 228, 176, 29);
		contentPanel.add(campoConfirmarSenha);
		
		campoSenha = new JPasswordField();
		campoSenha.setDocument(new TamanhoMaximo(20));
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoSenha.setColumns(10);
		
		campoSenha.setBorder(BorderFactory.createCompoundBorder(
				campoSenha.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		campoSenha.setBounds(232, 228, 176, 29);
		contentPanel.add(campoSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.DARK_GRAY);
		lblSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblSenha.setBounds(232, 201, 47, 22);
		contentPanel.add(lblSenha);
		
		JButton botaoSalvar = new JButton("Salvar");
		
		botaoSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				validarInformacoes();
				
			}
		
		});
		
		botaoSalvar.setBounds(418, 274, 177, 30);
		contentPanel.add(botaoSalvar);
		
		JButton botaoCancelar = new JButton("Cancelar Cadastro");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoCancelar.setBounds(232, 274, 177, 30);
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
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(167, 93, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 349);
		lblNewLabel.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/novo_administrador.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
	
	@SuppressWarnings("deprecation")
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
			
			Administrador administrador = new Administrador();
			administrador.setNome(campoNome.getText());
			administrador.setLogin(campoLogin.getText());
			administrador.setSenha(campoSenha.getText());
			
			fachada = Fachada.getInstance();
			try {
				
				fachada.inserirAdministrador(administrador);
				
				fundoMensagemErro.setVisible(false);
				erro.setVisible(false);
				
				fundoMensagemSalvo.setVisible(true);
				sucesso.setVisible(true);
				
				campoNome.setText("");
				campoLogin.setText("");
				campoSenha.setText("");
				
				campoNome.requestFocus();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}