package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;

public class VueloEspecificoNacional extends VueloEspecifico{
	private int iva;

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public VueloEspecificoNacional(LocalDate fecha, String tipoAvion, int capacidad, long tarifa,
			VueloPlaneado vueloPlaneado, int iva) {
		super(fecha, tipoAvion, capacidad, tarifa, vueloPlaneado);
		this.iva = iva;
	}
	/**
	 * Calcula el valor de un pasaje en el vuelo específico tomando en cuenta que es un vuelo nacional
	 */
	public long calcularValorPasaje() {
		if(this.fecha.getMonthValue() == 12 || this.fecha.getMonthValue() == 6){
			return (long) (this.tarifaBasica*0.2)+this.tarifaBasica+this.iva;
		}
		return this.tarifaBasica+this.iva;
	}
}
