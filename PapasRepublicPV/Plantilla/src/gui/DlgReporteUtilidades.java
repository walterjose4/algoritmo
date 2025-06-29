package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.toedter.calendar.JDateChooser;

public class DlgReporteUtilidades extends JDialog {
    
    // Componentes principales
    private JRadioButton rbDiario;
    private JRadioButton rbMensual;
    private ButtonGroup bgTipoReporte;
    
    private JDateChooser dateInicio;
    private JDateChooser dateFin;
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
    
    private JComboBox<String> cbMes;
    private JComboBox<Integer> cbA�o;
    private JLabel lblMes;
    private JLabel lblA�o;
    
    // Opciones espec�ficas para reporte de utilidades
    private JCheckBox chkIncluirVentas;
    private JCheckBox chkIncluirCompras;
    private JCheckBox chkMostrarDesglose;
    private JCheckBox chkCalcularMargen;
    
    private JComboBox<String> cbTipoCalculo;
    private JLabel lblTipoCalculo;
    
    private JButton btnGenerar;
    private JButton btnCancelar;
    private JButton btnLimpiar;
    private JButton btnVistaPrevia;
    
    private JPanel panelFechas;
    private JPanel panelMensual;
    private JPanel panelCentral;
    private CardLayout cardLayout;
    
    public DlgReporteUtilidades(Frame parent) {
        super(parent, "Reporte de Utilidades", true);
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
        lblA�o = new JLabel("A�o:");
        
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        cbMes = new JComboBox<>(meses);
        cbMes.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        
        // Combo de a�os (�ltimos 5 a�os y pr�ximos 2)
        Integer[] a�os = new Integer[8];
        int a�oActual = LocalDate.now().getYear();
        for (int i = 0; i < 8; i++) {
            a�os[i] = a�oActual - 5 + i;
        }
        cbA�o = new JComboBox<>(a�os);
        cbA�o.setSelectedItem(a�oActual);
        
        // Componentes espec�ficos para utilidades
        chkIncluirVentas = new JCheckBox("Incluir datos de ventas", true);
        chkIncluirCompras = new JCheckBox("Incluir datos de compras", true);
        chkMostrarDesglose = new JCheckBox("Mostrar desglose detallado", false);
        chkCalcularMargen = new JCheckBox("Calcular margen de ganancia", true);
        
        lblTipoCalculo = new JLabel("Tipo de C�lculo:");
        String[] tiposCalculo = {
            "Utilidad Bruta", "Utilidad Neta", "Margen Operativo", "ROI (Retorno de Inversi�n)"
        };
        cbTipoCalculo = new JComboBox<>(tiposCalculo);
        
        // Botones
        btnGenerar = new JButton("Generar Reporte");
        btnVistaPrevia = new JButton("Vista Previa");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
        
        // Configurar estilos de botones
        btnGenerar.setBackground(new Color(46, 125, 50));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnVistaPrevia.setBackground(new Color(25, 118, 210));
        btnVistaPrevia.setForeground(Color.WHITE);
        btnVistaPrevia.setFont(new Font("Arial", Font.BOLD, 12));
        
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
        JLabel lblTitulo = new JLabel("REPORTE DE UTILIDADES");
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
        
        // Separador
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 5, 10);
        JSeparator separator1 = new JSeparator();
        panelFechas.add(separator1, gbc);
        
