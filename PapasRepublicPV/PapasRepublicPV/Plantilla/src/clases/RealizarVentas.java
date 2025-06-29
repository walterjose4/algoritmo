package clases;

import java.time.LocalDate;
import java.util.ArrayList;

// This class represents a complete sales transaction
public class RealizarVentas {
    private int codigoVenta;
    private String nombreCliente;
    private LocalDate fechaVenta;
    // Now using a list of DetalleVenta objects for type safety and readability
    private ArrayList<DetalleVenta> detallesVenta;
    private double montoTotal;

    // Constructor for a new sale
    public RealizarVentas(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.fechaVenta = LocalDate.now();
        this.detallesVenta = new ArrayList<>();
        this.montoTotal = 0.0;
    }

    // Constructor to load from DB (if needed to list existing sales)
    // Note: details would still need to be loaded separately for existing sales
    public RealizarVentas(int codigoVenta, String nombreCliente, LocalDate fechaVenta, double montoTotal) {
        this.codigoVenta = codigoVenta;
        this.nombreCliente = nombreCliente;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.detallesVenta = new ArrayList<>(); // Details would be loaded separately if loading full sales
    }

    // Getters for the sales transaction properties
    public int getCodigoVenta() {
        return codigoVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public ArrayList<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    // Setters for the sales transaction properties
    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    // Method to add a sales detail (now accepts DetalleVenta object)
    public void agregarDetalle(DetalleVenta detalle) {
        this.detallesVenta.add(detalle);
        calcularMontoTotal(); // Recalculate total every time a detail is added
    }

    // Method to calculate the total amount of the sale based on its details
    public void calcularMontoTotal() {
        double total = 0;
        for (DetalleVenta detalle : detallesVenta) {
            total += detalle.getSubTotal(); // Access subTotal directly from the DetalleVenta object
        }
        this.montoTotal = total;
    }
}
