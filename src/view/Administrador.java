package view;

import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel logoBonanza;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

					Administrador frame = new Administrador();
					frame.setVisible(true);

				}

				catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	public Administrador() {
		
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				
				int metadeLarguraTituloPesquisa = Integer.parseInt(""
						+ ((contentPane.getWidth() / 2) - 295));

				int metadeAlturaTituloPesquisa = Integer.parseInt(""
						+ ((contentPane.getHeight() / 2) - 95));

				logoBonanza.setBounds(metadeLarguraTituloPesquisa,
						metadeAlturaTituloPesquisa, 590, 190);
				
			}
			
		});

		setTitle("BONANZA SUPERMECADOS - T\u00C1 BEM AQUI");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setExtendedState(MAXIMIZED_BOTH);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				dispose();
				Login.main(null);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPrincipal = new JMenu("Principal");
		menuBar.add(mnPrincipal);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmGerarNovo = new JMenuItem("Gerar Novo");
		mnRelatrios.add(mntmGerarNovo);

		JMenu mnConfigurao = new JMenu("Configura\u00E7\u00E3o");
		menuBar.add(mnConfigurao);
		
		JMenuItem mntmTrocarFrase = new JMenuItem("Trocar Frase");
		mntmTrocarFrase.setIcon(new ImageIcon(Administrador.class.getResource("/view/img/trocar_frase.png")));
		mntmTrocarFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TrocarFrase.main(null);
				
			}
		});
		mnConfigurao.add(mntmTrocarFrase);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logoBonanza = new JLabel("");
		logoBonanza.setIcon(new ImageIcon(Administrador.class.getResource("/view/img/logo-bonanza.png")));
		logoBonanza.setBounds(115, 187, 590, 190);
		contentPane.add(logoBonanza);
		
		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal.setIcon(new ImageIcon(Administrador.class.getResource("/view/img/fundo-principal.jpg")));
		fundoPrincipal.setBounds(0, 0, 1920, 1080);
		contentPane.add(fundoPrincipal);

	}
}