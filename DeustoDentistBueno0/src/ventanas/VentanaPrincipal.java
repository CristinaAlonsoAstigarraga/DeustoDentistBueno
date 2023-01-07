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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class VentanaPrincipal extends JFrame {
	private JPanel contentPane;
	VentanaGestionPacientes vgp = new VentanaGestionPacientes();
	VentanaAgenda va = new VentanaAgenda();
	VentanaExportarDatos ved = new VentanaExportarDatos();
	VentanaImportarDatos vid = new VentanaImportarDatos();
	VentanaHistorial2 vh2 = new VentanaHistorial2();
	VentanaInventario vi = new VentanaInventario();
	public ImageIcon imagenFondo;
	public URL fondo;

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
	 */
	 
	public VentanaPrincipal() {
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
		
		JLabel lblNewLabel_1 = new JLabel("       USUARIO:");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		panelNorte.add(lblNewLabel_1);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(SystemColor.windowBorder);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Principal");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		panelSur.add(lblNewLabel);
		
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
		
		JButton btnNewButton = new JButton("GESTIÓN PACIENTES");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vgp.setVisible(true);
				setVisible(false);
			}
			
		});
		panelGestionPacientes.add(btnNewButton);
		
		JPanel panelAgenda = new JPanel();
		panelCentro.add(panelAgenda);
		panelAgenda.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAgenda = new JButton("AGENDA (citas)");
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				va.setVisible(true);
				setVisible(false);
			}
		});
		panelAgenda.add(btnAgenda);
		
		JPanel panelImportarDatos = new JPanel();
		panelCentro.add(panelImportarDatos);
		panelImportarDatos.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnImportarDatos = new JButton("IMPORTAR DATOS");
		btnImportarDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vid.setVisible(true);
				setVisible(false);
			}
		});
		panelImportarDatos.add(btnImportarDatos);
		
		JPanel panelInventario = new JPanel();
		panelCentro.add(panelInventario);
		panelInventario.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnInventario = new JButton("INVENTARIO");
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vi.setVisible(true);
				setVisible(false);
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
				ved.setVisible(true);
				setVisible(false);
			}
		});
		panelExportarDatos.add(btnExportarDatos);
		
		JPanel panelHistorialClinico = new JPanel();
		panelCentro.add(panelHistorialClinico);
		panelHistorialClinico.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnHistorialClinico = new JButton("HISTORIAL CLÍNICO");
		btnHistorialClinico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHistorialClinico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vh2.setVisible(true);
				setVisible(false);
			}
		});
		panelHistorialClinico.add(btnHistorialClinico);
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
