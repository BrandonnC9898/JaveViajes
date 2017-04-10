package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ISistemaViajes {

	List<Aerolinea> getAerolineas(); 
	void setAerolineas(List<Aerolinea> aerolineas); 
	List<Ciudad> getCiudades();
	void setCiudades(List<Ciudad> ciudades); 
	List<Agente> getAgentes();
	void setAgentes(List<Agente> agentes); 
	List<Aerolinea> getAerolineasPedidas(); 
	void setAerolineasPedidas(List<Aerolinea> aerolineasPedidas); 

	void crearCiudad(String nombreCiudad,int codigo);
	void crearAerolinea(long codigo, String nombre, String cuentaBanco);
	void agregarVueloPlaneado(long aerolinea, long codigo, String numeroVuelo, String diaSemana, LocalTime horaSalida, LocalTime horaLlegada, long origen, long destino);
	void crearAgente(long codigo, String nombre, String email);
	int buscarAerolinea(long codigoReal);
	int buscarVueloPlaneado(long codigoReal, long codigoVP);
	long crearVueloEspecifico(long codigoA, long codigoVP, LocalDate fecha, String tipoAvion, int capacidad, long tarifa, long valorAdicional, String tipo);
	int buscarAgente(long codigoAg);
	long crearItinerario(long codigoAgente, String nombre);
	void crearPasajero(long codAg, long codIt, String identificacion, String nombre, boolean tipo, boolean requiere);
	boolean mostrarVuelosEspecificosPedidos(long codAgente, long codItinerario, long codOrigen, long codDest, LocalDate fecha);
	void crearTrayecto(long codAg, long codVE);
	int buscarItinerario(long codItinerario);
	boolean verificarCupo(long codAgente, long codItinerario);
	void comprarItinerario(long codAgente, long codItinerario);
	long calcularValorItinerario(long codAgente, long codItinerario);
	int pasajerosItinerario(long codAgente,long codItinerario);
	int cantiTrayectos(long codAgente,long codItinerario);
	void marcarSilla(long codAgente, long codItinerario, long idTrayecto, String silla);
	boolean comprobarItinerario(long codAgente, long codItinerario);
}
