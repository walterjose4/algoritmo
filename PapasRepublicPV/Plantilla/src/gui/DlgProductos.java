package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import arreglos.ArregloProductos;
import clases.Producto;

public class DlgProductos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tblProductos;
	private JLabel lblNombre;
	private JLabel lblItem;
	private JLabel lblCantidadDisponible;
	private JLabel lblPreciodeVenta;
	private JTextField txtNombre;
	private JTextField txtItem;
	private JTextField txtCantidadDisponible;
	private JTextField txtPreciodeVenta;
	private JButton btnAdicionar;
	private JButton btnModificar;
        private JButton btnEliminar;
        private DefaultTableModel modelo;
        private ArregloProductos ap = new ArregloProductos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProductos dialog = new DlgProductos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProductos() {
		setTitle("Productos");
		setBounds(100, 100, 859, 547);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		
		lblNombre = new JLabel("Nombre	:");
		
		lblItem = new JLabel("Item		:");
		
		lblCantidadDisponible = new JLabel("Cantidad disponible	:");
		
		lblPreciodeVenta = new JLabel("Precio de venta	:");
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		
		txtItem = new JTextField();
		txtItem.setColumns(10);
		
		txtCantidadDisponible = new JTextField();
		txtCantidadDisponible.setColumns(10);
		
		txtPreciodeVenta = new JTextField();
		txtPreciodeVenta.setColumns(10);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		
		btnModificar = new JButton("Modificar");
		
		btnEliminar = new JButton("Eliminar");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblItem)
								.addComponent(lblCantidadDisponible)
								.addComponent(lblPreciodeVenta))
							.addGap(45)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCantidadDisponible, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 550, Short.MAX_VALUE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(txtPreciodeVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblItem)
								.addComponent(txtItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidadDisponible)
								.addComponent(txtCantidadDisponible, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPreciodeVenta)
								.addComponent(txtPreciodeVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addGap(6)
							.addComponent(btnModificar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
                modelo = new DefaultTableModel();
                modelo.addColumn("NOMBRE");
                modelo.addColumn("ITEM");
                modelo.addColumn("CANTIDAD DISPONIBLE");
                modelo.addColumn("PRECIO DE VENTA");

                tblProductos = new JTable(modelo);
                tblProductos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                tblProductos.setToolTipText("Nombre");
		scrollPane.setViewportView(tblProductos);
		contentPanel.setLayout(gl_contentPanel);
	}
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdicionar) {
                        actionPerformedBtnAdicionar(e);
                } else if (e.getSource() == btnModificar) {
                        actionPerformedBtnModificar(e);
                } else if (e.getSource() == btnEliminar) {
                        actionPerformedBtnEliminar(e);
                }
        }

        protected void actionPerformedBtnAdicionar(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String item = txtItem.getText().trim();
                int cantidad = 0;
                double precio = 0;
                try {
                        cantidad = Integer.parseInt(txtCantidadDisponible.getText().trim());
                        precio = Double.parseDouble(txtPreciodeVenta.getText().trim());
                } catch (NumberFormatException ex) {
                        return;
                }
                Producto p = new Producto(nombre, item, cantidad, precio);
                ap.adicionar(p);
                Object[] fila = { nombre, item, cantidad, precio };
                modelo.addRow(fila);
        }

        protected void actionPerformedBtnModificar(ActionEvent e) {
                int fila = tblProductos.getSelectedRow();
                if (fila == -1) {
                        return;
                }
                String nombre = txtNombre.getText().trim();
                String item = txtItem.getText().trim();
                int cantidad;
                double precio;
                try {
                        cantidad = Integer.parseInt(txtCantidadDisponible.getText().trim());
                        precio = Double.parseDouble(txtPreciodeVenta.getText().trim());
                } catch (NumberFormatException ex) {
                        return;
                }
                Producto p = ap.obtener(fila);
                p.setNombre(nombre);
                p.setItem(item);
                p.setCantidadDisponible(cantidad);
                p.setPrecioDeVenta(precio);
                modelo.setValueAt(nombre, fila, 0);
                modelo.setValueAt(item, fila, 1);
                modelo.setValueAt(cantidad, fila, 2);
                modelo.setValueAt(precio, fila, 3);
        }

        protected void actionPerformedBtnEliminar(ActionEvent e) {
                int fila = tblProductos.getSelectedRow();
                if (fila == -1) {
                        return;
                }
                Producto p = ap.obtener(fila);
                ap.eliminar(p);
                modelo.removeRow(fila);
        }
}