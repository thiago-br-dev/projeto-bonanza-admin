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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

		setTitle("Alterar frase do painel - Bonanza Supermercados");
		setBounds(100, 100, 637, 278);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(47, 143, 530, 34);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBorder(BorderFactory.createCompoundBorder(
				textField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPanel.add(textField);
		textField.setColumns(10);

		JButton btnAlterar = new JButton("Alterar Agora");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// M�todo para trocar frase do painel

			}
			
		});
		
		btnAlterar.setBounds(455, 188, 123, 34);
		contentPanel.add(btnAlterar);

		JLabel lblAltereAquiA = new JLabel(
				"Altere aqui a frase do seu painel de atendimento.");
		lblAltereAquiA.setBounds(47, 115, 530, 25);
		lblAltereAquiA.setForeground(Color.GRAY);
		lblAltereAquiA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblAltereAquiA);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 643, 251);
		lblNewLabel.setIcon(new ImageIcon(TrocarFrase.class
				.getResource("/view/img/trocar_frase.jpg")));
		contentPanel.add(lblNewLabel);

	}
	
}