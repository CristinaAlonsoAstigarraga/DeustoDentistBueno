package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JSeparator;
import javax.swing.JSlider;
import java.awt.SystemColor;

public class VentanaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable tablaGestionAgenda;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	
	private JComboBox<String> comboBoxCITA;
	private JComboBox<String> comboBoxDENTISTA;
	private JComboBox<String> comboBoxDNI;
	private Date fecha;
	
	Connection con = BD.initBD("BaseDatos.db");
	private JTextField textFieldFecha;

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
		setBounds(100, 100, 848, 460);
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
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		//Meter la conexón con la Base de Datos
		String [] columnas = {"ID", "DNI", "PACIENTE", "FECHA", "TIPO", "DENTISTA",};
		ArrayList<Cita> aCitas = BD.obtenerListaCitas(con); 
		DefaultTableModel modelo = new DefaultTableModel(columnas, 3);
		
		Collections.sort(aCitas, new Comparator<Cita>() {
		    @Override
		    public int compare(Cita o1, Cita o2) {
				// TODO Auto-generated method stub
				return o1.getFecha().compareTo(o2.getFecha());
			}
		});
		for(Cita c: aCitas) {
			System.out.println(c.getFecha()+"\n");
		}
		Object O [] = null;
		for (int i = 0; i < aCitas.size(); i++) {
			modelo.addRow(O);
			Cita getCita = (Cita) aCitas.get(i);
			modelo.setValueAt(i, i, 0);
			modelo.setValueAt(getCita.getDniPaciente(), i, 1);
			modelo.setValueAt(getCita.getNombrePaciente(), i, 2);
			modelo.setValueAt(sdf.format(getCita.getFecha()), i, 3);
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
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnBuscar = new JButton("BUSCAR");
		//btnBuscar.setBorder(new RoundedBorder(20));
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
						
						comboBoxDNI.setSelectedItem(cita.getDniPaciente());
						textFieldFecha.setText(sdf.format(cita.getFecha()).toString());
						comboBoxCITA.setSelectedItem(cita.getTipo());
						comboBoxDENTISTA.setSelectedItem(cita.getNombreDentista());
						
						//VentanaModificarCita c=new VentanaModificarCita(cita);
					}
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		            
				
			}
		});
		panelSur.add(btnModificar);
		
		JSeparator separator = new JSeparator();
		panelSur.add(separator);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout(0,0));
		
		JPanel pNorteE = new JPanel();
		pNorteE.setBackground(new Color(176, 196, 222));
		panelEste.add(pNorteE, BorderLayout.NORTH);
		JLabel lblTitulo = new JLabel("MODIFICAR PACIENTE: ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		pNorteE.add(lblTitulo);
		
		JPanel pSurE = new JPanel();
		pSurE.setBackground(new Color(176, 196, 222));
		panelEste.add(pSurE, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarCita();
				
			}

			
		});
		
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pSurE.add(btnAceptar);
		
		
		JLabel lblNombre = new JLabel("AQUI EL NOMBRE");
		pNorteE.add(lblNombre);
		
		JPanel pCentroE = new JPanel();
		panelEste.add(pCentroE, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroE = new GridBagLayout();
		gbl_pCentroE.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_pCentroE.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pCentroE.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pCentroE.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pCentroE.setLayout(gbl_pCentroE);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		pCentroE.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblDni = new JLabel("DNI");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.gridx = 2;
		gbc_lblDni.gridy = 1;
		pCentroE.add(lblDni, gbc_lblDni);
		
		 comboBoxDNI = new JComboBox();
		GridBagConstraints gbc_comboBoxDNI = new GridBagConstraints();
		gbc_comboBoxDNI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDNI.gridx = 3;
		gbc_comboBoxDNI.gridy = 1;
		pCentroE.add(comboBoxDNI, gbc_comboBoxDNI);
		comboBoxDNI.addItemListener(new ItemListener() {
			 	public void itemStateChanged(ItemEvent e) {
			 		
			 		String nombrePaciente=BD.buscarPacientePorDni(con, e.getItem().toString());
					if(nombrePaciente!=null) {
						lblNombre.setText(nombrePaciente);
					}
			 	}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		pCentroE.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
		JLabel lblFecha = new JLabel("FECHA Y HORA");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 2;
		pCentroE.add(lblFecha, gbc_lblFecha);
		
		textFieldFecha = new JTextField();
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 3;
		gbc_textFieldFecha.gridy = 2;
		pCentroE.add(textFieldFecha, gbc_textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lbTipoCita = new JLabel("TIPO CITA");
		GridBagConstraints gbc_lbTipoCita = new GridBagConstraints();
		gbc_lbTipoCita.anchor = GridBagConstraints.EAST;
		gbc_lbTipoCita.insets = new Insets(0, 0, 5, 5);
		gbc_lbTipoCita.gridx = 2;
		gbc_lbTipoCita.gridy = 3;
		pCentroE.add(lbTipoCita, gbc_lbTipoCita);
		
		 comboBoxCITA = new JComboBox();
		GridBagConstraints gbc_comboBoxCITA = new GridBagConstraints();
		gbc_comboBoxCITA.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCITA.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCITA.gridx = 3;
		gbc_comboBoxCITA.gridy = 3;
		pCentroE.add(comboBoxCITA, gbc_comboBoxCITA);
		
		JLabel lblDentista = new JLabel("DENTISTA");
		GridBagConstraints gbc_lblDentista = new GridBagConstraints();
		gbc_lblDentista.anchor = GridBagConstraints.EAST;
		gbc_lblDentista.insets = new Insets(0, 0, 0, 5);
		gbc_lblDentista.gridx = 2;
		gbc_lblDentista.gridy = 4;
		pCentroE.add(lblDentista, gbc_lblDentista);
		
		 comboBoxDENTISTA = new JComboBox();
		GridBagConstraints gbc_comboBoxDENTISTA = new GridBagConstraints();
		gbc_comboBoxDENTISTA.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDENTISTA.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDENTISTA.gridx = 3;
		gbc_comboBoxDENTISTA.gridy = 4;
		pCentroE.add(comboBoxDENTISTA, gbc_comboBoxDENTISTA);
		
		
		tablaGestionAgenda.getTableHeader().setReorderingAllowed(false);//bloquear columnas

		tablaGestionAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
			 
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	System.out.println("evento de raton");
	        	try {
					if (tablaGestionAgenda.getSelectedRow() != -1) {
						String dni = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 1).toString();
			            String nombre = modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 2).toString();  
						Date fecha= sdf.parse("20-02-2022 22:00");
						//Date fecha= sdf.parse(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 3).toString());
						TipoCita tipocita=TipoCita.valueOf(modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 4).toString());
						String nombreD=modelo.getValueAt(tablaGestionAgenda.getSelectedRow(), 5).toString();
						Cita cita=new Cita(dni,nombre,nombreD,fecha,tipocita);
						
						comboBoxDNI.setSelectedItem(cita.getDniPaciente());
						textFieldFecha.setText(sdf.format(cita.getFecha()).toString());
						comboBoxCITA.setSelectedItem(cita.getTipo());
						comboBoxDENTISTA.setSelectedItem(cita.getNombreDentista());
						
						//VentanaModificarCita c=new VentanaModificarCita(cita);
					}
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		            
	        }
	        });
		
		CargarComboBoxDni();
		CargarComboBoxCita();
		CargarComBoxDentista();
		AutoCompleteDecorator.decorate(comboBoxDNI);
		AutoCompleteDecorator.decorate(comboBoxCITA);
		AutoCompleteDecorator.decorate(comboBoxDENTISTA);
	}
	private void CargarComboBoxDni() {
		// TODO Auto-generated method stub
		ArrayList<Paciente> lista = new ArrayList<>();
		lista=BD.obtenerListaPaciente(con);
		for(Paciente p: lista) {
			comboBoxDNI.addItem(p.getDni());
		}
		comboBoxDNI.setSelectedItem("");
	}

	private void CargarComBoxDentista() {
		// TODO Auto-generated method stub
		ArrayList<Dentista> lista = new ArrayList<>();
		lista=BD.obtenerListaDentista(con);
		
		for(Dentista d: lista) {
			comboBoxCITA.addItem(d.getNombre());
		}
	}

	private void CargarComboBoxCita() {
		// TODO Auto-generated method stub
		for(TipoCita n:TipoCita.values()) {
			comboBoxDENTISTA.addItem(n.name());
		}
			
	}
	
	
	//METODOS BOTONES
	private void insertarCita() {
		
		
			
				//comprobar campos no nulos
			if(!(comboBoxDNI.getSelectedItem().toString().isEmpty()|| textFieldFecha.getText().isEmpty())) {
				
				//comprobar dni
				String nombrePaciente=BD.buscarPacientePorDni(con,comboBoxDNI.getSelectedItem().toString());
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
					c.setDniPaciente(comboBoxDNI.getSelectedItem().toString());
					c.setNombrePaciente(nombrePaciente);
					c.setFecha(fecha);
					c.setTipo(TipoCita.valueOf(comboBoxCITA.getSelectedItem().toString())); 
					c.setNombreDentista((String) comboBoxDENTISTA.getSelectedItem());
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
