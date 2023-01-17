package ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class VentanaPrincipal extends JFrame {
	String rol="";
	String user="";
	private JPanel contentPane;
	JButton btnInventario,btnGestionp,btnAgenda,btnImportarDatos,btnHistorialClinico;
	public ImageIcon imagenFondo;
	public URL fondo;
	private JLabel lblUsuario;
	private JLabel lblHora;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param rol
	 */

	public VentanaPrincipal() {
		components();
		
	}
	public VentanaPrincipal(String rol,	String user) {
		this.rol=rol;
		this.user=user;
		components();
		comprobarRol(rol);
		lblUsuario.setText("  USUARIO: "+user);
	}
//	@Override
//    public void paint(Graphics g) {
//		Dimension tamanio=getSize();
//		imagenFondo=new ImageIcon(getClass().getResource("/img/fondo1.png"));
//		g.drawImage(imagenFondo.getImage(), 0, 0,tamanio.width, tamanio.height, null);
//		//setOpaque(false);
//		//setOpacity(DEFAULT_CURSOR);
//		super.paint(g);
//    }

	private void components() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("REGISTRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(SystemColor.windowBorder);
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("DENTIST\r\n");
		lblTitulo.setForeground(SystemColor.inactiveCaptionBorder);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);

		 lblUsuario = new JLabel("");
		lblUsuario.setForeground(SystemColor.inactiveCaptionBorder);
		panelNorte.add(lblUsuario);

		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);

		 lblHora = new JLabel("HORA:");
		lblHora.setForeground(SystemColor.inactiveCaptionBorder);
		lblHora.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		panelSur.add(lblHora);

		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);

		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 3, 15, 15));

		JPanel panelGestionPacientes = new JPanel();
		panelCentro.add(panelGestionPacientes);
		panelGestionPacientes.setLayout(new GridLayout(0, 1, 0, 0));

		 btnGestionp = new JButton("GESTIÓN PACIENTES");
		btnGestionp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGestionp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionPacientes vgp = new VentanaGestionPacientes(rol,user);
				vgp.setVisible(true);
				setVisible(false);
			}

		});
		panelGestionPacientes.add(btnGestionp);

		JPanel panelAgenda = new JPanel();
		panelCentro.add(panelAgenda);
		panelAgenda.setLayout(new GridLayout(1, 0, 0, 0));

		 btnAgenda = new JButton("AGENDA (citas)");
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgenda va = new VentanaAgenda(rol,user);
				va.setVisible(true);
				setVisible(false);
			}
		});
		panelAgenda.add(btnAgenda);

		JPanel panelInventario = new JPanel();
		panelCentro.add(panelInventario);
		panelInventario.setLayout(new GridLayout(1, 0, 0, 0));

		 btnInventario = new JButton("INVENTARIO");
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInventarioFinal vif = new VentanaInventarioFinal(rol,user);
				vif.setVisible(true);
				//setVisible(false);
			}
		});
		panelInventario.add(btnInventario);

		JPanel panelExportarDatos = new JPanel();
		panelCentro.add(panelExportarDatos);
		panelExportarDatos.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnExportarDatos = new JButton("EXPORTAR DATOS");
		btnExportarDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaExportarDatos ved = new VentanaExportarDatos(rol,user);
				ved.setVisible(true);
				//setVisible(false);
			}
		});
		panelExportarDatos.add(btnExportarDatos);

		JPanel panelHistorialClinico = new JPanel();
		panelCentro.add(panelHistorialClinico);
		panelHistorialClinico.setLayout(new GridLayout(1, 0, 0, 0));

		 btnHistorialClinico = new JButton("HISTORIAL CLINICO");
		btnHistorialClinico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHistorialClinico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHistorial2 vhc = new VentanaHistorial2(rol,user);
				vhc.setVisible(true);
				//setVisible(false);
			}
		});
		panelHistorialClinico.add(btnHistorialClinico);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					
					long fecha = System.currentTimeMillis();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String d = sdf.format(new Date(fecha));
					
					lblHora.setText("HORA: "+d);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
			}
		};
		
		Thread t = new Thread(r);
		
		t.start();
	}

	private void comprobarRol(String rol) {
		// TODO Auto-generated method stub
		// btnInventario,btnGestionp,btnAgenda,btnImportarDatos,btnHistorialClinico;
		switch (rol) {

		case "ADMINISTRADOR":
			btnInventario.setEnabled(true);
			btnGestionp.setEnabled(true);
			btnAgenda.setEnabled(true);
			btnImportarDatos.setEnabled(true);
			btnHistorialClinico.setEnabled(true);
			break;
		case "RECEPCIONISTA":
			btnInventario.setEnabled(true);
			btnGestionp.setEnabled(true);
			btnAgenda.setEnabled(true);
			btnImportarDatos.setEnabled(true);
			btnHistorialClinico.setEnabled(false);
			break;
		case "DENTISTA":
			btnInventario.setEnabled(false);
			btnGestionp.setEnabled(false);
			btnAgenda.setEnabled(true);
			btnImportarDatos.setEnabled(false);
			btnHistorialClinico.setEnabled(true);
			break;
		default:

		}
	}
	
	
//	@Override
//    public void paint(Graphics g) {
//		Dimension tamanio=getSize();
//		imagenFondo=new ImageIcon(getClass().getResource("/img/fondo1.png"));
//		g.drawImage(imagenFondo.getImage(), 0, 0,tamanio.width, tamanio.height, null);
//		//setOpaque(false);
//		//setOpacity(DEFAULT_CURSOR);
//		super.paint(g);
//    }

}
