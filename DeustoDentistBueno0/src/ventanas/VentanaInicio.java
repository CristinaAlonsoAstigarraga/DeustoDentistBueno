package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ventanas.VentanaInicioSesion;
import ventanas.VentanaRegistro;
import java.awt.SystemColor;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	
	
	// ventana 
	private JFrame ventanaAnterior;
	private static JFrame ventanaActual;
	
	VentanaRegistro vr = new VentanaRegistro();
	VentanaInicioSesion vis = new VentanaInicioSesion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//centrar ventana
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("INICIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setForeground(SystemColor.inactiveCaptionBorder);
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("DeustoDentist");
		lblTitulo.setForeground(SystemColor.inactiveCaptionBorder);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("sesion");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		panelSur.add(lblNewLabel);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2, 0, 10));
		
		JButton btnInicioSesion = new JButton("INICIAR SESIÓN");
		btnInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vis.setVisible(true);
				setVisible(false);
//				dispose();
//				VentanaInicioSesion.setVisible(true);
//				
			}
		});
		panelCentro.add(btnInicioSesion);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vr.setVisible(true);
				setVisible(false);
			}
		});
		panelCentro.add(btnRegistrarse);
	}

}
