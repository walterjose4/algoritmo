package gui;

import clases.Usuario;
import arreglos.ArregloUsuarios;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;

public class DlgUsuarios extends JDialog implements ActionListener, FocusListener, MouseListener { // Implementar FocusListener
    private static final long serialVersionUID = 1L;

    // Atributos de la clase
    private JLabel lblCodigo;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JTextField txtCodigo;
    private JTextField txtUsuario;
    private JTextField txtContrasenia;
    private JScrollPane scrollPane;
    private JButton btnAdicionar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnRegresar;
    private JTable tblUsuarios;
    private JTextField txtBuscador;
    ArregloUsuarios arregloUsuarios = new ArregloUsuarios();
    DefaultTableModel modelo = new DefaultTableModel();
    ArrayList<Usuario> lista;
    int fila = -1; // Variable para almacenar la fila seleccionada
    Usuario usuario = new Usuario();

    /**
     * Lanza la aplicación.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DlgUsuarios dialog = new DlgUsuarios();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Crea el diálogo.
     */
    public void actualizarTabla() {    	
    	while(modelo.getRowCount() > 0) {
			modelo.removeRow(0); // Limpiar la tabla antes de actualizar
		}
		lista = arregloUsuarios.consultaUsuarios(); // Obtener la lista de usuarios
		for(Usuario usuario : lista) {
			Object fila[] = new Object[7];
			fila[0] = usuario.getId();
			fila[1] = usuario.getUsuario();
			fila[2] = usuario.getPassword();
			fila[3] = usuario.getNombre();
			fila[4] = usuario.getApPaterno();
			fila[5] = usuario.getApMaterno();
			fila[6] = usuario.getRol();
			modelo.addRow(fila);
		}
		tblUsuarios.setModel(modelo);
	}
    
    public void limpiarCampos() {
    	txtBuscador.setText("");
		txtCodigo.setText("");
		txtUsuario.setText("");
		txtContrasenia.setText("");
		txtNombres.setText("");
		txtApPaterno.setText("");
		txtApMaterno.setText("");
		cmbRol.setSelectedIndex(0);
	}
    
    public DlgUsuarios() {
        setResizable(false);
        setTitle("Mantenimiento | Usuarios");
        setBounds(100, 100, 810, 610);
        getContentPane().setLayout(null);

        lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(248, 111, 110, 23);
        getContentPane().add(lblCodigo);

        lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(10, 51, 110, 23);
        getContentPane().add(lblUsuario);

        lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(10, 111, 110, 23);
        getContentPane().add(lblContrasenia);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(362, 111, 110, 23);
        getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);
        txtCodigo.setEditable(false); // El campo de código no es editable

        txtUsuario = new JTextField();
        txtUsuario.setBounds(124, 51, 110, 23);
        getContentPane().add(txtUsuario);
        txtUsuario.setColumns(10);

