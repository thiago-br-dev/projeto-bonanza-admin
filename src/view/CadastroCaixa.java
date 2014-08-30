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
import facade.Fachada;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel erro, fundoMensagemErro, fundoMensagemSalvo, sucesso;
	private JTextField campoNumero;
	
	Fachada fachada;
	private JTextField textField;

	public static void main(String[] args) {
		
		try {
			
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			CadastroCaixa dialog = new CadastroCaixa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public CadastroCaixa() {
		
		setTitle("Cadastro de Caixa - Bonanza Supermercados");
		setBounds(100, 100, 615, 274);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		campoNumero = new JTextField();
		campoNumero.setBounds(31, 157, 93, 29);
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNumero.setColumns(10);
		
		campoNumero.setBorder(BorderFactory.createCompoundBorder(
				campoNumero.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		contentPanel.add(campoNumero);
		
		JLabel numero = new JLabel("N\u00FAmero");
		numero.setForeground(Color.DARK_GRAY);
		numero.setFont(new Font("Cambria", Font.PLAIN, 17));
		numero.setBounds(31, 130, 93, 22);
		contentPanel.add(numero);
		
		JButton botaoSalvar = new JButton("Salvar");
		
		botaoSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				validarInformacoes();
				
			}
		
		});
		
		botaoSalvar.setBounds(405, 197, 177, 30);
		contentPanel.add(botaoSalvar);
		
		JButton botaoCancelar = new JButton("Cancelar Cadastro");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoCancelar.setBounds(219, 197, 177, 30);
		contentPanel.add(botaoCancelar);
		
		erro = new JLabel("Erro");
		erro.setHorizontalAlignment(SwingConstants.CENTER);
		erro.setForeground(Color.GRAY);
		erro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erro.setVisible(false);
		erro.setBounds(154, 89, 427, 24);
		
		contentPanel.add(erro);
		
		fundoMensagemErro = new JLabel("New label");
		fundoMensagemErro.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_erro.png")));
		fundoMensagemErro.setBounds(153, 85, 428, 34);
		fundoMensagemErro.setVisible(false);
		contentPanel.add(fundoMensagemErro);
		
		sucesso = new JLabel("Usuário cadastrado com sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(154, 89, 427, 24);
		
		contentPanel.add(sucesso);
		
		fundoMensagemSalvo = new JLabel("New label");
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(153, 85, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);
		
		JLabel lblAtendentecampoOpcional = new JLabel("Atendente - (este campo \u00E9 opcional).");
		lblAtendentecampoOpcional.setForeground(Color.DARK_GRAY);
		lblAtendentecampoOpcional.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblAtendentecampoOpcional.setBounds(134, 130, 459, 22);
		contentPanel.add(lblAtendentecampoOpcional);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(BorderFactory.createCompoundBorder(
						campoNumero.getBorder(),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textField.setBounds(134, 157, 447, 29);
		contentPanel.add(textField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 611, 337);
		lblNewLabel.setIcon(new ImageIcon(CadastroCaixa.class.getResource("/view/img/novo_caixa.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
	
	public void validarInformacoes() {
		
		if (campoNumero.getText().trim().isEmpty()) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("O campo (número) ainda não foi preenchido.");
			erro.setVisible(true);

			campoNumero.requestFocus();

		}
		
		else {
			
			// Método para salvar o novo caixa.
			
		}
		
	}
}