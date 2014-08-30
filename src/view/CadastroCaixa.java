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
import util.TamanhoMaximo;
import models.Caixa;
import facade.Fachada;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CadastroCaixa extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JLabel erro, fundoMensagemErro, fundoMensagemSalvo, sucesso;
	private JTextField campoNumero;

	private Fachada fachada;
	private JTextField campoAtendente;

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
		campoNumero.setDocument(new TamanhoMaximo(3));
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
		
		fundoMensagemErro.setIcon(new ImageIcon(CadastroAdministrador.class
				.getResource("/view/img/mensagem_erro.png")));
		
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
		
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class
				.getResource("/view/img/mensagem_sucesso.png")));
		
		fundoMensagemSalvo.setBounds(153, 85, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		
		contentPanel.add(fundoMensagemSalvo);

		JLabel lblAtendentecampoOpcional = new JLabel(
				"Atendente - (este campo \u00E9 opcional).");
		
		lblAtendentecampoOpcional.setForeground(Color.DARK_GRAY);
		lblAtendentecampoOpcional.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblAtendentecampoOpcional.setBounds(134, 130, 459, 22);
		contentPanel.add(lblAtendentecampoOpcional);

		campoAtendente = new JTextField();
		campoAtendente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoAtendente.setColumns(10);
		campoAtendente.setDocument(new TamanhoMaximo(100));
		
		campoAtendente.setBorder(BorderFactory.createCompoundBorder(
		campoAtendente.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		campoAtendente.setBounds(134, 157, 447, 29);
		contentPanel.add(campoAtendente);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 611, 337);
		
		lblNewLabel.setIcon(new ImageIcon(CadastroCaixa.class
				.getResource("/view/img/novo_caixa.jpg")));
		
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

		else if (ValidaNumero(campoNumero.getText()) == false) {

			fundoMensagemSalvo.setVisible(false);
			sucesso.setVisible(false);

			fundoMensagemErro.setVisible(true);

			erro.setText("Digite apenas valores numéricos no campo número.");
			erro.setVisible(true);

			campoNumero.setText("");
			campoNumero.requestFocus();

		}

		else {

			fachada = Fachada.getInstance();

			Caixa caixa = new Caixa();
			
			caixa.setCaixa(campoNumero.getText().toString());
			caixa.setAtendente(campoAtendente.getText().toString());

			try {
				
				if (fachada.inserirCaixa(caixa)) {

					fundoMensagemErro.setVisible(false);
					erro.setVisible(false);

					fundoMensagemSalvo.setVisible(true);
					sucesso.setText("Caixa cadastrado com Sucesso");
					sucesso.setVisible(true);
					
					campoAtendente.setText("");
					campoNumero.setText("");
					campoNumero.requestFocus();

				}
				
				else {
					
					erro.setText("Ocorreu um Erro tente Novamente!");
					erro.setVisible(true);
					
				}
				
			}
			
			catch (SQLException e) {
				
				e.printStackTrace();
				
			}

		}

	}

	public boolean ValidaNumero(String Numero) {
		
		long valor;
		
		try {
			
			valor = Long.parseLong(Numero);
			System.out.println(valor);
			
			return true;
			
		}
		
		catch (NumberFormatException ex) {
			
			return false;
			
		}
		
	}

}