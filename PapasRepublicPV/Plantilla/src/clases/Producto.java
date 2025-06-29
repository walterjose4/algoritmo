package clases;

public class Producto {
    private String nombre;
    private String item;
    private int cantidadDisponible;
    private double precioDeVenta;

    public Producto(String nombre, String item, int cantidadDisponible, double precioDeVenta) {
        this.nombre = nombre;
        this.item = item;
        this.cantidadDisponible = cantidadDisponible;
        this.precioDeVenta = precioDeVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }
}
