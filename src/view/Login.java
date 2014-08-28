package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoLogin;
	private JTextField campoSenha;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

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
		setTitle("BONANZA SUPERMECADOS - LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 491);
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
				
				botaoPrincipal.setIcon(new ImageIcon(Login.class.getResource("/view/img/botao_login_mouse.png")));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				botaoPrincipal.setIcon(new ImageIcon(Login.class.getResource("/view/img/botao_login.png")));
				
			}
			
		});
		
		JLabel botaoFecharError = new JLabel("");
		botaoFecharError.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botaoFecharError.setIcon(new ImageIcon(Login.class.getResource("/view/img/close_msg_login_mouse.jpg")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botaoFecharError.setIcon(new ImageIcon(Login.class.getResource("/view/img/close_msg_login.jpg")));
			}
		});
		
		campoLogin = new JTextField();
		campoLogin.setBounds(253, 168, 293, 34);
		campoLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoLogin.setBackground(Color.WHITE);
		campoLogin.setBorder(new LineBorder(Color.WHITE));
		campoLogin.setText("Digite seu Login");
		campoLogin.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(campoLogin);
		campoLogin.setColumns(10);
		
		campoSenha = new JPasswordField();
		campoSenha.setColumns(10);
		campoSenha.setBounds(253, 238, 293, 34);
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoSenha.setBackground(Color.WHITE);
		campoSenha.setBorder(new LineBorder(Color.WHITE));
		campoSenha.setText("Senha");
		campoSenha.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(campoSenha);
		
		botaoFecharError.setIcon(new ImageIcon(Login.class.getResource("/view/img/close_msg_login.jpg")));
		botaoFecharError.setBounds(572, 15, 47, 46);
		contentPane.add(botaoFecharError);
		
		JLabel fundoMensagemError = new JLabel("");
		fundoMensagemError.setIcon(new ImageIcon(Login.class.getResource("/view/img/fundo_erro_login.jpg")));
		fundoMensagemError.setBounds(219, 15, 356, 46);
		contentPane.add(fundoMensagemError);
		botaoPrincipal.setIcon(new ImageIcon(Login.class.getResource("/view/img/botao_login.png")));
		botaoPrincipal.setBounds(241, 300, 356, 50);
		contentPane.add(botaoPrincipal);
		
		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal.setIcon(new ImageIcon(Login.class.getResource("/view/img/fundo_login.jpg")));
		fundoPrincipal.setBounds(0, 0, 835, 463);
		contentPane.add(fundoPrincipal);

	}
}