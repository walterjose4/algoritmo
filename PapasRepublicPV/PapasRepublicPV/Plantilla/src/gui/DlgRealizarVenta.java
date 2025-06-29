package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea; // Nueva importación para mostrar el ticket

import java.time.LocalDate;
import java.time.LocalDateTime; // Importar LocalDateTime para fecha y hora
import java.time.format.DateTimeFormatter; // Importar DateTimeFormatter para formato de fecha/hora

// Importar las clases de lógica de negocio y modelo
import clases.RealizarVentas;
import clases.DetalleVenta; // Importar la nueva clase DetalleVenta
// No se importa la clase Producto directamente aquí
import arreglos.ArregloRealizarVentas; // Se asume que esta clase existe

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DlgRealizarVenta extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JLabel lblProductos;
    private JLabel lblCantidad;
    private JLabel lblNombreCliente;
    private JButton btnAdicionar;
    private JButton btnLimpiar;
    private JButton btnRealizarVenta;
    private JTextField txtCantidad;
    private JTextField txtNombreCliente;
    private JComboBox<String> cboProductos;    
    private JScrollPane scrollPane;

    // Componentes para la fase de añadir productos (la tabla)
    private JTable tblRealizarVenta;
    private DefaultTableModel model; // Modelo para tblRealizarVenta

    // Componente para mostrar el ticket después de la venta (área de texto)
    private JTextArea ticketDisplayArea; // Nuevo componente para la visualización del ticket

    private ArregloRealizarVentas arregloVentas;
    private RealizarVentas ventaActual;

    private DecimalFormat df = new DecimalFormat("0.00");

    // Almacena objetos Map<String, Object> para acceso rápido por nombre de producto
    // Estos mapas contienen idProducto, nombreProducto, precioUnitario, stock
    private Map<String, Map<String, Object>> productosMap; 

    // Información de la empresa y usuario (para el ticket)
    private final String NOMBRE_EMPRESA = "Papas Republic";
    private final String RUC_EMPRESA = "20609424193";
    private String nombreUsuario = "Vendedor01"; // Placeholder: En un sistema real, esto vendría del login

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgRealizarVenta dialog = new DlgRealizarVenta();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgRealizarVenta() {
        setTitle("Ventas | Realizar Venta");
        setBounds(100, 100, 450, 673);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Etiquetas y Campos de texto
        lblProductos = new JLabel("Productos");
        lblProductos.setBounds(10, 35, 80, 14);
        contentPanel.add(lblProductos);

        lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(10, 60, 80, 14);
        contentPanel.add(lblCantidad);

        lblNombreCliente = new JLabel("Nombre del Cliente");
        lblNombreCliente.setBounds(10, 85, 120, 14);
        contentPanel.add(lblNombreCliente);

        // Botones
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(335, 31, 89, 23);
        contentPanel.add(btnAdicionar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setBounds(335, 56, 89, 23);
        contentPanel.add(btnLimpiar);

        btnRealizarVenta = new JButton("Realizar Venta");
        btnRealizarVenta.addActionListener(this);
        btnRealizarVenta.setBounds(316, 81, 108, 23);
        contentPanel.add(btnRealizarVenta);

        // Campos de entrada
        txtCantidad = new JTextField();
        txtCantidad.setBounds(130, 57, 144, 20);
        contentPanel.add(txtCantidad);
        txtCantidad.setColumns(10);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(130, 82, 162, 20);
        contentPanel.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);

        cboProductos = new JComboBox<>();
        cboProductos.setBounds(130, 31, 162, 22);
        contentPanel.add(cboProductos);

        // Panel de desplazamiento (JScrollPane)
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 123, 414, 500);
        contentPanel.add(scrollPane);

        // Inicializar la tabla y el área de texto para el ticket
        configurarTabla(); // Configura el modelo y la JTable
        ticketDisplayArea = new JTextArea();
        ticketDisplayArea.setEditable(false); // El ticket no debe ser editable
        // Usa una fuente monoespaciada para asegurar la alineación del ticket
        ticketDisplayArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12)); 

        // Inicialmente, muestra la tabla en el scroll pane
        scrollPane.setViewportView(tblRealizarVenta);

        // Inicialización de lógica de negocio y carga de datos
        arregloVentas = new ArregloRealizarVentas();
        productosMap = new HashMap<>(); // Inicializa el mapa de productos
        cargarProductos();
        iniciarNuevaVenta();
    }

    // Método para configurar la JTable
    private void configurarTabla() {
        model = new DefaultTableModel();
        model.addColumn("ID Prod.");
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("P. Unit.");
        model.addColumn("Subtotal");
        tblRealizarVenta = new JTable(model); // Inicializa JTable con el modelo
    }

    // Método para cargar productos en el combo box desde la base de datos (a través de ArregloRealizarVentas)
    private void cargarProductos() {
        // Ahora arregloVentas.listarProductos() devuelve ArrayList<Map<String, Object>>
        ArrayList<Map<String, Object>> productos = arregloVentas.listarProductos(); 
        cboProductos.removeAllItems();
        productosMap.clear(); // Limpia el mapa antes de recargar

        for (Map<String, Object> p : productos) {
            String nombreProducto = (String) p.get("nombreProducto");
            cboProductos.addItem(nombreProducto);
            productosMap.put(nombreProducto, p); // Almacena el mapa de detalles del producto por su nombre
        }
    }

    // Método para iniciar una nueva venta (resetea UI y datos)
    private void iniciarNuevaVenta() {
        ventaActual = new RealizarVentas(""); // Crea un nuevo objeto RealizarVentas
        txtNombreCliente.setText("");
        txtCantidad.setText("");
        model.setRowCount(0); // Limpia la tabla de productos
        mostrarTabla(); // Asegura que la tabla sea visible para una nueva venta
    }

    // Método para cambiar el contenido del scroll pane a la vista de tabla
    private void mostrarTabla() {
        scrollPane.setViewportView(tblRealizarVenta);
        scrollPane.revalidate(); // Revalida el layout
        scrollPane.repaint();   // Repinta el componente
    }

    // Método para cambiar el contenido del scroll pane a la vista de ticket
    private void mostrarTicket() {
        scrollPane.setViewportView(ticketDisplayArea);
        scrollPane.revalidate(); // Revalida el layout
        scrollPane.repaint();   // Repinta el componente
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) {
            actionPerformedBtnAdicionar(e);
        } else if (e.getSource() == btnLimpiar) {
            actionPerformedBtnLimpiar(e);
        } else if (e.getSource() == btnRealizarVenta) {
            actionPerformedBtnRealizarVenta(e);
        }
    }

    // Lógica para el botón "Adicionar"
    private void actionPerformedBtnAdicionar(ActionEvent e) {
        String nombreProductoSeleccionado = (String) cboProductos.getSelectedItem();
        if (nombreProductoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el mapa de detalles del producto del productosMap
        Map<String, Object> productoDetalles = productosMap.get(nombreProductoSeleccionado);
        if (productoDetalles == null) {
            JOptionPane.showMessageDialog(this, "No se encontraron detalles para el producto seleccionado.", "Error Interno", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Acceder a los datos específicos usando las claves del mapa
        int idProducto = (int) productoDetalles.get("idProducto");
        double precioUnitario = (double) productoDetalles.get("precioUnitario");

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo.", "Error de Cantidad", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida (solo números enteros).", "Error de Cantidad", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto DetalleVenta
        DetalleVenta nuevoDetalle = new DetalleVenta(idProducto, nombreProductoSeleccionado, cantidad, precioUnitario);
        
        // Añadir a la venta actual
        ventaActual.agregarDetalle(nuevoDetalle);

        // Añadir a la tabla de la GUI
        Object[] rowForTable = {
            nuevoDetalle.getIdProducto(),
            nuevoDetalle.getNombreProducto(),
            nuevoDetalle.getCantidad(),
            df.format(nuevoDetalle.getPrecioUnitario()), // Formateado para visualización
            df.format(nuevoDetalle.getSubTotal())        // Formateado para visualización
        };
        model.addRow(rowForTable);

        // Limpiar el campo de cantidad
        txtCantidad.setText("");
    }

    // Lógica para el botón "Limpiar"
    private void actionPerformedBtnLimpiar(ActionEvent e) {
        iniciarNuevaVenta(); // Reinicia la venta y limpia la tabla/campos
        cargarProductos(); // Recarga los productos para reflejar el stock actualizado desde la 'DB'
    }

    // Lógica para el botón "Realizar Venta"
    private void actionPerformedBtnRealizarVenta(ActionEvent e) {
        String nombreCliente = txtNombreCliente.getText().trim();
        if (nombreCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ventaActual.getDetallesVenta().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Establecer nombre del cliente y calcular el total final
        ventaActual.setNombreCliente(nombreCliente);
        ventaActual.calcularMontoTotal(); // Asegura que el total esté calculado con los últimos detalles

        // Diálogo de confirmación antes de registrar la venta
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea confirmar la venta a " + nombreCliente + "?\nTotal: S/ " + df.format(ventaActual.getMontoTotal()),
                "Confirmar Venta",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = arregloVentas.registrarVenta(ventaActual);
            if (exito) {
                ticketDisplayArea.setText(generarContenidoTicket());
                mostrarTicket();
                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente!", "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
                iniciarNuevaVenta();
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta. Consulte la consola para más detalles.", "Error de Venta", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para generar el contenido del ticket como un String formateado
    private String generarContenidoTicket() {
        StringBuilder ticket = new StringBuilder();
        // Formato para la fecha y hora en el ticket
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(); // Obtener la fecha y hora actuales

        // Encabezado del ticket
        ticket.append("----------------------------------------\n");
        ticket.append(String.format("%22s\n", NOMBRE_EMPRESA)); // Centrar nombre de la empresa
        ticket.append(String.format("%26s\n", "RUC: " + RUC_EMPRESA)); // Centrar RUC
        ticket.append("----------------------------------------\n");
        ticket.append("Fecha: ").append(now.format(dateFormatter)).append("\n");
        ticket.append("Usuario: ").append(nombreUsuario).append("\n");
        ticket.append("Cliente: ").append(ventaActual.getNombreCliente()).append("\n");
        ticket.append("----------------------------------------\n");
        ticket.append("Productos:\n");
        // Cabecera de la tabla de productos en el ticket
        ticket.append(String.format("%-4s %-15s %-6s %-8s %-10s\n", "ID", "Producto", "Cant.", "P.Unit.", "Subtotal"));
        ticket.append("---- --------------- ------ -------- ----------\n");

        // Detalles de los productos en la venta
        for (DetalleVenta detalle : ventaActual.getDetallesVenta()) {
            ticket.append(String.format("%-4d %-15s %-6d %-8s %-10s\n",
                detalle.getIdProducto(),
                detalle.getNombreProducto(),
                detalle.getCantidad(),
                df.format(detalle.getPrecioUnitario()), // Formatear precio unitario
                df.format(detalle.getSubTotal())));        // Formatear subtotal
        }
        ticket.append("----------------------------------------\n");
        // Monto total de la venta
        ticket.append(String.format("Monto Total: S/ %s\n", df.format(ventaActual.getMontoTotal())));
        ticket.append("----------------------------------------\n");

        return ticket.toString();
    }
}