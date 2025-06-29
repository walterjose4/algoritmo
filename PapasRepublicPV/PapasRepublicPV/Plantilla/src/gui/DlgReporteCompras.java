package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import com.toedter.calendar.JDateChooser;

public class DlgReporteCompras extends JDialog {
    
    // Componentes principales
    private JRadioButton rbDiario;
    private JRadioButton rbMensual;
    private ButtonGroup bgTipoReporte;
    
    private JDateChooser dateInicio;
    private JDateChooser dateFin;
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
    
    private JComboBox<String> cbMes;
    private JComboBox<Integer> cbAño;
    private JLabel lblMes;
    private JLabel lblAño;
    
    // Filtros espec�ficos para compras/inventario
    private JComboBox<String> cbProveedor;
    private JComboBox<String> cbInsumo;
    private JLabel lblProveedor;
    private JLabel lblInsumo;
    private JCheckBox chkTodosProveedores;
    private JCheckBox chkTodosInsumos;
    
    private JButton btnGenerar;
    private JButton btnCancelar;
    private JButton btnLimpiar;
    
    private JPanel panelFechas;
    private JPanel panelMensual;
    private JPanel panelCentral;
    private CardLayout cardLayout;
    
    public DlgReporteCompras(Frame parent) {
        super(parent, "Reporte de Compras / Inventario", true);
        initComponents();
        setupLayout();
        setupEventListeners();
        configurarDialog();
    }
    
