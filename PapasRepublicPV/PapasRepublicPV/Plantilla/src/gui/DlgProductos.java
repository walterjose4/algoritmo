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

public class DlgProductos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
        private JTable tblProductos;
        private DefaultTableModel modelo;
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
                btnModificar.addActionListener(this);

                btnEliminar = new JButton("Eliminar");
                btnEliminar.addActionListener(this);
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
		
                tblProductos = new JTable();
                modelo = new DefaultTableModel(
                                new Object[][] {},
                                new String[] {
                                        "NOMBRE", "ITEM", "CANTIDAD DISPONIBLE", "PRECIO DE VENTA"
                                }
                );
                tblProductos.setModel(modelo);
                tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tblProductos.setToolTipText("Nombre");
                tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                                int fila = tblProductos.getSelectedRow();
                                if (fila != -1) {
                                        txtNombre.setText(String.valueOf(modelo.getValueAt(fila, 0)));
                                        txtItem.setText(String.valueOf(modelo.getValueAt(fila, 1)));
                                        txtCantidadDisponible.setText(String.valueOf(modelo.getValueAt(fila, 2)));
                                        txtPreciodeVenta.setText(String.valueOf(modelo.getValueAt(fila, 3)));
                                }
                        }
                });
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

        private void actionPerformedBtnAdicionar(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String item = txtItem.getText().trim();
                String cantStr = txtCantidadDisponible.getText().trim();
                String precioStr = txtPreciodeVenta.getText().trim();
                if (nombre.isEmpty() || item.isEmpty() || cantStr.isEmpty() || precioStr.isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos");
                        return;
                }
                try {
                        int cantidad = Integer.parseInt(cantStr);
                        double precio = Double.parseDouble(precioStr);
                        Object[] fila = { nombre, item, cantidad, precio };
                        modelo.addRow(fila);
                        limpiarCampos();
                } catch (NumberFormatException ex) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser n\u00FAmeros");
                }
        }

        private void actionPerformedBtnModificar(ActionEvent e) {
                int filaSel = tblProductos.getSelectedRow();
                if (filaSel == -1) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila a modificar");
                        return;
                }
                try {
                        int cantidad = Integer.parseInt(txtCantidadDisponible.getText().trim());
                        double precio = Double.parseDouble(txtPreciodeVenta.getText().trim());
                        modelo.setValueAt(txtNombre.getText().trim(), filaSel, 0);
                        modelo.setValueAt(txtItem.getText().trim(), filaSel, 1);
                        modelo.setValueAt(cantidad, filaSel, 2);
                        modelo.setValueAt(precio, filaSel, 3);
                        limpiarCampos();
                } catch (NumberFormatException ex) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser n\u00FAmeros");
                }
        }

        private void actionPerformedBtnEliminar(ActionEvent e) {
                int filaSel = tblProductos.getSelectedRow();
                if (filaSel != -1) {
                        modelo.removeRow(filaSel);
                        limpiarCampos();
                }
        }

        private void limpiarCampos() {
                txtNombre.setText("");
                txtItem.setText("");
                txtCantidadDisponible.setText("");
                txtPreciodeVenta.setText("");
        }
}