package arreglos; 

import clases.Ventas; 
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArregloVentas {
    Conexion cx;
    public ArregloVentas() {
        cx = new Conexion();
    }

    // Listar todas las ventas desde la base de datos
    public ArrayList<Ventas> listarVentas() {
        ArrayList<Ventas> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.conectar().prepareStatement("SELECT * FROM Ventas");
            rs = ps.executeQuery();
            while (rs.next()) {
                int codigoVenta = rs.getInt("idVenta");
                java.time.LocalDate fecha = java.time.LocalDate.parse(rs.getString("fechaVenta"));
                double totalVenta = rs.getDouble("montoTotal");
                String usuario = ""; // Si tienes usuario, agrega la columna en la tabla y aquí
                String cliente = rs.getString("nombreCliente");
                Ventas v = new Ventas(codigoVenta, fecha, totalVenta, usuario, cliente);
                lista.add(v);
            }
            cx.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Buscar una venta por código en la base de datos
    public Ventas buscar(int codigoVenta) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.conectar().prepareStatement("SELECT * FROM Ventas WHERE idVenta = ?");
            ps.setInt(1, codigoVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                java.time.LocalDate fecha = java.time.LocalDate.parse(rs.getString("fechaVenta"));
                double totalVenta = rs.getDouble("montoTotal");
                String usuario = "";
                String cliente = rs.getString("nombreCliente");
                return new Ventas(codigoVenta, fecha, totalVenta, usuario, cliente);
            }
            cx.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Eliminar una venta por código
    public boolean eliminar(int codigoVenta) {
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("DELETE FROM Ventas WHERE idVenta = ?");
            ps.setInt(1, codigoVenta);
            int rows = ps.executeUpdate();
            cx.desconectar();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modificar una venta
    public boolean modificar(Ventas v) {
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("UPDATE Ventas SET fechaVenta = ?, montoTotal = ?, nombreCliente = ? WHERE idVenta = ?");
            ps.setString(1, v.getFecha().toString());
            ps.setDouble(2, v.getTotalVenta());
            ps.setString(3, v.getCliente());
            ps.setInt(4, v.getCodigoVenta());
            int rows = ps.executeUpdate();
            cx.desconectar();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar una venta (alias de modificar para compatibilidad)
    public boolean actualizar(Ventas v) {
        return modificar(v);
    }
}