    private void initComponents() {
        // Radio buttons para tipo de reporte
        rbDiario = new JRadioButton("Reporte Diario", true);
        rbMensual = new JRadioButton("Reporte Mensual");
        bgTipoReporte = new ButtonGroup();
        bgTipoReporte.add(rbDiario);
        bgTipoReporte.add(rbMensual);
        
        // Componentes para reporte diario
        lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaFin = new JLabel("Fecha Fin:");
        dateInicio = new JDateChooser();
        dateFin = new JDateChooser();
        
        // Configurar date choosers
        dateInicio.setDateFormatString("dd/MM/yyyy");
        dateFin.setDateFormatString("dd/MM/yyyy");
        dateInicio.setDate(new java.util.Date());
        dateFin.setDate(new java.util.Date());
        
        // Componentes para reporte mensual
        lblMes = new JLabel("Mes:");
        lblAño = new JLabel("Año:");
        
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        cbMes = new JComboBox<>(meses);
        cbMes.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        
        // Combo de a�os (�ltimos 5 a�os y pr�ximos 2)
        Integer[] años = new Integer[8];
        int añoActual = LocalDate.now().getYear();
        for (int i = 0; i < 8; i++) {
            años[i] = añoActual - 5 + i;
        }
        cbAño = new JComboBox<>(años);
        cbAño.setSelectedItem(añoActual);
        
        // Componentes espec�ficos para compras/inventario
        lblProveedor = new JLabel("Proveedor:");
        lblInsumo = new JLabel("Insumo:");
        
        // Datos de ejemplo - aqu� cargar�as desde tu base de datos
        String[] proveedores = {
            "Seleccionar...", "Proveedor A", "Proveedor B", "Proveedor C", 
            "Distribuidora Central", "Suministros del Norte"
        };
        cbProveedor = new JComboBox<>(proveedores);
        
        String[] insumos = {
            "Seleccionar...", "Papas", "Aceite", "Sal", "Condimentos", 
            "Empaques", "Etiquetas", "Cajas de cart�n"
        };
        cbInsumo = new JComboBox<>(insumos);
        
        chkTodosProveedores = new JCheckBox("Todos los proveedores", true);
        chkTodosInsumos = new JCheckBox("Todos los insumos", true);
        
        // Botones
        btnGenerar = new JButton("Generar Reporte");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
        
        // Configurar estilos de botones
        btnGenerar.setBackground(new Color(46, 125, 50));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnCancelar.setBackground(new Color(211, 47, 47));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnLimpiar.setBackground(new Color(117, 117, 117));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal con padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel superior - T�tulo
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("REPORTE DE COMPRAS / INVENTARIO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(33, 37, 41));
        panelTitulo.add(lblTitulo);
        
        // Panel de tipo de reporte
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTipo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Tipo de Reporte", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        panelTipo.add(rbDiario);
        panelTipo.add(Box.createHorizontalStrut(20));
        panelTipo.add(rbMensual);
        
        // Panel central con CardLayout para alternar entre diario y mensual
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        
        // Panel para reporte diario
        panelFechas = new JPanel(new GridBagLayout());
        panelFechas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Configuraci�n de Reporte Diario",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Fecha inicio
        gbc.gridx = 0; gbc.gridy = 0;
        panelFechas.add(lblFechaInicio, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFechas.add(dateInicio, gbc);
        
        // Fecha fin
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panelFechas.add(lblFechaFin, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFechas.add(dateFin, gbc);
        
        // Filtros para reporte diario
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panelFechas.add(lblProveedor, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFechas.add(cbProveedor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelFechas.add(chkTodosProveedores, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1;
        panelFechas.add(lblInsumo, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFechas.add(cbInsumo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelFechas.add(chkTodosInsumos, gbc);
        
        // Panel para reporte mensual
        panelMensual = new JPanel(new GridBagLayout());
        panelMensual.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Configuraci�n de Reporte Mensual",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Mes
        gbc.gridx = 0; gbc.gridy = 0;
        panelMensual.add(lblMes, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelMensual.add(cbMes, gbc);
        
        // A�o
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panelMensual.add(lblAño, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelMensual.add(cbAño, gbc);
        
        // Filtros para reporte mensual
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panelMensual.add(new JLabel("Proveedor:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // Crear nuevo combo para el panel mensual
        String[] proveedoresMensual = {
            "Seleccionar...", "Proveedor A", "Proveedor B", "Proveedor C", 
            "Distribuidora Central", "Suministros del Norte"
        };
        JComboBox<String> cbProveedorMensual = new JComboBox<>(proveedoresMensual);
        panelMensual.add(cbProveedorMensual, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JCheckBox chkTodosProveedoresMensual = new JCheckBox("Todos los proveedores", true);
        panelMensual.add(chkTodosProveedoresMensual, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1;
        panelMensual.add(new JLabel("Insumo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        String[] insumosMensual = {
            "Seleccionar...", "Papas", "Aceite", "Sal", "Condimentos", 
            "Empaques", "Etiquetas", "Cajas de cart�n"
        };
        JComboBox<String> cbInsumoMensual = new JComboBox<>(insumosMensual);
        panelMensual.add(cbInsumoMensual, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JCheckBox chkTodosInsumosMensual = new JCheckBox("Todos los insumos", true);
        panelMensual.add(chkTodosInsumosMensual, gbc);
        
        // Agregar paneles al CardLayout
        panelCentral.add(panelFechas, "DIARIO");
        panelCentral.add(panelMensual, "MENSUAL");
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.add(btnGenerar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);
        
        // Ensamblar el layout principal
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelTitulo, BorderLayout.NORTH);
        topPanel.add(panelTipo, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(panelCentral, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Inicialmente mostrar panel diario
        cardLayout.show(panelCentral, "DIARIO");
    }
    
    private void setupEventListeners() {
        // Cambio entre tipo de reporte
        rbDiario.addActionListener(e -> cardLayout.show(panelCentral, "DIARIO"));
        
        rbMensual.addActionListener(e -> cardLayout.show(panelCentral, "MENSUAL"));
        
        // Checkboxes para habilitar/deshabilitar combos
        chkTodosProveedores.addActionListener(e -> {
            cbProveedor.setEnabled(!chkTodosProveedores.isSelected());
            if (chkTodosProveedores.isSelected()) {
                cbProveedor.setSelectedIndex(0);
            }
        });
        
        chkTodosInsumos.addActionListener(e -> {
            cbInsumo.setEnabled(!chkTodosInsumos.isSelected());
            if (chkTodosInsumos.isSelected()) {
                cbInsumo.setSelectedIndex(0);
            }
        });
        
        // Bot�n generar
        btnGenerar.addActionListener(e -> generarReporte());
        
        // Bot�n limpiar
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        // Bot�n cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
    
    private void configurarDialog() {
        setSize(500, 550);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
    
    // M�todos de funcionalidad (para implementar la l�gica)
    private void generarReporte() {
        if (validarDatos()) {
            StringBuilder mensaje = new StringBuilder();
            
            if (rbDiario.isSelected()) {
                java.util.Date fechaIni = dateInicio.getDate();
                java.util.Date fechaFin = dateFin.getDate();
                
                mensaje.append("Generando reporte diario de compras/inventario\n");
                mensaje.append("Desde: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaIni)).append("\n");
                mensaje.append("Hasta: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaFin)).append("\n");
            } else {
                String mes = (String) cbMes.getSelectedItem();
                Integer año = (Integer) cbAño.getSelectedItem();
                
                mensaje.append("Generando reporte mensual de compras/inventario\n");
                mensaje.append("Mes: ").append(mes).append(" ").append(año).append("\n");
            }
            
            // Agregar filtros
            if (!chkTodosProveedores.isSelected() && cbProveedor.getSelectedIndex() > 0) {
                mensaje.append("Proveedor: ").append(cbProveedor.getSelectedItem()).append("\n");
            } else {
                mensaje.append("Todos los proveedores\n");
            }
            
            if (!chkTodosInsumos.isSelected() && cbInsumo.getSelectedIndex() > 0) {
                mensaje.append("Insumo: ").append(cbInsumo.getSelectedItem()).append("\n");
            } else {
                mensaje.append("Todos los insumos\n");
            }
            
            JOptionPane.showMessageDialog(this, 
                mensaje.toString(),
                "Reporte de Compras/Inventario", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean validarDatos() {
        if (rbDiario.isSelected()) {
            if (dateInicio.getDate() == null || dateFin.getDate() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione ambas fechas", 
                    "Error de validaci�n", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (dateInicio.getDate().after(dateFin.getDate())) {
                JOptionPane.showMessageDialog(this, 
                    "La fecha de inicio no puede ser posterior a la fecha fin", 
                    "Error de validaci�n", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    private void limpiarCampos() {
        rbDiario.setSelected(true);
        dateInicio.setDate(new java.util.Date());
        dateFin.setDate(new java.util.Date());
        cbMes.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        cbAño.setSelectedItem(LocalDate.now().getYear());
        
        cbProveedor.setSelectedIndex(0);
        cbInsumo.setSelectedIndex(0);
        chkTodosProveedores.setSelected(true);
        chkTodosInsumos.setSelected(true);
        cbProveedor.setEnabled(false);
        cbInsumo.setEnabled(false);
        
        // Mostrar panel diario
        cardLayout.show(panelCentral, "DIARIO");
    }
    
}