// CLASSE CONCLUÍDA !!!!!!!!!!!!!!!!!!

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import facade.Fachada;
import java.awt.Toolkit;
import java.sql.SQLException;
import models.Administrador;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField campoLogin;
	private JTextField campoSenha;
	private Fachada fachada;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
					Login frame = new Login();
					frame.setVisible(true);

				}

				catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	public Login() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/view/img/icon_screen.png")));
		setTitle("Realizando Login (administrador) - Bonanza Supermercados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 472);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel botaoPrincipal = new JLabel("");
		botaoPrincipal.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				botaoPrincipal.setIcon(new ImageIcon(Login.class
						.getResource("/view/img/botao_login_mouse.png")));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				botaoPrincipal.setIcon(new ImageIcon(Login.class
						.getResource("/view/img/botao_login.png")));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				validarLogin();
				
			}
			
		});

		campoLogin = new JTextField();
		campoLogin.setBounds(185, 131, 293, 34);
		campoLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoLogin.setBackground(new Color(240, 238, 240));
		campoLogin.setBorder(new LineBorder(new Color(240, 238, 240)));
		campoLogin.setText("Digite seu Login");
		campoLogin.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(campoLogin);
		campoLogin.setColumns(10);

		campoLogin.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (campoLogin.getText().equals("Digite seu Login")) {

					if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

						System.exit(0);
						
					}

					else {

						campoLogin.setText("");

					}

				}

				if (campoLogin.getText().equals("Digite seu Login")
						& arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					validarLogin();
					
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					validarLogin();
					
				}
				
				else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					System.exit(0);
					
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					campoSenha.requestFocus();

				}

			}

		});

		campoSenha = new JPasswordField();
		campoSenha.setColumns(10);
		campoSenha.setBounds(185, 202, 293, 34);
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoSenha.setBackground(new Color(240, 238, 240));
		campoSenha.setBorder(new LineBorder(new Color(240, 238, 240)));
		campoSenha.setText("Senha");
		campoSenha.setHorizontalAlignment(SwingConstants.LEFT);

		campoSenha.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (campoSenha.getText().equals("Senha")) {

					if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

						System.exit(0);
						
					}

					else {

						campoSenha.setText("");

					}

				}

				if (campoSenha.getText().equals("Senha")
						& arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					validarLogin();
					
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					validarLogin();
					
				}
				
				else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					System.exit(0);
					
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoLogin.requestFocus();

				}

			}

		});
		
		contentPane.add(campoSenha);

		botaoPrincipal.setIcon(new ImageIcon(Login.class
				.getResource("/view/img/botao_login.png")));
		
		botaoPrincipal.setBounds(169, 270, 356, 44);

		contentPane.add(botaoPrincipal);

		JLabel fundoPrincipal = new JLabel("");
		
		fundoPrincipal.setIcon(new ImageIcon(Login.class
				.getResource("/view/img/fundo_login.jpg")));
		
		fundoPrincipal.setBounds(0, 0, 694, 446);

		contentPane.add(fundoPrincipal);

	}
	
	public void validarLogin() {
		
		if (campoLogin.getText().trim().isEmpty() | campoLogin.getText().equals("Digite seu Login")) {
			
			JOptionPane.showMessageDialog(null, "O campo de login está vazio. Digite algo e tente novamente.", "Ops, um erro foi encontrado !", JOptionPane.ERROR_MESSAGE);
			campoLogin.requestFocus();
			
		}
		
		else if (campoSenha.getText().trim().isEmpty() | campoSenha.getText().equals("Senha")) {
			
			JOptionPane.showMessageDialog(null, "O campo de senha está vazio. Digite algo e tente novamente.", "Ops, um erro foi encontrado !", JOptionPane.ERROR_MESSAGE);
			campoSenha.requestFocus();
			
		}
		
		else {
			
			fachada = Fachada.getInstance();
			
			Administrador administrador = new Administrador();
			administrador.setLogin(campoLogin.getText());
			administrador.setSenha(campoSenha.getText());
			
			try {
				
				if (fachada.verificarLogin(administrador)) {
					
					dispose();
					view.Administrador.main(null);
					
				}
				
				else {
					
					JOptionPane.showMessageDialog(null, "Dados incorretos (login ou senha). Verifique e tente novamente.", "Ops, um erro foi encontrado !", JOptionPane.ERROR_MESSAGE);
					
					campoLogin.setText("");
					campoSenha.setText("");
					campoLogin.requestFocus();
					
				}
				
			}
			
			catch (HeadlessException e) {
				
				e.printStackTrace();
				
			}
			
			catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}

}