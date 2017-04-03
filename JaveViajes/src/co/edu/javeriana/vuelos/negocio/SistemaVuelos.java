package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaVuelos {
	private List<Aerolinea> aerolineas;
	private List<Ciudad> ciudades;
	private List<Agente> agentes;
	private List<Aerolinea> aerolineasPedidas;
	public SistemaVuelos() {
		super();
		this.aerolineas = new ArrayList<Aerolinea>();
		this.ciudades = new ArrayList<Ciudad>();
		this.agentes = new ArrayList<Agente>();
		this.aerolineasPedidas = new ArrayList<Aerolinea>();
	}
	public List<Aerolinea> getAerolineas() {
		return aerolineas;
	}
	public void setAerolineas(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	public List<Agente> getAgentes() {
		return agentes;
	}
	public void setAgentes(List<Agente> agentes) {
		this.agentes = agentes;
	}
	public List<Aerolinea> getAerolineasPedidas() {
		return aerolineasPedidas;
	}
	public void setAerolineasPedidas(List<Aerolinea> aerolineasPedidas) {
		this.aerolineasPedidas = aerolineasPedidas;
	}
	/**
	 * Crea una ciudad y la a�ade a su lista de ciudades
	 * @param nombreCiudad
	 * @param codigo
	 */
	public void crearCiudad(String nombreCiudad,int codigo){
		Ciudad nuevaCiudad = new Ciudad(codigo, nombreCiudad);
		this.agregarCiudad(nuevaCiudad);
	}
	//quite el que revisaba si lista de ciudades era null
	private void agregarCiudad(Ciudad ciudad){
		this.ciudades.add(ciudad);
	}
	/**
	 * Crea una aerolinea y la a�ade a su lista de aerolineas
	 * @param codigo
	 * @param nombre
	 * @param cuentaBanco
	 */
	public void crearAerolinea(long codigo, String nombre, String cuentaBanco){
		Aerolinea nuevaAerolinea = new Aerolinea(codigo, nombre, cuentaBanco);
		this.agregarAerolinea(nuevaAerolinea);
	}
	//quite el que revisaba si lista de aerolineas era null
	private void agregarAerolinea(Aerolinea nuevaAerolinea){
		this.aerolineas.add(nuevaAerolinea);
	}
	/**
	 * busca aerolineas por codigo
	 * @param codigoReal
	 * @return la posicion de la aerolinea 
	 */
	//correg� el else que retornaba -1
	public int buscarAerolinea(long codigoReal){
		if(!this.aerolineas.isEmpty()){
			for(Aerolinea aerolinea : this.aerolineas){
				if(aerolinea.getCodigo() == codigoReal){
					return this.aerolineas.indexOf(aerolinea);
				}
			}
		}
		return -1;
	}
	/**
	 * busca un vuelo planeado, primero hallando su aerolinea
	 * @param codigoReal
	 * @param codigoVP
	 * @return posicion del vuelo planeado
	 */
	public int buscarVueloPlaneado(long codigoReal, long codigoVP){
		Aerolinea aerolinea = this.aerolineas.get(this.buscarAerolinea(codigoReal));
		return this.buscarVueloPlaneado(aerolinea, codigoVP);
	}
	/**
	 * busca un vuelo planeado
	 * @param aerolinea
	 * @param codigoVP
	 * @return posicion del vuelo planeado
	 */
	public int buscarVueloPlaneado(Aerolinea aerolinea, long codigoVP){
		return aerolinea.buscarVueloPlaneado(codigoVP);
	}
	/**
	 * agrega un vuelo planeado a la aerolinea seleccionada
	 * @param aerolinea
	 * @param codigo
	 * @param numeroVuelo
	 * @param diaSemana
	 * @param horaSalida
	 * @param horaLlegada
	 * @param origen
	 * @param destino
	 */
	//falta agregar un retorno, por si no encuentra la aerol�nea
	public void agregarVueloPlaneado(long aerolinea, long codigo, String numeroVuelo, String diaSemana, LocalTime horaSalida, LocalTime horaLlegada, long origen, long destino){
		Ciudad orig = this.buscarCiudad(origen);
		Ciudad dest = this.buscarCiudad(destino);
		this.aerolineas.get(this.buscarAerolinea(aerolinea)).crearVueloPlaneado(codigo, numeroVuelo, diaSemana, horaSalida, horaLlegada, orig, dest);
	}
	/**
	 * devuelve la ciudad correspondiente al codigo
	 * @param codigo
	 * @return
	 */
	public Ciudad buscarCiudad(long codigo){
		for(Ciudad ciudad:this.ciudades){
			if(ciudad.getCodigo() == codigo){
				return ciudad;
			}
		}
		return null;
	}
	/**
	 * crea un agente y lo agrega a la lista de agentes
	 * @param codigo
	 * @param nombre
	 * @param email
	 */
	public void crearAgente(long codigo, String nombre, String email){
		Agente nuevoAgente = new Agente(codigo, nombre, email);
		this.agregarAgente(nuevoAgente);
	}
	//cambie la revisi�n de null
	private void agregarAgente(Agente nuevoAgente){
		this.agentes.add(nuevoAgente);
	}
	/**
	 * busca un agente por codigo
	 * @param codigoAg
	 * @return la posicion del agente 
	 */
	public int buscarAgente(long codigoAg){
		if(this.agentes != null){
			for(Agente agente : this.agentes){
				if(agente.getCodigo() == codigoAg){
					return this.agentes.indexOf(agente);
				}
			}
		}
		return -1;
	}
	/**
	 * crea un vuelo especifico, pero primero busca su aerolinea
	 * @param codigoA
	 * @param codigoVP
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 * @return el codigo del vuelo especifico creado
	 */
	public long crearVueloEspecifico(long codigoA, long codigoVP, LocalDate fecha, String tipoAvion, int capacidad, long tarifa){
		Aerolinea aerolinea = this.aerolineas.get(this.buscarAerolinea(codigoA));
		return aerolinea.crearVueloEspecifico(codigoVP, fecha, tipoAvion, capacidad, tarifa);
	}
	/**
	 * crea un itinerario, pero primero busca su agente
	 * @param codigoAgente
	 * @param nombre
	 * @return el codigo del itinerario creado
	 */
	public long crearItinerario(long codigoAgente, String nombre){
		if(this.agentes != null){
			return this.agentes.get(this.buscarAgente(codigoAgente)).crearItinerario(nombre);
		}
		return -1;
	}
	/**
	 * crea un pasajero, pero primero busca su agente
	 * @param codAg
	 * @param codIt
	 * @param identificacion
	 * @param nombre
	 */
	public void crearPasajero(long codAg, long codIt, String identificacion, String nombre){
		this.agentes.get(this.buscarAgente(codAg)).crearPasajero(codIt, identificacion, nombre);
	}
	/**
	 * se encarga de preparar los vuelos especificos que concuerdan con los parametros
	 * @param codAgente
	 * @param codItinerario
	 * @param codOrigen
	 * @param codDest
	 * @param fecha
	 * @return si exite un vuelo especifico que concuerda
	 */
	public boolean mostrarVuelosEspecificosPedidos(long codAgente, long codItinerario, long codOrigen, long codDest, LocalDate fecha){
		boolean retorno = false;
		if(this.aerolineasPedidas != null){
			this.aerolineasPedidas.clear();
		}
		if(this.ciudades != null){
			if(this.buscarCiudad(codDest) != null){
				if(this.aerolineas != null){
					if(this.agentes != null && this.aerolineas != null){
						Agente agente = this.agentes.get(this.buscarAgente(codAgente));
						int cantiPasajeros = agente.comprobarPasajeros(codItinerario);
						for(Aerolinea aerolinea : this.aerolineas){
							if(aerolinea.mostrarVuelosEspecificosPedidos(fecha, codOrigen, codDest,cantiPasajeros)){
								if(this.aerolineasPedidas == null){
									this.aerolineasPedidas = new ArrayList<Aerolinea>();
								}
								this.aerolineasPedidas.add(aerolinea);
								retorno = true;
							}
						}
					}
				}
			}
		}
		return retorno;
	}
	/**
	 * Crea un trayecto
	 * @param codAg
	 * @param codVE
	 */
	public void crearTrayecto(long codAg, long codVE){
		if(this.aerolineas != null){
			for(Aerolinea aerolinea : this.aerolineas){
				Trayecto trayecto = aerolinea.crearTrayecto(codVE);
				if(trayecto != null){
					this.asociarTrayectoItinerario(codAg, codVE,trayecto);
				}
			}
		}
	}
	/**
	 * asocia un trayecto con un itinerario
	 * @param codAgente
	 * @param codItinerario
	 * @param trayecto
	 */
	private void asociarTrayectoItinerario(long codAgente, long codItinerario, Trayecto trayecto){
		if(this.agentes != null){
			if(this.buscarAgente(codAgente) >= 0){
				Agente agente = this.agentes.get(this.buscarAgente(codAgente));
				agente.asociarTrayectoItinerario(codItinerario, trayecto);
			}
		}
	}
	/**
	 * 
	 * @param codAgente
	 * @param codItinerario
	 * @return si hay cupo disponible en el itinerario
	 */
	public boolean verificarCupo(long codAgente, long codItinerario){
		if(this.agentes != null){
			int ret = this.buscarAgente(codAgente);
			if(ret >= 0){
				Agente agente = this.agentes.get(ret);
				if(agente.verificarCupo(codItinerario)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * marca al itinerario como comprado
	 * @param codAgente
	 * @param codItinerario
	 */
	public void comprarItinerario(long codAgente, long codItinerario){
		int ret = this.buscarAgente(codAgente);
		if(ret >= 0){
			Agente agente = this.agentes.get(ret);
			agente.comprarItinerario(codItinerario);
		}
	}
	/**
	 * 
	 * @param codAgente
	 * @param codItinerario
	 * @return cantidad de pasajeros asociados al itinerario
	 */
	public int pasajerosItinerario(long codAgente,long codItinerario){
		if(this.agentes != null){
			int ret = this.buscarAgente(codAgente);
			if(ret >= 0){
				Agente agente = this.agentes.get(ret);
				return agente.comprobarPasajeros(codItinerario);
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param codAgente
	 * @param codItinerario
	 * @return cantidad de trayectos en el itinerario se�alado
	 */
	public int cantiTrayectos(long codAgente,long codItinerario){
		if(this.agentes != null){
			int ret = this.buscarAgente(codAgente);
			if(ret >= 0){
				Agente agente = this.agentes.get(ret);
				return agente.cantiTrayectos(codItinerario);
			}
		}
		return -1;
	}
	/**
	 * marca una silla como comprada
	 * @param codAgente
	 * @param codItinerario
	 * @param idTrayecto
	 * @param silla
	 */
	public void marcarSilla(long codAgente, long codItinerario, long idTrayecto, String silla){
		if(this.agentes != null){
			int ret = this.buscarAgente(codAgente);
			if(ret >= 0){
				Agente agente = this.agentes.get(ret);
				agente.marcarSilla(codItinerario, idTrayecto, silla);
			}
		}
	}
	/**
	 * 
	 * @param codAgente
	 * @param codItinerario
	 * @return si esta o no comprado un itinerario
	 */
	public boolean comprobarItinerario(long codAgente, long codItinerario){
		int ret = this.buscarAgente(codAgente);
		if(ret >= 0){
			Agente agente = this.agentes.get(ret);
			return agente.comprobarItinerario(codItinerario);
		}
		return false;
	}
	/**
	 * 
	 * @param codAgente
	 * @param codItinerario
	 * @return valor del itinerario 
	 */
	public long calcularValor(long codAgente, long codItinerario){
		int ret = this.buscarAgente(codAgente);
		if(ret >= 0){
			Agente agente = this.agentes.get(ret);
			return agente.calcularValor(codItinerario);
		}
		return -1;
	}
}
