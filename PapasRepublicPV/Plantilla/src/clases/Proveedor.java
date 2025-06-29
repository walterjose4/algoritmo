package clases;

public class Proveedor {
	
	// Private attributes
	private int codigo;
	private long numeroDeDocumento;
	private String proveedor, tipoDeDocumento;

	// Constructor
	public Proveedor(int codigo, String proveedor, long numeroDeDocumento, String tipoDeDocumento) {
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.numeroDeDocumento = numeroDeDocumento;
		this.tipoDeDocumento = tipoDeDocumento;
	}

	// Setters and getters
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public void setNumeroDeDocumento(long numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}
	public void setTipoDeDocumento(String tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getProveedor() {
		return proveedor;
	}
	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}
	public long getNumeroDeDocumento() {
		return numeroDeDocumento;
	}
}
