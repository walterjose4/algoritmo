// Archivo: MainApp.java
// Este es un ejemplo simple para demostrar como interactuar con la base de datos
// a traves de la clase ArregloRealizarVentas.

package demo; // Puedes ponerlo en cualquier paquete, por ejemplo, 'demo' o 'principal'

import arreglos.ArregloRealizarVentas;
import java.util.ArrayList;
import java.util.Map;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("--- Listando productos desde la base de datos ---");

        // Crear una instancia de ArregloRealizarVentas
        ArregloRealizarVentas arregloVentas = new ArregloRealizarVentas();

        // Obtener la lista de productos de la base de datos
        // El metodo listarProductos() en ArregloRealizarVentas se conecta a la BD
        // y recupera los datos.
        ArrayList<Map<String, Object>> productos = arregloVentas.listarProductos();

        // Verificar si se encontraron productos
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en la base de datos.");
        } else {
            System.out.println("ID | Nombre del Producto       | Precio Unitario | Stock");
            System.out.println("---|---------------------------|-----------------|-------");
            // Iterar sobre la lista de productos e imprimir sus detalles
            for (Map<String, Object> producto : productos) {
                int id = (int) producto.get("idProducto");
                String nombre = (String) producto.get("nombreProducto");
                double precio = (double) producto.get("precioUnitario");
                int stock = (int) producto.get("stock");

                System.out.printf("%-2d | %-25s | %-15.2f | %-5d\n", id, nombre, precio, stock);
            }
        }

        System.out.println("\n--- Fin de la lista de productos ---");
    }
}