        txtContrasenia = new JTextField();
        txtContrasenia.setBounds(124, 111, 110, 23);
        getContentPane().add(txtContrasenia);
        txtContrasenia.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 775, 390);
        getContentPane().add(scrollPane);

        tblUsuarios = new JTable();
        tblUsuarios.addMouseListener(this);
        tblUsuarios.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblUsuarios);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setForeground(Color.BLACK);
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(635, 35, 150, 23);
        getContentPane().add(btnAdicionar);

        btnModificar = new JButton("Modificar");
        btnModificar.setForeground(Color.BLACK);
        btnModificar.addActionListener(this);
        btnModificar.setBounds(635, 64, 150, 23);
        getContentPane().add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addMouseListener(this);
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(635, 91, 150, 23);
        getContentPane().add(btnEliminar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(this);
        btnRegresar.setBounds(681, 137, 104, 23);
        getContentPane().add(btnRegresar);
        
        txtBuscador = new JTextField();
        txtBuscador.setBounds(124, 13, 148, 20);
        getContentPane().add(txtBuscador);
        txtBuscador.setColumns(10);
        
        lblBuscador = new JLabel("Buscador");
        lblBuscador.setBounds(10, 16, 65, 14);
        getContentPane().add(lblBuscador);
        
        lblRol = new JLabel("Rol");
        lblRol.setBounds(10, 137, 110, 23);
        getContentPane().add(lblRol);
 
		cmbRol = new JComboBox();
		ArrayList<String> roles = arregloUsuarios.obtenerRoles();
		for (String rol : roles) {
		    cmbRol.addItem(rol);
		}
		cmbRol.setBounds(124, 137, 110, 22);
		getContentPane().add(cmbRol);

        
        lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(10, 80, 110, 23);
        getContentPane().add(lblNombres);
        
        lblApPaterno = new JLabel("Apellido Paterno");
        lblApPaterno.setBounds(248, 51, 110, 23);
        getContentPane().add(lblApPaterno);
        
        txtNombres = new JTextField();
        txtNombres.setColumns(10);
        txtNombres.setBounds(124, 80, 110, 23);
        getContentPane().add(txtNombres);
        
        txtApPaterno = new JTextField();
        txtApPaterno.setColumns(10);
        txtApPaterno.setBounds(362, 51, 110, 23);
        getContentPane().add(txtApPaterno);
        
        lblApellidoMaterno = new JLabel("Apellido Materno");
        lblApellidoMaterno.setBounds(248, 80, 110, 23);
        getContentPane().add(lblApellidoMaterno);
        
        txtApMaterno = new JTextField();
        txtApMaterno.setColumns(10);
        txtApMaterno.setBounds(362, 80, 110, 23);
        getContentPane().add(txtApMaterno);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setBounds(362, 12, 110, 23);
        getContentPane().add(btnLimpiar);
        
    	modelo.addColumn("CODIGO");
    	modelo.addColumn("NOMBRE DE USUARIO");
    	modelo.addColumn("CONTRASEÑA");
    	modelo.addColumn("NOMBRES");
    	modelo.addColumn("APELLIDO PATERNO");
    	modelo.addColumn("APELLIDO MATERNO");
    	modelo.addColumn("ROL");
        actualizarTabla(); // Llamar al método para actualizar la tabla con los datos de los usuarios
    }

    private JLabel lblRol;
    private JComboBox cmbRol;
    private JLabel lblNombres;
    private JLabel lblApPaterno;
    private JTextField txtNombres;
    private JTextField txtApPaterno;
    private JLabel lblApellidoMaterno;
    private JTextField txtApMaterno;
    private JLabel lblBuscador;
    private JButton btnLimpiar;

    public void actionPerformed(ActionEvent arg0) {
    	if (arg0.getSource() == btnLimpiar) {
    		actionPerformedBtnLimpiar(arg0);
    	}
        if (arg0.getSource() == btnEliminar) {
            actionPerformedBtnEliminar(arg0);
        } else if (arg0.getSource() == btnModificar) {
            actionPerformedBtnModificar(arg0);
        } else if (arg0.getSource() == btnAdicionar) {
            actionPerformedBtnAdicionar(arg0);
        } else if (arg0.getSource() == btnRegresar) {
            actionPerformedBtnRegresar(arg0);
        }
    }
    
    protected void actionPerformedBtnRegresar(ActionEvent arg0) {
        dispose(); // Cierra el diálogo
    }

    protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
    	try {
    		
    		if(txtUsuario.getText().equals("")||txtContrasenia.getText().equals("")||txtNombres.getText().equals("")||txtApPaterno.getText().equals("")||txtApMaterno.getText().equals("")||cmbRol.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Debes completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			usuario.setUsuario(txtUsuario.getText());
			usuario.setPassword(txtContrasenia.getText());
			usuario.setNombre(txtNombres.getText());
			usuario.setApPaterno(txtApPaterno.getText());
			usuario.setApMaterno(txtApMaterno.getText());
			usuario.setRol(cmbRol.getSelectedItem().toString());
			
			if(arregloUsuarios.insertarUsuario(usuario)) {
				actualizarTabla();
				limpiarCampos();
				JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al agregar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
    	}

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
    	try {
    		if(txtUsuario.getText().equals("")||txtContrasenia.getText().equals("")||txtNombres.getText().equals("")||txtApPaterno.getText().equals("")||txtApMaterno.getText().equals("")||cmbRol.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Debes completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			usuario.setUsuario(txtUsuario.getText());
			usuario.setPassword(txtContrasenia.getText());
			usuario.setNombre(txtNombres.getText());
			usuario.setApPaterno(txtApPaterno.getText());
			usuario.setApMaterno(txtApMaterno.getText());
			usuario.setRol(cmbRol.getSelectedItem().toString());
			
			if(arregloUsuarios.modificarUsuario(usuario)) {
				actualizarTabla();
				limpiarCampos();
				JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al modificar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			}
    	}
		catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
    	}

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
    	   	try {
    	   		int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el usuario seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
    	   		if (opcion == 0) {
    	   		if(arregloUsuarios.eliminarUsuario(usuario.getId())&&usuario.getId()>0) {
		   			JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
		   			actualizarTabla();
		   			limpiarCampos();
		   		} else {
		   			JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		   			}
    	   		}
    	   	}catch (Exception ex) {
    	   		JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	   	}

    }
 

    // Métodos de FocusListener
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txtBuscador) {
            if (txtBuscador.getText().equalsIgnoreCase("Buscador") && txtBuscador.getForeground() == Color.GRAY) {
                txtBuscador.setText("");
                txtBuscador.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtBuscador) {
            if (txtBuscador.getText().trim().isEmpty()) {
                txtBuscador.setText("Buscador");
                txtBuscador.setForeground(Color.GRAY);
            }
        }
    }
	protected void actionPerformedBtnLimpiar(ActionEvent arg0) {
		limpiarCampos();
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblUsuarios) {
			mouseClickedTblUsuarios(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			mouseClickedBtnEliminar(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedBtnEliminar(MouseEvent arg0) {

	}
	protected void mouseClickedTblUsuarios(MouseEvent arg0) {
		fila = tblUsuarios.getSelectedRow();
		usuario = lista.get(fila);
		txtCodigo.setText(String.valueOf(usuario.getId()));
		txtUsuario.setText(usuario.getUsuario()); 
		txtContrasenia.setText(usuario.getPassword());
		txtNombres.setText(usuario.getNombre());
		txtApPaterno.setText(usuario.getApPaterno());
		txtApMaterno.setText(usuario.getApMaterno());
		cmbRol.setSelectedItem(usuario.getRol());
	}
}
