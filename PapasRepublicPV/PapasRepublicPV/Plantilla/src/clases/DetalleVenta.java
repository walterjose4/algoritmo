// Archivo: clases/DetalleVenta.java
package clases;

// This class represents a single item (product) in a sales transaction
public class DetalleVenta {
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal; // Calculated as cantidad * precioUnitario

    // Constructor 
    public DetalleVenta(int idProducto, String nombreProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = cantidad * precioUnitario; // Calculate subtotal upon creation
    }

    // Getters 
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    // Setters 
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subTotal = this.cantidad * this.precioUnitario; // Recalculate subtotal if quantity changes
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subTotal = this.cantidad * this.precioUnitario; // Recalculate subtotal if unit price changes
    }

    // No direct setter for subTotal, as it's a derived value from quantity and unit price
}