package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BD.BD;
import Clases.Producto;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInventario extends JFrame /*implements TableCellRenderer*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaGestionInventario;
//	private Producto producto;
	DefaultTableModel modelo;
	
	//VentanaCompras vc = new VentanaCompras();
	VentanaAnadirProducto vap = new VentanaAnadirProducto();
	
	Connection con = BD.initBD("BaseDatos.db");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInventario frame = new VentanaInventario();
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
	public VentanaInventario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGestionPacientes.class.getResource("/img/dienteNegro.jpg")));
		setTitle("INVENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTituloGP = new JLabel("INVENTARIO");
		lblTituloGP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTituloGP.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTituloGP);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		panelOeste.add(menuBar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		//Meter la conexón con la Base de Datos
		
		String [] columnas = {"COMPRAR", "ID", "CODIGO PRODUCTO", "NOMBRE PRODUCTO", "DESCRIPCIÓN","PRECIO", "CANTIDAD"};
		//ArrayList<Inventario> aInventario = BD.obtenerListaInventario(con);
		List<Producto> productos = BD.obtenerListaProducto(con);
		modelo = new DefaultTableModel(columnas, 0);
		
		tablaGestionInventario = new JTable(modelo);
		tablaGestionInventario.setBounds(100, 100, 450, 300);
		
		Object O [] = null;
		for (int i = 0; i < productos.size(); i++) {
			modelo.addRow(O);
			Producto getProducto = productos.get(i);
			addCheckBox(0, tablaGestionInventario);
//			DefaultCellEditor chechBox = new DefaultCellEditor(checkbox);
//			tablaGestionInventaio.getColumnModel().getColumn(0).setCellEditor(checkBox);
			modelo.setValueAt(i, i, 1);
			modelo.setValueAt(getProducto.getCodigo(), i, 2);
			modelo.setValueAt(getProducto.getNombre(), i, 3);
			modelo.setValueAt(getProducto.getDescripcion(), i, 4);
			modelo.setValueAt(getProducto.getPrecio(), i, 5);
			modelo.setValueAt(getProducto.getCantidad(), i, 6);
		}
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionInventario);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		
		JPanel panelSur = new JPanel();
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
		
		JButton btnBuscar = new JButton("BUSCAR");
		panelSur.add(btnBuscar); 
		
		JButton btnAnadir = new JButton("AÑADIR");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				BD.anadirProducto(con, null);
//				anadirProducto();
//				ArrayList<Producto> aProductos;
//				aProductos = BD.obtenerListaProducto(con);
				vap.setVisible(true);
			}
		});
		panelSur.add(btnAnadir);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.modificarProducto(con, ALLBITS, ABORT);
			}
		});
		panelSur.add(btnModificar);
		
		JButton btnCompras = new JButton("COMPRAS");
		/*
		 * Se seleccionan en la tabla los productos que se quieren comprar y cuando se le da al botón "Compras", 
		 * se pide el máximo y mínimo importe. Con los tres datos, se generan posibles tickets de compra para esos productos. 
		 * */
//		btnCalcular.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String importe = JOptionPane.showInputDialog("Dinero disponible:");
//				double dineroDisponible = Double.parseDouble(importe);
//				String resto = JOptionPane.showInputDialog("Dinero sobrante máximo:");
//				double dineroSobranteMax = Double.parseDouble(resto);
//				JOptionPane.showMessageDialog(null, combinacionesProductos(productos, dineroDisponible, dineroSobranteMax), "COMPRAS POSBILES", JOptionPane.INFORMATION_MESSAGE);
//			}
//		});
		
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//vc.setVisible(true);
			}
		});
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarP(con);
				ArrayList<Producto> aProductos;
				aProductos = BD.obtenerListaProducto(con);
				actualizartabla(tablaGestionInventario, modelo, aProductos);
			}
		});
		panelSur.add(btnBorrar);
		panelSur.add(btnCompras);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
	}
	
	private static void combinacionesProductos(List<List<Producto>> resultado,
												List<Producto> elementos, 
												double DineroDisponible, 
												double DineroSobreanteMax, 
												List<Producto> provisional) {
		if (DineroDisponible < 0) {
			return;
		} else if (DineroDisponible < DineroSobreanteMax) {
			provisional.sort((Producto p1, Producto p2) -> Integer.compare(p1.getCodigo(), p2.getCodigo()));
			if (!resultado.contains(provisional)) {
				resultado.add(new ArrayList<>(provisional));
			}			
		} else {
			for (Producto p : elementos) {
				provisional.add(p);
				combinacionesProductos(resultado, elementos, DineroDisponible-p.getPrecio(), DineroSobreanteMax, provisional);
				provisional.remove(provisional.size()-1);
			}
		}
		
	}
	
	public static List<List<Producto>> combinacionesProductos(List<Producto> elementos, double DineroDisponible, double DineroSobrante){
		List<List<Producto>> resultado = new ArrayList<>();
		combinacionesProductos(resultado, elementos, DineroDisponible, DineroSobrante, new ArrayList<>());
		
		return resultado;		
	}
	
	
	private void borrarP(Connection con) {
		int fila = tablaGestionInventario.getSelectedRow();
		String valor = tablaGestionInventario.getValueAt(fila, 2).toString();
		String sql = "DELETE FROM Producto WHERE cod_p=" + valor;
		int seguro = JOptionPane.showConfirmDialog(this,  "¿Desea eliminar el producto?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(seguro == JOptionPane.YES_OPTION ) {
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
				JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO CORRECTAMENTE");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: " + e + ". INTÉNTELO DE NUEVO.");
			}
			
		} else {
			System.out.println("Prueba");
		}
	}
	
	private void actualizartabla(JTable tablaGestionInventaio, DefaultTableModel modelo, ArrayList<Producto> aProductos) {
		Object O[] = null;
		// limpiar el modelo y volver a escribir
		for (int i = 0; i < tablaGestionInventaio.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		// rellenar con la nueva busqueda-->filtro
		for (int i = 0; i < aProductos.size(); i++) {
			modelo.addRow(O);
			Producto getProducto = (Producto) aProductos.get(i);
			
				addCheckBox(0, tablaGestionInventario);
				modelo.setValueAt(i, i, 1);
				modelo.setValueAt(getProducto.getCodigo(), i, 2);
				modelo.setValueAt(getProducto.getNombre(), i, 3);
				modelo.setValueAt(getProducto.getDescripcion(), i, 4);
				modelo.setValueAt(getProducto.getPrecio(), i, 5);
				modelo.setValueAt(getProducto.getCantidad(), i, 6);
			}
		
	}
	
	public void addCheckBox(int column, JTable tablaGestionInventaio) {
		TableColumn tc = tablaGestionInventaio.getColumnModel().getColumn(column);
		tc.setCellEditor(tablaGestionInventaio.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tablaGestionInventaio.getDefaultRenderer(Boolean.class));
	}



//	@Override
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//			int row, int column) {
//		// TODO Auto-generated method stub
//		
//		return this;
//	}
	
	
	
	

}
