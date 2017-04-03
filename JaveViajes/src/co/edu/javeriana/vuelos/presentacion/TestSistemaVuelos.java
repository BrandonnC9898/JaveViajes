package co.edu.javeriana.vuelos.presentacion;

import java.time.LocalDate;

import java.util.Scanner;
import java.util.StringTokenizer;

import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.Aerolinea;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;
import co.edu.javeriana.vuelos.negocio.VueloEspecifico;
import co.edu.javeriana.vuelos.negocio.Agente;
import co.edu.javeriana.vuelos.negocio.Itinerario;
import co.edu.javeriana.vuelos.negocio.Trayecto;
import co.edu.javeriana.vuelos.negocio.Silla;

public class TestSistemaVuelos {
	/**
	 * Parte del sistema que se comunica con el usuario
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numeroR = 0;
		String linea;
		SistemaVuelos sistemaVuelo = new SistemaVuelos();
		Scanner scanner = new Scanner(System.in);
		do{
			System.out.println("Menú:");
			System.out.println("Opción 1: Ingresar ciudades del sistema.");
			System.out.println("Opción 2: Ingresar aerolineas y vuelos planeados del sistema.");
			System.out.println("Opción 3: Ingresar agentes del sistema.");
			System.out.println("Opción 4: Agregar un vuelo especifico para un vuelo planeado.");
			System.out.println("Opción 5: Mostrar aerolineas, vuelos planeados y vuelos especificos del sistema.");
			System.out.println("Opción 6: Agregar un itinerario para un agente.");
			System.out.println("Opción 7: Agregar un trayecto asociado a un itinerario.");
			System.out.println("Opción 8: Mostrar agentes, itinerarios y trayectos");
			System.out.println("Opción 9: Comprar un itinearario asignando a cada pasajero una silla sobre cada trayecto del itinerario.");
			System.out.println("Opción 10: Tiquete electrónico");
			System.out.println("Opción 11: Reporte vuelos especificos: dado el nombre de una ciudad de origen y el de una ciudad destino.");
			System.out.println("Opción 12: Terminar.");
			System.out.println("Ingrese la opción:");
			String numero = scanner.nextLine();
			numeroR = Integer.parseInt(numero);
			switch(numeroR){
			case 1:
			{
				System.out.println("Opción 1: Ingresar ciudades del sistema.");
				System.out.println("Ingrese el nombre del archivo (incluya la extesión)");
				linea = scanner.nextLine();
				if(ManejoArchivos.leerCiudad(linea,sistemaVuelo)){
					System.out.println("Datos leídos correctamente");
				}
				else{
					System.out.println("Error");
				}
			}
			break;
			case 2:{
				System.out.println("Opción 2: Ingresar aerolineas y vuelos planeados del sistema.");
				System.out.println("Ingrese el nombre del archivo (incluya la extesión)");
				linea = scanner.nextLine();
				if(ManejoArchivos.ingresarAerolinea(linea,sistemaVuelo)){
					System.out.println("Datos leídos correctamente");
				}
				else{
					System.out.println("Error");
				}
			}
			break;
			case 3:
			{
				System.out.println("Opción 3: Ingresar agentes del sistema.");
				System.out.println("Ingrese el nombre del archivo (incluya la extesión)");
				linea = scanner.nextLine();
				if(ManejoArchivos.ingresarAgente(linea,sistemaVuelo)){
					System.out.println("Datos leídos correctamente");
				}
				else{
					System.out.println("Error");
				}
			}
			break;
			case 4:{
				System.out.println("Opción 4: Agregar un vuelo especifico para un vuelo planeado.");
				System.out.println("AEROLINEAS REGISTRADAS:");
				System.out.println("Código   Nombre");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAerolineas() != null){
					for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
						System.out.println(aerolineaI.toString());
					}
					System.out.println("--------------------------------------------------\n");
				}
				else{
					System.out.println("No hay aerolineas regristradas");
				}
				System.out.println("Ingrese el codigo de la aerolinea a la cual pertenece el vuelo especifico");
				linea = scanner.nextLine();
				long codAerolinea = Long.parseLong(linea.trim());
				int iAerolinea = sistemaVuelo.buscarAerolinea(codAerolinea);
				Aerolinea aerolinea = sistemaVuelo.getAerolineas().get(iAerolinea);
				System.out.println("LISTA DE VUELOS PLANEADOS DE LA AEROLINEA:"+aerolinea.getNombre());
				System.out.println("--------------------------------------------------");
				System.out.println("Código  Número de vuelo Día de semana  Hora salida  Hora llegada Origen Destino");
				if(aerolinea.getVuelosPlaneados() != null){
					for(VueloPlaneado vueloPlaneadoI : aerolinea.getVuelosPlaneados()){
						System.out.println(vueloPlaneadoI.toString() + " " + vueloPlaneadoI.getOrigen().getNombre() + " " + vueloPlaneadoI.getDestino().getNombre());
					}
					System.out.println("--------------------------------------------------\n");
				}
				else{
					System.out.println("No hay vuelos planeados regristrados");
				}
				System.out.println("Ingrese el codigo del vuelo planeado al cual pertenece el vuelo especifico");
				linea = scanner.nextLine(); 
				long codVP = Long.parseLong(linea.trim());
				System.out.println("Ingrese los datos del vuelo especifico: fecha(YYYY-MM-DD), tipo de avion, capacidad y tarifa (US)");
				linea = scanner.nextLine();
				StringTokenizer organiza = new StringTokenizer(linea.trim()," ");
				String fech = organiza.nextToken();
				String tipoAvion = organiza.nextToken();
				String capacida = organiza.nextToken();
				String tarif = organiza.nextToken();
				LocalDate fecha = Utils.conversorFecha(fech);
				int capacidad = Integer.parseInt(capacida);
				int tarifa = Integer.parseInt(tarif);
				Long codigoVE = sistemaVuelo.crearVueloEspecifico(codAerolinea, codVP, fecha, tipoAvion, capacidad, tarifa);
				System.out.println("El codigo del vuelo especifico creado es:" + codigoVE);
			}
			break;
			case 5:{
				System.out.println("Opción 5: Mostrar aerolineas, vuelos planeados y vuelos especificos del sistema.");
				System.out.println("AEROLINEAS REGISTRADAS:");
				System.out.println("Código   Nombre");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAerolineas() != null){
					for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
						System.out.println(aerolineaI.toString());
						System.out.println("LISTA DE VUELOS PLANEADOS:");
						System.out.println("--------------------------------------------------");
						if(aerolineaI.getVuelosPlaneados() != null){
							for(VueloPlaneado vueloPlaneadoI : aerolineaI.getVuelosPlaneados()){
								String origenI = vueloPlaneadoI.getOrigen().getNombre();
								String destinoI = vueloPlaneadoI.getDestino().getNombre();
								System.out.println(vueloPlaneadoI.toString() + " " + origenI + " " + destinoI);
								System.out.println("LISTA DE VUELOS ESPECIFICOS");
								System.out.println("--------------------------------------------------");
								if(vueloPlaneadoI.getVuelosEspecificos() != null){
									for(VueloEspecifico vueloEspecifico : vueloPlaneadoI.getVuelosEspecificos()){
										System.out.println(vueloEspecifico.toString());
									}
								}
								else{
									System.out.println("No hay vuelos especificos registrados");
								}
							}
							System.out.println("--------------------------------------------------\n");
						}
						else{
							System.out.println("No hay vuelos planeados regristrados");
						}
					}
					System.out.println("--------------------------------------------------\n");
				}
				else{
					System.out.println("No hay aerolineas regristradas");
				}
			}
			break;
			case 6:{
				System.out.println("Opción 6: Agregar un itinerario para un agente.");
				System.out.println("AGENTES REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAgentes() != null){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del agente al cual pertenece el nuevo itinerario");
				linea = scanner.nextLine();
				long codAgente= Long.parseLong(linea.trim());
				System.out.println("Ingrese el nombre del nuevo itinerario");
				String nombreItinerario = scanner.nextLine();
				long codItinerario = sistemaVuelo.crearItinerario(codAgente, nombreItinerario);
				System.out.println("Ingrese la cantidad de pasajeros");
				int cantiPasajeros = Integer.parseInt(scanner.nextLine().trim());
				System.out.println("Ingrese los datos para cada pasajero, separadas por linea. Para cada dato separe con *");
				for(int i = 0; i < cantiPasajeros; i++){
					linea = scanner.nextLine();
					StringTokenizer organiza = new StringTokenizer(linea.trim(), "*");
					String identificacion = organiza.nextToken();
					String nombre = organiza.nextToken();
					sistemaVuelo.crearPasajero(codAgente, codItinerario, identificacion, nombre);
				}
				System.out.println("El itinerario se creo con el codigo: " + codItinerario);
				//int iAgente = sistemaVuelo.buscarAgente(codAgente);
			}
			break;
			case 7:{
				System.out.println("Opción 7: Agregar un trayecto asociado a un itinerario.");
				System.out.println("AGENTES REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAgentes() != null){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del agente que desea seleccionar");
				linea = scanner.nextLine();
				long codAgente = Long.parseLong(linea.trim());
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("ITINERARIOS REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(agente.getItinerarios() != null){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del itinerario que desea seleccionar");
				linea = scanner.nextLine();
				long codItinerario= Long.parseLong(linea.trim());
				int i = 0;
				while(i < 2){
					System.out.println("CIUDADES REGISTRADAS");
					System.out.println("--------------------------------------------------");
					if(sistemaVuelo.getCiudades() != null){
						for(Ciudad ciudad : sistemaVuelo.getCiudades()){
							System.out.println(ciudad.toString());
						}
					}
					else{
						System.out.println("No hay ciudades registradas");
					}
					System.out.println("--------------------------------------------------\n");
					System.out.println("Ingrese el codigo de la ciudad origen");
					linea = scanner.nextLine();
					long codOrigen = Long.parseLong(linea.trim());
					System.out.println("Ingrese el codigo de la ciudad destino");
					linea = scanner.nextLine();
					long codDest = Long.parseLong(linea.trim());
					System.out.println("Ingrese la fecha de salida del vuelo, en formato (YYYY-MM-DD)");
					LocalDate fecha = Utils.conversorFecha(scanner.nextLine());
					if(sistemaVuelo.mostrarVuelosEspecificosPedidos(codAgente, codItinerario, codOrigen, codDest, fecha)){
						i = 2;
						System.out.println("VUELOS CORRESPONDIENTES A LOS CRITERIOS DE BUSQUEDA");
						System.out.println("--------------------------------------------------");
						for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
							for(VueloPlaneado vueloPlaneadoI : aerolineaI.getVuelosPlaneadosPedidos()){
								for(VueloEspecifico vueloEspecificoI : vueloPlaneadoI.getVuelosEspecificosPedidos()){
									System.out.println(vueloEspecificoI.toString());
								}
							}
						}
						System.out.println("--------------------------------------------------\n");
						System.out.println("Ingrese el codigo del vuelo especifico");
						linea = scanner.nextLine();
						long codVE = Long.parseLong(linea.trim());
						sistemaVuelo.crearTrayecto(codAgente, codVE);
					}
					else{
						System.out.println("No se encontraron resultados");
						i++;
					}
				}
			}
			break;
			case 8:{
				System.out.println("Opción 8: Mostrar agentes, itinerarios y trayectos");
				System.out.println("AGENTES REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAgentes() != null){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
						System.out.println("ITINERARIOS REGISTRADOS");
						System.out.println("--------------------------------------------------");
						if(agenteI.getItinerarios() != null){
							for(Itinerario itinerario : agenteI.getItinerarios()){
								String comprado = " No comprado";
								if(itinerario.isComprado()){
									comprado = " Comprado";
								}
								System.out.println(itinerario.toString() + comprado);
								System.out.println("TRAYECTOS REGISTRADOS");
								System.out.println("--------------------------------------------------");
								if(itinerario.getTrayectos() != null){
									for(Trayecto trayecto : itinerario.getTrayectos()){
										System.out.println(trayecto.toString());
										VueloEspecifico vEsp = trayecto.getVueloEspecifico();
										System.out.println("Codigo del vuelo especifico: " + vEsp.getCodigo() + " Fecha del vuelo especifico: " + vEsp.getFecha());
										System.out.println("Informacion del vuelo planeado asociado: ");
										System.out.println(vEsp.getVueloPlaneado().toString());
									}
								}
								else{
									System.out.println("No hay trayectos registrados");
								}
								System.out.println("--------------------------------------------------\n");
							}
						}
						else{
							System.out.println("No hay itinerarios registrados");
						}
						System.out.println("--------------------------------------------------\n");
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				System.out.println("--------------------------------------------------\n");
			}
			break;
			case 9:{
				System.out.println("Opción 9: Comprar un itinearario asignando a cada pasajero una silla sobre cada trayecto del itinerario.");
				System.out.println("AGENTES REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAgentes() != null){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del agente que desea seleccionar");
				linea = scanner.nextLine();
				long codAgente = Long.parseLong(linea.trim());
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("ITINERARIOS REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(agente.getItinerarios() != null){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del itinerario que desea seleccionar");
				linea = scanner.nextLine();
				long codItinerario= Long.parseLong(linea.trim());
				if(sistemaVuelo.verificarCupo(codAgente, codItinerario)){
					sistemaVuelo.comprarItinerario(codAgente, codItinerario);
					long valor = sistemaVuelo.calcularValor(codAgente, codItinerario);
					System.out.println("costo del itinerario: " + valor);
					int cantiPasajeros = sistemaVuelo.pasajerosItinerario(codAgente, codItinerario);
					int cantiTrayectos = sistemaVuelo.cantiTrayectos(codAgente, codItinerario);
					Agente agenteI = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
					Itinerario itinerarioI = agenteI.getItinerarios().get(agenteI.buscarItinerario(codItinerario));
					for(int ii = 0; ii < cantiPasajeros; ii++){
						for(int iii = 0; iii < cantiTrayectos; iii++){
							int tra = iii+1;
							System.out.println("SILLAS DISPONIBLES PARA EL TRAYECTO " + tra);
							Trayecto trayecto = itinerarioI.getTrayectos().get(iii);
							VueloEspecifico vueloE = trayecto.getVueloEspecifico();
							for(int iiii = 0; iiii < vueloE.getSillas().size(); iiii++){
								Silla silla = vueloE.getSillas().get(iiii);
								if(!(silla.isComprada())){
									System.out.println(silla.toString());
								}
							}
							System.out.println("Ingrese el id de la silla");
							String silla = scanner.nextLine();
							sistemaVuelo.marcarSilla(codAgente, codItinerario, tra, silla);
						}
					}
				}
				else{
					System.out.println("Cupos llenos");
				}
			}
			break;
			case 10:{
				System.out.println("Opción 10: Tiquete electrónico");
				System.out.println("AGENTES REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(sistemaVuelo.getAgentes() != null){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
					break;
				}
				System.out.println("--------------------------------------------------\n");
				System.out.println("Ingrese el codigo del agente que desea seleccionar");
				linea = scanner.nextLine();
				long codAgente = Long.parseLong(linea.trim());
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("ITINERARIOS REGISTRADOS");
				System.out.println("--------------------------------------------------");
				if(agente.getItinerarios() != null){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
					break;
				}
				System.out.println("--------------------------------------------------\n");System.out.println("Ingrese el codigo del itinerario que desea seleccionar");
				linea = scanner.nextLine();
				long codItinerario= Long.parseLong(linea.trim());
				if(sistemaVuelo.comprobarItinerario(codAgente, codItinerario)){
					Agente agenteI = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
					Itinerario itinerarioI = agenteI.getItinerarios().get(agenteI.buscarItinerario(codItinerario));
					System.out.println("DATOS DEL ITINERARIO");
					System.out.println(itinerarioI.toString());
					long valor = sistemaVuelo.calcularValor(codAgente, codItinerario);
					System.out.println("costo del itinerario: " + valor);
					System.out.println("DATOS DE CADA TRAYECTO DEL ITINERARIO");
					for(Trayecto trayecto : itinerarioI.getTrayectos()){
						VueloEspecifico vueloE = trayecto.getVueloEspecifico();
						System.out.println(trayecto.toString());
						System.out.println("DATOS DEL VUELO ESPECIFICO");
						System.out.println(vueloE.getCodigo() + " " + vueloE.getFecha());
						System.out.println("DATOS DEL VUELO PLANEADO");
						System.out.println(vueloE.getVueloPlaneado().toString());
						System.out.println("SILLAS ASOCIADAS");
						for(Silla silla : trayecto.getSillas()){
							System.out.println(silla.toString());
							System.out.println("DATOS DEL OCUPANTE");
							System.out.println(silla.getPasajero().toString());
						}
					}
				}
				else{
					System.out.println("El itinerario no ha sido comprado");
					break;
				}
			}
			break;
			}
			System.out.println("\n\n\n");
		}while(numeroR != 12);
		scanner.close();
	}
}
