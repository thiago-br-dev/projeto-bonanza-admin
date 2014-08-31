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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import models.Caixa;
import facade.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GerenciarCaixa extends JDialog {

	private static final long serialVersionUID = 1L;

	static String[] colunas = new String[] { "Número do Caixa", "Atendente" };

	private final JPanel contentPanel = new JPanel();

	private static JTable tabelaDeResultados;
	private JScrollPane scrollTabela;
	private JLabel sucesso, fundoMensagemSalvo;
	private JButton btnRemoverCaixa;

	private Fachada fachada;
	private List<Caixa> caixasBD;

	private int coluna = 0;
	private int linha = 0;

	public static void main(String[] args) {

		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			GerenciarCaixa dialog = new GerenciarCaixa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public GerenciarCaixa() {

		setTitle("Gerenciamento de Caixas - Bonanza Supermercados");
		setBounds(100, 100, 739, 350);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel painelTabela = new JPanel();
		painelTabela.setBounds(27, 135, 674, 114);
		contentPanel.add(painelTabela);
		painelTabela.setLayout(new BorderLayout());

		fachada = Fachada.getInstance();
		caixasBD = new ArrayList<Caixa>();

		try {

			caixasBD = fachada.listarCaixa();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		String[][] dados = new String[caixasBD.size()][];
		int i = 0;

		for (Caixa caixa : caixasBD) {

			dados[i] = new String[] { String.valueOf(caixa.getCaixa()),
					caixa.getAtendente() };

			i += 1;

		}

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

		tabelaDeResultados = new JTable(modelo) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;

			}

		};
		tabelaDeResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				coluna = tabelaDeResultados.getSelectedColumn();
				linha = tabelaDeResultados.getSelectedRow();
				btnRemoverCaixa.setEnabled(true);
				
			}
			
		});

		tabelaDeResultados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}

		});

		tabelaDeResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabelaDeResultados.getColumnModel().getColumn(1).setPreferredWidth(600);

		tabelaDeResultados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SetRowHight(tabelaDeResultados);

		tabelaDeResultados.getTableHeader().setReorderingAllowed(false);

		tabelaDeResultados
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaDeResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollTabela = new JScrollPane(tabelaDeResultados);
		painelTabela.add(scrollTabela);

		btnRemoverCaixa = new JButton("Remover Caixa");
		btnRemoverCaixa.setEnabled(false);
		btnRemoverCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tabelaDeResultados.isCellSelected(linha, coluna)) {

					int decisao = JOptionPane
							.showConfirmDialog(
									null,
									"Tem certeza que deseja excluir este caixa ? Os dados não poderam ser recuperados.",
									"Confirmação de Exclusão",
									JOptionPane.INFORMATION_MESSAGE);

					if (decisao == 1) {

						// Não fazer nada ...

					}

					else if (decisao == 0) {

						int row = tabelaDeResultados.getSelectedRow();

						Caixa caixaRemover = new Caixa();
						caixaRemover.setId(caixasBD.get(row).getId());

						try {

							fachada.removerCaixa(caixaRemover);

							fundoMensagemSalvo.setVisible(true);
							sucesso.setText("Caixa Deletado com Sucesso.");
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
		btnRemoverCaixa.setBounds(559, 260, 143, 35);
		contentPanel.add(btnRemoverCaixa);

		sucesso = new JLabel("Usuário cadastrado com sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(275, 89, 427, 24);

		contentPanel.add(sucesso);

		fundoMensagemSalvo = new JLabel("New label");
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class
				.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(274, 85, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 745, 443);
		lblNewLabel.setIcon(new ImageIcon(GerenciarCaixa.class
				.getResource("/view/img/cadastrar_caixa.jpg")));
		contentPanel.add(lblNewLabel);

	}

	public void SetRowHight(JTable table) {

		int height = table.getRowHeight();
		table.setRowHeight(height + 7);

	}

	public void atualizarNovamenteTabela() {

		caixasBD = new ArrayList<Caixa>();

		Fachada resgatarCaixas = Fachada.getInstance();

		try {
			
			caixasBD = resgatarCaixas.listarCaixa();
			
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}

		String[][] dados2 = new String[caixasBD.size()][];
		int i = 0;

		for (Caixa caixa : caixasBD) {

			dados2[i] = new String[] { String.valueOf(caixa.getCaixa()),
					caixa.getAtendente() };

			i += 1;

		}

		DefaultTableModel modelo2 = new DefaultTableModel(dados2, colunas);
		tabelaDeResultados.setModel(modelo2);

		tabelaDeResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabelaDeResultados.getColumnModel().getColumn(1).setPreferredWidth(600);

	}

}