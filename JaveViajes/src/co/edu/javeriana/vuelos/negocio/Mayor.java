package co.edu.javeriana.vuelos.negocio;

public class Mayor extends Pasajero{
	private boolean requiereAsistencia;

	public boolean isRequiereAsistencia() {
		return requiereAsistencia;
	}

	public void setRequiereAsistencia(boolean requiereAsistencia) {
		this.requiereAsistencia = requiereAsistencia;
	}

	public Mayor(String identificacion, String nombre, boolean requiereAsistencia) {
		super(identificacion, nombre);
		this.requiereAsistencia = requiereAsistencia;
	}
	/**
	 * Calcula el valor de un itinerario tomando en cuenta que es un pasajero mayor de 12 a�os
	 */
	public long calcularValorItinerario() {
		long valorItinerario = 0;
		for(Silla silla : this.sillas){
			valorItinerario = silla.calcularValorSilla() + valorItinerario; 
		}
		if(this.requiereAsistencia){
			return valorItinerario+50;
		}
		return valorItinerario;
	}
}
