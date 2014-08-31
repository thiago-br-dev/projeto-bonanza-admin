package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GerarRelatorio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField dataInicial;
	private JFormattedTextField dataFinal;
	JLabel sucesso, fundoMensagemSalvo;

	public static void main(String[] args) {
		
		try {
			
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			GerarRelatorio dialog = new GerarRelatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public GerarRelatorio() {
		
		setTitle("Gerar Relat\u00F3rios - Bonanza Supermecados");
		setBounds(100, 100, 637, 279);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		dataInicial = new JFormattedTextField(setMascara("##/##/####"));
		dataInicial.setBounds(37, 140, 179, 34);
		contentPanel.add(dataInicial);
		dataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataInicial.setColumns(10);
		
		dataInicial.setBorder(BorderFactory.createCompoundBorder(
				dataInicial.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		dataFinal = new JFormattedTextField(setMascara("##/##/####"));
		dataFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataFinal.setColumns(10);
		
		dataFinal.setBorder(BorderFactory.createCompoundBorder(
				dataFinal.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dataFinal.setBounds(226, 140, 179, 34);
		contentPanel.add(dataFinal);
		
		JButton botaoExportarRelatorio = new JButton("Exportar Relatório");
		botaoExportarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		botaoExportarRelatorio.setBounds(415, 184, 180, 35);
		contentPanel.add(botaoExportarRelatorio);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(415, 140, 179, 33);
		contentPanel.add(comboBox);
		
		sucesso = new JLabel("Usuário cadastrado com sucesso.");
		sucesso.setHorizontalAlignment(SwingConstants.CENTER);
		sucesso.setForeground(Color.GRAY);
		sucesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sucesso.setVisible(false);
		sucesso.setBounds(166, 90, 427, 24);
		
		contentPanel.add(sucesso);
		
		fundoMensagemSalvo = new JLabel("New label");
		fundoMensagemSalvo.setIcon(new ImageIcon(CadastroAdministrador.class.getResource("/view/img/mensagem_sucesso.png")));
		fundoMensagemSalvo.setBounds(165, 86, 428, 34);
		fundoMensagemSalvo.setVisible(false);
		contentPanel.add(fundoMensagemSalvo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 251);
		lblNewLabel.setIcon(new ImageIcon(TrocarFrase.class.getResource("/view/img/gerar_relatorio.jpg")));
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
	
	private MaskFormatter setMascara(String mascara) {

		MaskFormatter mask = null;

		try {

			mask = new MaskFormatter(mascara);

		}

		catch (java.text.ParseException ex) {

		}

		return mask;

	}
	
}