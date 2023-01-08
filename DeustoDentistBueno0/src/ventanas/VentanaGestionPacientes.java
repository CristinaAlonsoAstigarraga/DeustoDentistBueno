package ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BD.BD;
import Clases.Paciente;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

public class VentanaGestionPacientes extends JFrame{

	private JPanel contentPane;
	private JTable tablaGestionPacientes;
	private JTextField textFieldDNIInsertar, textFieldNombreInsertar, textFieldApellidoInsertar, textFieldTelefonoInsertar, textFieldDirInsertar,
	textFieldNombreModificar, textFieldApellidoModificar, textFieldTelefonoModificar, textFieldDirModificar, textFieldBuscar;
	JDateChooser dateChooserFechaNacimientoInsertar, dateChooserFechaNacimientoModificar;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private JComboBox<String> comboBoxGInsertar, comboBoxGModificar, comboBoxDNIModificar;
	private DefaultTableModel modelo;
	private Paciente paciente;
	private ArrayList<Paciente> pacientes;
	private Date fechaNac;
	
	Connection con = BD.initBD("BaseDatos.db");

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
		setBounds(100, 100, 848, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/*
		 * PANEL NORTE
		 */
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("PACIENTES");
		lblTituloGP.setForeground(SystemColor.text);
		lblTituloGP.setBackground(SystemColor.text);
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		/*
		 * PANEL ESTE
		 */
		
		JPanel panelEste = new JPanel();
		panelEste.setBackground(SystemColor.windowBorder);
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		panelEste.add(tabbedPane, BorderLayout.CENTER);
		
		/*
		 * PANEL INICIO
		 */
		
		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		tabbedPane.addTab(" ", null, panelInicio, null);
		panelInicio.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBackground(SystemColor.inactiveCaptionBorder);
		lblImagen.setIcon(new ImageIcon(VentanaGestionPacientes.class.getResource("/img/imagen2 (1).jpg")));
		panelInicio.add(lblImagen, BorderLayout.CENTER);
		
		/*
		 * PANEL INSERTAR
		 */
		
		JPanel panelInsertar = new JPanel();
		tabbedPane.addTab("INSERTAR", null, panelInsertar, null);
		panelInsertar.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorteI = new JPanel();
		panelNorteI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteI.setBackground(SystemColor.inactiveCaption);
		panelInsertar.add(panelNorteI, BorderLayout.NORTH);
		
		JLabel lblInsertarPaciente = new JLabel("INSERTAR PACIENTE: ");
		lblInsertarPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteI.add(lblInsertarPaciente);
		
		JPanel panelSurI = new JPanel();
		panelSurI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurI.setBackground(SystemColor.inactiveCaption);
		panelInsertar.add(panelSurI, BorderLayout.SOUTH);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarPaciente();
				ArrayList<Paciente> aPacientes;
				aPacientes = BD.obtenerListaPaciente(con);
				actualizarTabla(tablaGestionPacientes, modelo, aPacientes);
				
			}
		});
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurI.add(btnInsertar);
		
		JPanel panelCentroI = new JPanel();
		panelCentroI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelInsertar.add(panelCentroI, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroI = new GridBagLayout();
		gbl_pCentroI.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_pCentroI);
		
		JLabel lblNewLabelI = new JLabel(".");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		panelCentroI.add(lblNewLabelI, gbc_lblNewLabel);
		
		JLabel lblDniI = new JLabel("DNI");
		GridBagConstraints gbc_lblDniI = new GridBagConstraints();
		gbc_lblDniI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDniI.anchor = GridBagConstraints.EAST;
		gbc_lblDniI.gridx = 2;
		gbc_lblDniI.gridy = 1;
		panelCentroI.add(lblDniI, gbc_lblDniI);
		
		textFieldDNIInsertar = new JTextField();
		GridBagConstraints gbc_textFieldDNIInsertar = new GridBagConstraints();
		gbc_textFieldDNIInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDNIInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDNIInsertar.gridx = 3;
		gbc_textFieldDNIInsertar.gridy = 1;
		panelCentroI.add(textFieldDNIInsertar, gbc_textFieldDNIInsertar);
		textFieldDNIInsertar.setColumns(10);
		
		JLabel lblNewLabel_I = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_I = new GridBagConstraints();
		gbc_lblNewLabel_I.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_I.gridx = 1;
		gbc_lblNewLabel_I.gridy = 2;
		panelCentroI.add(lblNewLabel_I, gbc_lblNewLabel_I);
		
		JLabel lblNombreI = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreI = new GridBagConstraints();
		gbc_lblNombreI.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreI.anchor = GridBagConstraints.EAST;
		gbc_lblNombreI.gridx = 2;
		gbc_lblNombreI.gridy = 2;
		panelCentroI.add(lblNombreI, gbc_lblNombreI);
	
		textFieldNombreInsertar = new JTextField();
		GridBagConstraints gbc_textFieldNombreInsertar = new GridBagConstraints();
		gbc_textFieldNombreInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreInsertar.gridx = 3;
		gbc_textFieldNombreInsertar.gridy = 2;
		panelCentroI.add(textFieldNombreInsertar, gbc_textFieldNombreInsertar);
		textFieldNombreInsertar.setColumns(10);
		
		JLabel lblApellidoI = new JLabel("APELLIDO");
		GridBagConstraints gbc_lblApellidoI = new GridBagConstraints();
		gbc_lblApellidoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidoI.anchor = GridBagConstraints.EAST;
		gbc_lblApellidoI.gridx = 2;
		gbc_lblApellidoI.gridy = 3;
		panelCentroI.add(lblApellidoI, gbc_lblApellidoI);
		
		textFieldApellidoInsertar = new JTextField();
		GridBagConstraints gbc_textFieldApellidoInsertar = new GridBagConstraints();
		gbc_textFieldApellidoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidoInsertar.gridx = 3;
		gbc_textFieldApellidoInsertar.gridy = 3;
		panelCentroI.add(textFieldApellidoInsertar, gbc_textFieldApellidoInsertar);
		textFieldApellidoInsertar.setColumns(10);
		
		JLabel lblFechaNacimientoI = new JLabel("FECHA NACIMIENTO");
		GridBagConstraints gbc_lblFechaNacimientoI = new GridBagConstraints();
		gbc_lblFechaNacimientoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimientoI.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimientoI.gridx = 2;
		gbc_lblFechaNacimientoI.gridy = 4;
		panelCentroI.add(lblFechaNacimientoI, gbc_lblFechaNacimientoI);
		
		dateChooserFechaNacimientoInsertar = new JDateChooser("dd-MM-yyyy", "##-##-####", '_');
		GridBagConstraints gbc_dateChooserFechaNacimientoInsertar = new GridBagConstraints();
		gbc_dateChooserFechaNacimientoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaNacimientoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFechaNacimientoInsertar.gridx = 3;
		gbc_dateChooserFechaNacimientoInsertar.gridy = 4;
		panelCentroI.add(dateChooserFechaNacimientoInsertar, gbc_dateChooserFechaNacimientoInsertar);
	
		JLabel lblTelefonoI = new JLabel("TELEFONO");
		GridBagConstraints gbc_lblTelefonoI = new GridBagConstraints();
		gbc_lblTelefonoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonoI.anchor = GridBagConstraints.EAST;
		gbc_lblTelefonoI.gridx = 2;
		gbc_lblTelefonoI.gridy = 5;
		panelCentroI.add(lblTelefonoI, gbc_lblTelefonoI);
		
		textFieldTelefonoInsertar = new JTextField();
		GridBagConstraints gbc_textFieldTelefonoInsertar = new GridBagConstraints();
		gbc_textFieldTelefonoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTelefonoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTelefonoInsertar.gridx = 3;
		gbc_textFieldTelefonoInsertar.gridy = 5;
		panelCentroI.add(textFieldTelefonoInsertar, gbc_textFieldTelefonoInsertar);
		textFieldTelefonoInsertar.setColumns(10);
		
		JLabel lblDirI = new JLabel("DIRECCION");
		GridBagConstraints gbc_lblDirI = new GridBagConstraints();
		gbc_lblDirI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirI.anchor = GridBagConstraints.EAST;
		gbc_lblDirI.gridx = 2;
		gbc_lblDirI.gridy = 6;
		panelCentroI.add(lblDirI, gbc_lblDirI);
		
		textFieldDirInsertar = new JTextField();
		GridBagConstraints gbc_textFieldDirInsertar = new GridBagConstraints();
		gbc_textFieldDirInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDirInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDirInsertar.gridx = 3;
		gbc_textFieldDirInsertar.gridy = 6;
		panelCentroI.add(textFieldDirInsertar, gbc_textFieldDirInsertar);
		textFieldDirInsertar.setColumns(10);
		
		JLabel lbGeneroI = new JLabel("GENERO");
		GridBagConstraints gbc_lbGeneroI = new GridBagConstraints();
		gbc_lbGeneroI.anchor = GridBagConstraints.EAST;
		gbc_lbGeneroI.insets = new Insets(0, 0, 5, 5);
		gbc_lbGeneroI.gridx = 2;
		gbc_lbGeneroI.gridy = 7;
		panelCentroI.add(lbGeneroI, gbc_lbGeneroI);
		
		comboBoxGInsertar = new JComboBox();
		comboBoxGInsertar.addItem("Masculino");
		comboBoxGInsertar.addItem("Femenino");
		GridBagConstraints gbc_comboBoxGInsertar = new GridBagConstraints();
		gbc_comboBoxGInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGInsertar.gridx = 3;
		gbc_comboBoxGInsertar.gridy = 7;
		panelCentroI.add(comboBoxGInsertar, gbc_comboBoxGInsertar);
		
		/*
		 * PANEL MODIFICAR
		 */
		
		JPanel panelModificar = new JPanel();
		tabbedPane.addTab("MODIFICAR", null, panelModificar, null);
		panelModificar.setLayout(new BorderLayout(0, 0));
		
		JPanel pNorteM = new JPanel();
		pNorteM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		pNorteM.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pNorteM, BorderLayout.NORTH);
		pNorteM.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitulo = new JLabel("MODIFICAR PACIENTE: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		pNorteM.add(lblTitulo);

		JPanel pSurM = new JPanel();
		pSurM.setBorder(new LineBorder(SystemColor.desktop, 1, true));
		pSurM.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pSurM, BorderLayout.SOUTH);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPaciente();
				ArrayList<Paciente> aPacientes;
				aPacientes = BD.obtenerListaPaciente(con);
				actualizarTabla(tablaGestionPacientes, modelo, aPacientes);
				
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pSurM.add(btnModificar);
		
		JPanel pCentroM = new JPanel();
		pCentroM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelModificar.add(pCentroM);
		GridBagLayout gbl_pCentroM = new GridBagLayout();
		gbl_pCentroM.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroM.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pCentroM.setLayout(gbl_pCentroM);

		JLabel lblNewLabelM = new JLabel(".");
		lblNewLabelM.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelM = new GridBagConstraints();
		gbc_lblNewLabelM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelM.gridx = 5;
		gbc_lblNewLabelM.gridy = 0;
		pCentroM.add(lblNewLabelM, gbc_lblNewLabelM);
		
		JLabel lblDNIM = new JLabel("DNI");
		GridBagConstraints gbc_lblDNIM = new GridBagConstraints();
		gbc_lblDNIM.insets = new Insets(0, 0, 5, 5);
		gbc_lblDNIM.anchor = GridBagConstraints.EAST;
		gbc_lblDNIM.gridx = 3;
		gbc_lblDNIM.gridy = 3;
		pCentroM.add(lblDNIM, gbc_lblDNIM);
		
		comboBoxDNIModificar = new JComboBox<String>();
		cargarCB_DNI_Modificar();
		GridBagConstraints gbc_comboBoxDNIModificar = new GridBagConstraints();
		gbc_comboBoxDNIModificar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDNIModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDNIModificar.gridx = 5;
		gbc_comboBoxDNIModificar.gridy = 3;
		pCentroM.add(comboBoxDNIModificar, gbc_comboBoxDNIModificar);
		
		JLabel lblPacienteM = new JLabel("");
		GridBagConstraints gbc_lblPacienteM = new GridBagConstraints();
		gbc_lblPacienteM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPacienteM.gridx = 2;
		gbc_lblPacienteM.gridy = 4;
		pCentroM.add(lblPacienteM, gbc_lblPacienteM);
		
		JLabel lblNombreM = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreM = new GridBagConstraints();
		gbc_lblNombreM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreM.anchor = GridBagConstraints.EAST;
		gbc_lblNombreM.gridx = 3;
		gbc_lblNombreM.gridy = 4;
		pCentroM.add(lblNombreM, gbc_lblNombreM);
	
		textFieldNombreModificar = new JTextField();
		GridBagConstraints gbc_textFieldNombreModificar = new GridBagConstraints();
		gbc_textFieldNombreModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreModificar.gridx = 5;
		gbc_textFieldNombreModificar.gridy = 4;
		pCentroM.add(textFieldNombreModificar, gbc_textFieldNombreModificar);
		textFieldNombreModificar.setColumns(10);
		
		JLabel lblApellidoM = new JLabel("APELLIDO");
		GridBagConstraints gbc_lblApellidoM = new GridBagConstraints();
		gbc_lblApellidoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidoM.anchor = GridBagConstraints.EAST;
		gbc_lblApellidoM.gridx = 3;
		gbc_lblApellidoM.gridy = 5;
		pCentroM.add(lblApellidoM, gbc_lblApellidoM);
		
		textFieldApellidoModificar = new JTextField();
		GridBagConstraints gbc_textFieldApellidoModificar = new GridBagConstraints();
		gbc_textFieldApellidoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidoModificar.gridx = 5;
		gbc_textFieldApellidoModificar.gridy = 5;
		pCentroM.add(textFieldApellidoModificar, gbc_textFieldApellidoModificar);
		textFieldApellidoModificar.setColumns(10);
		
		JLabel lblFechaNacimientoM = new JLabel("FECHA NACIMIENTO");
		GridBagConstraints gbc_lblFechaNacimientoM = new GridBagConstraints();
		gbc_lblFechaNacimientoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimientoM.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimientoM.gridx = 3;
		gbc_lblFechaNacimientoM.gridy = 6;
		pCentroM.add(lblFechaNacimientoM, gbc_lblFechaNacimientoM);
		
		dateChooserFechaNacimientoModificar = new JDateChooser("dd-MM-yyyy", "##-##-####", '_');
		GridBagConstraints gbc_dateChooserFechaNacimientoModificar = new GridBagConstraints();
		gbc_dateChooserFechaNacimientoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaNacimientoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFechaNacimientoModificar.gridx = 5;
		gbc_dateChooserFechaNacimientoModificar.gridy = 6;
		pCentroM.add(dateChooserFechaNacimientoModificar, gbc_dateChooserFechaNacimientoModificar);
		
		JLabel lblTelefonoM = new JLabel("TELEFONO");
		GridBagConstraints gbc_lblTelefonoM = new GridBagConstraints();
		gbc_lblTelefonoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonoM.anchor = GridBagConstraints.EAST;
		gbc_lblTelefonoM.gridx = 3;
		gbc_lblTelefonoM.gridy = 7;
		pCentroM.add(lblTelefonoM, gbc_lblTelefonoM);
		
		textFieldTelefonoModificar = new JTextField();
		GridBagConstraints gbc_textFieldTelefonoModificar1 = new GridBagConstraints();
		gbc_textFieldTelefonoModificar1.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTelefonoModificar1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTelefonoModificar1.gridx = 5;
		gbc_textFieldTelefonoModificar1.gridy = 7;
		pCentroM.add(textFieldTelefonoModificar, gbc_textFieldTelefonoModificar1);
		textFieldTelefonoModificar.setColumns(10);
		
		JLabel lblDirM = new JLabel("DIRECCION");
		GridBagConstraints gbc_lblDirM = new GridBagConstraints();
		gbc_lblDirM.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirM.anchor = GridBagConstraints.EAST;
		gbc_lblDirM.gridx = 3;
		gbc_lblDirM.gridy = 8;
		pCentroM.add(lblDirM, gbc_lblDirM);
		
		textFieldDirModificar = new JTextField();
		GridBagConstraints gbc_textFieldDirModificar = new GridBagConstraints();
		gbc_textFieldDirModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDirModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDirModificar.gridx = 5;
		gbc_textFieldDirModificar.gridy = 8;
		pCentroM.add(textFieldDirModificar, gbc_textFieldDirModificar);
		textFieldDirModificar.setColumns(10);
		
		JLabel lbGeneroM = new JLabel("GENERO");
		GridBagConstraints gbc_lbGeneroM = new GridBagConstraints();
		gbc_lbGeneroM.anchor = GridBagConstraints.EAST;
		gbc_lbGeneroM.insets = new Insets(0, 0, 5, 5);
		gbc_lbGeneroM.gridx = 3;
		gbc_lbGeneroM.gridy = 9;
		pCentroM.add(lbGeneroM, gbc_lbGeneroM);
		
		comboBoxGModificar = new JComboBox();
		comboBoxGModificar.addItem("Masculino");
		comboBoxGModificar.addItem("Femenino");
		GridBagConstraints gbc_comboBoxGModificar = new GridBagConstraints();
		gbc_comboBoxGModificar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGModificar.gridx = 5;
		gbc_comboBoxGModificar.gridy = 9;
		pCentroM.add(comboBoxGModificar, gbc_comboBoxGModificar);
		
		/*
		 * PANEL BORRAR
		 */
		
		JPanel panelBorrar = new JPanel();
		tabbedPane.addTab("BORRAR", null, panelBorrar, null);
		panelBorrar.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSurB = new JPanel();
		panelSurB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurB.setBackground(SystemColor.inactiveCaption);
		panelBorrar.add(panelSurB, BorderLayout.SOUTH);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPaciente();
				ArrayList<Paciente> aPacientes;
				aPacientes = BD.obtenerListaPaciente(con);
				actualizarTabla(tablaGestionPacientes, modelo, aPacientes);
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurB.add(btnBorrar);
		
		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(SystemColor.inactiveCaption);
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);
		
		JLabel lblBorrarPaciente = new JLabel("BORRAR PACIENTE: ");
		lblBorrarPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteB.add(lblBorrarPaciente);
		
		JPanel panelCentralBorrar = new JPanel();
		panelCentralBorrar.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentralBorrar, BorderLayout.CENTER);
		
		JLabel lblBorrar = new JLabel("Borrar al paciente: ");
		panelCentralBorrar.add(lblBorrar);
		lblBorrar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		JLabel lblNombrePacienteB = new JLabel("");
		panelCentralBorrar.add(lblNombrePacienteB);
		
		/*
		 * PANEL OESTE
		 */
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		/*
		 * PANEL CENTRO
		 */
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		//Meter la conexón con la Base de Datos
		String [] columnas = {"DNI", "NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "DIRECCION", "TELEFONO", "GENERO"};
		ArrayList<Paciente> aPersonas = BD.obtenerListaPaciente(con);
		
		modelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		Collections.sort(aPersonas, new Comparator<Paciente>() {
			@Override
			public int compare(Paciente o1, Paciente o2) {
				// TODO Auto-generated method stub
				return o1.getApellido().compareTo(o2.getApellido());
			}
		});
		
		tablaGestionPacientes = new JTable(modelo);
		tablaGestionPacientes.setBounds(100, 100, 450, 300);

		Object O [] = null;
		for (int i = 0; i < aPersonas.size(); i++) {
			
			modelo.addRow(O);
			Paciente getPaciente = (Paciente) aPersonas.get(i);
			modelo.setValueAt(getPaciente.getDni(), i, 0);
			modelo.setValueAt(getPaciente.getNombre(), i, 1);
			modelo.setValueAt(getPaciente.getApellido(), i, 2);
			modelo.setValueAt(sdf.format(getPaciente.getFechaNacimiento()), i, 3);
			modelo.setValueAt(getPaciente.getDireccion(), i, 4);
			modelo.setValueAt(getPaciente.getTelefono(), i, 5);
			modelo.setValueAt(getPaciente.getGenero(), i, 6);
		}
		
		/*
		 * PANEL BUSCAR
		 */
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(SystemColor.inactiveCaption);
		panelCentro.add(panelBuscar, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Buscar por Apellido: ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblNewLabel_2);
		
		textFieldBuscar = new JTextField(20);
		panelBuscar.add(textFieldBuscar);
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionPacientes);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCentro.add(scrollTabla, BorderLayout.CENTER);
		
