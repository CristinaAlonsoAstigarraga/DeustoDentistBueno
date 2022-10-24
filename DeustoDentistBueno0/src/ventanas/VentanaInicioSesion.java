package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion frame = new VentanaInicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("INICIO SESIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("DeustoDentist");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2, 0, 10));
		
		JPanel panelcentroDatos = new JPanel();
		panelCentro.add(panelcentroDatos);
		panelcentroDatos.setLayout(new GridLayout(2, 2, 0, 10));
		
		JLabel lblUsuario = new JLabel("Introduzca su usuario:");
		panelcentroDatos.add(lblUsuario);
		
		txtUsuario = new JTextField();
		panelcentroDatos.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasenia = new JLabel("Introduzca su contraseña");
		panelcentroDatos.add(lblContrasenia);
		
		txtContrasenia = new JTextField();
		panelcentroDatos.add(txtContrasenia);
		txtContrasenia.setColumns(10);
		
		JPanel panelcentroBoton = new JPanel();
		panelCentro.add(panelcentroBoton);
		panelcentroBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
		
		JButton btnInicioSesion = new JButton("INICIAR SESIÓN");
		panelcentroBoton.add(btnInicioSesion);
	}

}
