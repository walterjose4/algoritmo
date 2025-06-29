package clases;

// Importar para LocalDate, que es el tipo de dato para la fecha
import java.time.LocalDate;

public class Ventas {
    private int codigoVenta;
    private LocalDate fecha;      // Atributo para la fecha de la venta
    private double totalVenta;    // Atributo para el total de la venta
    private String usuario;       // Atributo para el usuario que realizó la venta
    private String cliente;       // Atributo para el nombre del cliente

    // Constructor de la clase Ventas
    // Recibe los valores iniciales para todos los atributos
    public Ventas(int codigoVenta, LocalDate fecha, double totalVenta, String usuario, String cliente) {
        this.codigoVenta = codigoVenta;
        this.fecha = fecha;
        this.totalVenta = totalVenta;
        this.usuario = usuario;
        this.cliente = cliente;
    }

    // --- Métodos Getters (para obtener el valor de los atributos) ---

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCliente() {
        return cliente;
    }

    // --- Métodos Setters (para modificar el valor de los atributos) ---

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}