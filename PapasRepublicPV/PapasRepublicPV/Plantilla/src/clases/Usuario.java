package clases;

public class Usuario {
    private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String Rol;
    
	public Usuario() {

	}

	public int getId() {
		return id;
	}

	public void setCodigo(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getRol() {
		return Rol;
	}
	
	public void setRol(String rol) {
		Rol = rol;
	}
	
    
}