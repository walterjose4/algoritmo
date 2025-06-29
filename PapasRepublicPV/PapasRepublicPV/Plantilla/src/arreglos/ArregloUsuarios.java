package arreglos;

import conexion.Conexion;
import clases.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ArregloUsuarios {
	
	Conexion cx;
	
	public ArregloUsuarios() {
		cx = new Conexion();
	}
	
	public boolean insertarUsuario(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO Usuarios (Nombres, Appaterno, Apmaterno, User, Password, Rol) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApPaterno());
			ps.setString(3, usuario.getApMaterno());
			ps.setString(4, usuario.getUsuario());
			ps.setString(5, usuario.getPassword());
			ps.setString(6, usuario.getRol());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
       
	public ArrayList<Usuario> consultaUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Usuarios");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("Id"));
				usuario.setNombre(rs.getString("Nombres"));
				usuario.setApPaterno(rs.getString("apPaterno"));
				usuario.setApMaterno(rs.getString("apMaterno"));
				usuario.setUsuario(rs.getString("user"));
				usuario.setPassword(rs.getString("password"));
				usuario.setRol(rs.getString("Rol"));
				lista.add(usuario);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public boolean eliminarUsuario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Usuarios WHERE id = ?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarUsuario(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE Usuarios SET nombres = ?, apPaterno = ?, apMaterno = ?, user = ?, password = ?, rol = ? WHERE Id = ?");
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApPaterno());
			ps.setString(3, usuario.getApMaterno());
			ps.setString(4, usuario.getUsuario());
			ps.setString(5, usuario.getPassword());
			ps.setString(6, usuario.getRol());
			ps.setInt(7, usuario.getId());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Usuario buscarUsuario(String usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario user = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Usuarios WHERE user = ?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new Usuario();
				user.setNombre(rs.getString("Nombres"));
				user.setApPaterno(rs.getString("apPaterno"));
				user.setApMaterno(rs.getString("apMaterno"));
				user.setUsuario(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setRol(rs.getString("Rol"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<String> obtenerRoles() {
	    ArrayList<String> roles = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = cx.conectar().prepareStatement("SELECT Rol FROM rolesusuarios");
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            roles.add(rs.getString("Rol"));
	        }
	        cx.desconectar();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return roles;
	}

}