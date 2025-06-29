package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.toedter.calendar.JDateChooser;

public class DlgReporteVentas extends JDialog {
    
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
    
    private JButton btnGenerar;
    private JButton btnCancelar;
    private JButton btnLimpiar;
    
    private JPanel panelFechas;
    private JPanel panelMensual;
    private JPanel panelCentral;
    private CardLayout cardLayout;
    
    public DlgReporteVentas(Frame parent) {
        super(parent, "Reporte de Ventas", true);
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
        
        // Combo de años (últimos 5 años y próximos 2)
        Integer[] años = new Integer[8];
        int añoActual = LocalDate.now().getYear();
        for (int i = 0; i < 8; i++) {
            años[i] = añoActual - 5 + i;
        }
        cbAño = new JComboBox<>(años);
        cbAño.setSelectedItem(añoActual);
        
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
        
        // Panel superior - Título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("REPORTE DE VENTAS");
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
            "Selección de Fechas",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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
        
        // Panel para reporte mensual
        panelMensual = new JPanel(new GridBagLayout());
        panelMensual.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Selección de Mes",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Mes
        gbc.gridx = 0; gbc.gridy = 0;
        panelMensual.add(lblMes, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelMensual.add(cbMes, gbc);
        
        // Año
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panelMensual.add(lblAño, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelMensual.add(cbAño, gbc);
        
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
        
        // Botón generar
        btnGenerar.addActionListener(e -> generarReporte());
        
        // Botón limpiar
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        // Botón cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
    
    private void configurarDialog() {
        setSize(450, 400);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Agregar icono si tienes uno
        // setIconImage(iconImage);
    }
    
    // Métodos de funcionalidad (para implementar la lógica)
    private void generarReporte() {
        if (validarDatos()) {
            // Aquí implementarías la lógica para generar el reporte
            if (rbDiario.isSelected()) {
                java.util.Date fechaIni = dateInicio.getDate();
                java.util.Date fechaFin = dateFin.getDate();
                
                JOptionPane.showMessageDialog(this, 
                    "Generando reporte diario desde " + 
                    new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaIni) + 
                    " hasta " + 
                    new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaFin),
                    "Reporte", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                String mes = (String) cbMes.getSelectedItem();
                Integer año = (Integer) cbAño.getSelectedItem();
                
                JOptionPane.showMessageDialog(this, 
                    "Generando reporte mensual de " + mes + " " + año,
                    "Reporte", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private boolean validarDatos() {
        if (rbDiario.isSelected()) {
            if (dateInicio.getDate() == null || dateFin.getDate() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione ambas fechas", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (dateInicio.getDate().after(dateFin.getDate())) {
                JOptionPane.showMessageDialog(this, 
                    "La fecha de inicio no puede ser posterior a la fecha fin", 
                    "Error de validación", 
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
        
        // Mostrar panel diario
        cardLayout.show(panelCentral, "DIARIO");
    }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DlgReporteVentas dialog = new DlgReporteVentas(null);
			dialog.setVisible(true);
		});
	}
}