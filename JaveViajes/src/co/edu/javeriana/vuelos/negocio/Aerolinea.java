package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
	private long codigo;
	private String nombre;
	private String cuentaBanco;
	private List<VueloPlaneado> vuelosPlaneados;
	private List<VueloPlaneado> vuelosPlaneadosPedidos;
	public Aerolinea(long codigo, String nombre, String cuentaBanco) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cuentaBanco = cuentaBanco;
		this.vuelosPlaneados = new ArrayList<VueloPlaneado>();
		this.vuelosPlaneadosPedidos = new ArrayList<VueloPlaneado>();
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
	public String getCuentaBanco() {
		return cuentaBanco;
	}
	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}
	public List<VueloPlaneado> getVuelosPlaneados() {
		return vuelosPlaneados;
	}
	public void setVuelosPlaneados(List<VueloPlaneado> vuelosPlaneados) {
		this.vuelosPlaneados = vuelosPlaneados;
	}
	
	public List<VueloPlaneado> getVuelosPlaneadosPedidos() {
		return vuelosPlaneadosPedidos;
	}
	public void setVuelosPlaneadosPedidos(List<VueloPlaneado> vuelosPlaneadosPedidos) {
		this.vuelosPlaneadosPedidos = vuelosPlaneadosPedidos;
	}
	@Override
	public String toString() {
		return String.format("%d %s %s", codigo, nombre,cuentaBanco);
	}
	/**
	 * crea un vuelo planeado y lo agrega a vuelos planeados
	 * @param codigo
	 * @param numeroVuelo
	 * @param diaSemana
	 * @param horaSalida
	 * @param horaLlegada
	 * @param origen
	 * @param destino
	 */
	public void crearVueloPlaneado(long codigo, String numeroVuelo, String diaSemana, LocalTime horaSalida, LocalTime horaLlegada, Ciudad origen, Ciudad destino){
		VueloPlaneado nuevoVP = new VueloPlaneado(codigo, numeroVuelo, diaSemana, horaSalida, horaLlegada, this, origen, destino);
		this.agregarVueloPlaneado(nuevoVP);
	}
	//eliminé la parte que verifica si la lista es null
	public void agregarVueloPlaneado(VueloPlaneado nuevoVP){
		this.vuelosPlaneados.add(nuevoVP);
	}
	/**
	 * busca un vuelo planeado por codigo
	 * @param codigoVP
	 * @return posicion del vuelo planeado
	 */
	public int buscarVueloPlaneado(long codigoVP){
		if(this.vuelosPlaneados != null){
			for(VueloPlaneado vueloPlaneado : this.vuelosPlaneados){
				if(vueloPlaneado.getCodigo() == codigoVP){
					return this.vuelosPlaneados.indexOf(vueloPlaneado);
				}
			}
		}
		return -1;
	}
	/**
	 * crea un vuelo especifico
	 * @param codigoVP
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 * @return codigo del vuelo especifico creado
	 */
	public long crearVueloEspecifico(long codigoVP, LocalDate fecha, String tipoAvion, int capacidad, long tarifa){
		VueloPlaneado vueloPlaneado = this.vuelosPlaneados.get(this.buscarVueloPlaneado(codigoVP));
		return vueloPlaneado.crearVueloEspecifico(fecha, tipoAvion, capacidad, tarifa);
	}
	/**
	 * prepara los vuelos especificos que corresponden a los parametros
	 * @param fecha
	 * @param codOrigen
	 * @param codDest
	 * @param pasajeros
	 * @return si contiene vuelos especificos correspondientes a los parametros
	 */
	public boolean mostrarVuelosEspecificosPedidos(LocalDate fecha, long codOrigen, long codDest, int pasajeros){
		boolean retorno = false;
		if(this.vuelosPlaneadosPedidos != null){
			this.vuelosPlaneados.clear();
		}
		if(this.vuelosPlaneados != null){
			for(VueloPlaneado vueloPlaneado : this.vuelosPlaneados){
				if(vueloPlaneado.mostrarVuelosEspecificosPedidos(fecha, codOrigen, codDest, pasajeros)){
					if(this.vuelosPlaneadosPedidos == null){
						this.vuelosPlaneadosPedidos = new ArrayList<VueloPlaneado>();
					}
					this.vuelosPlaneadosPedidos.add(vueloPlaneado);
					retorno = true;
				}
			}
		}
		return retorno;
	}
	/**
	 * crea un trayecto
	 * @param codVE
	 * @return trayecto creado
	 */
	public Trayecto crearTrayecto(long codVE){
		Trayecto trayecto = null;
		if(this.vuelosPlaneados != null){
			for(VueloPlaneado vueloPlaneado : this.vuelosPlaneados){
				if(vueloPlaneado.buscarVueloEspecifico(codVE) >= 0){
					trayecto = vueloPlaneado.crearTrayecto(codVE);
				}
			}
		}
		return trayecto;
	}
}

