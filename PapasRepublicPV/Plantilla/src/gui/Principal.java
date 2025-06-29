package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmUsuarios;
	private JMenuItem mntmClientes;
	private JMenuItem mntmProveedores;
	private JMenuItem mntmProductos;
	private JMenuItem mntmInventario;
	private JMenuItem mntmVentas;
	private JMenu mnVentas;
	private JMenuItem rlzrVenta;
	private JMenu mnAlmacen;
	private JMenuItem IngCompra;
	private JMenu mnGenerarReporte;
	private JMenuItem gnrRepVentas;
	private JMenuItem gnrRepCompras;
	private JMenuItem gnrRepUtilidades;
	private JMenuItem mntmDscntsPrmcns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Papas Republic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
		
		mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(this);
		mnMantenimiento.add(mntmUsuarios);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(this);
		mnMantenimiento.add(mntmClientes);
		
		mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.addActionListener(this);
		mnMantenimiento.add(mntmProveedores);
		
		mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(this);
		mnMantenimiento.add(mntmProductos);
		
		mntmInventario = new JMenuItem("Inventario");
		mntmInventario.addActionListener(this);
		mnMantenimiento.add(mntmInventario);
		
		mntmVentas = new JMenuItem("Ventas");
		mntmVentas.addActionListener(this);
		mnMantenimiento.add(mntmVentas);
		
		mntmDscntsPrmcns = new JMenuItem("Descuentos / Promociones");
		mntmDscntsPrmcns.addActionListener(this);
		mnMantenimiento.add(mntmDscntsPrmcns);
		
		mnVentas = new JMenu("Ventas");
		menuProyecto.add(mnVentas);
		
		rlzrVenta = new JMenuItem("Realizar Venta");
		mnVentas.add(rlzrVenta);
		
		mnAlmacen = new JMenu("Almacen");
		menuProyecto.add(mnAlmacen);
		
		IngCompra = new JMenuItem("Ingresar Compra");
		mnAlmacen.add(IngCompra);
		
		mnGenerarReporte = new JMenu("Generar Reportes");
		menuProyecto.add(mnGenerarReporte);
		
		gnrRepVentas = new JMenuItem("Reporte de Ventas");
		gnrRepVentas.addActionListener(this);
		mnGenerarReporte.add(gnrRepVentas);
		
		gnrRepCompras = new JMenuItem("Reporte de Compras / Inventario");
		gnrRepCompras.addActionListener(this);
		mnGenerarReporte.add(gnrRepCompras);
		
		gnrRepUtilidades = new JMenuItem("Reporte de Utilidades");
		gnrRepUtilidades.addActionListener(this);
		mnGenerarReporte.add(gnrRepUtilidades);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gnrRepUtilidades) {
			actionPerformedGnrRepUtilidades(e);
		}
		if (e.getSource() == gnrRepCompras) {
			actionPerformedGnrRepCompras(e);
		}
		if (e.getSource() == gnrRepVentas) {
			actionPerformedGnrRepVentas(e);
		}
		if (e.getSource() == mntmVentas) {
			actionPerformedMntmVentas(e);
		}
		if (e.getSource() == mntmProveedores) {
			actionPerformedMntmProveedores(e);
		}
		if (e.getSource() == mntmProductos) {
			actionPerformedMntmProductos(e);
		}
		if (e.getSource() == mntmInventario) {
			actionPerformedMntmInventario(e);
		}
		if (e.getSource() == mntmDscntsPrmcns) {
			actionPerformedMntmDscntsPrmcns(e);
		}
		if (e.getSource() == mntmClientes) {
			actionPerformedMntmClientes(e);
		}
		if (e.getSource() == mntmUsuarios) {
			actionPerformedMntmUsuarios(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		System.exit(0);
	}
	protected void actionPerformedMntmUsuarios(ActionEvent e) {
		DlgUsuarios dp = new DlgUsuarios();
		dp.setLocationRelativeTo(this);
		dp.setVisible(true);
	}
	
	protected void actionPerformedMntmClientes(ActionEvent e) {
		DlgClientes dp = new DlgClientes();
		dp.setLocationRelativeTo(this);
		dp.setVisible(true);
	}
	protected void actionPerformedMntmDscntsPrmcns(ActionEvent e) {
	}
	protected void actionPerformedMntmInventario(ActionEvent e) {
	}
	protected void actionPerformedMntmProductos(ActionEvent e) {
	}
	protected void actionPerformedMntmProveedores(ActionEvent e) {
		DlgProveedores dp = new DlgProveedores();
		dp.setLocationRelativeTo(this);
		dp.setVisible(true);
	}
	protected void actionPerformedMntmVentas(ActionEvent e) {
	}
	protected void actionPerformedGnrRepVentas(ActionEvent e) {
		DlgReporteVentas dialog = new DlgReporteVentas(this);
	    dialog.setVisible(true);
	}
	protected void actionPerformedGnrRepCompras(ActionEvent e) {
		DlgReporteCompras dialog = new DlgReporteCompras(this);
	    dialog.setLocationRelativeTo(this);
	    dialog.setVisible(true);
	}
	protected void actionPerformedGnrRepUtilidades(ActionEvent e) {
		DlgReporteUtilidades dialog = new DlgReporteUtilidades(this);
	    dialog.setLocationRelativeTo(this);
	    dialog.setVisible(true);
	}
}