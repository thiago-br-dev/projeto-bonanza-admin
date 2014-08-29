package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
		
		setTitle("Bonanza Supermecados - Gerar Relatório");
		setBounds(100, 100, 637, 294);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		dataInicial = new JFormattedTextField(setMascara("##/##/####"));
		dataInicial.setBounds(47, 143, 175, 34);
		contentPanel.add(dataInicial);
		dataInicial.setColumns(10);
		
		dataFinal = new JFormattedTextField(setMascara("##/##/####"));
		dataFinal.setColumns(10);
		dataFinal.setBounds(232, 143, 175, 34);
		contentPanel.add(dataFinal);
		
		JButton btnAlterar = new JButton("Exportar Relatório");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setBounds(455, 188, 123, 34);
		contentPanel.add(btnAlterar);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(417, 143, 161, 34);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 263);
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