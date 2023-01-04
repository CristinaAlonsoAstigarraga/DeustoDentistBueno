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
import Clases.Historial;
import Clases.Paciente;



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

public class VentanaHistorial2 extends JFrame {

	private JPanel contentPane;
	private JTable tablahistorial;
	private DefaultTableModel modelotabla;
	private JComboBox<String> comboBoxDNI, comboBoxDNI2, comboBoxBuscar;
	private Historial historial ;
	Connection con = BD.initBD("BaseDatos.db");
	private JTextField textFieldDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHistorial2 frame = new VentanaHistorial2();
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
	public VentanaHistorial2() {
		//VentanaCrearCita v_cita = new VentanaCrearCita();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("Historial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblTituloHistorial = new JLabel("Historial");
		lblTituloHistorial.setForeground(SystemColor.text);
		lblTituloHistorial.setBackground(SystemColor.text);
		lblTituloHistorial.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloHistorial);

		JPanel panelEste = new JPanel();
		panelEste.setBackground(SystemColor.windowBorder);
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout());

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		panelEste.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		tabbedPane.addTab(" ", null, panelInicio, null);
		panelInicio.setLayout(new BorderLayout(0, 0));

		JLabel lblImagen = new JLabel("");
		lblImagen.setBackground(SystemColor.inactiveCaptionBorder);
		lblImagen.setIcon(new ImageIcon(VentanaHistorial2.class.getResource("/img/imagen2 (1).jpg")));
		panelInicio.add(lblImagen, BorderLayout.CENTER);

		//PESTAÑA INSERTAR
		JPanel panelInsertar = new JPanel();
		tabbedPane.addTab("INSERTAR", null, panelInsertar, null);
		panelInsertar.setLayout(new BorderLayout(0, 0));

		JPanel panelNorteI = new JPanel();
		panelNorteI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteI.setBackground(SystemColor.inactiveCaption);
		panelInsertar.add(panelNorteI, BorderLayout.NORTH);

		JLabel lblInsertarPaciente = new JLabel("INSERTAR HISTORIAL: ");
		lblInsertarPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteI.add(lblInsertarPaciente);

		JPanel panelSurI = new JPanel();
		panelSurI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurI.setBackground(SystemColor.inactiveCaption);
		panelInsertar.add(panelSurI, BorderLayout.SOUTH);

		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarHistorial();
				ArrayList<Historial> aHistoriales;
				aHistoriales = BD.obtenerListaHistorial(con);
				actualizartabla(tablahistorial, modelotabla, aHistoriales);
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
		gbl_pCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_pCentroI);

		JLabel lblNewLabelI = new JLabel(" ");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
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

		comboBoxDNI2 = new JComboBox();
		GridBagConstraints gbc_comboBoxDNI2 = new GridBagConstraints();
		gbc_comboBoxDNI2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDNI2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDNI2.gridx = 3;
		gbc_comboBoxDNI2.gridy = 1;
		panelCentroI.add(comboBoxDNI2, gbc_comboBoxDNI2);

		JLabel lblNombre = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 2;
		panelCentroI.add(lblNombre, gbc_lblNombre);
		
		comboBoxDNI2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String nombrePaciente = BD.buscarPacientePorDni(con, e.getItem().toString());
				if (nombrePaciente != null) {
					lblNombre.setText(nombrePaciente);
					//lblNombre2.setText(nombrePaciente);
				}
			}
		});


		JLabel lbDesc= new JLabel("DESCRIPCIÓN");
		GridBagConstraints gbc_lbDesc = new GridBagConstraints();
		gbc_lbDesc.anchor = GridBagConstraints.EAST;
		gbc_lbDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lbDesc.gridx = 2;
		gbc_lbDesc.gridy = 3;
		panelCentroI.add(lbDesc, gbc_lbDesc );
		
		textFieldDesc = new JTextField();
		GridBagConstraints gbc_textFieldDesc = new GridBagConstraints();
		gbc_textFieldDesc.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDesc.gridx = 3;
		gbc_textFieldDesc.gridy = 3;
		panelCentroI.add(textFieldDesc, gbc_textFieldDesc);
		textFieldDesc.setColumns(10);


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
				borrarHistorial();
				ArrayList<Historial> aHistoriales;
				aHistoriales = BD.obtenerListaHistorial(con);
				actualizartabla(tablahistorial, modelotabla, aHistoriales);
			}

			
		});
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurB.add(btnBorrar);

		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(SystemColor.inactiveCaption);
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);

		JLabel lblBorrarHistorial = new JLabel("BORRAR HISTORIAL: ");
		lblBorrarHistorial.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteB.add(lblBorrarHistorial);

		JPanel panelCentralBorrar = new JPanel();
		panelCentralBorrar.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentralBorrar, BorderLayout.CENTER);

		JLabel lblNewLabel_4 = new JLabel("Borrar la cita de: ");
		panelCentralBorrar.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));


		JLabel lblNombre2 = new JLabel(" ");
		panelCentralBorrar.add(lblNombre2);
		panelModificar.setLayout(new BorderLayout(0, 0));

		JPanel pNorteE = new JPanel();
		pNorteE.setBorder(new LineBorder(SystemColor.activeCaptionText));
		pNorteE.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pNorteE, BorderLayout.NORTH);
		pNorteE.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblTitulo = new JLabel("MODIFICAR HISTORIAL: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		pNorteE.add(lblTitulo);

		JPanel pSurE = new JPanel();
		pSurE.setBorder(new LineBorder(SystemColor.desktop, 1, true));
		pSurE.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pSurE, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("MODIFICAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Historial> aHistoriales;
				aHistoriales = BD.obtenerListaHistorial(con);
				actualizartabla(tablahistorial, modelotabla, aHistoriales);
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

		JLabel lblNombre1 = new JLabel("AQUI EL NOMBRE");
		lblNombre1.setForeground(new Color(0, 0, 0));
		lblNombre1.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNombre1 = new GridBagConstraints();
		gbc_lblNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre1.gridx = 5;
		gbc_lblNombre1.gridy = 1;
		pCentroE.add(lblNombre1, gbc_lblNombre1);

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
		comboBoxDNI.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {

			String nombrePaciente = BD.buscarPacientePorDni(con, e.getItem().toString());
			if (nombrePaciente != null) {
				lblNombre1.setText(nombrePaciente);
				lblNombre2.setText(nombrePaciente);
			}
		}
		});

		JLabel lblNewLabel_11 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 2;
		gbc_lblNewLabel_11.gridy = 4;
		pCentroE.add(lblNewLabel_11, gbc_lblNewLabel_11);



		/*
		 * AQUI ACABA
		 */
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		// Meter la conexón con la Base de Datos
		String[] columnas = {"DNI", "NOMBRE", "DESCRIPCION"};
		ArrayList<Historial> aHistoriales = BD.obtenerListaHistorial(con);

		modelotabla = new DefaultTableModel(columnas, 0);


		tablahistorial = new JTable(modelotabla);
		tablahistorial.setBounds(100, 100, 450, 300);
		
		Object fila[] = null;
		for (int i = 0; i < aHistoriales.size(); i++) {

			modelotabla.addRow(fila);
			Historial gethHistorial = (Historial) aHistoriales.get(i);
			modelotabla.setValueAt((gethHistorial.getDni()), i, 0);
			modelotabla.setValueAt(gethHistorial.getNombre(), i, 1);
			modelotabla.setValueAt(gethHistorial.getDesc(), i, 2);
		}
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFIltro = new JPanel();
		panelCentro.add(panelFIltro, BorderLayout.NORTH);
		
		JLabel lblFiltro = new JLabel("Buscar historial: ");
		panelFIltro.add(lblFiltro);
		
		comboBoxBuscar = new JComboBox();
		panelFIltro.add(comboBoxBuscar);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		panelFIltro.add(btnBuscar);
		// btnBuscar.setBorder(new RoundedBorder(20));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Historial> aHistoriales;
			
				String dni = comboBoxBuscar.getSelectedItem().toString();
				// controlar el filtro segun dentista
				if (comboBoxBuscar.getSelectedItem().toString() == "Todos") {
					aHistoriales = BD.obtenerListaHistorial(con);	
				} else {
					aHistoriales = BD.buscarHistorialPorDNI(con, dni);
				}

				actualizartabla(tablahistorial,modelotabla,aHistoriales);
			}

			
		});

		JScrollPane scrollTabla = new JScrollPane(tablahistorial);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCentro.add(scrollTabla, BorderLayout.CENTER);

		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER AL MENÚ");
		btnVolver.addActionListener(new ActionListener() {
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

		tablahistorial.getTableHeader().setReorderingAllowed(false);// que las columnas no se muevan

		tablahistorial.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("evento de raton");
				if (tablahistorial.getSelectedRow() != -1) {
					String dni = modelotabla.getValueAt(tablahistorial.getSelectedRow(), 0).toString();
					String nombre = modelotabla.getValueAt(tablahistorial.getSelectedRow(), 1).toString();
					String desc = modelotabla.getValueAt(tablahistorial.getSelectedRow(), 2).toString();
					
					historial = new Historial(dni, nombre, desc);
					lblNombre2.setText(nombre);
				}

			}
		});
	
		comboBoxDNI.enable(false);
		CargarComboBox();
	}

	private void CargarComboBox() {
		// TODO Auto-generated method stub
		ArrayList<Paciente> lista = new ArrayList<>();
		lista = BD.obtenerListaPaciente(con);
		for (Paciente p : lista) {
			comboBoxDNI.addItem(p.getDni());
			comboBoxDNI2.addItem(p.getDni());
			comboBoxBuscar.addItem(p.getDni());
		}
		comboBoxBuscar.addItem("Todos");
	}

	private void actualizartabla(JTable tablahistorial, DefaultTableModel modelo, ArrayList<Historial> aHistoriales) {
		Object fila[] = null;
		// limpiar el modelo y volver a escribir
		for (int i = 0; i < tablahistorial.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		// rellenar con la nueva busqueda-->filtro
		for (int i = 0; i < aHistoriales.size(); i++) {
			modelo.addRow(fila);
			Historial getHistorial = (Historial) aHistoriales.get(i);
			modelo.setValueAt((getHistorial.getDni()), i, 0);
			modelo.setValueAt(getHistorial.getNombre(), i, 1);
			modelo.setValueAt(getHistorial.getDesc(), i, 2);
		}
		
	}
	
	
	/*----------OPERACIONES----------*/

	private void borrarHistorial() {
		if(historial!=null) {
			BD.borrarHistorial(con, historial);
			JOptionPane.showMessageDialog(null, "SE HA BORRADO EL HISTORIAL",
					"BORRADO", JOptionPane.OK_OPTION);
		}else {
			JOptionPane.showMessageDialog(null, "SELECCIONE UN HISTORIAL",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	     
	private void insertarHistorial() {

		// comprobar campos no nulos
		if (!(textFieldDesc.getText().isEmpty())) {

			//Coger nombre del comboBox
			String nombrePaciente = BD.buscarPacientePorDni(con, comboBoxDNI2.getSelectedItem().toString());
			if (nombrePaciente != null) {

				// Crear historial
				Historial h = new Historial(nombrePaciente, nombrePaciente, nombrePaciente);
				h.setDni(comboBoxDNI2.getSelectedItem().toString());
				h.setNombre(nombrePaciente);
				h.setDesc(textFieldDesc.getText());
				
				BD.anadirHistorial(con, h);
			} else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN DNI VALIDO", "USUARIO NO ENCONTRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATO", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
}