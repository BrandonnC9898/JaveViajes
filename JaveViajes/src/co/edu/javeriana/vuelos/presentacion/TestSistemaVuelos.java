package co.edu.javeriana.vuelos.presentacion;

import java.time.LocalDate;

import java.util.Scanner;
import java.util.StringTokenizer;

import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.ISistemaViajes;
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
		ISistemaViajes sistemaVuelo = new SistemaVuelos();
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
			System.out.println("Opción 12: Terminar.");
			System.out.println("Ingrese la opción:");
			String numero = scanner.nextLine();
			numeroR = Integer.parseInt(numero);
			switch(numeroR){
			case 1:
			{
				System.out.println("--Ingresar ciudades al sistema");
				System.out.println("--¿Cuál es el nombre del archivo? (incluya la extensión)");
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
				System.out.println("--Ingresar aerolineas y vuelos planeados del sistema");
				System.out.println("--¿Cuál es el nombre del archivo? (incluya la extensión)");
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
				System.out.println("--Ingresar agentes al sistema");
				System.out.println("--¿Cuál es el nombre del archivo? (incluya la extensión)");
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
				System.out.println("--AGREGAR VUELO ESPECÍFICO");
				do{
					System.out.println("--¿Qué tipo de vuelo necesita?\n  nacional para vuelo nacional o internacional para vuelo internacional");
					linea = scanner.nextLine();
				}while(!linea.equals("internacional") && !linea.equals("nacional"));
				String tipo = linea;
				long valorAdicional = 0;
				LocalDate fecha = null;
				int capacidad = 0, tarifa = 0;
				String tipoAvion = null;
				System.out.println("\n--¿Cuáles son los datos del vuelo específico?: fecha(YYYY-MM-DD), tipo de avion, capacidad y tarifa (US)");
				linea = scanner.nextLine();
				StringTokenizer organiza = new StringTokenizer(linea.trim()," ");
				String fech = organiza.nextToken();
				tipoAvion = organiza.nextToken();
				String capacida = organiza.nextToken();
				String tarif = organiza.nextToken();
				fecha = Utils.conversorFecha(fech);
				capacidad = Integer.parseInt(capacida);
				tarifa = Integer.parseInt(tarif);
				if(tipo.equals("nacional")){
					System.out.println("\n--Ingrese el valor del IVA");
					linea = scanner.nextLine();
					valorAdicional = Integer.parseInt(linea);
				}
				if(tipo.equals("internacional")){
					System.out.println("\n--Ingrese el valor del Impuesto de Salida");
					linea = scanner.nextLine();
					valorAdicional = Long.parseLong(linea);
				}
				System.out.println("\n--Aerolineas");
				System.out.println("Código \t Nombre");
				System.out.println("--------------------------------------------------");
				if(!sistemaVuelo.getAerolineas().isEmpty()){
					for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
						System.out.println(aerolineaI.getCodigo()+" \t "+aerolineaI.getNombre());
					}
				}
				else{
					System.out.println("No hay aerolineas regristradas");
				}
				int iAerolinea = 0;
				long codAerolinea = 0;
				do{
					System.out.println("\n--¿Cuál es el código de la aerolinea al cual pertenece el nuevo vuelo específico?");
					linea = scanner.nextLine();
					codAerolinea = Long.parseLong(linea.trim());
					iAerolinea = sistemaVuelo.buscarAerolinea(codAerolinea);
				}while(iAerolinea < 0);
				Aerolinea aerolinea = sistemaVuelo.getAerolineas().get(iAerolinea);
				System.out.println("\n--Vuelos planeados de la aerolinea "+aerolinea.getNombre());
				System.out.println("Código \t Número de vuelo \t Día de semana \t Hora salida \t Hora llegada \t Origen \t Destino");
				if(!aerolinea.getVuelosPlaneados().isEmpty()){
					for(VueloPlaneado vueloPlaneadoI : aerolinea.getVuelosPlaneados()){
						System.out.println(vueloPlaneadoI.toString() + " \t " + vueloPlaneadoI.getOrigen().getNombre() + " \t " + vueloPlaneadoI.getDestino().getNombre());
					}
				}
				else{
					System.out.println("No hay vuelos planeados regristrados");
				}
				long codVP = 0;
				do{
					System.out.println("\n--¿Cuál es el código del vuelo planeado al que pertenece el vuelo especifico?");
					linea = scanner.nextLine(); 
					codVP = Long.parseLong(linea.trim());
				}while(sistemaVuelo.buscarVueloPlaneado(codAerolinea, codVP) < 0);
				Long codigoVE = sistemaVuelo.crearVueloEspecifico(codAerolinea, codVP, fecha, tipoAvion, capacidad, tarifa, valorAdicional, tipo);
				System.out.println("\nEl codigo del vuelo especifico creado es: " + codigoVE);
			}
			break;
			case 5:{
				System.out.println("--REPORTE DE AEROLÍNEAS:");
				if(!sistemaVuelo.getAerolineas().isEmpty()){
					for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
						System.out.println("\nAerolínea: " + aerolineaI.getNombre()+" cuenta Banco: "+aerolineaI.getCuentaBanco());
						System.out.println("--------------------------------------------------\n");
						System.out.println("\tVuelo planeado de: "+aerolineaI.getNombre());
						System.out.println("\tCódigo \t Número de vuelo \t Día de semana \t Hora salida \t Hora llegada \t Origen \t Destino");
						System.out.println("\t--------------------------------------------------");
						if(!aerolineaI.getVuelosPlaneados().isEmpty()){
							for(VueloPlaneado vueloPlaneadoI : aerolineaI.getVuelosPlaneados()){
								String origenI = vueloPlaneadoI.getOrigen().getNombre();
								String destinoI = vueloPlaneadoI.getDestino().getNombre();
								System.out.println("\t"+vueloPlaneadoI.toString() + "\t" + origenI + "\t" + destinoI);
								System.out.println("\n\t\tVuelos específicos para el vuelo planeado "+vueloPlaneadoI.getNumeroVuelo()+" :");
								System.out.println("\t\tid \t fecha \t\t tipo Avión \t capacidad \t libres \t tarifa \t impto \t valor pasaje");
								if(!vueloPlaneadoI.getVuelosEspecificos().isEmpty()){
									for(VueloEspecifico vueloEspecifico : vueloPlaneadoI.getVuelosEspecificos()){
										System.out.println("\t\t"+vueloEspecifico.toString());
									}
								}
								else{
									System.out.println("\t\tNo hay vuelos especificos registrados");
								}
							}
						}
						else{
							System.out.println("\tNo hay vuelos planeados regristrados");
						}
					}
				}
				else{
					System.out.println("No hay aerolineas regristradas");
				}
			}
			break;
			case 6:{
				System.out.println("--AGREGAR ITINERARIO PARA UN AGENTE");
				System.out.println("--Agentes del sistema");
				System.out.println("Código \t nombre \t email");
				if(!sistemaVuelo.getAgentes().isEmpty()){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				long codAgente = 0;
				do{
					System.out.println("\n--¿Cuál es el código del agente al cual pertenece el nuevo itinerario?");
					linea = scanner.nextLine();
					codAgente = Long.parseLong(linea.trim());
				}while(sistemaVuelo.buscarAgente(codAgente) <= 0);
				System.out.println("\n--¿Cuál es el nombre del nuevo itinerario?");
				String nombreItinerario = scanner.nextLine();
				long codItinerario = sistemaVuelo.crearItinerario(codAgente, nombreItinerario);
				System.out.println("\n--¿Cuál es la cantidad de pasajeros?");
				int cantiPasajeros = Integer.parseInt(scanner.nextLine().trim());
				System.out.println("\n--Ingrese la identificación, nombre y fecha de nacimiento (YYYY-MM-DD) para cada pasajero, separadas por linea. Para cada dato separe con *");
				for(int i = 0; i < cantiPasajeros; i++){
					System.out.println("Pasajero "+(i+1)+" :");
					linea = scanner.nextLine();
					StringTokenizer organiza = new StringTokenizer(linea.trim(), "*");
					String identificacion = organiza.nextToken().trim();
					String nombre = organiza.nextToken().trim();
					String fechaN = organiza.nextToken().trim();
					LocalDate fechaNacimiento = Utils.conversorFecha(fechaN);
					long edad = Utils.obtenerEdad(fechaNacimiento);
					boolean tipo;
					boolean requiere;
					if(edad < 12){
						tipo = false;
						do{
							System.out.println("Dado que el pasajero es menor de 12 años, ¿él viaja solo? s/n");
							linea = scanner.nextLine().trim();
						}while(!linea.equals("s") && !linea.equals("n"));
						if(linea.equals("s")){
							requiere = true;
						}
						else{
							requiere = false;
						}
					}
					else{
						tipo = true;
						do{
							System.out.println("¿El pasajeo requiere asistencia? s/n");
							linea = scanner.nextLine().trim();
						}while(!linea.equals("s") && !linea.equals("n"));
						if(linea.equals("s")){
							requiere = true;
						}
						else{
							requiere = false;
						}
					}

					sistemaVuelo.crearPasajero(codAgente, codItinerario, identificacion, nombre, tipo, requiere);
				}
				System.out.println("El itinerario se creo con el código: " + codItinerario);
			}
			break;
			case 7:{
				System.out.println("--AGREGAR UN TRAYECTO ASOCIADO A UN ITINERARIO");
				System.out.println("--Agentes");
				if(!sistemaVuelo.getAgentes().isEmpty()){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				System.out.println("\n--¿Cuál es el código del agente?");
				linea = scanner.nextLine();
				long codAgente = Long.parseLong(linea.trim());
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("\n--Itinerarios del agente "+agente.getNombre());
				if(!agente.getItinerarios().isEmpty()){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
				}
				System.out.println("\n--¿Cuál es el código del itinerario?");
				linea = scanner.nextLine();
				long codItinerario= Long.parseLong(linea.trim());
				int i = 0;
				while(i < 2){
					System.out.println("\n--Ciudades");
					if(!sistemaVuelo.getCiudades().isEmpty()){
						for(Ciudad ciudad : sistemaVuelo.getCiudades()){
							System.out.println(ciudad.toString());
						}
					}
					else{
						System.out.println("No hay ciudades registradas");
					}
					System.out.println("\n--¿Cuál es el código de la ciudad origen?");
					linea = scanner.nextLine();
					long codOrigen = Long.parseLong(linea.trim());
					System.out.println("\n--¿Cuál es el código de la ciudad destino?");
					linea = scanner.nextLine();
					long codDest = Long.parseLong(linea.trim());
					System.out.println("\n--Ingrese la fecha de salida del vuelo, en formato (YYYY-MM-DD)");
					LocalDate fecha = Utils.conversorFecha(scanner.nextLine());
					if(sistemaVuelo.mostrarVuelosEspecificosPedidos(codAgente, codItinerario, codOrigen, codDest, fecha)){
						i = 2;
						System.out.println("\n--Vuelos correspondientes a los criterios de búsqueda");
						System.out.println("--------------------------------------------------");
						for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
							for(VueloPlaneado vueloPlaneadoI : aerolineaI.getVuelosPlaneadosPedidos()){
								for(VueloEspecifico vueloEspecificoI : vueloPlaneadoI.getVuelosEspecificosPedidos()){
									System.out.println(vueloEspecificoI.toString());
								}
							}
						}
						System.out.println("\n--¿Cuál es el código del vuelo específico?");
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
				System.out.println("--REPORTE DE AGENTES E ITINERARIOS:");
				if(!sistemaVuelo.getAgentes().isEmpty()){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println("\nAgente: "+agenteI.getNombre()+" email: "+agenteI.getEmail());
						System.out.println("--------------------------------------------------");
						System.out.println("\tItinerarios de "+agenteI.getNombre());
						System.out.println("--------------------------------------------------");
						if(!agenteI.getItinerarios().isEmpty()){
							for(Itinerario itinerario : agenteI.getItinerarios()){
								String comprado = " no reservado";
								if(itinerario.isComprado()){
									comprado = " reservado";
								}
								System.out.println("\tid \t nombre \t numero de Pasajeros");
								System.out.println("\t"+itinerario.toString()+comprado+" "+itinerario.cantidadPasajeros());
								System.out.println("\n\t\tVuelos especificos asociados al itinerario "+itinerario.getNombre());
								System.out.println("\t\tCódigo \t Número de vuelo \t Día de semana \t Hora salida \t Hora llegada \t Origen \t Destino");
								System.out.println("\t\t--------------------------------------------------");
								if(!itinerario.getTrayectos().isEmpty()){
									for(Trayecto trayecto : itinerario.getTrayectos()){
										VueloEspecifico vEsp = trayecto.getVueloEspecifico();
										System.out.println("\t\t"+vEsp.toString());
										System.out.println("\t\tInformacion del vuelo planeado asociado: ");
										System.out.println("\t\t"+vEsp.getVueloPlaneado().toString());
									}
								}
								else{
									System.out.println("\t\tNo hay trayectos registrados");
								}
							}
						}
						else{
							System.out.println("\tNo hay itinerarios registrados");
						}
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
			}
			break;
			case 9:{
				System.out.println("--COMPRAR UN ITINERARIO");
				System.out.println("--Agentes del sistema:");
				if(!sistemaVuelo.getAgentes().isEmpty()){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
				}
				long codAgente = 0;
				do{
					System.out.println("\n--¿Cuál es el codigo del agente?");
					linea = scanner.nextLine();
					codAgente = Long.parseLong(linea.trim());
				}while(sistemaVuelo.buscarAgente(codAgente) < 0);
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("\n--Itinerarios del agente "+agente.getNombre());
				if(!agente.getItinerarios().isEmpty()){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
				}
				long codItinerario;
				do{
					System.out.println("\n--¿Cuál es el id del itinerario seleccionado?");
					linea = scanner.nextLine();
					codItinerario = Long.parseLong(linea.trim());
				}while(sistemaVuelo.buscarItinerario(codItinerario) < 0);
				if(sistemaVuelo.verificarCupo(codAgente, codItinerario)){
					sistemaVuelo.comprarItinerario(codAgente, codItinerario);
					int cantiPasajeros = sistemaVuelo.pasajerosItinerario(codAgente, codItinerario);
					int cantiTrayectos = sistemaVuelo.cantiTrayectos(codAgente, codItinerario);
					Agente agenteI = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
					Itinerario itinerarioI = agenteI.getItinerarios().get(agenteI.buscarItinerario(codItinerario));
					for(int ii = 0; ii < cantiPasajeros; ii++){
						for(int iii = 0; iii < cantiTrayectos; iii++){
							int tra = iii+1;
							System.out.println("\n--Sillas disponibles para trayecto " + tra);
							Trayecto trayecto = itinerarioI.getTrayectos().get(iii);
							VueloEspecifico vueloE = trayecto.getVueloEspecifico();
							for(int iiii = 0; iiii < vueloE.getSillas().size(); iiii++){
								Silla silla = vueloE.getSillas().get(iiii);
								if(!(silla.isComprada())){
									System.out.println(silla.toString());
								}
							}
							System.out.println("\n--Ingrese el id de la silla, y no olvide usar mayúsculas si es necesario");
							String silla = scanner.nextLine();
							sistemaVuelo.marcarSilla(codAgente, codItinerario, tra, silla);
							long valor = sistemaVuelo.calcularValorItinerario(codAgente, codItinerario);
							System.out.println("\n--costo del itinerario: " + valor);
						}
					}
				}
				else{
					System.out.println("--Cupos llenos");
				}
			}
			break;
			case 10:{
				System.out.println("--TIQUETE ELECTRÓNICO");
				System.out.println("--Agentes registrados");
				if(!sistemaVuelo.getAgentes().isEmpty()){
					for(Agente agenteI : sistemaVuelo.getAgentes()){
						System.out.println(agenteI.toString());
					}
				}
				else{
					System.out.println("No hay agentes registrados");
					break;
				}
				System.out.println("\n--¿Cuàl el código del agente seleccionado?");
				linea = scanner.nextLine();
				long codAgente = Long.parseLong(linea.trim());
				Agente agente = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
				System.out.println("\n--Itinerarios del agente "+agente.getNombre());
				if(!agente.getItinerarios().isEmpty()){
					for(Itinerario itinerario : agente.getItinerarios()){
						System.out.println(itinerario.toString());
					}
				}
				else{
					System.out.println("No hay itinerarios registrados");
					break;
				}
				System.out.println("\n--¿Cuál es el código del itinerario seleccionado?");
				linea = scanner.nextLine();
				long codItinerario= Long.parseLong(linea.trim());
				if(sistemaVuelo.comprobarItinerario(codAgente, codItinerario)){
					Agente agenteI = sistemaVuelo.getAgentes().get(sistemaVuelo.buscarAgente(codAgente));
					Itinerario itinerarioI = agenteI.getItinerarios().get(agenteI.buscarItinerario(codItinerario));
					System.out.println("\n--Datos del itinerario: ");
					String comprado = " no comprado";
					if(itinerarioI.isComprado()){
						comprado = " comprado";
					}
					System.out.println("\tid \t nombre \t numero de Pasajeros \t estado");
					System.out.println("\t"+itinerarioI.toString()+" \t "+comprado);
					long valor = sistemaVuelo.calcularValorItinerario(codAgente, codItinerario);
					for(Trayecto trayecto : itinerarioI.getTrayectos()){
						VueloEspecifico vueloE = trayecto.getVueloEspecifico();
						System.out.println("\n\t\t--Vuelos específicos asociados");
						System.out.println("\t\tCodigo \t fecha");
						System.out.println("\t\t"+vueloE.getCodigo() + " " + vueloE.getFecha());
						System.out.println("\n\t\tDatos del vuelo planeado");
						System.out.println("\t\t"+vueloE.getVueloPlaneado().toString());
						System.out.println("\n\t\tSillas asociadas");
						for(Silla silla : trayecto.getSillas()){
							System.out.println("\t\t"+silla.toString());
							System.out.println("\n\t\tDatos del ocupante");
							System.out.println("\t\t"+silla.getPasajero().toString());
							System.out.println("\nCosto del itinerario para este pasajero: "+silla.getPasajero().calcularValorItinerario());
						}
					}
					System.out.println("\tValor total del itinerario: " + valor);
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
