package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Pasajero {
	protected String identificacion;
	protected String nombre;
	protected List<Silla> sillas;
	protected LocalDate fechaNacimiento;
	public Pasajero(String identificacion, String nombre) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.sillas = new ArrayList<Silla>();
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
		this.sillas.add(silla);
	}
	/**
	 * calcula el valor de un itinerario
	 * @return
	 */
	public abstract long calcularValorItinerario();
}
