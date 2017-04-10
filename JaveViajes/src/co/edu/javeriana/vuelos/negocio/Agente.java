package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Agente {
	private long codigo;
	private String nombre;
	private String email;
	private List<Itinerario> itinerarios;
	public Agente(long codigo, String nombre, String email) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.itinerarios = new ArrayList<Itinerario>();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Itinerario> getItinerarios() {
		return itinerarios;
	}
	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}
	@Override
	public String toString() {
		return String.format("%d \t %s \t %s", codigo, nombre, email);
	}
	/**
	 * crea un itinerario y lo agrega a itinerarios
	 * @param nombre
	 * @return codigo del itinerario creado
	 */
	public long crearItinerario(String nombre){
		Itinerario itinerario = new Itinerario(nombre, this);
		this.agregarItinerario(itinerario);
		return itinerario.getCodigo();
	}
	/**
	 * Agrega un itinerario a su lista de itinerarios
	 * @param itinerario
	 */
	private void agregarItinerario(Itinerario itinerario){
		this.itinerarios.add(itinerario);
	}
	/**
	 * busca un itinerario por codigo
	 * @param codigoIt
	 * @return posicion del itinerario
	 */
	public int buscarItinerario(long codigoIt){
		if(this.itinerarios != null){
			for(Itinerario itinerario : this.itinerarios){
				if(itinerario.getCodigo() == codigoIt){
					return this.itinerarios.indexOf(itinerario);
				}
			}
		}
		return -1;
	}
	/**
	 * crea un pasajero
	 * @param codIt
	 * @param identificacion
	 * @param nombre
	 */
	public void crearPasajero(long codIt, String identificacion, String nombre, boolean tipo, boolean requiere){
		Itinerario itinerario = this.itinerarios.get(this.buscarItinerario(codIt));
		itinerario.crearPasajero(identificacion, nombre, tipo, requiere);
	}
	/**
	 * 
	 * @param codItinerario
	 * @return cantidad de pasajeros en un itinerario
	 */
	public int comprobarPasajeros(long codItinerario){
		if(!this.itinerarios.isEmpty()){
			for(Itinerario itinerario : this.itinerarios){
				if(itinerario.getCodigo() == codItinerario){
					return itinerario.cantidadPasajeros();
				}
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param codItinerario
	 * @return cantidad de trayectos en un itinerario
	 */
	public int cantiTrayectos(long codItinerario){
		if(this.itinerarios != null){
			for(Itinerario itinerario : this.itinerarios){
				if(itinerario.getCodigo() == codItinerario){
					return itinerario.cantiTrayectos();
				}
			}
		}
		return -1;
	}
	/**
	 * agrega un trayecto a un itinerario
	 * @param codItinerario
	 * @param trayecto
	 */
	public void asociarTrayectoItinerario(long codItinerario, Trayecto trayecto){
		if(!this.itinerarios.isEmpty()){
			if(this.buscarItinerario(codItinerario) >= 0){
				Itinerario itinerario = this.itinerarios.get(this.buscarItinerario(codItinerario));
				itinerario.asociarTrayectoItinerario(trayecto);
			}
		}
	}
	/**
	 * 
	 * @param codItinerario
	 * @return si hay cupo disponible en un itinerario o no 
	 */
	public boolean verificarCupo(long codItinerario){
		if(this.itinerarios != null){
			int ret = this.buscarItinerario(codItinerario);
			if(ret >= 0){
				Itinerario itinerario = this.itinerarios.get(ret);
				if(itinerario.verificarCupo()){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * marca un itinerario como comprado
	 * @param codItinerario
	 */
	public void comprarItinerario(long codItinerario){
		int ret = this.buscarItinerario(codItinerario);
		if(ret >= 0){
			Itinerario itinerario = this.itinerarios.get(ret);
			itinerario.setComprado(true);
		}
	}
	/**
	 * 
	 * @param codItinerario
	 * @return si un itinerario ha sido comprado o no
	 */
	public boolean comprobarItinerario(long codItinerario){
		int ret = this.buscarItinerario(codItinerario);
		if(ret >= 0){
			Itinerario itinerario = this.itinerarios.get(ret);
			return itinerario.isComprado();
		}
		return false;
	}
	/**
	 * marca una silla como comprada
	 * @param codItinerario
	 * @param idTrayecto
	 * @param silla
	 */
	public void marcarSilla(long codItinerario, long idTrayecto, String silla){
		int ret = this.buscarItinerario(codItinerario);
		if(ret >= 0){
			Itinerario itinerario = this.itinerarios.get(ret);
			itinerario.marcarSilla(idTrayecto, silla);
		}
	}
	/**
	 * 
	 * @param codItinerario
	 * @return valor del itinerario
	 */
	public long calcularValorItinerario(long codItinerario){
		int ret = this.buscarItinerario(codItinerario);
		if(ret >= 0){
			Itinerario itinerario = this.itinerarios.get(ret);
			return itinerario.calcularValorItinerario();
		}
		return -1;
	}
}
