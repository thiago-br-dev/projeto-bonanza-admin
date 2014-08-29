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
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField numero;

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
		
		setTitle("Bonanza Supermecados - Novo Caixa");
		setBounds(100, 100, 637, 294);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		numero = new JFormattedTextField(setMascara("##"));
		numero.setBounds(23, 133, 86, 34);
		contentPanel.add(numero);
		numero.setColumns(10);
		
		JButton botaoSalvar = new JButton("Salvar Caixa");
		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JTextField nomeAtendente = new JTextField();
		nomeAtendente.setColumns(10);
		nomeAtendente.setBounds(119, 133, 459, 34);
		contentPanel.add(nomeAtendente);
		botaoSalvar.setBounds(455, 188, 123, 34);
		contentPanel.add(botaoSalvar);
		
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