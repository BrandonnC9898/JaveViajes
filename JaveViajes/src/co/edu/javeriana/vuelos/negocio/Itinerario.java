package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private static long CONSECUTIVO = 0;
	private long codigo;
	private String nombre;
	private boolean comprado;
	private Agente agente;
	private List<Trayecto> trayectos;
	private List<Pasajero> pasajeros;
	public Itinerario(String nombre, Agente agente) {
		super();
		CONSECUTIVO++;
		this.codigo = CONSECUTIVO;
		this.nombre = nombre;
		this.comprado = false;
		this.agente = agente;
		this.trayectos = null;
		this.pasajeros = null;
	}
	public static long getCONSECUTIVO() {
		return CONSECUTIVO;
	}
	public static void setCONSECUTIVO(long cONSECUTIVO) {
		CONSECUTIVO = cONSECUTIVO;
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
	public boolean isComprado() {
		return comprado;
	}
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public List<Trayecto> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(List<Trayecto> trayectos) {
		this.trayectos = trayectos;
	}
	public List<Pasajero> getPasajeros() {
		return pasajeros;
	}
	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
	@Override
	public String toString() {
		return String.format("%d %s", codigo, nombre);
	}
	/**
	 * crea un pasajero y lo agrega a la lista de pasajeros
	 * @param identificacion
	 * @param nombre
	 */
	public void crearPasajero(String identificacion, String nombre){
		Pasajero pasajero = new Pasajero(identificacion, nombre);
		this.agregarPasajero(pasajero);
	}
	private void agregarPasajero(Pasajero pasajero){
		if(this.pasajeros == null){
			this.pasajeros = new ArrayList<Pasajero>();
		}
		this.pasajeros.add(pasajero);
	}
	/**
	 * 
	 * @return la cantidad de pasajeros asociados
	 */
	public int cantidadPasajeros(){
		return this.pasajeros.size();
	}
	/**
	 * 
	 * @return cantidad de trayectos asociados
	 */
	public int cantiTrayectos(){
		return this.trayectos.size();
	}
	/**
	 * agrega un trayecto a la trayectos
	 * @param trayecto
	 */
	public void asociarTrayectoItinerario(Trayecto trayecto){
		if(this.trayectos == null){
			this.trayectos = new ArrayList<Trayecto>();
		}
		this.trayectos.add(trayecto);
	}
	/**
	 * 
	 * @return si hay o no disponibilidad de cupo
	 */
	public boolean verificarCupo(){
		boolean retorno = false;
		if(this.trayectos != null){
			int cantP = this.cantidadPasajeros();
			for(Trayecto trayecto : this.trayectos){
				if(trayecto.verificarCupo(cantP)){
					retorno = true;
				}
			}
		}
		return retorno;
	}
	/**
	 * busca un trayeccto por id
	 * @param idTrayecto
	 * @return posicion del trayecto
	 */
	public int buscarTrayecto(long idTrayecto){
		if(this.trayectos != null){
			for(Trayecto trayecto : this.trayectos){
				if(trayecto.getId() == idTrayecto){
					return this.trayectos.indexOf(trayecto);
				}
			}
		}
		return -1;
	}
	/**
	 * marca una silla como comprada
	 * @param idTrayecto
	 * @param silla
	 */
	public void marcarSilla(long idTrayecto, String silla){
		int ret = this.buscarTrayecto(idTrayecto);
		if(ret >= 0){
			Trayecto trayecto = this.trayectos.get(ret);
			for(Pasajero pasajero : this.pasajeros){
				if(pasajero.getSillas() == null){
					Silla si = trayecto.marcarSilla(silla, pasajero);
					pasajero.agregarSilla(si);
					break;
				}
			}
		}
	}
	/**
	 * calcula el valor del itinerario
	 * @return
	 */
	public long calcularValor(){
		if(this.trayectos != null){
			long valor = 0 ;
			for(Trayecto trayecto : this.trayectos){
				valor = trayecto.calcularValor() + valor;
			}
			return valor;
		}
		return -1;
	}
}

