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
import Clases.Historial;
import Clases.Producto;

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
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

public class VentanaInventarioFinal extends JFrame{
	
	private static Logger logger = Logger.getLogger(VentanaInventarioFinal.class.getName());

	private JPanel contentPane;
	private JTable tablaGestionProductos;
	private JTextField textFieldCodigoInsertar, textFieldNombreInsertar, textFieldDescripcionInsertar, textFieldPrecioInsertar, textFieldCantidadInsertar,
	textFieldNombreModificar, textFieldDescripcionModificar, textFieldPrecioModificar, textFieldBuscar;
	private JComboBox<Integer> comboBoxCodigos, comboBoxCodigoModificar;
	private JLabel lblNombreProdcuto;
	private DefaultTableModel modelo;
	private Producto producto;
	private boolean cambio;
//	private ArrayList<Producto> productos;
	
	Connection con = BD.initBD("BaseDatos.db");
	private JTextField textFieldCantidadModificar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInventarioFinal frame = new VentanaInventarioFinal();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param user 
	 * @param rol 
	 */
	public VentanaInventarioFinal(String rol, String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInventarioFinal.class.getResource("/img/dienteNegro.jpg")));
		setTitle("GESTIÓN INVENTARIO");
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
		
		JLabel lblTituloGP = new JLabel("INVENTARIO");
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
		lblImagen.setIcon(new ImageIcon(VentanaInventarioFinal.class.getResource("/img/imagen2 (1).jpg")));
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
		
		JLabel lblInsertarPaciente = new JLabel("INSERTAR PRODUCTO: ");
		lblInsertarPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteI.add(lblInsertarPaciente);
		
		JPanel panelSurI = new JPanel();
		panelSurI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurI.setBackground(SystemColor.inactiveCaption);
		panelInsertar.add(panelSurI, BorderLayout.SOUTH);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarProducto();
				textFieldCodigoInsertar.setText("");
				textFieldNombreInsertar.setText("");
				textFieldDescripcionInsertar.setText("");
				textFieldPrecioInsertar.setText("");
				textFieldCantidadInsertar.setText("");
				ArrayList<Producto> aProductos;
				aProductos = BD.obtenerListaProducto(con);
				actualizarTabla(tablaGestionProductos, modelo, aProductos);
				//Además de actualizar la tabla, actualizamos el JComboBox
				cargarCB_Codigo_Modificar();
			}
		});
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurI.add(btnInsertar);
		
		JPanel panelCentroI = new JPanel();
		panelCentroI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelInsertar.add(panelCentroI, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroI = new GridBagLayout();
		gbl_pCentroI.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_pCentroI);
		
		JLabel lblNewLabelI = new JLabel(".");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		panelCentroI.add(lblNewLabelI, gbc_lblNewLabel);
		
		JLabel lblCodigo = new JLabel("CÓDIGO");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.gridx = 2;
		gbc_lblCodigo.gridy = 1;
		panelCentroI.add(lblCodigo, gbc_lblCodigo);
		
		textFieldCodigoInsertar = new JTextField();
		GridBagConstraints gbc_textFieldCodigoInsertar = new GridBagConstraints();
		gbc_textFieldCodigoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodigoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodigoInsertar.gridx = 3;
		gbc_textFieldCodigoInsertar.gridy = 1;
		panelCentroI.add(textFieldCodigoInsertar, gbc_textFieldCodigoInsertar);
		textFieldCodigoInsertar.setColumns(10);
		
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
		
		JLabel lblDescripcion = new JLabel("DESCRIPCIÓN");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 3;
		panelCentroI.add(lblDescripcion, gbc_lblDescripcion);
		
		textFieldDescripcionInsertar = new JTextField();
		GridBagConstraints gbc_textFieldDescripcionInsertar = new GridBagConstraints();
		gbc_textFieldDescripcionInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcionInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionInsertar.gridx = 3;
		gbc_textFieldDescripcionInsertar.gridy = 3;
		panelCentroI.add(textFieldDescripcionInsertar, gbc_textFieldDescripcionInsertar);
		textFieldDescripcionInsertar.setColumns(10);
			
		JLabel lblPrecio = new JLabel("PRECIO");
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.anchor = GridBagConstraints.EAST;
		gbc_lblPrecio.gridx = 2;
		gbc_lblPrecio.gridy = 4;
		panelCentroI.add(lblPrecio, gbc_lblPrecio);
			
		textFieldPrecioInsertar = new JTextField();
		GridBagConstraints gbc_textFieldPrecioInsertar = new GridBagConstraints();
		gbc_textFieldPrecioInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrecioInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrecioInsertar.gridx = 3;
		gbc_textFieldPrecioInsertar.gridy = 4;
		panelCentroI.add(textFieldPrecioInsertar, gbc_textFieldPrecioInsertar);
		textFieldPrecioInsertar.setColumns(10);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.anchor = GridBagConstraints.EAST;
		gbc_lblCantidad.gridx = 2;
		gbc_lblCantidad.gridy = 5;
		panelCentroI.add(lblCantidad, gbc_lblCantidad);
		
		textFieldCantidadInsertar = new JTextField();
		GridBagConstraints gbc_textFieldCantidadInsertar = new GridBagConstraints();
		gbc_textFieldCantidadInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidadInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadInsertar.gridx = 3;
		gbc_textFieldCantidadInsertar.gridy = 5;
		panelCentroI.add(textFieldCantidadInsertar, gbc_textFieldCantidadInsertar);
		textFieldCantidadInsertar.setColumns(10);
		
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
		
		JLabel lblTitulo = new JLabel("MODIFICAR PRODUCTO: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		pNorteM.add(lblTitulo);

		JPanel pSurM = new JPanel();
		pSurM.setBorder(new LineBorder(SystemColor.desktop, 1, true));
		pSurM.setBackground(SystemColor.inactiveCaption);
		panelModificar.add(pSurM, BorderLayout.SOUTH);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarProducto();
				ArrayList<Producto> aProductos;
				aProductos = BD.obtenerListaProducto(con);
				actualizarTabla(tablaGestionProductos, modelo, aProductos);
				textFieldNombreModificar.setText("");
				textFieldDescripcionModificar.setText("");
				textFieldPrecioModificar.setText("");
				textFieldCantidadModificar.setText("");
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
		
		
		JLabel lblCodigoM = new JLabel("CÓDIGO");
		GridBagConstraints gbc_lblCodigoM = new GridBagConstraints();
		gbc_lblCodigoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoM.anchor = GridBagConstraints.EAST;
		gbc_lblCodigoM.gridx = 3;
		gbc_lblCodigoM.gridy = 1;
		pCentroM.add(lblCodigoM, gbc_lblCodigoM);
		
		comboBoxCodigoModificar = new JComboBox<Integer>();
		cargarCB_Codigo_Modificar();
		GridBagConstraints gbc_comboBoxCodigoModificar = new GridBagConstraints();
		gbc_comboBoxCodigoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCodigoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCodigoModificar.gridx = 5;
		gbc_comboBoxCodigoModificar.gridy = 1;
		pCentroM.add(comboBoxCodigoModificar, gbc_comboBoxCodigoModificar);
			
		JLabel lblNombreM = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreM = new GridBagConstraints();
		gbc_lblNombreM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreM.anchor = GridBagConstraints.EAST;
		gbc_lblNombreM.gridx = 3;
		gbc_lblNombreM.gridy = 2;
		pCentroM.add(lblNombreM, gbc_lblNombreM);
		
		textFieldNombreModificar = new JTextField();
		GridBagConstraints gbc_textFieldNombreModificar = new GridBagConstraints();
		gbc_textFieldNombreModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreModificar.gridx = 5;
		gbc_textFieldNombreModificar.gridy = 2;
		pCentroM.add(textFieldNombreModificar, gbc_textFieldNombreModificar);
		textFieldNombreModificar.setColumns(10);
		
		JLabel lblDescripcionM = new JLabel("DESCRIPCIÓN");
		GridBagConstraints gbc_lblDescripcionM = new GridBagConstraints();
		gbc_lblDescripcionM.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionM.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionM.gridx = 3;
		gbc_lblDescripcionM.gridy = 3;
		pCentroM.add(lblDescripcionM, gbc_lblDescripcionM);
		
		textFieldDescripcionModificar = new JTextField();
		GridBagConstraints gbc_textFieldDescripcionModificar = new GridBagConstraints();
		gbc_textFieldDescripcionModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcionModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionModificar.gridx = 5;
		gbc_textFieldDescripcionModificar.gridy = 3;
		pCentroM.add(textFieldDescripcionModificar, gbc_textFieldDescripcionModificar);
		textFieldDescripcionModificar.setColumns(10);
		
		JLabel lblPacienteM = new JLabel("");
		GridBagConstraints gbc_lblPacienteM = new GridBagConstraints();
		gbc_lblPacienteM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPacienteM.gridx = 2;
		gbc_lblPacienteM.gridy = 4;
		pCentroM.add(lblPacienteM, gbc_lblPacienteM);
		
		JLabel lblPrecioM = new JLabel("PRECIO");
		GridBagConstraints gbc_lblPrecioM = new GridBagConstraints();
		gbc_lblPrecioM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioM.anchor = GridBagConstraints.EAST;
		gbc_lblPrecioM.gridx = 3;
		gbc_lblPrecioM.gridy = 4;
		pCentroM.add(lblPrecioM, gbc_lblPrecioM);
		
		textFieldPrecioModificar = new JTextField();
		GridBagConstraints gbc_textFieldPrecioModificar = new GridBagConstraints();
		gbc_textFieldPrecioModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrecioModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrecioModificar.gridx = 5;
		gbc_textFieldPrecioModificar.gridy = 4;
		pCentroM.add(textFieldPrecioModificar, gbc_textFieldPrecioModificar);
		textFieldPrecioModificar.setColumns(10);
		
		JLabel lblCantidadM = new JLabel("CANTIDAD");
		GridBagConstraints gbc_lblCantidadM = new GridBagConstraints();
		gbc_lblCantidadM.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadM.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadM.gridx = 3;
		gbc_lblCantidadM.gridy = 5;
		pCentroM.add(lblCantidadM, gbc_lblCantidadM);
		
		textFieldCantidadModificar = new JTextField();
		GridBagConstraints gbc_textFieldCantidadModificar = new GridBagConstraints();
		gbc_textFieldCantidadModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidadModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadModificar.gridx = 5;
		gbc_textFieldCantidadModificar.gridy = 5;
		pCentroM.add(textFieldCantidadModificar, gbc_textFieldCantidadModificar);
		textFieldCantidadModificar.setColumns(10);
		
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
				borrarProducto();
				ArrayList<Producto> aProductos;
				aProductos = BD.obtenerListaProducto(con);
				actualizarTabla(tablaGestionProductos, modelo, aProductos);
				//Actualizamos el comboBox de insertar también
				cargarCB_Codigo_Modificar();
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurB.add(btnBorrar);
		
		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(SystemColor.inactiveCaption);
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);
		
		JLabel lblBorrarPaciente = new JLabel("BORRAR PRODUCTO: ");
		lblBorrarPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteB.add(lblBorrarPaciente);
		
		JPanel panelCentralBorrar = new JPanel();
		panelCentralBorrar.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentralBorrar, BorderLayout.CENTER);
		
		JLabel lblBorrar = new JLabel("Borrar al producto: ");
		panelCentralBorrar.add(lblBorrar);
		lblBorrar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		lblNombreProdcuto = new JLabel("");
		panelCentralBorrar.add(lblNombreProdcuto);
		
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
		String [] columnas = {"CÓDIGO", "NOMBRE", "DESCRIPCIÓN", "PRECIO", "CANTIDAD"};
		ArrayList<Producto> aProductos = BD.obtenerListaProducto(con);
		
		modelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		Collections.sort(aProductos, new Comparator<Producto>() {
			@Override
			public int compare(Producto o1, Producto o2) {
				// TODO Auto-generated method stub
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		
		tablaGestionProductos = new JTable(modelo);
		tablaGestionProductos.setBounds(100, 100, 450, 300);

		Object O [] = null;
		for (int i = 0; i < aProductos.size(); i++) {
			
			modelo.addRow(O);
			Producto getProducto = (Producto) aProductos.get(i);
			modelo.setValueAt(getProducto.getCodigo(), i, 0);
			modelo.setValueAt(getProducto.getNombre(), i, 1);
			modelo.setValueAt(getProducto.getDescripcion(), i, 2);
			modelo.setValueAt(getProducto.getPrecio(), i, 3);
			modelo.setValueAt(getProducto.getCantidad(), i, 4);
		}
		
		/*
		 * PANEL BUSCAR
		 */
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(SystemColor.inactiveCaption);
		panelCentro.add(panelBuscar, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Buscar por CÓDIGO de producto: ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblNewLabel_2);
		
		textFieldBuscar = new JTextField(20);
		panelBuscar.add(textFieldBuscar);
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionProductos);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCentro.add(scrollTabla, BorderLayout.CENTER);
		
		TableCellRenderer renderer = (table, value, selected, focus, row, column) ->{
			JLabel label = new JLabel(value.toString());
			
			if(!textFieldBuscar.getText().isBlank() && table.getValueAt(row, 0).toString().contains(textFieldBuscar.getText())) {
				Color colorAzul = new Color(62, 185, 230);
				label.setBackground(colorAzul);
			}
			
			if(column == 4) {
				int cantidad = (int) modelo.getValueAt(row, 4);
				if(cantidad < 16) {
					label.setForeground(Color.RED);
				} else if (cantidad < 50) {
					Color colorNaranja = new Color(242, 195, 19);
					label.setForeground(colorNaranja);
				} else {
					Color colorVerde = new Color(50, 162, 41);
					label.setForeground(colorVerde);
				}
			} 
			
			if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4) {
				label.setHorizontalAlignment(JLabel.CENTER);
			}
			
			label.setOpaque(true);
			
			return label;
		};
		
		tablaGestionProductos.setDefaultRenderer(Object.class, renderer);
		
		textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();				
			}
		});
		
		/*
		 * PANEL CALCULAR
		 */
		
		JPanel panelCalcular = new JPanel();
		tabbedPane.addTab("STOCK", null, panelCalcular, null);
		panelCalcular.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSurC = new JPanel();
		panelSurB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurB.setBackground(SystemColor.inactiveCaption);
		panelCalcular.add(panelSurC, BorderLayout.SOUTH);
		
		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurC.add(btnCalcular);
		
		JPanel panelNorteC = new JPanel();
		panelNorteC.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteC.setBackground(SystemColor.inactiveCaption);
		panelCalcular.add(panelNorteC, BorderLayout.NORTH);
		
		JLabel lblCalcularStock = new JLabel("POSIBLES COMPRAS:");
		lblCalcularStock.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorteC.add(lblCalcularStock);
		
		JPanel panelCentralCalcular = new JPanel();
		panelCentralCalcular.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelCalcular.add(panelCentralCalcular, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentralCalcular = new GridBagLayout();
		gbl_panelCentralCalcular.columnWidths = new int[]{0, 96, 0, 0};
		gbl_panelCentralCalcular.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCentralCalcular.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentralCalcular.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentralCalcular.setLayout(gbl_panelCentralCalcular);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelCentralCalcular.add(scrollPane, gbc_scrollPane);
		
		JTextArea textAreaResultado = new JTextArea();
		scrollPane.setViewportView(textAreaResultado);
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Producto> aProductos;
////				ArrayList<Producto> aayuda = new ArrayList<>();
				aProductos = BD.obtenerListaProducto(con);

				Double disponible = Double.parseDouble(JOptionPane.showInputDialog("Dinero disponible: "));
				Double sobranteMax = Double.parseDouble(JOptionPane.showInputDialog("Dinero sobrante máximo: "));
			
				if(disponible != null && sobranteMax != null) {
					if(disponible>sobranteMax) {
						List<Producto> result = combinacionesProductos(aProductos, disponible, sobranteMax);
						
						for (int i=0; i<result.size(); i++) {
							textAreaResultado.append("Opción nº " + i);
							textAreaResultado.append(": "+result.get(i) + "\n");
					
						}
					} else {
						JOptionPane.showMessageDialog(null, "El valor sobrante, debe ser menor al disponible", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Debe rellenar los campos (double)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
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
				VentanaPrincipal vp = new VentanaPrincipal(rol,user);
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
		
		tablaGestionProductos.getTableHeader().setReorderingAllowed(false);
		tablaGestionProductos.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
					if (tablaGestionProductos.getSelectedRow() != -1) {
						int cod_p = Integer.parseInt(modelo.getValueAt(tablaGestionProductos.getSelectedRow(), 0).toString());
						String nombre = modelo.getValueAt(tablaGestionProductos.getSelectedRow(), 1).toString();
						String desc = modelo.getValueAt(tablaGestionProductos.getSelectedRow(), 2).toString();
						float precio = Float.parseFloat(modelo.getValueAt(tablaGestionProductos.getSelectedRow(), 3).toString());
						int cantidad = Integer.parseInt(modelo.getValueAt(tablaGestionProductos.getSelectedRow(), 4).toString());
						
						producto = new Producto(cod_p, nombre, desc, precio, cantidad);
						lblNombreProdcuto.setText(nombre);
					}
			}
		});
	}
	
	
	/**
	 * Método para cargar el comboBox en la pestaña de modificar un producto. El comboBox se carga con los códigos de producto disponibles
	 */
	private void cargarCB_Codigo_Modificar() {
		ArrayList<Producto> lista = new ArrayList<>();
		lista = BD.obtenerListaProducto(con);
		for(Producto p : lista) {
			comboBoxCodigoModificar.addItem(p.getCodigo());
		}
	}

	/**
	 * Método para actualizar la tabla trás haberse realizado algún cambio (insertar, borrar, modificar)
	 * @param tablaGestionProductos - tabla que se va a actualizar
	 * @param modelo - modelo de la tabla
	 * @param aProductos - lista de los productos que hay en la tabla
	 */
	private void actualizarTabla(JTable tablaGestionProductos, DefaultTableModel modelo, ArrayList<Producto> aProductos) {
		Object O[] = null;
		// limpiar el modelo y volver a escribir
		for (int i = 0; i < tablaGestionProductos.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		for(int i = 0; i<aProductos.size(); i++) {
			modelo.addRow(O);
			Producto getProducto = (Producto) aProductos.get(i);
			modelo.setValueAt((getProducto.getCodigo()), i, 0);
			modelo.setValueAt((getProducto.getNombre()), i, 1);
			modelo.setValueAt((getProducto.getDescripcion()), i, 2);
			modelo.setValueAt((getProducto.getPrecio()), i, 3);
			modelo.setValueAt((getProducto.getCantidad()), i, 4);
		}
	}
	
	
	/**
	 * Método para insertar un producto en la tabla
	 */
	private void insertarProducto() {
		//Creamos expreciones regulares para validar los datos(código, precio y cantidad) introducidos
		
		//Código
		String erCod = "[0-9]{5}";
		String codigo = textFieldCodigoInsertar.getText();
			
		//Precio
		String erPrecio = "[0-9]{1,}.[0-9]{1,2}";
		String erPrecio1 = "[0-9]{1,}";
		String precio = textFieldPrecioInsertar.getText();
		
		//Cantidad
		String erCantidad = "[0-9]{1,7}";
		String cantidad = textFieldCantidadInsertar.getText();
		
		if (!(textFieldCodigoInsertar.getText().isEmpty()) || !(textFieldNombreInsertar.getText().isEmpty()) || !(textFieldDescripcionInsertar.getText().isEmpty())
				|| !(textFieldPrecioInsertar.getText().isEmpty()) || !(textFieldCantidadInsertar.getText().isEmpty())) {
			
			if(Pattern.matches(erCod, codigo)) {
				if((Pattern.matches(erPrecio, precio))||(Pattern.matches(erPrecio1, precio))) {
					if(Pattern.matches(erCantidad, cantidad)) {
						int codigoBueno = Integer.parseInt(codigo);
						String nombre = textFieldNombreInsertar.getText();
						String descripcion = textFieldDescripcionInsertar.getText();
						float precioBueno = Float.parseFloat(precio);
						int cantidadBuena = Integer.parseInt(cantidad);
						BD.anadirProducto(con, codigoBueno, nombre, descripcion, precioBueno, cantidadBuena);
					} else {
						JOptionPane.showMessageDialog(null, "El formato de la cantidad introducida no es correcta. Debe introducir un número entero (p.e: 17)", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El formato del precio no es correcto. Debe introducir dos decimales y tener en cuenta el '.' (p.e: 12.35)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El formato del código no es correcto. Debe introducir cinco números (p.e: 12345)", "ERROR", JOptionPane.ERROR_MESSAGE);
			}		
		}else {
			JOptionPane.showMessageDialog(null, "DEBES RELLENAR TODOS LOS CAMPOS",
					"FORMATO ERRONEO", JOptionPane.ERROR_MESSAGE);		}
	}
	
	/**
	 * Método para modificar un producto de la tabla
	 */
	private void modificarProducto() {
		if (!(textFieldNombreModificar.getText().isEmpty()) || !(textFieldDescripcionModificar.getText().isEmpty()) ||
			!(textFieldPrecioModificar.getText().isEmpty()) || !(textFieldCantidadModificar.getText().isEmpty())) {
			
			
			String nombreProducto = BD.buscarProducto(con, (int) comboBoxCodigoModificar.getSelectedItem());
			if(nombreProducto != null) {
				int cod_p = (int) comboBoxCodigoModificar.getSelectedItem();
				String nom = textFieldNombreModificar.getText();
				String desc = textFieldDescripcionModificar.getText();
				Float precio = Float.parseFloat(textFieldPrecioModificar.getText());
				int cantidad = Integer.parseInt(textFieldCantidadModificar.getText());
				
				BD.modificarTuplaProducto(con, cod_p, nom, desc, precio, cantidad);
			}else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN CÓDIGO VALIDO", "PRODUCTO NO ENCONTRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	/**
	 * Método para borrar un prodcuto de la tabla
	 */
	private void borrarProducto() {
		if(producto != null) {
			BD.borrarProducto(con, producto);
			JOptionPane.showMessageDialog(null, "SE HA BORRADO EL PRODUCTO",
					"BORRADO", JOptionPane.OK_OPTION);
		}else {
			JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para generar posibles compras de productos
	 * @param resultado - ArrayList<Producto> con el resultado de posibles opciones de compra
	 * @param elementos - ArrayList<Producto> con los elementos que se van a utilizar para generar las combinaciones
	 * @param DineroDisponible - Dinero que hay disponible para hacer las compras
	 * @param DineroSobreanteMax - Cuánto dinero puede sobrar como máximo después de hacer una compra
	 * @param provisional - List<Producto> que ayuda en el proceso de generación de combinaciones
	 */
	private static void combinacionesProductos(ArrayList<Producto> resultado,
												ArrayList<Producto> elementos, 
												double DineroDisponible, 
												double DineroSobreanteMax, 
												List<Producto> provisional) {
		if (DineroDisponible < 0) {
			return;
		} else if (DineroDisponible < DineroSobreanteMax) {
			provisional.sort((Producto p1, Producto p2) -> Integer.compare(p1.getCodigo(), p2.getCodigo()));
			if (!resultado.contains(provisional)) {
				resultado.addAll(new ArrayList<>(provisional));
			}			
		} else {
			for (Producto p : elementos) {
				provisional.add(p);
				combinacionesProductos(resultado, elementos, DineroDisponible-p.getPrecio(), DineroSobreanteMax, provisional);
				provisional.remove(provisional.size()-1);
			}
		}
	
	}

	/**
	 * Método para llamar a combinacionesProductos() y generar las combinaciones
	 * @param elementos - ArrayList<Producto>, lista con los Productos que se van a utilizar para generar las combinaciones
	 * @param DineroDisponible - Dinero que hay disponible para hacer las compras
	 * @param DineroSobrante - Cuánto dinero puede sobrar como máximo después de hacer una compra
	 * @return ArrayList<Producto>, devuelve un arrayList con las posibles compras a realizar con los valores que se han pasado como parámetro
	 */
	public static ArrayList<Producto> combinacionesProductos(ArrayList<Producto> elementos,
																double DineroDisponible,
																double DineroSobrante){
		ArrayList<Producto> resultado = new ArrayList<>();
		combinacionesProductos(resultado, elementos, DineroDisponible, DineroSobrante, new ArrayList<>());
		return resultado;		
	}

	
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by product containing: " + selectStr);
	}
}