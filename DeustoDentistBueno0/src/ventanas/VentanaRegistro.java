package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("REGISTRO");
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
		
		JButton btnVolver = new JButton("VOLVER AL MENÚ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio vi = new VentanaInicio();
				vi.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2, 0, 10));
		
		JPanel panelcentroDatos = new JPanel();
		panelCentro.add(panelcentroDatos);
		panelcentroDatos.setLayout(new GridLayout(3, 2, 15, 10));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelcentroDatos.add(lblNombre);
		
		textField = new JTextField();
		panelcentroDatos.add(textField);
		textField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		panelcentroDatos.add(lblApellido);
		
		textField_3 = new JTextField();
		panelcentroDatos.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		panelcentroDatos.add(lblUsuario);
		
		textField_1 = new JTextField();
		panelcentroDatos.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		panelcentroDatos.add(lblFechaNac);
		
		textField_4 = new JTextField();
		panelcentroDatos.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol:");
		panelcentroDatos.add(lblRol);
		
		textField_5 = new JTextField();
		panelcentroDatos.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblGenero = new JLabel("Género:");
		panelcentroDatos.add(lblGenero);
		
		textField_2 = new JTextField();
		panelcentroDatos.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panelcentroBoton = new JPanel();
		panelCentro.add(panelcentroBoton);
		panelcentroBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
		
		JButton btnRegistro = new JButton("REGISTRAR");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hacer que se abra otra ventana y que se muestre el mensaje. La ventana tiene que aparecer mientras se ve la aneterior.
				//JOptionPane.showMessageDialog(null, "Introducir contraseña para validar:", "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panelcentroBoton.add(btnRegistro);
	}

}
