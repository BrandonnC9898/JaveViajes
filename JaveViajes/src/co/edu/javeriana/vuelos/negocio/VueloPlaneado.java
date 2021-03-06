package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * poner localDateTime seguramente el dia de la semana
 * @author BrairannC
 *
 */
public class VueloPlaneado {
	private long codigo;
	private String numeroVuelo;
	private String diaSemana;
	private LocalTime horaSalida;
	private LocalTime horaLlegada;
	private Aerolinea aerolinea;
	private List<VueloEspecifico> vuelosEspecificos;
	private Ciudad origen;
	private Ciudad destino;
	private List<VueloEspecifico> vuelosEspecificosPedidos;
	public VueloPlaneado(long codigo, String numeroVuelo, String diaSemana,
			LocalTime horaSalida, LocalTime horaLlegada, Aerolinea aerolinea, Ciudad origen,
			Ciudad destino) {
		super();
		this.codigo = codigo;
		this.numeroVuelo = numeroVuelo;
		this.diaSemana = diaSemana;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.aerolinea = aerolinea;
		this.vuelosEspecificos = new ArrayList<VueloEspecifico>();
		this.vuelosEspecificosPedidos = new ArrayList<VueloEspecifico>();
		this.origen = origen;
		this.destino = destino;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNumeroVuelo() {
		return numeroVuelo;
	}
	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public LocalTime getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}
	public LocalTime getHoraLlegada() {
		return horaLlegada;
	}
	public void setHoraLlegada(LocalTime horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	public List<VueloEspecifico> getVuelosEspecificos() {
		return vuelosEspecificos;
	}
	public void setVuelosEspecificos(List<VueloEspecifico> vuelosEspecificos) {
		this.vuelosEspecificos = vuelosEspecificos;
	}
	public Ciudad getOrigen() {
		return origen;
	}
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	public Ciudad getDestino() {
		return destino;
	}
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	
	public List<VueloEspecifico> getVuelosEspecificosPedidos() {
		return vuelosEspecificosPedidos;
	}
	public void setVuelosEspecificosPedidos(List<VueloEspecifico> vuelosEspecificosPedidos) {
		this.vuelosEspecificosPedidos = vuelosEspecificosPedidos;
	}
	@Override
	public String toString() {
		return String.format("%d \t %s \t %s \t %s \t %s", codigo,numeroVuelo,diaSemana,horaSalida,horaLlegada);
	}
	/**
	 * crea un vuelo especifico, y lo agrega a vuelos especificos
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 * @return codigo del vuelo especifico creado
	 */
	public long crearVueloEspecifico(LocalDate fecha, String tipoAvion, int capacidad, long tarifaBasica, long valorAdicional, String tipo){
		if(tipo.equals("nacional")){
			VueloEspecifico vueloEspecifico = new VueloEspecificoNacional(fecha,tipoAvion,capacidad,tarifaBasica,this,(int)valorAdicional);
			this.agregarVueloEspecifico(vueloEspecifico);
			return vueloEspecifico.getCodigo();
		}
		else{
			VueloEspecifico vueloEspecifico = new VueloEspecificoInternacional(fecha,tipoAvion,capacidad,tarifaBasica,this,valorAdicional);
			this.agregarVueloEspecifico(vueloEspecifico);
			return vueloEspecifico.getCodigo();
		}
		
	}
	/**
	 * Agrega un vuelo específico a su lista de vuelos específicos
	 * @param vueloEspecifico
	 */
	public void agregarVueloEspecifico(VueloEspecifico vueloEspecifico){
		this.vuelosEspecificos.add(vueloEspecifico);
	}
	/**
	 * busca un vuelo especifico por codigo
	 * @param codVE
	 * @return posicion del vuelo especifico
	 */
	public int buscarVueloEspecifico(long codVE){
		if(!this.vuelosEspecificos.isEmpty()){
			for(VueloEspecifico vueloEspecifico : this.vuelosEspecificos){
				if(vueloEspecifico.getCodigo() == codVE){
					return this.vuelosEspecificos.indexOf(vueloEspecifico);
				}
			}
		}
		return -1;
	}
	/**
	 * prepara los vuelos especificos que corresponden a los parametros
	 * @param fecha
	 * @param codOrigen
	 * @param codDest
	 * @param pasajeros
	 * @return si posee vuelos especificos correspondientes a los parametros
	 */
	public boolean mostrarVuelosEspecificosPedidos(LocalDate fecha, long codOrigen, long codDest, int pasajeros){
		if(!this.vuelosEspecificosPedidos.isEmpty()){
			this.vuelosEspecificosPedidos.clear();
		}
		if(this.origen.getCodigo() == codOrigen && this.destino.getCodigo() == codDest && !this.vuelosEspecificos.isEmpty()){
			for(VueloEspecifico vueloEspecifico : this.vuelosEspecificos){
				if(vueloEspecifico.getFecha().equals(fecha) && vueloEspecifico.getCuposLibres() >= pasajeros){
					this.vuelosEspecificosPedidos.add(vueloEspecifico);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * crea un trayecto
	 * @param codVE
	 * @return trayecto creado
	 */
	public Trayecto crearTrayecto(long codVE){
		return (this.vuelosEspecificos.get(this.buscarVueloEspecifico(codVE))).crearTrayecto();
	}
	public boolean mostrarVuelosEspecificosPedidos(LocalDate fecha, long codOrigen, long codDest){
		if(!this.vuelosEspecificosPedidos.isEmpty()){
			this.vuelosEspecificosPedidos.clear();
		}
		if(this.origen.getCodigo() == codOrigen && this.destino.getCodigo() == codDest && !this.vuelosEspecificos.isEmpty()){
			for(VueloEspecifico vueloEspecifico : this.vuelosEspecificos){
				if(vueloEspecifico.getFecha().equals(fecha)){
					this.vuelosEspecificosPedidos.add(vueloEspecifico);
					return true;
				}
			}
		}
		return false;
	}
	public Trayecto crearTrayecto(long codVE, int cantiPasajeros){
		return (this.vuelosEspecificos.get(this.buscarVueloEspecifico(codVE))).crearTrayecto();
	}
}
