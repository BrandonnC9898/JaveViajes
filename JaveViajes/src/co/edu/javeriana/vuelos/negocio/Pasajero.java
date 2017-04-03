package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Pasajero {
	private String identificacion;
	private String nombre;
	private List<Silla> sillas;
	public Pasajero(String identificacion, String nombre) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.sillas = null;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Silla> getSillas() {
		return sillas;
	}
	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}
	@Override
	public String toString() {
		return String.format("%s %s", identificacion,nombre);
	}
	/**
	 * agrega a sillas, la silla que se le ha asignado
	 * @param silla
	 */
	public void agregarSilla(Silla silla){
		if(this.sillas == null){
			this.sillas = new ArrayList<Silla>();
		}
		this.sillas.add(silla);
	}
}