//		textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
//			
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				String buscador = textFieldBuscar.getText();
//				while(modelo.getRowCount()>0) {
//					modelo.removeRow(0);
//				}
//				for (Paciente p : pacientes) {
//					if(p.getApellido().startsWith(buscador)) {
//					}
//				}
//				
//			}
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		/*
		 * PANEL SUR
		 */
		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER AL MENU");
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				setVisible(false);
				
			}
		});
		
		panelSur.add(btnVolver);
		
		JLabel lblNewLabel_3 = new JLabel("aplicacion Dentist");
		lblNewLabel_3.setForeground(SystemColor.menu);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panelSur.add(lblNewLabel_3);
		
		/*
		 * ACABAN PANELES
		 */
		
		tablaGestionPacientes.getTableHeader().setReorderingAllowed(false);
		tablaGestionPacientes.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
					if (tablaGestionPacientes.getSelectedRow() != -1) {
						String dni = modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 0).toString();
						String nombre = modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 1).toString();
						String apellido = modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 2).toString();
						try {
							fechaNac = sdf.parse(modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 3).toString());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String dir = modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 4).toString();
						int telf = Integer.parseInt(modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 5).toString());
						String gen = modelo.getValueAt(tablaGestionPacientes.getSelectedRow(), 6).toString();
						
						paciente = new Paciente(dni, nombre, apellido, fechaNac, telf, dir, gen);
						lblNombrePacienteB.setText(nombre);
					}
			}
		});
	}
	
	private void cargarCB_DNI_Modificar() {
		ArrayList<Paciente> lista = new ArrayList<>();
		lista = BD.obtenerListaPaciente(con);
		for(Paciente p : lista) {
			comboBoxDNIModificar.addItem(p.getDni());
		}
	}
	
	private void actualizarTabla(JTable tablaGestionPacientes, DefaultTableModel modelo, ArrayList<Paciente> aPacientes) {
		Object O[] = null;
		// limpiar el modelo y volver a escribir
		for (int i = 0; i < tablaGestionPacientes.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		for(int i = 0; i<aPacientes.size(); i++) {
			modelo.addRow(O);
			Paciente getPaciente = (Paciente) aPacientes.get(i);
			modelo.setValueAt((getPaciente.getDni()), i, 0);
			modelo.setValueAt((getPaciente.getNombre()), i, 1);
			modelo.setValueAt((getPaciente.getApellido()), i, 2);
			modelo.setValueAt(sdf.format(getPaciente.getFechaNacimiento()), i, 3);
			modelo.setValueAt((getPaciente.getDireccion()), i, 4);
			modelo.setValueAt((getPaciente.getTelefono()), i, 5);
			modelo.setValueAt((getPaciente.getGenero()), i, 6);
		}
	}
	
	private void insertarPaciente() {
		
		if (!(textFieldApellidoInsertar.getText().isEmpty()) || !(textFieldDirInsertar.getText().isEmpty()) || !(textFieldDNIInsertar.getText().isEmpty())
				|| !(textFieldNombreInsertar.getText().isEmpty()) || !(textFieldTelefonoInsertar.getText().isEmpty())) {
			
			Paciente p = new Paciente();
			p.setDni(textFieldDNIInsertar.getText());
			p.setNombre(textFieldNombreInsertar.getText());
			p.setApellido(textFieldApellidoInsertar.getText());
			p.setFechaNacimiento(dateChooserFechaNacimientoInsertar.getDate());
			p.setDireccion(textFieldDirInsertar.getText());
			p.setTelefono(Integer.parseInt(textFieldTelefonoInsertar.getText()));
			p.setGenero(comboBoxGInsertar.getSelectedItem().toString());
			
			BD.anadirPaciente(con, p);
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR VALORES VALIDOS",
					"FORMATO ERRONEO", JOptionPane.ERROR_MESSAGE);		}
	}
	
	private void modificarPaciente() {
		
		if (!(textFieldApellidoModificar.getText().isEmpty()) || !(textFieldDirModificar.getText().isEmpty()) ||
			!(textFieldNombreModificar.getText().isEmpty()) || !(textFieldTelefonoModificar.getText().isEmpty())) {
			
			String nombrePaciente = BD.buscarPacientePorDni(con, comboBoxDNIModificar.getSelectedItem().toString());
			if(nombrePaciente != null) {
				String dni = comboBoxDNIModificar.getSelectedItem().toString();
				String nombre = textFieldNombreModificar.getText();
				String apellido = textFieldApellidoModificar.getText();
				fechaNac = dateChooserFechaNacimientoModificar.getDate();
				String dir = textFieldDirModificar.getText();
				int telf = Integer.parseInt(textFieldTelefonoModificar.getText());
				String gen = comboBoxGModificar.getSelectedItem().toString();
				
				BD.modificarTuplaPaciente(con, dni, nombre, apellido, sdf.format(fechaNac), dir, telf, gen);
			}else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN DNI VALIDO", "USUARIO NO ENCONTRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATO", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	private void borrarPaciente() {
		if(paciente != null) {
			BD.borrarPaciente(con, paciente);
			JOptionPane.showMessageDialog(null, "SE HA BORRADO EL PACIENTE",
					"BORRADO", JOptionPane.OK_OPTION);
		}else {
			JOptionPane.showMessageDialog(null, "SELECCIONE UN PACIENTE",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
		
}