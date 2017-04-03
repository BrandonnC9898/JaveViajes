package co.edu.javeriana.vuelos.negocio;

public class Ciudad {
	private long codigo;
	private String nombre;
	public Ciudad(long codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return String.format("%d %s", codigo, nombre);
	}
	
}
