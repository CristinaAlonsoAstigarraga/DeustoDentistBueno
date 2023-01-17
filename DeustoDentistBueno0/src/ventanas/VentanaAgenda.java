package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import BD.BD;
import Clases.Cita;
import Clases.Dentista;
import Clases.Paciente;
import Clases.TipoCita;


import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 * clase Ventana Agenda en la cual se visualizara uns lista con todas las citas y 
 * sus funcionalidades
 * @author irene
 *
 */
public class VentanaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable tablaGestionAgenda;
	private DefaultTableModel modelo;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	private JComboBox<String> comboBoxD, comboBoxDI;
	private JComboBox<String> comboBoxCITA, comboBoxCITAI;
	private JComboBox<String> comboBoxDENTISTA, comboBoxDENTISTAI;
	private JComboBox<String> comboBoxDNI, comboBoxDNII;
	private JTextField textFieldFecha, textFieldFechaI;
	private Date fecha, fecha1;
	private LocalDateTime fyhActual = LocalDateTime.now();
	private int id=-1;
	private Cita cita ;
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaAgenda frame = new VentanaAgenda();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);//centrar ventana
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param rol 
	 * @param user 
	 */
	public VentanaAgenda(String rol, String user) {
		VentanaCrearCita v_cita = new VentanaCrearCita();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblTituloGP = new JLabel("AGENDA");
		lblTituloGP.setForeground(SystemColor.text);
		lblTituloGP.setBackground(SystemColor.text);
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);

		JPanel panelEste = new JPanel();
		panelEste.setBackground(SystemColor.windowBorder);
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout());

		/*
		 * AQUI EMPIEZA
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		panelEste.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		tabbedPane.addTab(" ", null, panelInicio, null);
		panelInicio.setLayout(new BorderLayout(0, 0));

		JLabel lblImagen = new JLabel("");
		lblImagen.setBackground(SystemColor.inactiveCaptionBorder);
		lblImagen.setIcon(new ImageIcon(VentanaAgenda.class.getResource("/img/imagen2 (1).jpg")));
		panelInicio.add(lblImagen, BorderLayout.CENTER);

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
				insertarCita();
				ArrayList<Cita> aCitas;
				aCitas = BD.obtenerListaCitas(con);
				actualizartabla(tablaGestionAgenda, modelo, aCitas);
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

		JLabel lblDni1 = new JLabel("DNI");
		GridBagConstraints gbc_lblDni1 = new GridBagConstraints();
		gbc_lblDni1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni1.anchor = GridBagConstraints.EAST;
		gbc_lblDni1.gridx = 2;
		gbc_lblDni1.gridy = 1;
		panelCentroI.add(lblDni1, gbc_lblDni1);

		comboBoxDNII = new JComboBox();
		GridBagConstraints gbc_comboBoxDNII = new GridBagConstraints();
		gbc_comboBoxDNII.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDNII.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDNII.gridx = 3;
		gbc_comboBoxDNII.gridy = 1;
		panelCentroI.add(comboBoxDNII, gbc_comboBoxDNII);

		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panelCentroI.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblFecha = new JLabel("FECHA Y HORA");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 2;
		panelCentroI.add(lblFecha, gbc_lblFecha);

		textFieldFechaI = new JTextField();
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 3;
		gbc_textFieldFecha.gridy = 2;
		panelCentroI.add(textFieldFechaI, gbc_textFieldFecha);
		textFieldFechaI.setColumns(10);

		JLabel lbTipoCita = new JLabel("TIPO CITA");
		GridBagConstraints gbc_lbTipoCita = new GridBagConstraints();
		gbc_lbTipoCita.anchor = GridBagConstraints.EAST;
		gbc_lbTipoCita.insets = new Insets(0, 0, 5, 5);
		gbc_lbTipoCita.gridx = 2;
		gbc_lbTipoCita.gridy = 3;
		panelCentroI.add(lbTipoCita, gbc_lbTipoCita);

		comboBoxCITAI = new JComboBox();
		GridBagConstraints gbc_comboBoxCITA = new GridBagConstraints();
		gbc_comboBoxCITA.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCITA.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCITA.gridx = 3;
		gbc_comboBoxCITA.gridy = 3;
		panelCentroI.add(comboBoxCITAI, gbc_comboBoxCITA);

		JLabel lblDentista = new JLabel("DENTISTA");
		GridBagConstraints gbc_lblDentista = new GridBagConstraints();
		gbc_lblDentista.anchor = GridBagConstraints.EAST;
		gbc_lblDentista.insets = new Insets(0, 0, 0, 5);
		gbc_lblDentista.gridx = 2;
		gbc_lblDentista.gridy = 4;
		panelCentroI.add(lblDentista, gbc_lblDentista);

		comboBoxDENTISTAI = new JComboBox();
		GridBagConstraints gbc_comboBoxDENTISTA = new GridBagConstraints();
		gbc_comboBoxDENTISTA.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDENTISTA.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDENTISTA.gridx = 3;
		gbc_comboBoxDENTISTA.gridy = 4;
		panelCentroI.add(comboBoxDENTISTAI, gbc_comboBoxDENTISTA);

		// +++++++++++++++++++

		JPanel panelModificar = new JPanel();
		tabbedPane.addTab("MODIFICAR", null, panelModificar, null);

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
				borrarCita();
				ArrayList<Cita> aCitas;
				aCitas = BD.obtenerListaCitas(con);
				actualizartabla(tablaGestionAgenda, modelo, aCitas);
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

		JLabel lblNewLabel_4 = new JLabel("Borrar la cita de ");
		panelCentralBorrar.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));

		JLabel lblNombre2 = new JLabel("");
		panelCentralBorrar.add(lblNombre2);
		panelModificar.setLayout(new BorderLayout(0, 0));

		JPanel pNorteE = new JPanel();
		pNorteE.setBorder(new LineBorder(SystemColor.activeCaptionText));
		pNorteE.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pNorteE, BorderLayout.NORTH);
		pNorteE.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblTitulo = new JLabel("MODIFICAR PACIENTE: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		pNorteE.add(lblTitulo);

		JPanel pSurE = new JPanel();
		pSurE.setBorder(new LineBorder(SystemColor.desktop, 1, true));
		pSurE.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pSurE, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("MODIFICAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCita();
				ArrayList<Cita> aCitas;
				aCitas = BD.obtenerListaCitas(con);
				actualizartabla(tablaGestionAgenda, modelo, aCitas);
			}

		});

		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pSurE.add(btnAceptar);

		JPanel pCentroE = new JPanel();
		pCentroE.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelModificar.add(pCentroE);
		GridBagLayout gbl_pCentroE = new GridBagLayout();
		gbl_pCentroE.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroE.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroE.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroE.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pCentroE.setLayout(gbl_pCentroE);

		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.gridx = 5;
		gbc_lblNewLabel1.gridy = 0;
		pCentroE.add(lblNewLabel, gbc_lblNewLabel1);

		JLabel lblNombre = new JLabel("");
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 5;
		gbc_lblNombre.gridy = 1;
		pCentroE.add(lblNombre, gbc_lblNombre);

		JLabel lblDni = new JLabel("DNI");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.gridx = 3;
		gbc_lblDni.gridy = 3;
		pCentroE.add(lblDni, gbc_lblDni);

		comboBoxDNI = new JComboBox();
		GridBagConstraints gbc_comboBoxDNI = new GridBagConstraints();
		gbc_comboBoxDNI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDNI.gridx = 5;
		gbc_comboBoxDNI.gridy = 3;
		pCentroE.add(comboBoxDNI, gbc_comboBoxDNI);
//		comboBoxDNI.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//
//				String nombrePaciente = BD.buscarPacientePorDni(con, e.getItem().toString());
//				//System.out.println(nombrePaciente);
//				if (nombrePaciente != null) {
//					//lblNombre.setText(nombrePaciente);
//					
//				}
//			}
//		});

		JLabel lblNewLabel_11 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 2;
		gbc_lblNewLabel_11.gridy = 4;
		pCentroE.add(lblNewLabel_11, gbc_lblNewLabel_11);

		JLabel lblFecha1 = new JLabel("FECHA Y HORA");
		GridBagConstraints gbc_lblFecha1 = new GridBagConstraints();
		gbc_lblFecha1.anchor = GridBagConstraints.EAST;
		gbc_lblFecha1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha1.gridx = 3;
		gbc_lblFecha1.gridy = 4;
		pCentroE.add(lblFecha1, gbc_lblFecha1);
		
		
		
		textFieldFecha = new JTextField();
		GridBagConstraints gbc_textFieldFecha1 = new GridBagConstraints();
		gbc_textFieldFecha1.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha1.gridx = 5;
		gbc_textFieldFecha1.gridy = 4;
		pCentroE.add(textFieldFecha, gbc_textFieldFecha1);
		textFieldFecha.setColumns(10);

		JLabel lbTipoCita1 = new JLabel("TIPO CITA");
		GridBagConstraints gbc_lbTipoCita1 = new GridBagConstraints();
		gbc_lbTipoCita1.anchor = GridBagConstraints.EAST;
		gbc_lbTipoCita1.insets = new Insets(0, 0, 5, 5);
		gbc_lbTipoCita1.gridx = 3;
		gbc_lbTipoCita1.gridy = 5;
		pCentroE.add(lbTipoCita1, gbc_lbTipoCita1);

		comboBoxCITA = new JComboBox();
		GridBagConstraints gbc_comboBoxCITA1 = new GridBagConstraints();
		gbc_comboBoxCITA1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCITA1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCITA1.gridx = 5;
		gbc_comboBoxCITA1.gridy = 5;
		pCentroE.add(comboBoxCITA, gbc_comboBoxCITA1);

		JLabel lblDentista1 = new JLabel("DENTISTA");
		GridBagConstraints gbc_lblDentista1 = new GridBagConstraints();
		gbc_lblDentista1.anchor = GridBagConstraints.EAST;
		gbc_lblDentista1.insets = new Insets(0, 0, 0, 5);
		gbc_lblDentista1.gridx = 3;
		gbc_lblDentista1.gridy = 6;
		pCentroE.add(lblDentista1, gbc_lblDentista1);

		comboBoxDENTISTA = new JComboBox();
		GridBagConstraints gbc_comboBoxDENTISTA1 = new GridBagConstraints();
		gbc_comboBoxDENTISTA1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDENTISTA1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDENTISTA1.gridx = 5;
		gbc_comboBoxDENTISTA1.gridy = 6;
		pCentroE.add(comboBoxDENTISTA, gbc_comboBoxDENTISTA1);

		// ********************

		/*
		 * AQUI ACABA
		 */
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		// Meter la conexón con la Base de Datos
		String[] columnas = { "ID", "DNI", "PACIENTE", "FECHA", "TIPO", "DENTISTA" };
		ArrayList<Cita> aCitas = BD.obtenerListaCitas(con);

		modelo = new DefaultTableModel(columnas, 0);

		Collections.sort(aCitas, new Comparator<Cita>() {
			@Override
			public int compare(Cita o1, Cita o2) {
				// TODO Auto-generated method stub
				return o1.getFecha().compareTo(o2.getFecha());
			}
		});
