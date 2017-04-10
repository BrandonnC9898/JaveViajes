package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class VueloEspecifico {
	private static long CONSECUTIVO = 0;
	protected long codigo;
	protected LocalDate fecha;
	protected String tipoAvion;
	protected int capacidad;
	protected int cuposLibres;
	//elimine esto private long tarifa;
	protected long tarifaBasica;
	private VueloPlaneado vueloPlaneado;
	private List<Trayecto> trayectos;
	private List<Silla> sillas;
	public VueloEspecifico(LocalDate fecha, String tipoAvion,
			int capacidad, long tarifa, VueloPlaneado vueloPlaneado) {
		super();
		CONSECUTIVO++;
		this.codigo = CONSECUTIVO;
		this.fecha = fecha;
		this.tipoAvion = tipoAvion;
		this.capacidad = capacidad;
		this.cuposLibres = capacidad;
		this.tarifaBasica = tarifa;
		this.vueloPlaneado = vueloPlaneado;
		this.trayectos = new ArrayList<Trayecto>();
		this.sillas = new ArrayList<Silla>();
		this.crearSillas(capacidad);
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoAvion() {
		return tipoAvion;
	}
	public void setTipoAvion(String tipoAvion) {
		this.tipoAvion = tipoAvion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCuposLibres() {
		return cuposLibres;
	}
	public void setCuposLibres(int cuposLibres) {
		this.cuposLibres = cuposLibres;
	}
	public long getTarifa() {
		return tarifaBasica;
	}
	public void setTarifa(long tarifa) {
		this.tarifaBasica = tarifa;
	}
	public VueloPlaneado getVueloPlaneado() {
		return vueloPlaneado;
	}
	public void setVueloPlaneado(VueloPlaneado vueloPlaneado) {
		this.vueloPlaneado = vueloPlaneado;
	}
	public List<Trayecto> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(List<Trayecto> trayectos) {
		this.trayectos = trayectos;
	}
	public List<Silla> getSillas() {
		return sillas;
	}
	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}
	@Override
	public String toString() {
		return String.format("%d \t %s \t %s \t %d \t %d \t %d", codigo, fecha, tipoAvion, capacidad, cuposLibres, tarifaBasica);
	}
	/**
	 * crea sillas y les asigna un codigo
	 * @param capacidad
	 */
	private void crearSillas(int capacidad){
		int ii = 1;
		int iii = 1;
		for(int i = 1; i <= capacidad; i++){
			String id = "";
			switch(ii){
			case 1:
			{
				id = id + iii +"A";
			}
			break;
			case 2:
			{
				id = id + iii +"B";
			}
			break;
			case 3:
			{
				id = id + iii +"C";
			}
			break;
			case 4:
			{
				id = id + iii +"D";
			}
			break;
			case 5:
			{
				id = id + iii +"E";
			}
			break;
			}
			Silla silla = new Silla(id, false, null, this, null);
			this.sillas.add(silla);
			if(ii == 5){
				ii = 0;
				iii++;
			}
			ii++;
		}
	}
	/**
	 * 
	 * @return cantidad de sillas disponibles
	 */
	public int comprobarSillasDisponibles(){
		int contador = 0;
		for(Silla silla : this.sillas){
			if(!(silla.isComprada())){
				contador++;
			}
		}
		return contador;
	}
	/**
	 * crea un trayecto y lo agrega a trayectos
	 * @return el trayecto creado
	 */
	public Trayecto crearTrayecto(){
		Trayecto trayecto = new Trayecto(this, null);
		this.agregarTrayecto(trayecto);
		return trayecto;
	}
	private void agregarTrayecto(Trayecto trayecto){
		if(this.trayectos == null){
			this.trayectos = new ArrayList<Trayecto>();
		}
		this.trayectos.add(trayecto);
	}
	/**
	 * busca un trayecto por id
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
	 * @param silla
	 * @param pasajero
	 * @return la silla comprada
	 */
	public Silla marcarSilla(String silla, Pasajero pasajero){
		if(this.sillas != null){
			for(Silla si : this.sillas){
				if(si.getId().equals(silla)){
					si.setComprada(true);
					si.setPasajero(pasajero);
					this.cuposLibres = this.comprobarSillasDisponibles();
					return si;
				}
			}
		}
		return null;
	}
	/**
	 * Calcula el valor de un pasaje en el vuelo específico
	 * @return
	 */
	public abstract long calcularValorPasaje();
}
