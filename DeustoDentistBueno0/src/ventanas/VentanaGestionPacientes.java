package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BD.BD;
import Clases.Paciente;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class VentanaGestionPacientes extends JFrame {

	private JPanel contentPane;
	private JTable tablaGestionPacientes;
	
	Connection con = BD.initBD("BaseDatos.db");
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionPacientes frame = new VentanaGestionPacientes();
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
	public VentanaGestionPacientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("GESTIÓN PACIENTES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("GESTIÓN PACIENTES");
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
	
		//Meter la conexón con la Base de Datos
		String [] columnas = {"DNI", "NOMBRE", "APELLIDOS", "FECHA NACIMIENTO", "DIRECCION", "TELEFONO", "GENERO"};
		ArrayList<Paciente> aPersonas = BD.obtenerListaPaciente(con);
		DefaultTableModel modelo = new DefaultTableModel(columnas, 3);
		
		Object O [] = null;
		for (int i = 0; i < aPersonas.size(); i++) {
			modelo.addRow(O);
			Paciente getPaciente = (Paciente) aPersonas.get(i);
			modelo.setValueAt(getPaciente.getDni(), i, 0);
			modelo.setValueAt(getPaciente.getNombre(), i, 1);
			modelo.setValueAt(getPaciente.getApellido(), i, 2);
			modelo.setValueAt(getPaciente.getFechaNacimiento(), i, 3);
			modelo.setValueAt(getPaciente.getDireccion(), i, 4);
			modelo.setValueAt(getPaciente.getTelefono(), i, 5);
			modelo.setValueAt(getPaciente.getGenero(), i, 6);
		}
		
		
		tablaGestionPacientes = new JTable(modelo);
		tablaGestionPacientes.setBounds(100, 100, 450, 300);
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionPacientes);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnBuscar = new JButton("BUSCAR");
		panelSur.add(btnBuscar); 
		
		JButton btnAnadir = new JButton("AÑADIR");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.anadirPaciente(con, null);
			}
		});
		panelSur.add(btnAnadir);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.eliminarPacientePorDni(con, getWarningString());
			}
		});
		panelSur.add(btnBorrar);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.modificarPaciente(con, getName(), getWarningString());
			}
		});
		panelSur.add(btnModificar);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
	}

}
