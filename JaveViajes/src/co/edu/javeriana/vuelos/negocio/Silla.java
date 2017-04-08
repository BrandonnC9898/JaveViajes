package co.edu.javeriana.vuelos.negocio;

public class Silla {
	private String id;
	private boolean comprada;
	private Trayecto trayecto;
	private VueloEspecifico vueloEspecifico;
	private Pasajero pasajero;
	public Silla(String id, boolean comprada, Trayecto trayecto,
			VueloEspecifico vueloEspecifico, Pasajero pasajero) {
		super();
		this.id = id;
		this.comprada = comprada;
		this.trayecto = trayecto;
		this.vueloEspecifico = vueloEspecifico;
		this.pasajero = pasajero;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isComprada() {
		return comprada;
	}
	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}
	public Trayecto getTrayecto() {
		return trayecto;
	}
	public void setTrayecto(Trayecto trayecto) {
		this.trayecto = trayecto;
	}
	public VueloEspecifico getVueloEspecifico() {
		return vueloEspecifico;
	}
	public void setVueloEspecifico(VueloEspecifico vueloEspecifico) {
		this.vueloEspecifico = vueloEspecifico;
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	@Override
	public String toString() {
		return String.format("%s", id);
	}
	public long calcularValorSilla(){
		return this.vueloEspecifico.calcularValorPasaje();
	}
}