        // Opciones de c�lculo para reporte diario
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelFechas.add(lblTipoCalculo, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFechas.add(cbTipoCalculo, gbc);
        
        // Checkboxes
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelFechas.add(chkIncluirVentas, gbc);
        
        gbc.gridy = 5;
        panelFechas.add(chkIncluirCompras, gbc);
        
        gbc.gridy = 6;
        panelFechas.add(chkMostrarDesglose, gbc);
        
        gbc.gridy = 7;
        panelFechas.add(chkCalcularMargen, gbc);
        
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
        panelMensual.add(lblA�o, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelMensual.add(cbA�o, gbc);
        
        // Separador
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 5, 10);
        JSeparator separator2 = new JSeparator();
        panelMensual.add(separator2, gbc);
        
        // Opciones de c�lculo para reporte mensual
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelMensual.add(new JLabel("Tipo de C�lculo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        String[] tiposCalculoMensual = {
            "Utilidad Bruta", "Utilidad Neta", "Margen Operativo", "ROI (Retorno de Inversi�n)"
        };
        JComboBox<String> cbTipoCalculoMensual = new JComboBox<>(tiposCalculoMensual);
        panelMensual.add(cbTipoCalculoMensual, gbc);
        
        // Checkboxes para reporte mensual
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JCheckBox chkIncluirVentasMensual = new JCheckBox("Incluir datos de ventas", true);
        panelMensual.add(chkIncluirVentasMensual, gbc);
        
        gbc.gridy = 5;
        JCheckBox chkIncluirComprasMensual = new JCheckBox("Incluir datos de compras", true);
        panelMensual.add(chkIncluirComprasMensual, gbc);
        
        gbc.gridy = 6;
        JCheckBox chkMostrarDesgloseMensual = new JCheckBox("Mostrar desglose detallado", false);
        panelMensual.add(chkMostrarDesgloseMensual, gbc);
        
        gbc.gridy = 7;
        JCheckBox chkCalcularMargenMensual = new JCheckBox("Calcular margen de ganancia", true);
        panelMensual.add(chkCalcularMargenMensual, gbc);
        
        // Agregar paneles al CardLayout
        panelCentral.add(panelFechas, "DIARIO");
        panelCentral.add(panelMensual, "MENSUAL");
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnGenerar);
        panelBotones.add(btnVistaPrevia);
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
        
        // Validaciones de checkboxes
        chkIncluirVentas.addActionListener(e -> {
            if (!chkIncluirVentas.isSelected() && !chkIncluirCompras.isSelected()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe incluir al menos ventas o compras para calcular utilidades",
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
                chkIncluirVentas.setSelected(true);
            }
        });
        
        chkIncluirCompras.addActionListener(e -> {
            if (!chkIncluirVentas.isSelected() && !chkIncluirCompras.isSelected()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe incluir al menos ventas o compras para calcular utilidades",
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
                chkIncluirCompras.setSelected(true);
            }
        });
        
        // Bot�n generar
        btnGenerar.addActionListener(e -> generarReporte());
        
        // Bot�n vista previa
        btnVistaPrevia.addActionListener(e -> mostrarVistaPrevia());
        
        // Bot�n limpiar
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        // Bot�n cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
    
    private void configurarDialog() {
        setSize(520, 600);
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
                
                mensaje.append("Generando reporte de utilidades diario\n");
                mensaje.append("Desde: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaIni)).append("\n");
                mensaje.append("Hasta: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaFin)).append("\n");
            } else {
                String mes = (String) cbMes.getSelectedItem();
                Integer a�o = (Integer) cbA�o.getSelectedItem();
                
                mensaje.append("Generando reporte de utilidades mensual\n");
                mensaje.append("Mes: ").append(mes).append(" ").append(a�o).append("\n");
            }
            
            // Agregar configuraciones
            mensaje.append("Tipo de c�lculo: ").append(cbTipoCalculo.getSelectedItem()).append("\n");
            mensaje.append("Incluye ventas: ").append(chkIncluirVentas.isSelected() ? "S�" : "No").append("\n");
            mensaje.append("Incluye compras: ").append(chkIncluirCompras.isSelected() ? "S�" : "No").append("\n");
            mensaje.append("Desglose detallado: ").append(chkMostrarDesglose.isSelected() ? "S�" : "No").append("\n");
            mensaje.append("Calcular margen: ").append(chkCalcularMargen.isSelected() ? "S�" : "No").append("\n");
            
            JOptionPane.showMessageDialog(this, 
                mensaje.toString(),
                "Reporte de Utilidades", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarVistaPrevia() {
        if (validarDatos()) {
            // Simular datos de vista previa
            String preview = "=== VISTA PREVIA DEL REPORTE DE UTILIDADES ===\n\n" +
                           "Ventas Totales: $15,750.00\n" +
                           "Compras Totales: $8,320.00\n" +
                           "Utilidad Bruta: $7,430.00\n" +
                           "Margen de Ganancia: 47.2%\n\n" +
                           "Este es un ejemplo de los datos que se mostrar�n\n" +
                           "en el reporte completo.";
            
            JTextArea textArea = new JTextArea(preview);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            
            JOptionPane.showMessageDialog(this, 
                scrollPane,
                "Vista Previa - Reporte de Utilidades", 
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
        
        if (!chkIncluirVentas.isSelected() && !chkIncluirCompras.isSelected()) {
            JOptionPane.showMessageDialog(this, 
                "Debe incluir al menos ventas o compras para calcular utilidades", 
                "Error de validaci�n", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void limpiarCampos() {
        rbDiario.setSelected(true);
        dateInicio.setDate(new java.util.Date());
        dateFin.setDate(new java.util.Date());
        cbMes.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        cbA�o.setSelectedItem(LocalDate.now().getYear());
        
        cbTipoCalculo.setSelectedIndex(0);
        chkIncluirVentas.setSelected(true);
        chkIncluirCompras.setSelected(true);
        chkMostrarDesglose.setSelected(false);
        chkCalcularMargen.setSelected(true);
        
        // Mostrar panel diario
        cardLayout.show(panelCentral, "DIARIO");
    }
    
}