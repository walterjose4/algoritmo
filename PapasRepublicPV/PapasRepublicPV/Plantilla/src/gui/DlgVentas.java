package gui;

import clases.Ventas;
import arreglos.ArregloVentas;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DlgVentas extends JDialog implements ActionListener, KeyListener, MouseListener {
    private static final long serialVersionUID = 1L;

    // --- Componentes de la Interfaz de Usuario (UI) ---
    private JTextField txtBuscador;
    private JScrollPane scrollPane;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTable tblVentas;
    private DefaultTableModel modelo;
    private JButton btnRegresar;
    private JLabel lblBuscador;

    // Componentes de UI para la entrada de datos de ventas
    private JLabel lblCodigoVenta;
    private JTextField txtCodigoVenta;
    private JLabel lblFecha;
    private JTextField txtFecha;
    private JLabel lblTotalVenta;
    private JTextField txtTotalVenta;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblCliente;
    private JTextField txtCliente;


    // --- Administrador de Datos ---
    ArregloVentas av = new ArregloVentas();
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Lanza la aplicación.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DlgVentas dialog = new DlgVentas();
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
    public DlgVentas() {
        setResizable(false);
        setTitle("Mantenimiento | Ventas");
        setBounds(100, 100, 810, 610);
        getContentPane().setLayout(null);


        btnModificar = new JButton("Modificar");
        btnModificar.setForeground(Color.BLACK);
        btnModificar.addActionListener(this);
       
        btnModificar.setBounds(636, 42, 150, 23);
        getContentPane().add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.addActionListener(this);
        
        btnEliminar.setBounds(636, 76, 150, 23); 
        getContentPane().add(btnEliminar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(this);
        btnRegresar.setBounds(636, 134, 150, 23);
        getContentPane().add(btnRegresar);

     
        lblBuscador = new JLabel("Buscador:");
        lblBuscador.setBounds(567, 14, 70, 14);
        getContentPane().add(lblBuscador);

        txtBuscador = new JTextField();
        txtBuscador.setToolTipText("Buscador por código, fecha o cliente"); 
        txtBuscador.setBounds(636, 11, 150, 20);
        getContentPane().add(txtBuscador);
        txtBuscador.setColumns(10);
        txtBuscador.addKeyListener(this);

        int yOffset = 180;

        lblCodigoVenta = new JLabel("Código Venta:");
        lblCodigoVenta.setBounds(567, yOffset, 90, 14);
        getContentPane().add(lblCodigoVenta);
        txtCodigoVenta = new JTextField();
        txtCodigoVenta.setEditable(false); 
        txtCodigoVenta.setBounds(660, yOffset, 126, 20);
        getContentPane().add(txtCodigoVenta);
        txtCodigoVenta.setColumns(10);

        yOffset += 30;
        lblFecha = new JLabel("Fecha (dd/MM/yyyy):");
        lblFecha.setBounds(567, 214, 120, 14);
        getContentPane().add(lblFecha);
        txtFecha = new JTextField(); 
        txtFecha.setBounds(690, 211, 96, 20); 
        getContentPane().add(txtFecha);
        txtFecha.setColumns(10);
        

        yOffset += 30;
        lblTotalVenta = new JLabel("Total Venta:");
        lblTotalVenta.setBounds(567, 245, 90, 14);
        getContentPane().add(lblTotalVenta);
        txtTotalVenta = new JTextField();
        txtTotalVenta.setEditable(true); 
        txtTotalVenta.setBounds(660, 239, 126, 20);
        getContentPane().add(txtTotalVenta);
        txtTotalVenta.setColumns(10);

        yOffset += 30;
        lblUsuario = new JLabel("Usuario:"); // Etiqueta para el usuario
        lblUsuario.setBounds(567, 270, 90, 14);
        getContentPane().add(lblUsuario);
        txtUsuario = new JTextField(); // Campo de texto para el usuario
        txtUsuario.setBounds(660, 267, 126, 20);
        getContentPane().add(txtUsuario);
        txtUsuario.setColumns(10);

        yOffset += 30;
        lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(567, 301, 90, 14);
        getContentPane().add(lblCliente);
        txtCliente = new JTextField();
        txtCliente.setBounds(658, 295, 126, 20);
        getContentPane().add(txtCliente);
        txtCliente.setColumns(10);

        // --- Tabla ---
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 547, 549);
        getContentPane().add(scrollPane);

        tblVentas = new JTable();
        tblVentas.addKeyListener(this);
        tblVentas.addMouseListener(this);
        tblVentas.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblVentas);

       
        modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO VENTA");
        modelo.addColumn("FECHA");
        modelo.addColumn("TOTAL VENTA");
        modelo.addColumn("USUARIO");
        modelo.addColumn("CLIENTE");
        tblVentas.setModel(modelo);

        ajustarAnchoColumnas();
        listar("");
        editarFila();
        habilitarEntradas(false); // Las entradas siempre están deshabilitadas inicialmente para modificar/eliminar
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnEliminar) {
            actionPerformedBtnEliminar(arg0);
        } else if (arg0.getSource() == btnModificar) {
            actionPerformedBtnModificar(arg0);
        }
        // Eliminado: else if (arg0.getSource() == btnAdicionar) { ... }
        else if (arg0.getSource() == btnRegresar) {
            actionPerformedBtnRegresar(arg0);
        }
    }

    protected void actionPerformedBtnRegresar(ActionEvent arg0) {
        dispose(); // Cierra el diálogo
    }

    // Eliminado: protected void actionPerformedBtnAdicionar(ActionEvent arg0) { ... }

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        int selectedRow = tblVentas.getSelectedRow();
        if (selectedRow == -1) {
            mensaje("Debe seleccionar una venta para modificar.");
            return;
        }
        int codigoVenta = leerCodigoVenta();
        LocalDate fecha;
        try {
            fecha = leerFecha();
        } catch (DateTimeParseException e) {
            error("Formato de fecha inválido. Use dd/MM/yyyy.", txtFecha);
            return;
        }
        double totalVenta;
        try {
            totalVenta = leerTotalVenta();
            if (totalVenta <= 0) {
                error("El total de venta debe ser un número positivo.", txtTotalVenta);
                return;
            }
        } catch (NumberFormatException e) {
            error("Ingrese un total de venta válido (solo números y no vacío).", txtTotalVenta);
            return;
        }
        String usuario = leerUsuario();
        String cliente = leerCliente();
        Ventas v = new Ventas(codigoVenta, fecha, totalVenta, usuario, cliente);
        boolean exito = av.modificar(v);
        if (exito) {
            listar("");
            mensaje("Venta modificada exitosamente.");
        } else {
            mensaje("Error al modificar la venta.");
        }
        btnModificar.setForeground(Color.BLACK);
        habilitarEntradas(false);
    }

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        int selectedRow = tblVentas.getSelectedRow();
        if (selectedRow == -1) {
            mensaje("Por favor, seleccione una venta para eliminar.");
            btnEliminar.setForeground(Color.BLACK);
            return;
        }
        editarFila();
        habilitarEntradas(false);
        int codigoVenta = leerCodigoVenta();
        int ok = confirmar("¿Desea eliminar CÓDIGO DE VENTA " + codigoVenta + " ?");
        if (ok == JOptionPane.YES_OPTION) {
            boolean exito = av.eliminar(codigoVenta);
            if (exito) {
                listar("");
                mensaje("Venta eliminada exitosamente.");
            } else {
                mensaje("Error al eliminar la venta.");
            }
        }
        btnEliminar.setForeground(Color.BLACK);
    }

    public void keyPressed(KeyEvent arg0) {
        // No implementado
    }

    public void keyReleased(KeyEvent arg0) {
        arg0.consume(); // Consume el evento (detiene el procesamiento adicional)
        if (arg0.getSource() == txtBuscador) {
            String searchText = txtBuscador.getText().trim();
            listar(searchText); // Filtrar la tabla
        } else if (arg0.getSource() == tblVentas) {
            editarFila(); // Actualizar los campos de entrada con los datos de la fila seleccionada
        }
    }

    public void keyTyped(KeyEvent arg0) {
        // No implementado
    }

    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() == tblVentas) {
            // Siempre restablecer los colores de los botones y deshabilitar las entradas al hacer clic en una fila
            btnModificar.setForeground(Color.BLACK);
            btnEliminar.setForeground(Color.BLACK);
            habilitarEntradas(false); // Deshabilitar entradas después de la selección
            editarFila(); // Cargar los datos de la fila seleccionada en los campos
        }
    }

    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

    // Ajusta el ancho de las columnas según el porcentaje del ancho de la tabla
    private void ajustarAnchoColumnas() {
        TableColumnModel tcm = tblVentas.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(anchoColumna(15)); // Código Venta
        tcm.getColumn(1).setPreferredWidth(anchoColumna(20)); // Fecha
        tcm.getColumn(2).setPreferredWidth(anchoColumna(20)); // Total Venta
        tcm.getColumn(3).setPreferredWidth(anchoColumna(20)); // Usuario
        tcm.getColumn(4).setPreferredWidth(anchoColumna(25)); // Cliente
    }

    // Lista las ventas en la tabla, filtrando si se proporciona texto de búsqueda
    void listar(String searchText) {
        modelo.setRowCount(0); // Limpiar filas existentes
        boolean hasSearch = !searchText.isEmpty();
        String searchLower = searchText.toLowerCase();
        java.util.List<Ventas> ventas = av.listarVentas();
        for (Ventas v : ventas) {
            if (hasSearch) {
                String codigoStr = String.valueOf(v.getCodigoVenta());
                String fechaStr = v.getFecha().format(DATE_FORMATTER); // Formatear fecha para búsqueda
                String usuarioLower = v.getUsuario().toLowerCase();
                String clienteLower = v.getCliente().toLowerCase();
                if (!(codigoStr.contains(searchLower) ||
                      fechaStr.contains(searchLower) ||
                      usuarioLower.contains(searchLower) ||
                      clienteLower.contains(searchLower))) {
                    continue;
                }
            }
            Object[] fila = {
                v.getCodigoVenta(),
                v.getFecha().format(DATE_FORMATTER),
                String.format("%.2f", v.getTotalVenta()),
                v.getUsuario(),
                v.getCliente()
            };
            modelo.addRow(fila);
        }
        if (modelo.getRowCount() > 0) {
            tblVentas.setRowSelectionInterval(0, 0);
            editarFila();
        } else {
            limpieza();
        }
    }

    // Carga los datos de la venta seleccionada en los campos de entrada
    void editarFila() {
        int selectedRow = tblVentas.getSelectedRow();
        if (selectedRow == -1 && modelo.getRowCount() > 0) {
            selectedRow = 0;
            tblVentas.setRowSelectionInterval(selectedRow, selectedRow);
        }
        if (selectedRow != -1) {
            int codigo = (int) modelo.getValueAt(selectedRow, 0);
            Ventas v = av.buscar(codigo);
            if (v != null) {
                txtCodigoVenta.setText("" + v.getCodigoVenta());
                txtFecha.setText(v.getFecha().format(DATE_FORMATTER));
                txtTotalVenta.setText(String.format("%.2f", v.getTotalVenta()));
                txtUsuario.setText(v.getUsuario());
                txtCliente.setText(v.getCliente());
            } else {
                limpieza();
            }
        } else {
            limpieza();
        }
    }

    // Limpia todos los campos de entrada (no se necesita código correlativo si no hay "Adicionar")
    void limpieza() {
        txtCodigoVenta.setText(""); // No hay código autogenerado aquí
        txtFecha.setText("");
        txtTotalVenta.setText("");
        txtUsuario.setText("");
        txtCliente.setText("");
    }

    
    void adicionarModificar() {
        
        int codigoVenta = leerCodigoVenta(); 
        if (codigoVenta == -1) {
            error("No se pudo obtener el código de venta a modificar.", (JTextField) null);
            return;
        }

        LocalDate fecha;
        try {
            fecha = leerFecha();
        } catch (DateTimeParseException e) {
            error("Formato de fecha inválido. Use dd/MM/yyyy.", txtFecha);
            return;
        }

        double totalVenta;
        try {
            totalVenta = leerTotalVenta();
            if (totalVenta <= 0) {
                error("El total de venta debe ser un número positivo.", txtTotalVenta);
                return;
            }
        } catch (NumberFormatException e) {
            error("Ingrese un total de venta válido (solo números y no vacío).", txtTotalVenta);
            return;
        }

        String usuario = leerUsuario();
        if (usuario.isEmpty()) {
            error("Ingrese el usuario que realizó la venta.", txtUsuario);
            return;
        }

        String cliente = leerCliente();
        if (cliente.isEmpty()) {
            error("Ingrese el nombre del cliente.", txtCliente);
            return;
        }

        int selectedTableIndex = tblVentas.getSelectedRow();
        if (selectedTableIndex == -1) {
            // Este caso idealmente no debería ocurrir si btnModificar se maneja correctamente
            error("Debe seleccionar una venta para modificar.", tblVentas);
            return;
        }
        int originalCodigo = (int) modelo.getValueAt(selectedTableIndex, 0);
        Ventas v = av.buscar(originalCodigo);
        if (v == null) {
            error("Venta a modificar no encontrada.", (JTextField) null);
            return;
        }

        // Actualizar el objeto de venta existente
        v.setFecha(fecha);
        v.setTotalVenta(totalVenta);
        v.setUsuario(usuario);
        v.setCliente(cliente);

        av.actualizar(v); // Actualizar la venta en el arreglo
        listar(""); // Actualizar tabla
        tblVentas.setRowSelectionInterval(selectedTableIndex, selectedTableIndex); // Mantener fila seleccionada
        mensaje("Venta modificada exitosamente.");
        btnModificar.setForeground(Color.BLACK); // Desactivar modo modificación
        habilitarEntradas(false); // Deshabilitar entradas
    }

    // Muestra un mensaje informativo
    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Muestra un mensaje de error y enfoca un JTextField
    void error(String s, JTextField txt) {
        mensaje(s);
        if (txt != null) {
            txt.setText("");
            txt.requestFocus();
        }
    }

    // Muestra un mensaje de error y enfoca un JTable
    void error(String s, JTable tbl) {
        mensaje(s);
        if (tbl != null) {
            tbl.requestFocusInWindow();
        }
    }

    // Habilita o deshabilita los campos de entrada
    void habilitarEntradas(boolean sino) {
        // txtCodigoVenta siempre es no editable
        txtFecha.setEditable(sino);
        txtTotalVenta.setEditable(sino);
        txtUsuario.setEditable(sino);
        txtCliente.setEditable(sino);
    }

    // Lee el código de venta
    int leerCodigoVenta() {
        try {
            return Integer.parseInt(txtCodigoVenta.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Lee y parsea la fecha
    LocalDate leerFecha() throws DateTimeParseException {
        String fechaStr = txtFecha.getText().trim();
        if (fechaStr.isEmpty()) {
            throw new DateTimeParseException("La fecha no puede estar vacía.", fechaStr, 0);
        }
        return LocalDate.parse(fechaStr, DATE_FORMATTER);
    }

    // Lee el monto total de la venta
    double leerTotalVenta() throws NumberFormatException {
        String totalStr = txtTotalVenta.getText().trim();
        if (totalStr.isEmpty()) {
            throw new NumberFormatException("El total de venta no puede estar vacío.");
        }
        return Double.parseDouble(totalStr);
    }

    // Lee el usuario
    String leerUsuario() {
        return txtUsuario.getText().trim();
    }

    // Lee el nombre del cliente
    String leerCliente() {
        return txtCliente.getText().trim();
    }

    // Calcula el ancho de la columna según el porcentaje
    int anchoColumna(int porcentaje) {
        if (scrollPane.getWidth() > 0) {
            return porcentaje * scrollPane.getWidth() / 100;
        } else {
            return porcentaje * 547 / 100; // Valor de respaldo si el scrollPane aún no está renderizado
        }
    }

    // Muestra un diálogo de confirmación
    int confirmar(String s) {
        return JOptionPane.showConfirmDialog(this, s, "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
    }
}