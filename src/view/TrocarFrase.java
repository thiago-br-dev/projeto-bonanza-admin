package view;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

public class TrocarFrase extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
		
		setBounds(100, 100, 641, 294);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(47, 143, 530, 34);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(454, 188, 123, 34);
		contentPanel.add(btnAlterar);
		
		JLabel lblAltereAquiA = new JLabel("Altere aqui a frase do painel.");
		lblAltereAquiA.setBounds(47, 118, 530, 14);
		contentPanel.add(lblAltereAquiA);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 633, 263);
		lblNewLabel.setIcon(new ImageIcon(TrocarFrase.class.getResource("/view/img/trocar_frase.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
}