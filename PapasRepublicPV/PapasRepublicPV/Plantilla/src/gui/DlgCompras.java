package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class DlgCompras extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCompras dialog = new DlgCompras();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCompras() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre del proveedor:");
			lblNewLabel.setBounds(10, 11, 129, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Insumo:");
			lblNewLabel_1.setBounds(10, 36, 60, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Cantidad:");
			lblNewLabel_2.setBounds(10, 63, 77, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Costo:");
			lblNewLabel_3.setBounds(10, 86, 46, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Impresiones Lucia S.R.L.", "Mc Descartables S.A.C.", "Distribuidora Andes S.R.L.", "Compa\u00F1\u00EDa Food Retail S.A.C.", "Distribuidora Caxas S.A.C.", "Hidrandina S.A.", "EPS SEDACAJ S.A.", "Parque Arauco S.A.", "Sofia Pereyra Castro", "Claro"}));
			comboBox.setBounds(137, 7, 185, 22);
			contentPanel.add(comboBox);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cono", "Empaque papas regular y nuggets", "Empaque papas mediano", "Empaque papas grande", "Box", "Vasos regular", "Vasos mediano", "Vasos grande", "Gaseosa Inca Kola", "Gaseosa Fanta", "Gaseosa Sprite", "Gaseosa Coca Cola", "Agua San Mateo sin gas", "Papas prefritas 10kg", "Hot dog pack 12 und", "Nuggets 2kg", "Sal", "Balde de aceite 20 Litros", "Balde de ketchup 4kg", "Balde de mayonesa 4kg", "Aj\u00ED Criollo Sachet 10g x 250un", "Balde de mostaza 4kg", "Lavavajillas 5 Litros", "Cloro Botella 4kg", "Servilletas pack 600 und", "Tenedores de cocktail pack 50 und", "Bolsas para llevar pack 100 und", "Bolsas para basura pack 50 und"}));
			comboBox.setBounds(137, 36, 228, 22);
			contentPanel.add(comboBox);
		}
		{
			textField = new JTextField();
			textField.setBounds(137, 60, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setEnabled(false);
			textField_1.setEditable(false);
			textField_1.setBounds(137, 83, 86, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnIngresarCompra = new JButton("Ingresar compra");
				btnIngresarCompra.setActionCommand("OK");
				buttonPane.add(btnIngresarCompra);
				getRootPane().setDefaultButton(btnIngresarCompra);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
