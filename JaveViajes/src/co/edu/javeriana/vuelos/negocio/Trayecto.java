package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Trayecto {
	private static int CONSECUTIVO = 0;
	private int id;
	private VueloEspecifico vueloEspecifico;
	private Itinerario itinerario;
	private List<Silla> sillas;
	public Trayecto(VueloEspecifico vueloEspecifico,
			Itinerario itinerario) {
		super();
		CONSECUTIVO++;
		this.id = CONSECUTIVO;
		this.vueloEspecifico = vueloEspecifico;
		this.itinerario = itinerario;
		this.sillas = null;
	}
	public int getCONSECUTIVO() {
		return CONSECUTIVO;
	}
	public void setCONSECUTIVO(int cONSECUTIVO) {
		CONSECUTIVO = cONSECUTIVO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public VueloEspecifico getVueloEspecifico() {
		return vueloEspecifico;
	}
	public void setVueloEspecifico(VueloEspecifico vueloEspecifico) {
		this.vueloEspecifico = vueloEspecifico;
	}
	public Itinerario getItinerario() {
		return itinerario;
	}
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	public List<Silla> getSillas() {
		return sillas;
	}
	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}
	@Override
	public String toString() {
		return String.format("%d", id);
	}
	/**
	 * especifica su itinerario
	 * @param itinerario
	 */
	public void asociarTrayectoItinerario(Itinerario itinerario){
		this.itinerario = itinerario;
	}
	/**
	 * 
	 * @param cantP
	 * @return si hay o no cupo disponible
	 */
	public boolean verificarCupo(int cantP){
		if(this.vueloEspecifico != null){
			if(this.vueloEspecifico.getCuposLibres() >= cantP){
				return true;
			}
		}
		return false;
	}
	/**
	 * marca una silla como comprada, y la agrega a sillas
	 * @param silla
	 * @param pasajero
	 * @return la silla que marco
	 */
	public Silla marcarSilla(String silla, Pasajero pasajero){
		Silla si = this.vueloEspecifico.marcarSilla(silla, pasajero);
		this.marcarSilla(si);
		return si;
	}
	private void marcarSilla(Silla silla){
		if(this.sillas == null){
			this.sillas = new ArrayList<Silla>();
		}
		this.sillas.add(silla);
	}
	/**
	 * 
	 * @return el valor del trayecto
	 */
	public long calcularValor(){
		if(this.vueloEspecifico != null){
			long silla = 0;
			if(this.sillas != null){
				silla = this.sillas.size();
			}
			return this.vueloEspecifico.getTarifa()*silla;
		}
		return -1;
	}
}
