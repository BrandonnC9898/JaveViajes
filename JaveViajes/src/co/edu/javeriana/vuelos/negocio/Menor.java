package co.edu.javeriana.vuelos.negocio;

public class Menor extends Pasajero{
	private boolean viajaSolo;

	public boolean isViajaSolo() {
		return viajaSolo;
	}

	public void setViajaSolo(boolean viajaSolo) {
		this.viajaSolo = viajaSolo;
	}

	public Menor(String identificacion, String nombre, boolean viajaSolo) {
		super(identificacion, nombre);
		this.viajaSolo = viajaSolo;
	}

	public long calcularValorItinerario() {
		long valorItinerario = 0;
		for(Silla silla : this.sillas){
			valorItinerario = silla.calcularValorSilla() + valorItinerario; 
		}
		if(this.viajaSolo){
			return valorItinerario-(long)(valorItinerario*0.35)+90;
		}
		return valorItinerario-(long)(valorItinerario*0.35);
	}
}