//		for(Cita c: aCitas) {
//			System.out.println(c.getFecha()+"\n");
//		}

		tablaGestionAgenda = new JTable(modelo);
		tablaGestionAgenda.setBounds(100, 100, 450, 300);
		
		Object O[] = null;
		for (int i = 0; i < aCitas.size(); i++) {

			modelo.addRow(O);
			Cita getCita = (Cita) aCitas.get(i);
			modelo.setValueAt((getCita.getId()), i, 0);
			modelo.setValueAt(getCita.getDniPaciente(), i, 1);
			modelo.setValueAt(getCita.getNombrePaciente(), i, 2);
			modelo.setValueAt(sdf.format(getCita.getFecha()), i, 3);
			modelo.setValueAt(getCita.getTipo().toString(), i, 4);
			modelo.setValueAt(getCita.getNombreDentista(), i, 5);
			//addCheckBox(6, tablaGestionAgenda);
		}
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(new Color(244, 164, 96));
		panelCentro.add(panelBuscar, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Realiza un filtro por un Dentista: ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblNewLabel_2);

		comboBoxD = new JComboBox();
		panelBuscar.add(comboBoxD);

		JButton btnBuscar = new JButton("BUSCAR");
		panelBuscar.add(btnBuscar);
		// btnBuscar.setBorder(new RoundedBorder(20));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Cita> aCitas;
			
				String nombre = comboBoxD.getSelectedItem().toString();
				// controlar el filtro segun dentista
				if (comboBoxD.getSelectedItem().toString() == "Todos") {
					aCitas = BD.obtenerListaCitas(con);
					
				} else {
					aCitas = BD.buscarCitaPorDentista(con, nombre);
				}

				actualizartabla(tablaGestionAgenda,modelo,aCitas);
			}

			
		});

		JScrollPane scrollTabla = new JScrollPane(tablaGestionAgenda);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCentro.add(scrollTabla, BorderLayout.CENTER);

		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER AL MENÚ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ROL:"+rol);
				System.out.println("USER:"+user);
				VentanaPrincipal vp = new VentanaPrincipal(rol, user);
				vp.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(btnVolver);

		JLabel lblNewLabel_3 = new JLabel("aplicacion Dentist");
		lblNewLabel_3.setForeground(SystemColor.menu);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panelSur.add(lblNewLabel_3);

		// ++++++++++++++++++++

		tablaGestionAgenda.getTableHeader().setReorderingAllowed(false);// bloquear columnas

		tablaGestionAgenda.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("evento de raton");
				try {
					if (tablaGestionAgenda.getSelectedRow() != -1) {
						 id = (int) modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 0);
						String dni = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 1).toString();
						String nombre = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 2).toString();
						 Date fecha= sdf.parse(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(),3).toString());
						TipoCita tipocita = TipoCita.valueOf(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 4).toString());
						String nombreD = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 5).toString();
						 cita = new Cita(id,dni, nombre, nombreD, fecha, tipocita);

						comboBoxDNI.setSelectedItem(cita.getDniPaciente().toString());
						textFieldFecha.setText(sdf.format(cita.getFecha()).toString());
						comboBoxCITA.setSelectedItem(cita.getTipo().toString());
						comboBoxDENTISTA.setSelectedItem(cita.getNombreDentista().toString());

						lblNombre2.setText(nombre);
						lblNombre.setText(nombre);
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		// render

