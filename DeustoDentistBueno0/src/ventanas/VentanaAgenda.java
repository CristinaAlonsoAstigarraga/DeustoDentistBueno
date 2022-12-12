package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BD.BD;
import Clases.Cita;
import Clases.Paciente;
import Clases.TipoCita;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable tablaGestionAgenda;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgenda frame = new VentanaAgenda();
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
	public VentanaAgenda() {
		VentanaCrearCita v_cita=new VentanaCrearCita();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("AGENDA");
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		//Meter la conexón con la Base de Datos
		String [] columnas = {"ID", "DNI", "NOMBRE PACIENTE", "FECHA", "TIPO", "NOMBRE DENTISTA"};
		ArrayList<Cita> aCitas = BD.obtenerListaCitas(con); 
		DefaultTableModel modelo = new DefaultTableModel(columnas, 3);
		
		Object O [] = null;
		for (int i = 0; i < aCitas.size(); i++) {
			modelo.addRow(O);
			Cita getCita = (Cita) aCitas.get(i);
			modelo.setValueAt(i, i, 0);
			modelo.setValueAt(getCita.getDniPaciente(), i, 1);
			modelo.setValueAt(getCita.getNombrePaciente(), i, 2);
			modelo.setValueAt(getCita.getFecha(), i, 3);
			modelo.setValueAt(getCita.getTipo(), i, 4);
			modelo.setValueAt(getCita.getNombreDentista(), i, 5);
		}
		
		
		tablaGestionAgenda = new JTable(modelo);
		tablaGestionAgenda.setBounds(100, 100, 450, 300);
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionAgenda);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		panelSur.add(btnBuscar); 
		
		JButton btnAnadir = new JButton("AÑADIR");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v_cita.setVisible(true);
				setVisible(false);
				
			}
		});
		panelSur.add(btnAnadir);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.eliminarCitaPorId(con, WIDTH);
			}
		});
		panelSur.add(btnBorrar);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tablaGestionAgenda.getSelectedRow() != -1) {
						String dni = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 1).toString();
			            String nombre = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 2).toString();  
						Date fecha= sdf.parse("20-02-2022 22:00");
						//Date fecha= sdf.parse(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 3).toString());
						TipoCita tipocita=TipoCita.valueOf(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 4).toString());
						String nombreD=modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 5).toString();
						Cita cita=new Cita(dni,nombre,nombreD,fecha,tipocita);
						
						VentanaModificarCita c=new VentanaModificarCita(cita);
					}
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		            
				
			}
		});
		panelSur.add(btnModificar);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		tablaGestionAgenda.getTableHeader().setReorderingAllowed(false);//bloquear columnas
	}

}
