package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BD.BD;
import Clases.Paciente;

public class VentanaAnadirPaciente extends JFrame{

	private JPanel contentPane;
	private JTextField textFieldDni;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldFechaNac;
	private JTextField textFieldTelf;
	private JTextField textFieldGenero;
	private JTextField textFieldDir;
	
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirPaciente frame = new VentanaAnadirPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaAnadirPaciente(){
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("ANADIR PACIENTE");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		JLabel lblAnadirPaciente = new JLabel("ANADIR PACIENTE");
		panelNorte.add(lblAnadirPaciente);
		

		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro);
		
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDni = new JLabel("DNI: ");
		panelCentro.add(lblDni);
		
		textFieldDni = new JTextField();
		panelCentro.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		panelCentro.add(lblNombre);
		
		textFieldNombre = new JTextField();
		panelCentro.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		panelCentro.add(lblApellido);
		
		textFieldApellido = new JTextField();
		panelCentro.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblFechaNac = new JLabel("FECHA NACIMIENTO");
		panelCentro.add(lblFechaNac);
		
		textFieldFechaNac = new JTextField();
		panelCentro.add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		JLabel lblTelf = new JLabel("TELEFONO:");
		panelCentro.add(lblTelf);
		
		textFieldTelf = new JTextField();
		panelCentro.add(textFieldTelf);
		textFieldTelf.setColumns(10);
		
		JLabel lblDir = new JLabel("DIRECCION:");
		panelCentro.add(lblDir);
		
		textFieldDir = new JTextField();
		panelCentro.add(textFieldDir);
		textFieldDir.setColumns(10);
		
		JLabel lblGenero = new JLabel("GENERO:");
		panelCentro.add(lblGenero);
		
		textFieldGenero = new JTextField();
		panelCentro.add(textFieldGenero);
		textFieldGenero.setColumns(10);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = textFieldDni.getText();
				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellido.getText();
				String telf = textFieldTelf.getText();
				int telfInt = Integer.parseInt(telf);
				String dir = textFieldDir.getText();
				String gen = textFieldGenero.getText();
				String fecha =  textFieldFechaNac.getText();
				
				BD.anadirPaciente(con, dni, nombre, apellidos, fecha, dir, telfInt, gen);
				
			}
		});
		
		panelSur.add(btnRegistrar);
		
		JButton btnverPacientes = new JButton("VER PACIENTES");
		panelSur.add(btnverPacientes);
		btnverPacientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaGestionPacientes vgp = new VentanaGestionPacientes();
				vgp.setVisible(true);
				setVisible(false);	
			}
		});
		
		
	}
}


