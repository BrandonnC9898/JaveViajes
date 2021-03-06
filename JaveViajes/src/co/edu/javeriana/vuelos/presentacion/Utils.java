package co.edu.javeriana.vuelos.presentacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;

public class Utils {
	/**
	 * convierte una cadena de caracteres en formato hora
	 * @param time
	 * @return hora creada
	 */
	public static LocalTime conversorHora(String time){
		StringTokenizer organizado = new StringTokenizer(time.trim(),":");
		int hora = Integer.parseInt(organizado.nextToken().trim());
		int minutos = Integer.parseInt(organizado.nextToken().trim());
		return LocalTime.of(hora, minutos);
	}
	/**
	 * convierte una cadena de caracteres en formato fecha
	 * @param fecha
	 * @return fecha creada
	 */
	public static LocalDate conversorFecha(String fecha){
		StringTokenizer organizado2 = new StringTokenizer(fecha.trim(),"-");
		int ano = Integer.parseInt(organizado2.nextToken());
		int mes = Integer.parseInt(organizado2.nextToken());
		int dia = Integer.parseInt(organizado2.nextToken());
		return LocalDate.of(ano, mes, dia);
	}
	/**
	 * Calcula la edad de una persona a partir de su fecha de nacimiento
	 * @param fechaNacimiento
	 * @return
	 */
	public static long obtenerEdad (LocalDate fechaNacimiento){
		return fechaNacimiento.until(LocalDate.now(), ChronoUnit.YEARS);
	}
	public static int obtenerAnoActual(){
		return LocalDate.now().getYear();
	}
}