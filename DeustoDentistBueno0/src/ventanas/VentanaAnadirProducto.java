package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import BD.BD;
import Clases.Producto;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Toolkit;

public class VentanaAnadirProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldPrecio;
	private JTextField textFieldCantidad;
	
	Connection con = BD.initBD("BaseDatos.db");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirProducto frame = new VentanaAnadirProducto();
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
	public VentanaAnadirProducto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/img/dienteNegro.jpg")));
		setTitle("AÑADIR PRODUCTO");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{426, 0};
		gbl_contentPane.rowHeights = new int[]{126, 126, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelCentro = new JPanel();
		GridBagConstraints gbc_panelCentro = new GridBagConstraints();
		gbc_panelCentro.fill = GridBagConstraints.BOTH;
		gbc_panelCentro.insets = new Insets(0, 0, 5, 0);
		gbc_panelCentro.gridx = 0;
		gbc_panelCentro.gridy = 0;
		contentPane.add(panelCentro, gbc_panelCentro);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCodigo = new JLabel("CÓDIGO:");
		panelCentro.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		panelCentro.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		panelCentro.add(lblNombre);
		
		textFieldNombre = new JTextField();
		panelCentro.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCIÓN:");
		panelCentro.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		panelCentro.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblPrecio = new JLabel("PRECIO:");
		panelCentro.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		panelCentro.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		panelCentro.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		panelCentro.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JPanel panelSur = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSur.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		GridBagConstraints gbc_panelSur = new GridBagConstraints();
		gbc_panelSur.gridx = 0;
		gbc_panelSur.gridy = 1;
		contentPane.add(panelSur, gbc_panelSur);
		
		JButton btnAnadir = new JButton("AÑADIR PRODUCTO");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String erCod = "[0-9]{5}";
				String codigo1 = textFieldCodigo.getText();
				//String erPrecio = "[0-9]+(,)?[0-9]{2}";
//				"[0-9].[0-9]{2}";
				//String precio1 = textFieldPrecio.getText();
				String erCantidad = "[0-9]{1,7}";
				String cantidad1 = textFieldCantidad.getText();
				int codigo = 0;
				int precio = 0;
				int cantidad = 0;
				
				if(Pattern.matches(erCod, codigo1)) {
					codigo = Integer.parseInt(textFieldCodigo.getText());
				} else {
					JOptionPane.showMessageDialog(null, "El formato del código no es correcto. Debe introducir cinco números (p.e: 12345)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				String nombre = textFieldNombre.getText();
				String descripcion = textFieldDescripcion.getText();
				
				//if(Pattern.matches(erPrecio, precio1)) {
					precio = Integer.parseInt(textFieldPrecio.getText());
//				} else {
//					JOptionPane.showMessageDialog(null, "El formato del precio no es correcto. Debe introducir dos decimales y tener en cuenta la ',' (p.e: 12,35)", "ERROR", JOptionPane.ERROR_MESSAGE);
//				}
				
				if(Pattern.matches(erCantidad, cantidad1)) {
					cantidad = Integer.parseInt(textFieldCantidad.getText());
				} else {
					JOptionPane.showMessageDialog(null, "El formato de la cantidad introducida no es correcta. Debe introducir un número entero (p.e: 17)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				//Funciona con el primer método de la base de datos.
//				Producto p = new Producto(codigo, nombre, descripcion, precio, cantidad);
//				BD.anadirProducto(con, p);
				
				BD.anadirProducto(con, codigo, nombre, descripcion, precio, cantidad);
			
				VentanaInventario vi = new VentanaInventario();
				vi.setVisible(true);
			}
			
			
		});
		panelSur.add(btnAnadir);
	}

}
