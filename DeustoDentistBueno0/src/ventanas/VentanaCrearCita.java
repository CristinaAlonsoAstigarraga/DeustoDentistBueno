package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.BD;
import Clases.Cita;
import Clases.Dentista;
import Clases.TipoCita;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaCrearCita extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDni;
	private JTextField textFieldFecha;
	private JComboBox<String> comboBoxCitas;
	private JComboBox<String> comboBoxDentista;
	private Connection con;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	private Date fecha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrearCita frame = new VentanaCrearCita();
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
	public VentanaCrearCita() {
		 con = BD.initBD("BaseDatos.db");//CONEXION A BASE DE DATOS MODIFICAR
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pNorte = new JPanel();
		pNorte.setBackground(new Color(176, 196, 222));
		panel.add(pNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("AÑADIR PACIENTE: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		pNorte.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("AQUI EL NOMBRE");
		pNorte.add(lblNombre);
		
		JPanel pSur = new JPanel();
		pSur.setBackground(new Color(176, 196, 222));
		panel.add(pSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarCita();
				
			}

			
		});
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pSur.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pSur.add(btnCancelar);
		
		JPanel pCentral = new JPanel();
		panel.add(pCentral, BorderLayout.CENTER);
		GridBagLayout gbl_pCentral = new GridBagLayout();
		gbl_pCentral.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_pCentral.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_pCentral.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_pCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pCentral.setLayout(gbl_pCentral);
		
		JLabel lblNewLabel_2 = new JLabel("                       .");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		pCentral.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("DNI");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		pCentral.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldDni = new JTextField();
		textFieldDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombrePaciente=BD.buscarPacientePorDni(con, textFieldDni.getText());
				if(nombrePaciente!=null) {
					lblNombre.setText(nombrePaciente);
				}
			}
		});
		GridBagConstraints gbc_textFieldDni = new GridBagConstraints();
		gbc_textFieldDni.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDni.gridx = 2;
		gbc_textFieldDni.gridy = 1;
		pCentral.add(textFieldDni, gbc_textFieldDni);
		textFieldDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("FECHA Y HORA");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		pCentral.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textFieldFecha = new JTextField();
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 2;
		gbc_textFieldFecha.gridy = 2;
		pCentral.add(textFieldFecha, gbc_textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lblTipoCita = new JLabel("TIPO CITA");
		GridBagConstraints gbc_lblTipoCita = new GridBagConstraints();
		gbc_lblTipoCita.anchor = GridBagConstraints.EAST;
		gbc_lblTipoCita.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoCita.gridx = 1;
		gbc_lblTipoCita.gridy = 3;
		pCentral.add(lblTipoCita, gbc_lblTipoCita);
		
		 comboBoxCitas = new JComboBox();
		GridBagConstraints gbc_comboBoxCitas = new GridBagConstraints();
		gbc_comboBoxCitas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCitas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCitas.gridx = 2;
		gbc_comboBoxCitas.gridy = 3;
		pCentral.add(comboBoxCitas, gbc_comboBoxCitas);
		
		JLabel lblNombreD = new JLabel("DENTISTA");
		GridBagConstraints gbc_lblNombreD = new GridBagConstraints();
		gbc_lblNombreD.anchor = GridBagConstraints.EAST;
		gbc_lblNombreD.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreD.gridx = 1;
		gbc_lblNombreD.gridy = 4;
		pCentral.add(lblNombreD, gbc_lblNombreD);
		
		 comboBoxDentista = new JComboBox();
		GridBagConstraints gbc_comboBoxDentista = new GridBagConstraints();
		gbc_comboBoxDentista.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDentista.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDentista.gridx = 2;
		gbc_comboBoxDentista.gridy = 4;
		pCentral.add(comboBoxDentista, gbc_comboBoxDentista);
		CargarComboBoxCita();
		CargarComBoxDentista();
	}

	private void CargarComBoxDentista() {
		// TODO Auto-generated method stub
		ArrayList<Dentista> lista = new ArrayList<>();
		lista=BD.obtenerListaDentista(con);
		
		for(Dentista d: lista) {
			comboBoxDentista.addItem(d.getNombre());
		}
	}

	private void CargarComboBoxCita() {
		// TODO Auto-generated method stub
		for(TipoCita n:TipoCita.values()) {
			comboBoxCitas.addItem(n.name());
		}
			
	}
	
	//METODOS BOTONES
	private void insertarCita() {
		
		
			
				//comprobar campos no nulos
			if(!(textFieldDni.getText().isEmpty()|| textFieldFecha.getText().isEmpty())) {
				
				//comprobar dni
				String nombrePaciente=BD.buscarPacientePorDni(con, textFieldDni.getText());
				if(nombrePaciente!=null) {
					
					//comprobar formato fecha
					try {
						fecha = sdf.parse(textFieldFecha.getText());
						System.out.println(sdf.format(fecha));
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR LA FECHA EN FORMATO: \"dd-MM-yyyy hh:mm\"",
				                "FORMATO ERRONEO", JOptionPane.ERROR_MESSAGE);
					}
	
					//crear cita
					Cita c=new Cita();
					c.setDniPaciente(textFieldDni.getText());
					c.setNombrePaciente(nombrePaciente);
					c.setFecha(fecha);
					c.setTipo(TipoCita.valueOf(comboBoxCitas.getSelectedItem().toString())); 
					c.setNombreDentista((String) comboBoxDentista.getSelectedItem());
					BD.anadirCita(con,c);
				}else {
					JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN DNI VALIDO",
			                "USUARIO NO ENCONTRADO", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS DATO",
		                "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		
	}

}