//		tablaGestionAgenda.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//					int row, int column) { 
//				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//				
//				TipoCita tipo = (TipoCita) modelo.getValueAt(row,4);
//				System.out.println((TipoCita) modelo.getValueAt(row,4));
//				
//				if(tipo.equals("CIRUJIA")) {
//						c.setBackground(Color.RED);
//				}else {
//						c.setBackground(Color.green);
//					
//				}
//				return c;
//			}
//		});
		comboBoxCITA.enable(false);
		comboBoxDENTISTA.enable(false);
		comboBoxDNI.enable(false);
		CargarComboBoxDni();
		CargarComboBoxCita();
		CargarComBoxDentista();
	}
	/**
	 * metodo para cargar el combobox de dni con una lista de todos los dni
	 * obtenidos de bbdd
	 */
	private void CargarComboBoxDni() {
		// TODO Auto-generated method stub
		ArrayList<Paciente> lista = new ArrayList<>();
		lista = BD.obtenerListaPaciente(con);
		for (Paciente p : lista) {
			comboBoxDNI.addItem(p.getDni());
			comboBoxDNII.addItem(p.getDni());
		}
	}
	/**
	 * metodo para cargar el combobox de dentista con una lista de todos los nombres de dentista
	 * obtenidos de bbdd
	 */
	private void CargarComBoxDentista() {
		// TODO Auto-generated method stub
		ArrayList<Dentista> lista = new ArrayList<>();
		lista = BD.obtenerListaDentista(con);

		for (Dentista d : lista) {
			comboBoxDENTISTA.addItem(d.getNombre());
			comboBoxDENTISTAI.addItem(d.getNombre());
			comboBoxD.addItem(d.getNombre());

//			comboBoxCITA.addItem(d.getNombre()+" "+d.getApellido());
//			comboBoxD.addItem(d.getNombre()+" "+d.getApellido());
		}
		comboBoxD.addItem("Todos");
	}
	/**
	 * metodo para cargar el combobox de cita con una lista de todos loso tipos de citas
	 * obtenidos de bbdd
	 */
	private void CargarComboBoxCita() {
		// TODO Auto-generated method stub
		for (TipoCita n : TipoCita.values()) {
			comboBoxCITA.addItem(n.name());
			comboBoxCITAI.addItem(n.name());
		}

	}
	
	/**
	 * metodo para actualizar la tabla con nuevos valores
	 * @param tablaGestionAgenda
	 * @param modelo
	 * @param aCitas
	 */
	private void actualizartabla(JTable tablaGestionAgenda, DefaultTableModel modelo, ArrayList<Cita> aCitas) {
		Object O[] = null;
		// limpiar el modelo y volver a escribir
		for (int i = 0; i < tablaGestionAgenda.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		// rellenar con la nueva busqueda-->filtro
		for (int i = 0; i < aCitas.size(); i++) {
			modelo.addRow(O);
			Cita getCita = (Cita) aCitas.get(i);
			modelo.setValueAt((getCita.getId()), i, 0);
			modelo.setValueAt(getCita.getDniPaciente(), i, 1);
			modelo.setValueAt(getCita.getNombrePaciente(), i, 2);
			modelo.setValueAt(sdf.format(getCita.getFecha()), i, 3);
			//System.out.println("FECHA DE BBDD: " + sdf.format(getCita.getFecha()));
			modelo.setValueAt(getCita.getTipo().toString(), i, 4);
			modelo.setValueAt(getCita.getNombreDentista(), i, 5);
		}
		
	}
	
	
	/*----------OPERACIONES----------*/
	/**
	 * emtodo para borrar una cita de la bbdd y de la lista
	 */
	private void borrarCita() {
		if(cita!=null) {
			BD.borrarCita(con,cita);
			JOptionPane.showMessageDialog(null, "SE HA BORRADO LA CITA",
					"BORRADO", JOptionPane.OK_OPTION);
		}else {
			JOptionPane.showMessageDialog(null, "SELECCIONE UNA CITA",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * metodo para modificar una cita de la bbdd y de la lista
	 */
	private void modificarCita() {
		// comprobar campos no nulos
		if (!(textFieldFecha.getText().isEmpty())) {

			// comprobar dni
			String nombrePaciente = BD.buscarPacientePorDni(con, comboBoxDNI.getSelectedItem().toString());
			if (nombrePaciente != null) {

				// comprobar formato fecha
				try {
					fecha = sdf.parse(textFieldFecha.getText());
					System.out.println(sdf.format(fecha));
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR LA FECHA EN FORMATO: \"dd-MM-yyyy hh:mm\"",
							"FORMATO ERRONEO", JOptionPane.ERROR_MESSAGE);
				}
				
				Date fyhActualDate = java.sql.Timestamp.valueOf(fyhActual);

				//int id=BD.obtenerCita(con,comboBoxDNI.getSelectedItem().toString(), sdf.format(fecha));
				
				System.out.println("ID:"+id);
				
				if(!fecha.before(fyhActualDate)) {
					BD.modificarCita(con, sdf.format(fecha), id);
				}else {
					JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UNA FECHA VALIDA", "FECHA ANTIGUA",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN DNI VALIDO", "USUARIO NO ENCONTRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATO", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// METODOS BOTONES
	/**
	 * metodo para insertar una cita en la bbdd y refrescar la tabla
	 */
	private void insertarCita() {

		// comprobar campos no nulos
		if (!(textFieldFechaI.getText().isEmpty())) {

			// comprobar dni
			String nombrePaciente = BD.buscarPacientePorDni(con, comboBoxDNII.getSelectedItem().toString());
			if (nombrePaciente != null) {

				// comprobar formato fecha
				try {
					fecha = sdf.parse(textFieldFechaI.getText());
					System.out.println(sdf.format(fecha));
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR LA FECHA EN FORMATO: \"dd-MM-yyyy hh:mm\"",
							"FORMATO ERRONEO", JOptionPane.ERROR_MESSAGE);
				}
				
				Date fyhActualDate = java.sql.Timestamp.valueOf(fyhActual);
				
				if(!fecha.before(fyhActualDate)){
					
					// crear cita
					Cita c = new Cita();
					c.setDniPaciente(comboBoxDNII.getSelectedItem().toString());
					c.setNombrePaciente(nombrePaciente);
					c.setFecha(fecha);
					c.setTipo(TipoCita.valueOf(comboBoxCITAI.getSelectedItem().toString()));
					c.setNombreDentista((String) comboBoxDENTISTAI.getSelectedItem());
					BD.anadirCita(con, c);
					
				}else {
					JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UNA FECHA VALIDA", "FECHA ANTIGUA",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN DNI VALIDO", "USUARIO NO ENCONTRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
//	public void addCheckBox(int column, JTable tablaGestionAgenda) {
//		TableColumn tc = tablaGestionAgenda.getColumnModel().getColumn(column);
//		tc.setCellEditor(tablaGestionAgenda.getDefaultEditor(Boolean.class));
//		tc.setCellRenderer(tablaGestionAgenda.getDefaultRenderer(Boolean.class));
//	}

}