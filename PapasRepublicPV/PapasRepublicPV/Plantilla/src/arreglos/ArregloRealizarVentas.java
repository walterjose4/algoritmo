// Archivo: arreglos/ArregloRealizarVentas.java
package arreglos;

// Importar clases de negocio
import clases.RealizarVentas;
import clases.DetalleVenta; // Importar la clase DetalleVenta
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Esta clase gestiona ventas y datos de productos, simulando interacciones con la base de datos
public class ArregloRealizarVentas {
    Conexion cx;
    public ArregloRealizarVentas() {
        cx = new Conexion();
    }

    // Listar productos desde la base de datos (sin stock)
    public ArrayList<Map<String, Object>> listarProductos() {
        ArrayList<Map<String, Object>> productos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.conectar().prepareStatement("SELECT idProducto, nombreProducto, precioUnitario FROM Productos");
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> prod = new HashMap<>();
                prod.put("idProducto", rs.getInt("idProducto"));
                prod.put("nombreProducto", rs.getString("nombreProducto"));
                prod.put("precioUnitario", rs.getDouble("precioUnitario"));
                productos.add(prod);
            }
            cx.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Registrar una venta y sus detalles (sin stock)
    public boolean registrarVenta(RealizarVentas venta) {
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;
        try {
            // Insertar cabecera de venta
            String sqlVenta = "INSERT INTO Ventas (nombreCliente, fechaVenta, montoTotal) VALUES (?, ?, ?)";
            psVenta = cx.conectar().prepareStatement(sqlVenta, java.sql.Statement.RETURN_GENERATED_KEYS);
            psVenta.setString(1, venta.getNombreCliente());
            psVenta.setString(2, venta.getFechaVenta().toString());
            psVenta.setDouble(3, venta.getMontoTotal());
            psVenta.executeUpdate();
            rs = psVenta.getGeneratedKeys();
            int idVenta = 0;
            if (rs.next()) {
                idVenta = rs.getInt(1);
            }
            // Insertar detalles de venta (sin stock)
            String sqlDetalle = "INSERT INTO DetalleVenta (idVenta, idProducto, nombreProducto, cantidad, precioUnitario, subTotal) VALUES (?, ?, ?, ?, ?, ?)";
            psDetalle = cx.conectar().prepareStatement(sqlDetalle);
            for (DetalleVenta d : venta.getDetallesVenta()) {
                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, d.getIdProducto());
                psDetalle.setString(3, d.getNombreProducto());
                psDetalle.setInt(4, d.getCantidad());
                psDetalle.setDouble(5, d.getPrecioUnitario());
                psDetalle.setDouble(6, d.getSubTotal());
                psDetalle.executeUpdate();
            }
            cx.desconectar();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Consultar ventas (cabecera y detalles)
    public ArrayList<RealizarVentas> consultaVentas() {
        ArrayList<RealizarVentas> ventas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.conectar().prepareStatement("SELECT * FROM Ventas");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt("idVenta");
                String nombreCliente = rs.getString("nombreCliente");
                java.time.LocalDate fechaVenta = java.time.LocalDate.parse(rs.getString("fechaVenta"));
                double montoTotal = rs.getDouble("montoTotal");
                RealizarVentas venta = new RealizarVentas(idVenta, nombreCliente, fechaVenta, montoTotal);
                // Consultar detalles de la venta
                PreparedStatement psDet = cx.conectar().prepareStatement("SELECT * FROM DetalleVenta WHERE idVenta = ?");
                psDet.setInt(1, idVenta);
                ResultSet rsDet = psDet.executeQuery();
                while (rsDet.next()) {
                    DetalleVenta det = new DetalleVenta(
                        rsDet.getInt("idProducto"),
                        rsDet.getString("nombreProducto"),
                        rsDet.getInt("cantidad"),
                        rsDet.getDouble("precioUnitario")
                    );
                    venta.agregarDetalle(det);
                }
                ventas.add(venta);
            }
            cx.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}