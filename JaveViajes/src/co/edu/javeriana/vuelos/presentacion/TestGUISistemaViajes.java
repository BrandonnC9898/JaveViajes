package co.edu.javeriana.vuelos.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.javeriana.vuelos.negocio.ISistemaViajes;
import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestGUISistemaViajes extends JFrame {
	private ISistemaViajes sistemaVuelo = new SistemaVuelos();
	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUISistemaViajes frame = new TestGUISistemaViajes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestGUISistemaViajes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 839, 453);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Men\u00FA servicios", null, panel, null);
		panel.setLayout(null);
		
		JButton btnAgregarVueloEspecfico = new JButton("Agregar vuelo espec\u00EDfico");
		btnAgregarVueloEspecfico.setBounds(51, 191, 315, 23);
		panel.add(btnAgregarVueloEspecfico);
		
		JButton btnNewButton = new JButton("Ingresar ciudades, aerolineas, vuelos planeados y agentes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarDatos(e);
			}
		});
		btnNewButton.setBounds(51, 157, 315, 23);
		panel.add(btnNewButton);
		
		JButton btnAgregarItinerario = new JButton("Agregar itinerario");
		btnAgregarItinerario.setBounds(51, 225, 315, 23);
		panel.add(btnAgregarItinerario);
		
		JButton btnAgregarTrayectoA = new JButton("Agregar trayecto a un itinerario");
		btnAgregarTrayectoA.setBounds(51, 259, 315, 23);
		panel.add(btnAgregarTrayectoA);
		
		JButton btnMostrarAerolineasVuelos = new JButton("Mostrar aerolineas, vuelos planeados y vuelos especificos");
		btnMostrarAerolineasVuelos.setBounds(455, 157, 315, 23);
		panel.add(btnMostrarAerolineasVuelos);
		
		JButton btnMostrarAgentesItinerarios = new JButton("Mostrar agentes, itinerarios y trayectos");
		btnMostrarAgentesItinerarios.setBounds(455, 191, 315, 23);
		panel.add(btnMostrarAgentesItinerarios);
		
		JButton btnComprarUnItinerario = new JButton("Comprar un itinerario");
		btnComprarUnItinerario.setBounds(455, 225, 315, 23);
		panel.add(btnComprarUnItinerario);
		
		JButton btnGenerarTiqueteElectrnico = new JButton("Generar tiquete electr\u00F3nico");
		btnGenerarTiqueteElectrnico.setBounds(455, 259, 315, 23);
		panel.add(btnGenerarTiqueteElectrnico);
		
		JButton btnSalvarLosDatos = new JButton("Salvar los datos del sistema");
		btnSalvarLosDatos.setBounds(244, 330, 315, 23);
		panel.add(btnSalvarLosDatos);
		
		JButton btnCargarLosDatos = new JButton("Cargar los datos del sistema");
		btnCargarLosDatos.setBounds(244, 364, 315, 23);
		panel.add(btnCargarLosDatos);
		
		JLabel lblServiciosDelSistema = new JLabel("SERVICIOS DEL SISTEMA DE VUELOS");
		lblServiciosDelSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiciosDelSistema.setBounds(10, 11, 814, 35);
		panel.add(lblServiciosDelSistema);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ingresar datos", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblIngresarDatos = new JLabel("Ingresar datos");
		lblIngresarDatos.setBounds(381, 5, 71, 14);
		panel_1.add(lblIngresarDatos);
		
		JButton btnSeleccionarArchivoDe = new JButton("Seleccionar archivo de ciudades");
		btnSeleccionarArchivoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarArchivoCiudades(e);
			}
		});
		btnSeleccionarArchivoDe.setBounds(44, 92, 259, 23);
		panel_1.add(btnSeleccionarArchivoDe);
		
		JButton btnSeleccionarArchivoDe_1 = new JButton("Seleccionar archivo de aerolineas y vuelos planeados");
		btnSeleccionarArchivoDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarArchivoAerolineas(e);
			}
		});
		btnSeleccionarArchivoDe_1.setBounds(44, 126, 321, 23);
		panel_1.add(btnSeleccionarArchivoDe_1);
		
		JButton btnSeleccionarArchivoDe_2 = new JButton("Seleccionar archivo de agentes");
		btnSeleccionarArchivoDe_2.setBounds(44, 160, 321, 23);
		panel_1.add(btnSeleccionarArchivoDe_2);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar.setBounds(20, 391, 89, 23);
		panel_1.add(btnRegresar);
	}
	private void IngresarDatos(ActionEvent e){
		tabbedPane.setSelectedIndex(1);
	}
	private void SeleccionarArchivoCiudades(ActionEvent e){
		JFileChooser chooser = new JFileChooser("./");
		int retorno = chooser.showSaveDialog(this);
		if(retorno == JFileChooser.APPROVE_OPTION){
			//String pathArchivo = chooser.getSelectedFile().getParent();
			String nombreArchivo = chooser.getSelectedFile().getName();
			boolean LeCiudad = ManejoArchivos.leerCiudad(nombreArchivo, sistemaVuelo);
			if(LeCiudad){
				JOptionPane.showMessageDialog(this, "Datos leídos con éxito","Informacion", JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showConfirmDialog(this, "Error en la lectura de archivos", "Problema", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void SeleccionarArchivoAerolineas(ActionEvent e){
		JFileChooser chooser = new JFileChooser("./");
		int retorno = chooser.showSaveDialog(this);
		if(retorno == JFileChooser.APPROVE_OPTION){
			String nombreArchivo = chooser.getSelectedFile().getName();
			boolean LeCiudad = ManejoArchivos.ingresarAerolinea(nombreArchivo, sistemaVuelo);
			if(LeCiudad){
				JOptionPane.showMessageDialog(this, "Datos leídos con éxito","Informacion", JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showConfirmDialog(this, "Error en la lectura de archivos", "Problema", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
