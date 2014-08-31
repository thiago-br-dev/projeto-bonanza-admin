package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;

import models.Administrador;
import facade.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenciarAdministrador extends JDialog {

	private static final long serialVersionUID = 1L;

	static String[] colunas = new String[] { "Nome Completo", "Login" };
	private final JPanel contentPanel = new JPanel();
	static JTable tabelaDeResultados;
	JScrollPane scrollTabela;
	JLabel sucesso, fundoMensagemSalvo;

	private Fachada fachada;
	private List<Administrador> administradoresBD;
	
	int coluna = 0;
	int linha = 0;

	public static void main(String[] args) {

		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			GerenciarAdministrador dialog = new GerenciarAdministrador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public GerenciarAdministrador() {

		setTitle("Gerenciamento de Administradores - Bonanza Supermercados");
		setBounds(100, 100, 739, 349);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel painelTabela = new JPanel();
		painelTabela.setBounds(34, 134, 669, 114);
		contentPanel.add(painelTabela);
		painelTabela.setLayout(new BorderLayout());

		fachada = Fachada.getInstance();
		administradoresBD = new ArrayList<models.Administrador>();

		try {

			administradoresBD = fachada.listarAdministrador();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		String[][] dados = new String[administradoresBD.size()][];
		int i = 0;

		for (Administrador adm : administradoresBD) {

			dados[i] = new String[] { String.valueOf(adm.getNome()),
					adm.getLogin() };

			i += 1;

		}

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

		tabelaDeResultados = new JTable(modelo) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;

			}

		};

		tabelaDeResultados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}

		});

		tabelaDeResultados.getColumnModel().getColumn(0).setPreferredWidth(500);
		tabelaDeResultados.getColumnModel().getColumn(1).setPreferredWidth(250);

		tabelaDeResultados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SetRowHight(tabelaDeResultados);

		tabelaDeResultados.getTableHeader().setReorderingAllowed(false);

		tabelaDeResultados
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaDeResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollTabela = new JScrollPane(tabelaDeResultados);
		painelTabela.add(scrollTabela);

		JButton btnRemoverCaixa = new JButton("Remover Administrador");
		btnRemoverCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tabelaDeResultados.isCellSelected(linha, coluna)) {

					int decisao = JOptionPane
							.showConfirmDialog(
									null,
									"Tem certeza que deseja excluir este administrador ? Os dados não poderam ser recuperados.",
									"Confirmação de Exclusão",
									JOptionPane.INFORMATION_MESSAGE);

					if (decisao == 1) {

						// Não fazer nada ...

					}

					else if (decisao == 0) {

						int row = tabelaDeResultados.getSelectedRow();

						models.Administrador administradorRemover = new models.Administrador();
						administradorRemover.setId(administradoresBD.get(row).getId());

						try {

							fachada.removerAdministrador(administradorRemover);

							fundoMensagemSalvo.setVisible(true);
							sucesso.setText("Administrador Deletado com Sucesso.");
							sucesso.setVisible(true);

							atualizarNovamenteTabela();

						}

						catch (SQLException e) {

							e.printStackTrace();

						}

					}

				}
				
			}
		});
		btnRemoverCaixa.setBounds(559, 259, 145, 35);
		contentPanel.add(btnRemoverCaixa);

		sucesso = new JLabel("Usuário cadastrado com sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(276, 89, 427, 24);

		contentPanel.add(sucesso);

		fundoMensagemSalvo = new JLabel("New label");
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class
				.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(275, 85, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 745, 419);
		lblNewLabel.setIcon(new ImageIcon(GerenciarAdministrador.class
				.getResource("/view/img/gerenciar_administradores.jpg")));
		contentPanel.add(lblNewLabel);

	}

	public void SetRowHight(JTable table) {

		int height = table.getRowHeight();
		table.setRowHeight(height + 7);

	}
	
	public void atualizarNovamenteTabela() {
		
		Fachada fachada2 = Fachada.getInstance();
		administradoresBD = new ArrayList<models.Administrador>();

		try {

			administradoresBD = fachada2.listarAdministrador();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
		
		String[][] dados2 = new String[administradoresBD.size()][];
		int i = 0;

		for (Administrador adm : administradoresBD) {

			dados2[i] = new String[] { String.valueOf(adm.getNome()),
					adm.getLogin() };

			i += 1;

		}
		
		DefaultTableModel modelo2 = new DefaultTableModel(dados2, colunas);
		tabelaDeResultados.setModel(modelo2);
		
		tabelaDeResultados.getColumnModel().getColumn(0).setPreferredWidth(500);
		tabelaDeResultados.getColumnModel().getColumn(1).setPreferredWidth(250);
		
	}

}