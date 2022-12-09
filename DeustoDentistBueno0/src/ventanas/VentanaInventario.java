package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.ArrayList;

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
import Clases.Inventario;
import Clases.Paciente;

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInventario extends JFrame {

	private JPanel contentPane;
	private JTable tablaGestionInventario;
	
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInventario frame = new VentanaInventario();
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
	public VentanaInventario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("INVENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("INVENTARIO");
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		panelOeste.add(menuBar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		//Meter la conexón con la Base de Datos
		
		String [] columnas = {"ID", "CODIGO PRODUCTO", "NOMBRE PRODUCTO", "CANTIDAD"};
		ArrayList<Inventario> aInventario = BD.obtenerListaInventario(con);
		DefaultTableModel modelo = new DefaultTableModel(columnas, 3);
		
		Object O [] = null;
		for (int i = 0; i < aInventario.size(); i++) {
			modelo.addRow(O);
			Inventario getInventario = (Inventario) aInventario.get(i);
			modelo.setValueAt(i, i, 0);
			modelo.setValueAt(getInventario.getCodigoProducto(), i, 1);
			modelo.setValueAt(getInventario.getNombreProducto(), i, 2);
			modelo.setValueAt(getInventario.getCantidad(), i, 3);
		}
	
	
		tablaGestionInventario = new JTable(modelo);
		tablaGestionInventario.setBounds(100, 100, 450, 300);
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionInventario);
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
				BD.anadirProducto(con, null);
			}
		});
		panelSur.add(btnAnadir);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.modificarProducto(con, ALLBITS, ABORT);
			}
		});
		panelSur.add(btnModificar);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
	}
}
