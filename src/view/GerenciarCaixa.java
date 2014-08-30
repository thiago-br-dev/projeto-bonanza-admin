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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;

public class GerenciarCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	
	static String[] colunas = new String[] { "Código", "Atendente" };
	
	private final JPanel contentPanel = new JPanel();
	
	static JTable tabelaDeResultados;
	JScrollPane scrollTabela;
	JLabel sucesso, fundoMensagemSalvo;

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
		
		String[][] dados = null;
		
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

		tabelaDeResultados.getColumnModel().getColumn(0)
				.setPreferredWidth(100);
		tabelaDeResultados.getColumnModel().getColumn(1)
				.setPreferredWidth(600);
		
		tabelaDeResultados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SetRowHight(tabelaDeResultados);
		
		tabelaDeResultados.getTableHeader().setReorderingAllowed(false);

		tabelaDeResultados
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaDeResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollTabela = new JScrollPane(tabelaDeResultados);
		painelTabela.add(scrollTabela);
		
		JButton btnRemoverCaixa = new JButton("Remover Caixa");
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
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(274, 85, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 745, 443);
		lblNewLabel.setIcon(new ImageIcon(GerenciarCaixa.class.getResource("/view/img/cadastrar_caixa.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
	
	public boolean validarTodasDatas(String data) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String dataString = data;

		if (data.equals("  /  /    ")) {
			return true;
		}

		try {
			Date dataVerificar = sdf.parse(dataString);
			System.out.println(dataVerificar);
			return true;
		} catch (ParseException e) {
			System.err.println("Data inválida");
			return false;
		}

	}
	
	public void SetRowHight(JTable table) {

		int height = table.getRowHeight();
		table.setRowHeight(height + 7);

	}
	
}