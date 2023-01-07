package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BD.BD;

import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class VentanaInicioSesion extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private VentanaPrincipal vp;
	Connection con = BD.initBD("BaseDatos.db");

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
		setBounds(100, 100, 659, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new LineBorder(SystemColor.desktop));
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("INICIO DE SESION");
		lblTitulo.setForeground(SystemColor.inactiveCaptionBorder);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setBorder(new LineBorder(SystemColor.desktop));
		lblNewLabel.setIcon(new ImageIcon(VentanaInicioSesion.class.getResource("/img/imagen1 (2) (1) (1).png")));
		panelEste.add(lblNewLabel);
		
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
		
		passwordField = new JPasswordField();
		panelcentroDatos.add(passwordField);
		
		JPanel panelcentroBoton = new JPanel();
		panelCentro.add(panelcentroBoton);
		panelcentroBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
		
		JButton btnInicioSesion = new JButton("INICIAR SESIÓN");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprobarUsuario();
			}

			
		});
		btnInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelcentroBoton.add(btnInicioSesion);
		
		JButton btnVolver = new JButton("VOLVER AL MENÚ");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelcentroBoton.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio vi = new VentanaInicio();
				vi.setVisible(true);
				setVisible(false);
			}
		});
	}
	private void comprobarUsuario() {
		// TODO Auto-generated method stub
		String user=txtUsuario.getText();
		String password=String.valueOf(passwordField.getPassword());
		String cifrado=Cifrar(password);
		
		String rol=BD.comprobarUsuario(con,user,cifrado);
		//comprobar usuario bbdd
		if(!(rol=="")) {
			vp=new VentanaPrincipal(rol,user);
			vp.setVisible(true);
			setVisible(false);
		}
	}
	
	private String Cifrar(String password) {
		// TODO Auto-generated method stub
		char array[]=password.toCharArray();
		
		for(int i=0;i<array.length;i++) {
			array[i]=(char)(array[i]+(char)5);
		}
		
		String encriptado=String.valueOf(array);
		System.out.println(encriptado);
		char arrayE[]=encriptado.toCharArray();	
		
		
		
		return encriptado;
	}
}
