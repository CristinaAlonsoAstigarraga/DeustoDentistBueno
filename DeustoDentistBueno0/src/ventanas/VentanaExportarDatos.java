package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BD.BD;
import Clases.Paciente;

import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaExportarDatos extends JFrame {

	private JPanel contentPane;
	private JComboBox <String>comboBoxDni;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private JCheckBox CheckBoxCabecera ;
	private JTextArea textObserv, textAreaObserH  ;
	
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaExportarDatos frame = new VentanaExportarDatos();
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
	 * @param user 
	 * @param rol 
	 */
	public VentanaExportarDatos(String rol, String user) {
		this.setResizable(false);//no agrandar pantalla
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("EXPORTAR DATOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(SystemColor.windowBorder);
		panelNorte.setForeground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("EXPORTAR DATOS");
		lblTituloGP.setForeground(Color.WHITE);
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(SystemColor.windowBorder);
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelCentro.add(tabbedPane);
		
		/* PANEL HISTORIAL*/
		JPanel panelHistorial = new JPanel();
		tabbedPane.addTab("HISTORIAL", null, panelHistorial, null);
		GridBagLayout gbl_panelHistorial = new GridBagLayout();
		gbl_panelHistorial.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelHistorial.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelHistorial.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelHistorial.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelHistorial.setLayout(gbl_panelHistorial);
		
		JLabel lbl1 = new JLabel("Generar fichero de historial;");
		lbl1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 7;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelHistorial.add(lbl1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("SELECCIONE PACIENTE :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panelHistorial.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("OBSERVACIONES:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.gridwidth = 3;
		gbc_lblNewLabel_11.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 4;
		gbc_lblNewLabel_11.gridy = 2;
		panelHistorial.add(lblNewLabel_1, gbc_lblNewLabel_11);
		
		comboBoxDni=new JComboBox();
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 3;
		panelHistorial.add(comboBoxDni, gbc_chckbxNewCheckBox);
		
		textAreaObserH = new JTextArea();
		textAreaObserH.setBorder(new LineBorder(SystemColor.activeCaptionText));
		textAreaObserH.setBackground( new Color(220,237,193));
		GridBagConstraints gbc_textAreaObserH = new GridBagConstraints();
		gbc_textAreaObserH.gridheight = 2;
		gbc_textAreaObserH.gridwidth = 5;
		gbc_textAreaObserH.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaObserH.fill = GridBagConstraints.BOTH;
		gbc_textAreaObserH.gridx = 4;
		gbc_textAreaObserH.gridy = 3;
		panelHistorial.add(textAreaObserH, gbc_textAreaObserH);
		
		JButton btnExpH = new JButton("EXPORTAR");
		btnExpH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarHistorial();
			}

			
		});
		btnExpH.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 7;
		panelHistorial.add(btnExpH, gbc_btnNewButton);
		
		JButton button = new JButton("MENU");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal(rol,user);
				vp.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 8;
		gbc_button.gridy = 7;
		panelHistorial.add(button, gbc_button);
		
		/* PANEL PACIENTES*/
		JPanel panelPacientee = new JPanel();
		tabbedPane.addTab("PACIENTES", null, panelPacientee, null);
		GridBagLayout gbl_panelPaciente = new GridBagLayout();
		gbl_panelPaciente.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPaciente.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPaciente.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPaciente.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPacientee.setLayout(gbl_panelPaciente);
		
		JLabel lblNewLabel_2 = new JLabel("Generar fichero de pacientes;");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 7;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		panelPacientee.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel1 = new JLabel("OPCIONES FICHERO:");
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.gridx = 1;
		gbc_lblNewLabel1.gridy = 2;
		panelPacientee.add(lblNewLabel1, gbc_lblNewLabel1);
		
		JLabel lblNewLabel_11 = new JLabel("OBSERVACIONES:");
		GridBagConstraints gbc_lblNewLabel_111 = new GridBagConstraints();
		gbc_lblNewLabel_111.gridwidth = 3;
		gbc_lblNewLabel_111.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_111.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_111.gridx = 4;
		gbc_lblNewLabel_111.gridy = 2;
		panelPacientee.add(lblNewLabel_11, gbc_lblNewLabel_111);
		
		CheckBoxCabecera = new JCheckBox("CON CABECERA");
		GridBagConstraints gbc_CheckBoxCabecera = new GridBagConstraints();
		gbc_CheckBoxCabecera.insets = new Insets(0, 0, 5, 5);
		gbc_CheckBoxCabecera.gridx = 1;
		gbc_CheckBoxCabecera.gridy = 3;
		panelPacientee.add(CheckBoxCabecera, gbc_CheckBoxCabecera);
		
	    textObserv = new JTextArea();
		textObserv.setBorder(new LineBorder(SystemColor.activeCaptionText));
		textObserv.setBackground(new Color(255,211,182));
		GridBagConstraints gbc_textObserv = new GridBagConstraints();
		gbc_textObserv.gridheight = 2;
		gbc_textObserv.gridwidth = 5;
		gbc_textObserv.insets = new Insets(0, 0, 5, 5);
		gbc_textObserv.fill = GridBagConstraints.BOTH;
		gbc_textObserv.gridx = 4;
		gbc_textObserv.gridy = 3;
		panelPacientee.add(textObserv, gbc_textObserv);
		
		JButton btnExportarP = new JButton("EXPORTAR");
		btnExportarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarFicheroP();
			}

		});
		btnExportarP.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnExportarP = new GridBagConstraints();
		gbc_btnExportarP.insets = new Insets(0, 0, 0, 5);
		gbc_btnExportarP.gridx = 7;
		gbc_btnExportarP.gridy = 7;
		panelPacientee.add(btnExportarP, gbc_btnExportarP);
		
		
	tabbedPane.setBackgroundAt(0, new Color(220,237,193));
	tabbedPane.setBackgroundAt(1, new Color(255,211,182));
	
	CargarComboBoxDni();
	}
	private void CargarComboBoxDni() {
		// TODO Auto-generated method stub
		ArrayList<Paciente> lista = new ArrayList<>();
		lista = BD.obtenerListaPaciente(con);
		for (Paciente p : lista) {
			comboBoxDni.addItem(p.getDni());
			
		}
	}
	private void exportarHistorial() {
		JFileChooser guardar=new JFileChooser();
		guardar.setApproveButtonText("Guardar");
		guardar.showSaveDialog(null);
		File fichero=new File(guardar.getSelectedFile()+".csv");
		
		
		String dni=comboBoxDni.getSelectedItem().toString();
		String des=BD.buscarHistorial(con,dni);
		
		
		try(PrintWriter pw=new PrintWriter(fichero);) {
			//escribir cabecera
			pw.println("DESCRIPCION HISTORIAL DEL PACIENTE: "+dni);
			pw.println(des);
				
			
			pw.println("");
			
			pw.println("OBSERVACIONES: "+textAreaObserH.getText());
			
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		
	}

	private void exportarFicheroP() {
		
		JFileChooser guardar=new JFileChooser();
		guardar.setApproveButtonText("Guardar");
		guardar.showSaveDialog(null);
		File fichero=new File(guardar.getSelectedFile()+".csv");
		
		
		try(PrintWriter pw=new PrintWriter(fichero)) {
			
			ArrayList<Paciente> lista= BD.obtenerListaPaciente(con);
			pw.println("FICHERO PACIENTES GENERADO DE FORMA AUTOMATICA");
			pw.println("");
			
			//comprobar si se ha selecionado el  checkbox
			if(CheckBoxCabecera.isSelected()) {
				pw.println("NOMBRE;APELLIDO;FECHA NAC;DIRECCION;TELEFONO;GENERO");
			}//fin if
			
			for(int i=0;i<lista.size();i++) {
				Paciente p=lista.get(i);
				pw.println(p.getNombre()+";"+p.getApellido()+";"+sdf.format( p.getFechaNacimiento())+";"+p.getDireccion()+";"+p.getTelefono()+";"+p.getGenero());
				
			}
			
			pw.println("");
			
			pw.println("OBSERVACIONES: "+textObserv.getText());
			
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
	}
}

