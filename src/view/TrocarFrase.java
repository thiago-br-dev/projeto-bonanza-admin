package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import facade.Fachada;
import models.Preferencia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrocarFrase extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Fachada fachada;
	private ArrayList<Preferencia> preferencias;
	private JLabel sucesso, fundoMensagemSalvo;

	public static void main(String[] args) {

		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			TrocarFrase dialog = new TrocarFrase();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public TrocarFrase() {

		fachada = Fachada.getInstance();

		setTitle("Alterar frase do Painel - Bonanza Supermercados");
		setBounds(100, 100, 637, 275);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(28, 156, 573, 29);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBorder(BorderFactory.createCompoundBorder(
				textField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPanel.add(textField);
		textField.setColumns(10);

		// Listar frase caso ja esteja cadastrado na base de dados
		// ---------------------------------------------------------------------------------------
		preferencias = new ArrayList<Preferencia>();

		try {
			preferencias = (ArrayList<Preferencia>) fachada.listarPreferencia();
			if (preferencias.size() != 0) {

				textField.setText(preferencias.get(0).getTexto().toString());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ---------------------------------------------------------------------------------------

		JButton btnAlterar = new JButton("Alterar Agora");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Método para trocar frase do painel

				preferencias = new ArrayList<Preferencia>();

				try {
					preferencias = (ArrayList<Preferencia>) fachada
							.listarPreferencia();
					if (preferencias.size() != 0) {

						
						//textField.setText(preferencias.get(0).getTexto()
							//	.toString());
						Preferencia preferencia = new Preferencia();
						String texto = textField.getText().toString();
						preferencia.setTexto(texto);
						preferencia.setId(preferencias.get(0).getId());
						
						if (fachada.atualizarPreferencia(preferencia)) {
							JOptionPane.showMessageDialog(null,
									"Frase alterada com Sucesso!");
						} else {
							System.out.println("fudeu");
							new SQLException();
						}

						

					} else {

						Preferencia preferencia = new Preferencia();
						String texto = textField.getText().toString();
						preferencia.setTexto(texto);

						if (fachada.inserirPreferencia(preferencia)) {
							
							fundoMensagemSalvo.setVisible(true);
							sucesso.setVisible(true);
							
						} else {
							System.out.println("fudeu2");
							new SQLException();
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		});

		btnAlterar.setBounds(478, 196, 123, 30);
		contentPanel.add(btnAlterar);

		JLabel lblAltereAquiA = new JLabel(
				"Altere aqui a frase do seu painel de atendimento.");

		lblAltereAquiA.setBounds(28, 128, 530, 25);
		lblAltereAquiA.setForeground(Color.GRAY);
		lblAltereAquiA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblAltereAquiA);
		
		sucesso = new JLabel("Mensagem alterada com Sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(174, 88, 427, 24);

		contentPanel.add(sucesso);

		fundoMensagemSalvo = new JLabel("New label");

		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class
				.getResource("/view/img/mensagem_sucesso.png")));

		fundoMensagemSalvo.setBounds(173, 84, 428, 34);
		fundoMensagemSalvo.setVisible(false);

		contentPanel.add(fundoMensagemSalvo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 643, 251);

		lblNewLabel.setIcon(new ImageIcon(TrocarFrase.class
				.getResource("/view/img/trocar_frase.jpg")));

		contentPanel.add(lblNewLabel);

	}

}