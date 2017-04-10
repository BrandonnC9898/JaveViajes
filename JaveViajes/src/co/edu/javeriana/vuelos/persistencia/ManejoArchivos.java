package co.edu.javeriana.vuelos.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.StringTokenizer;

import co.edu.javeriana.vuelos.negocio.ISistemaViajes;
import co.edu.javeriana.vuelos.presentacion.Utils;

public class ManejoArchivos {
	/**
	 * lee datos de ciudades almacenados en un archivo
	 * @param nombreArchivo
	 * @param sistemaVuelo
	 * @return si se pudo o no leer los datos 
	 */
	public static boolean leerCiudad (String nombreArchivo, ISistemaViajes sistemaVuelo){
		boolean retorno = false;
		File ciudadArchivo = new File("./"+nombreArchivo);
		FileInputStream tubo = null;

		try{
			tubo = new FileInputStream(ciudadArchivo);
			Scanner scaner = new Scanner(tubo);
			String linea = scaner.nextLine().trim();
			while(!(linea.equals("0"))){
				if(linea.indexOf("#") < 0 ){
					StringTokenizer organiza = new StringTokenizer(linea.trim(),"*");
					String codigo = organiza.nextToken().trim();
					int codigoReal = Integer.parseInt(codigo);
					String nombreCiudad = organiza.nextToken().trim();
					sistemaVuelo.crearCiudad(nombreCiudad, codigoReal);
				}
				else
				{
					linea = scaner.nextLine();
					continue;
				}
				linea = scaner.nextLine();
			}
			scaner.close();
			tubo.close();
			retorno = true;
		}
		//quite el finally por 2 catch
		catch (FileNotFoundException e){
			retorno = false;
		}
		catch (IOException e){
			retorno = false;
		}
		return retorno;
	}
	/**
	 * lee los datos de aerolineas almacenados en un archivo
	 * @param nombreArchivo
	 * @param sistemaVuelo
	 * @return si se pudo o no leer los datos 
	 */
	//arregle los tipos de dato (int-long)
	public static boolean ingresarAerolinea (String nombreArchivo, ISistemaViajes sistemaVuelo){
		boolean retorno = false;
		File aerolineaArchivo = new File("./"+nombreArchivo);
		FileInputStream tubo = null;

		try{
			tubo = new FileInputStream(aerolineaArchivo);
			Scanner scaner = new Scanner(tubo);
			String linea = scaner.nextLine().trim();
			while(!(linea.equals("#FIN"))){
				if(linea.indexOf("#") < 0 ){
					StringTokenizer organiza = new StringTokenizer(linea.trim(),"*");
					String codigo = organiza.nextToken().trim();
					long codigoReal = Long.parseLong(codigo);
					String nombre = organiza.nextToken().trim();
					String cuentaBanco = organiza.nextToken().trim();
					sistemaVuelo.crearAerolinea(codigoReal,nombre,cuentaBanco);
					//int posAerolinea = sistemaVuelo.buscarAerolinea(codigoReal);
					//verifica que la aerolinea se encuentre, sino está, lee una nueva línea
					//if(posAerolinea >= 0){
					linea = scaner.nextLine().trim();
					while(!(linea.equals("0"))){
						if(linea.indexOf("#") < 0 ){
							organiza = new StringTokenizer(linea.trim(),"*");
							String codigoVP = organiza.nextToken().trim();
							long codigoRVP = Long.parseLong(codigoVP);
							String numeroVuelo = organiza.nextToken().trim();
							String diaSemana = organiza.nextToken().trim();
							String hS = organiza.nextToken().trim();
							LocalTime horaSalida = Utils.conversorHora(hS);
							String hLl = organiza.nextToken().trim();
							LocalTime horaLlegada = Utils.conversorHora(hLl);
							String orig = organiza.nextToken().trim();
							long origen = Long.parseLong(orig);
							String dest = organiza.nextToken().trim();
							long destino = Long.parseLong(dest);
							sistemaVuelo.agregarVueloPlaneado(codigoReal, codigoRVP, numeroVuelo, diaSemana, horaSalida, horaLlegada, origen, destino);
						}
						linea = scaner.nextLine();
					}
					//}
				}
				else
				{
					linea = scaner.nextLine();
					continue;
				}
				linea = scaner.nextLine();
			}
			scaner.close();
			tubo.close();
			retorno = true;
		}
		//quite el finally por 2 catch
		catch (FileNotFoundException e){
			retorno = false;
		}
		catch (IOException e){
			retorno = false;
		}
		return retorno;
	}
	/**
	 * lee los datos de agentes almacenados en un archivo
	 * @param nombreArchivo
	 * @param sistemaVuelo
	 * @return si se pudo o no leer los datos 
	 */
	public static boolean ingresarAgente (String nombreArchivo, ISistemaViajes sistemaVuelo){
		boolean retorno = false;
		File ciudadArchivo = new File("./"+nombreArchivo);
		FileInputStream tubo = null;

		try{
			tubo = new FileInputStream(ciudadArchivo);
			Scanner scaner = new Scanner(tubo);
			String linea = scaner.nextLine().trim();
			while(!(linea.equals("0"))){
				if(linea.indexOf("#") < 0 ){
					StringTokenizer organiza = new StringTokenizer(linea.trim(),"*");
					String codigo = organiza.nextToken().trim();
					long codigoReal = Long.parseLong(codigo);
					String nombre = organiza.nextToken().trim();
					String email = organiza.nextToken().trim();
					sistemaVuelo.crearAgente(codigoReal, nombre, email);
				}
				else
				{
					linea = scaner.nextLine();
					continue;
				}
				linea = scaner.nextLine();
			}
			scaner.close();
			tubo.close();
			retorno = true;
		}
		catch (FileNotFoundException e){
			retorno = false;
		}
		catch (IOException e){
			retorno = false;
		}
		return retorno;
	}
}

