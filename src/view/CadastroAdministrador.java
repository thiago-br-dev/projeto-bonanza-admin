package view;

import java.awt.BorderLayout;
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
	private JLabel erro;
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
		setBounds(100, 100, 638, 331);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setBounds(35, 149, 561, 29);
		contentPanel.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel nome = new JLabel("Nome Completo");
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Cambria", Font.PLAIN, 17));
		nome.setBounds(35, 122, 117, 22);
		contentPanel.add(nome);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.DARK_GRAY);
		lblLogin.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblLogin.setBounds(35, 185, 40, 22);
		contentPanel.add(lblLogin);
		
		campoLogin = new JTextField();
		campoLogin.setColumns(10);
		campoLogin.setBounds(35, 212, 189, 29);
		contentPanel.add(campoLogin);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setForeground(Color.DARK_GRAY);
		lblConfirmarSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblConfirmarSenha.setBounds(420, 185, 176, 22);
		contentPanel.add(lblConfirmarSenha);
		
		campoConfirmarSenha = new JTextField();
		campoConfirmarSenha.setColumns(10);
		campoConfirmarSenha.setBounds(420, 212, 176, 29);
		contentPanel.add(campoConfirmarSenha);
		
		campoSenha = new JTextField();
		campoSenha.setColumns(10);
		campoSenha.setBounds(234, 212, 176, 29);
		contentPanel.add(campoSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.DARK_GRAY);
		lblSenha.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblSenha.setBounds(234, 185, 47, 22);
		contentPanel.add(lblSenha);
		
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botaoSalvar.setBounds(461, 252, 136, 30);
		contentPanel.add(botaoSalvar);
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setBounds(315, 252, 136, 30);
		contentPanel.add(botaoCancelar);
		
		erro = new JLabel("Erro");
		erro.setHorizontalAlignment(SwingConstants.CENTER);
		erro.setForeground(Color.GRAY);
		erro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erro.setVisible(false);
		erro.setBounds(169, 87, 427, 24);
		contentPanel.add(erro);
		
		JLabel fundoMensagemErro = new JLabel("New label");
		fundoMensagemErro.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_erro.png")));
		fundoMensagemErro.setBounds(168, 83, 428, 34);
		fundoMensagemErro.setVisible(false);
		contentPanel.add(fundoMensagemErro);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 304);
		lblNewLabel.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/novo_administrador.jpg")));
		contentPanel.add(lblNewLabel);
	}
}
