package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import BD.BD;
import Clases.Usuario;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtnick;
	private JComboBox <String> comboBoxRol;
	private JPasswordField txtpassword1,txtpassword2;
	Connection con = BD.initBD("BaseDatos.db");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("REGISTRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setForeground(SystemColor.inactiveCaptionBorder);
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("DeustoDentist");
		lblTitulo.setForeground(SystemColor.inactiveCaptionBorder);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER AL MENÚ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio vi = new VentanaInicio();
				vi.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2, 0, 10));
		
		JPanel panelcentroDatos = new JPanel();
		panelCentro.add(panelcentroDatos);
		panelcentroDatos.setLayout(new GridLayout(3, 2, 15, 10));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelcentroDatos.add(lblNombre);
		
		txtnombre = new JTextField();
		panelcentroDatos.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Contraseña:");
		panelcentroDatos.add(lblApellido);
		
		 txtpassword1 = new JPasswordField();
		panelcentroDatos.add(txtpassword1);
		txtpassword1.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario (nick):");
		panelcentroDatos.add(lblUsuario);
		
		txtnick = new JTextField();
		panelcentroDatos.add(txtnick);
		txtnick.setColumns(10);
		
		JLabel lblFechaNac = new JLabel("Repetir contraseña:");
		panelcentroDatos.add(lblFechaNac);
		
		 txtpassword2 = new JPasswordField();
		panelcentroDatos.add(txtpassword2);
		txtpassword2.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol:");
		panelcentroDatos.add(lblRol);
		
		 comboBoxRol = new JComboBox();
		panelcentroDatos.add(comboBoxRol);
		comboBoxRol.addItem("DENTISTA");
		comboBoxRol.addItem("ADMINISTRADOR");
		comboBoxRol.addItem("RECEPCIONISTA");
		
		JPanel panelcentroBoton = new JPanel();
		panelCentro.add(panelcentroBoton);
		panelcentroBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
		
		JButton btnRegistro = new JButton("REGISTRAR");
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro();
			}

		
		});
		panelcentroBoton.add(btnRegistro);
	}
	private void Registro() {
		Usuario u=new Usuario();
		String p1=String.valueOf(txtpassword1.getPassword());
		String p2=String.valueOf(txtpassword2.getPassword());
		if(p1.equals(p2)) {
			
			u.setNombre(txtnombre.getText());
			u.setNick(txtnick.getText());
			u.setRol(comboBoxRol.getSelectedItem().toString());
			String c_cifrada=Cifrar(txtpassword1.getText());
			u.setContrasenia(c_cifrada);
			BD.insertarUsuario(con,u);
			JOptionPane.showMessageDialog(null, "SE HA REGISTRADO UN NUEVO USUARIO", "USUARIO", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	private String Cifrar(String password) {
		// TODO Auto-generated method stub
		char array[]=password.toCharArray();
		
		for(int i=0;i<array.length;i++) {
			array[i]=(char)(array[i]+(char)5);
		}
		
		String encriptado=String.valueOf(array);
		System.out.println(encriptado);
		char arrayE[]=encriptado.toCharArray();	
		
		
		
		return encriptado;
	}
}
