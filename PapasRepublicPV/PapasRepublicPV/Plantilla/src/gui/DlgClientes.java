package gui;

import clases.Cliente;
import arreglos.ArregloClientes;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.awt.Color;

public class DlgClientes extends JDialog implements ActionListener, KeyListener, MouseListener {
    private static final long serialVersionUID = 1L;

    // Componentes
    private JLabel lblCodigo, lblNombre, lblNúmeroDeDocumento, lblTipoDeDocumento, lblBuscador;
    private JTextField txtCodigo, txtNombre, txtNúmeroDeDocumento, txtBuscador;
    private JScrollPane scrollPane;
    private JButton btnAdicionar, btnModificar, btnEliminar, btnRegresar;
    private JTable tblClientes;
    private DefaultTableModel modelo;
    private JComboBox<String> comboBox;

    // Lógica
    private ArregloClientes ap = new ArregloClientes();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DlgClientes dialog = new DlgClientes();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DlgClientes() {
        setResizable(false);
        setTitle("Mantenimiento | Clientes");
        setBounds(100, 100, 810, 610);
        getContentPane().setLayout(null);

        lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigo);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 35, 70, 23);
        getContentPane().add(lblNombre);

        lblNúmeroDeDocumento = new JLabel("NúmeroDeDocumento");
        lblNúmeroDeDocumento.setBounds(10, 60, 160, 23);
        getContentPane().add(lblNúmeroDeDocumento);

        lblTipoDeDocumento = new JLabel("Tipo de Documento");
        lblTipoDeDocumento.setBounds(10, 85, 144, 23);
        getContentPane().add(lblTipoDeDocumento);

        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(182, 10, 110, 23);
        txtCodigo.setColumns(10);
        getContentPane().add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(182, 35, 253, 23);
        txtNombre.setEditable(false);
        txtNombre.setColumns(10);
        getContentPane().add(txtNombre);

        txtNúmeroDeDocumento = new JTextField();
        txtNúmeroDeDocumento.setBounds(182, 60, 110, 23);
        txtNúmeroDeDocumento.setEditable(false);
        txtNúmeroDeDocumento.setColumns(10);
        getContentPane().add(txtNúmeroDeDocumento);

        comboBox = new JComboBox<>();
        comboBox.setBounds(182, 85, 180, 22);
        comboBox.addItem("DNI");
        comboBox.addItem("RUC");
        comboBox.addItem("PASAPORTE");
        comboBox.addItem("CARNET DE EXTRANJERIA");
        comboBox.setEnabled(false);
        getContentPane().add(comboBox);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 172, 775, 388);
        getContentPane().add(scrollPane);

        tblClientes = new JTable();
        tblClientes.addKeyListener(this);
        tblClientes.addMouseListener(this);
        tblClientes.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblClientes);

        modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("NÚMERO DE DOCUMENTO");
        modelo.addColumn("TIPO DE DOCUMENTO");
        tblClientes.setModel(modelo);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setForeground(Color.BLACK);
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(635, 10, 150, 23);
        getContentPane().add(btnAdicionar);

        btnModificar = new JButton("Modificar");
        btnModificar.setForeground(Color.BLACK);
        btnModificar.addActionListener(this);
        btnModificar.setBounds(635, 35, 150, 23);
        getContentPane().add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(635, 60, 150, 23);
        getContentPane().add(btnEliminar);

        lblBuscador = new JLabel("Buscador");
        lblBuscador.setBounds(579, 89, 61, 14);
        getContentPane().add(lblBuscador);

        txtBuscador = new JTextField();
        txtBuscador.setBounds(635, 86, 149, 20);
        txtBuscador.setColumns(10);
        getContentPane().add(txtBuscador);
        // Buscador: filtra la tabla al escribir
        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarClientes(txtBuscador.getText().trim());
            }
        });

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(695, 138, 89, 23);
        btnRegresar.addActionListener(e -> dispose());
        getContentPane().add(btnRegresar);

        ajustarAnchoColumnas();
        listar(0);
        editarFila();
        habilitarEntradas(false);
    }

    // Métodos de acción
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnEliminar) actionPerformedBtnEliminar(arg0);
        if (arg0.getSource() == btnModificar) actionPerformedBtnModificar(arg0);
        if (arg0.getSource() == btnAdicionar) actionPerformedBtnAdicionar(arg0);
    }
    protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
        if (btnAdicionar.getForeground() == Color.BLACK) {
            btnAdicionar.setForeground(Color.BLUE);
            btnModificar.setForeground(Color.BLACK);
            btnEliminar.setForeground(Color.BLACK);
            habilitarEntradas(true);
            limpieza();
            txtNombre.requestFocus();
        } else {
            adicionarModificar();
        }
    }
    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        if (ap.tamanio() > 0) {
            if (btnModificar.getForeground() == Color.BLACK) {
                btnAdicionar.setForeground(Color.BLACK);
                btnModificar.setForeground(Color.BLUE);
                btnEliminar.setForeground(Color.BLACK);
                editarFila();
                habilitarEntradas(true);
                txtNombre.requestFocus();
            } else {
                adicionarModificar();
            }
        }
    }
    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        if (ap.tamanio() > 0) {
            btnAdicionar.setForeground(Color.BLACK);
            btnModificar.setForeground(Color.BLACK);
            btnEliminar.setForeground(Color.BLUE);
            editarFila();
            habilitarEntradas(false);
            int codigo = leerCodigo(),
                ok = confirmar("¿Desea eliminar CÓDIGO " + codigo + " ?");
            if (ok == 0) {
                Cliente x = ap.buscar(codigo);
                int posFila = tblClientes.getSelectedRow();
                if (posFila == ap.tamanio() - 1) posFila--;
                ap.eliminar(x);
                listar(Math.max(posFila, 0));
                editarFila();
            }
            btnEliminar.setForeground(Color.BLACK);
        }
    }

    // KeyListener
    public void keyPressed(KeyEvent arg0) {}
    public void keyReleased(KeyEvent arg0) {
        arg0.consume();
        editarFila();
    }
    public void keyTyped(KeyEvent arg0) {}

    // MouseListener
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() == tblClientes) {
            btnAdicionar.setForeground(Color.BLACK);
            btnModificar.setForeground(Color.BLACK);
            habilitarEntradas(false);
            editarFila();
        }
    }
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

    // Métodos auxiliares
    private void ajustarAnchoColumnas() {
        TableColumnModel tcm = tblClientes.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(anchoColumna(8));   // Código
        tcm.getColumn(1).setPreferredWidth(anchoColumna(20));  // Nombre
        tcm.getColumn(2).setPreferredWidth(anchoColumna(12));  // Número de Documento
        tcm.getColumn(3).setPreferredWidth(anchoColumna(8));   // Tipo de Documento
    }
    void listar(int posFila) {
        modelo.setRowCount(0);
        for (int i = 0; i < ap.tamanio(); i++) {
        	Cliente x = ap.obtener(i);
            Object[] fila = { x.getCodigo(), x.getNombre(), x.getNumeroDeDocumento(), x.getTipoDeDocumento() };
            modelo.addRow(fila);
        }
        if (modelo.getRowCount() > 0 && posFila >= 0 && posFila < modelo.getRowCount())
            tblClientes.getSelectionModel().setSelectionInterval(posFila, posFila);
    }
    void editarFila() {
        if (ap.tamanio() == 0) {
            limpieza();
        } else {
            int fila = tblClientes.getSelectedRow();
            if (fila == -1) fila = 0;
            Cliente x = ap.obtener(fila);
            txtCodigo.setText("" + x.getCodigo());
            txtNombre.setText(x.getNombre());
            txtNúmeroDeDocumento.setText("" + x.getNumeroDeDocumento());
            comboBox.setSelectedItem(x.getTipoDeDocumento());
        }
    }
    void limpieza() {
        txtCodigo.setText("" + ap.codigoCorrelativo());
        txtNombre.setText("");
        txtNúmeroDeDocumento.setText("");
        comboBox.setSelectedIndex(0);
    }

    void adicionarModificar() {
        int codigo = leerCodigo();
        String nombre = leerNombre();
        if (nombre.length() > 0) {
            String tipoDeDocumento = leerTipoDeDocuemento();
            if (tipoDeDocumento.length() > 0) {
                try {
                    long numeroDeDocumento = leerNumeroDeDocumento();
                    int posFila = tblClientes.getSelectedRow();
                    if (btnAdicionar.getForeground() == Color.BLUE) {
                        if (ap.buscar(numeroDeDocumento) == null) {
                        	Cliente nueva = new Cliente(codigo, nombre, numeroDeDocumento, tipoDeDocumento);
                            posFila = ap.tamanio();
                            ap.adicionar(nueva);
                            limpieza();
                            txtCodigo.setText("" + ap.codigoCorrelativo());
                            txtNombre.requestFocus();
                        } else {
                            error("El número de documento " + numeroDeDocumento + " ya existe", txtNúmeroDeDocumento);
                        }
                    }
                    if (btnModificar.getForeground() == Color.BLUE) {
                    	Cliente x = ap.obtener(posFila);
                        x.setNombre(nombre);
                        x.setCodigo(codigo);
                        x.setNumeroDeDocumento(numeroDeDocumento);
                        x.setTipoDeDocumento(tipoDeDocumento);
                        btnModificar.setForeground(Color.BLACK);
                        habilitarEntradas(false);
                    }
                    listar(posFila);
                } catch (Exception e) {
                    error("Ingrese un NÚMERO DE DOCUMENTO correcto", txtNúmeroDeDocumento);
                }
            }
        }
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    void error(String s, JTextField txt) {
        mensaje(s);
        txt.setText("");
        txt.requestFocus();
    }
    void habilitarEntradas(boolean sino) {
        txtNombre.setEditable(sino);
        if (btnModificar.getForeground() == Color.BLUE)
            txtCodigo.setEditable(false);
        else
            txtCodigo.setEditable(false);
        txtNúmeroDeDocumento.setEditable(sino);
        comboBox.setEnabled(sino);
    }

    int leerCodigo() {
        return Integer.parseInt(txtCodigo.getText().trim());
    }
    String leerTipoDeDocuemento() {
        return (String) comboBox.getSelectedItem();
    }
    String leerNombre() {
        return txtNombre.getText().trim();
    }
    long leerNumeroDeDocumento() {
        return Long.parseLong(txtNúmeroDeDocumento.getText().trim());
    }
    int anchoColumna(int porcentaje) {
        return porcentaje * scrollPane.getWidth() / 100;
    }
    int confirmar(String s) {
        return JOptionPane.showConfirmDialog(this, s, "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
    }

    // Filtro para el buscador
    private void filtrarClientes(String criterio) {
        modelo.setRowCount(0);
        criterio = criterio.toLowerCase();
        for (int i = 0; i < ap.tamanio(); i++) {
        	Cliente x = ap.obtener(i);
            String codigo = String.valueOf(x.getCodigo());
            String nombre = x.getNombre().toLowerCase();
            String numeroDoc = String.valueOf(x.getNumeroDeDocumento());
            String tipoDoc = x.getTipoDeDocumento().toLowerCase();

            if (codigo.contains(criterio) ||
                nombre.contains(criterio) ||
                numeroDoc.contains(criterio) ||
                tipoDoc.contains(criterio)) {
                Object[] fila = { x.getCodigo(), x.getNombre(), x.getNumeroDeDocumento(), x.getTipoDeDocumento() };
                modelo.addRow(fila);
            }
        }
        // Si hay filas, selecciona la primera
        if (modelo.getRowCount() > 0) {
            tblClientes.setRowSelectionInterval(0, 0);
            editarFila();
        } else {
            limpieza();
        }
    }
}
	
	
	