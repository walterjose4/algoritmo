package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DescuentosPromociones extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblUnidadesD2;
	private JLabel lblUnidadesD3;
	private JLabel lblUnidadesD4;
	private JTextField txtUnidadesD1;
	private JTextField txtUnidadesD2;
	private JTextField txtUnidadesD3;
	private JTextField txtUnidadesD4;
	private JTextField txtUnidadesP1;
	private JTextField txtUnidadesP2;
	private JTextField txtUnidadesP3;
	private JTextField txtUnidadesP4;
	private JLabel lblDescuentos;
	private JLabel lblPromociones;
	private JButton btnGuardar;
	private JButton btnCerrar;
	private JLabel lblUnidadesP1;
	private JLabel lblUnidadesP2;
	private JLabel lblUnidadesP3;
	private JLabel lblUnidadesP4;
	private JLabel lblUnidadesD1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DescuentosPromociones dialog = new DescuentosPromociones();
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
	public DescuentosPromociones() {
		setTitle("Descuentos y Promociones");
		setBounds(100, 100, 613, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblUnidadesD1 = new JLabel("1 a 5 unidades");
			lblUnidadesD1.setBounds(33, 47, 98, 25);
			contentPanel.add(lblUnidadesD1);
		}
		
		lblUnidadesD2 = new JLabel("6 a 10 unidades");
		lblUnidadesD2.setBounds(33, 83, 98, 18);
		contentPanel.add(lblUnidadesD2);
		
		lblUnidadesD3 = new JLabel("11 a 20 unidades");
		lblUnidadesD3.setBounds(33, 112, 98, 25);
		contentPanel.add(lblUnidadesD3);
		
		lblUnidadesD4 = new JLabel("21 a más unidades");
		lblUnidadesD4.setBounds(33, 147, 116, 17);
		contentPanel.add(lblUnidadesD4);
		
		txtUnidadesD1 = new JTextField();
		txtUnidadesD1.setBounds(159, 49, 86, 20);
		contentPanel.add(txtUnidadesD1);
		txtUnidadesD1.setColumns(10);
		
		txtUnidadesD2 = new JTextField();
		txtUnidadesD2.setBounds(159, 82, 86, 20);
		contentPanel.add(txtUnidadesD2);
		txtUnidadesD2.setColumns(10);
		
		txtUnidadesD3 = new JTextField();
		txtUnidadesD3.setBounds(159, 114, 86, 20);
		contentPanel.add(txtUnidadesD3);
		txtUnidadesD3.setColumns(10);
		
		txtUnidadesD4 = new JTextField();
		txtUnidadesD4.setBounds(159, 145, 86, 20);
		contentPanel.add(txtUnidadesD4);
		txtUnidadesD4.setColumns(10);
		
		txtUnidadesP1 = new JTextField();
		txtUnidadesP1.setBounds(470, 49, 86, 20);
		contentPanel.add(txtUnidadesP1);
		txtUnidadesP1.setColumns(10);
		
		txtUnidadesP2 = new JTextField();
		txtUnidadesP2.setBounds(470, 82, 86, 20);
		contentPanel.add(txtUnidadesP2);
		txtUnidadesP2.setColumns(10);
		
		txtUnidadesP3 = new JTextField();
		txtUnidadesP3.setBounds(470, 114, 86, 20);
		contentPanel.add(txtUnidadesP3);
		txtUnidadesP3.setColumns(10);
		
		txtUnidadesP4 = new JTextField();
		txtUnidadesP4.setBounds(470, 145, 86, 20);
		contentPanel.add(txtUnidadesP4);
		txtUnidadesP4.setColumns(10);
		
		lblDescuentos = new JLabel("Descuentos");
		lblDescuentos.setBounds(109, 11, 98, 26);
		contentPanel.add(lblDescuentos);
		
		lblPromociones = new JLabel("Promociones");
		lblPromociones.setBounds(411, 14, 98, 20);
		contentPanel.add(lblPromociones);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(193, 206, 89, 23);
		contentPanel.add(btnGuardar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(327, 206, 89, 23);
		contentPanel.add(btnCerrar);
		
		lblUnidadesP1 = new JLabel("1 a 5 unidades");
		lblUnidadesP1.setBounds(340, 47, 110, 25);
		contentPanel.add(lblUnidadesP1);
		
		lblUnidadesP2 = new JLabel("6 a 10 unidades");
		lblUnidadesP2.setBounds(340, 83, 110, 18);
		contentPanel.add(lblUnidadesP2);
		
		lblUnidadesP3 = new JLabel("11 a 20 unidades");
		lblUnidadesP3.setBounds(340, 112, 110, 25);
		contentPanel.add(lblUnidadesP3);
		
		lblUnidadesP4 = new JLabel("21 a más unidades");
		lblUnidadesP4.setBounds(340, 147, 110, 17);
		contentPanel.add(lblUnidadesP4);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
	}
}
