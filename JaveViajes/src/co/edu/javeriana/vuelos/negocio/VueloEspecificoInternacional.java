package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;

public class VueloEspecificoInternacional extends VueloEspecifico{
	private long impuestoSalida;
	
	public long getImpuestoSalida() {
		return impuestoSalida;
	}

	public void setImpuestoSalida(long impuestoSalida) {
		this.impuestoSalida = impuestoSalida;
	}

	public VueloEspecificoInternacional(LocalDate fecha, String tipoAvion, int capacidad, long tarifa,
			VueloPlaneado vueloPlaneado, long impuestoSalida) {
		super(fecha, tipoAvion, capacidad, tarifa, vueloPlaneado);
		this.impuestoSalida = impuestoSalida;
	}
	/**
	 * Calcula el valor de un pasaje en el vuelo específico tomando en cuenta que es Internacional
	 */
	public long calcularValorPasaje() {
		if(this.fecha.getMonthValue() == 12 || this.fecha.getMonthValue() == 6){
			return (long) (this.tarifaBasica*0.2)+this.tarifaBasica+this.impuestoSalida;
		}
		return this.tarifaBasica+this.impuestoSalida;
	}
}
