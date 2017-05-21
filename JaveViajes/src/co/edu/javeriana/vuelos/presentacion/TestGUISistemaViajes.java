package co.edu.javeriana.vuelos.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.javeriana.vuelos.negocio.Aerolinea;
import co.edu.javeriana.vuelos.negocio.ISistemaViajes;
import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TestGUISistemaViajes extends JFrame {
	private ISistemaViajes sistemaVuelo = new SistemaVuelos();
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JComboBox<String> comboAerolineas = new JComboBox<String>();
	private JPanel agregarVueloEspecifico;
	private JTable tableVuelosP;
	private JScrollPane scrollPaneaVuelosP;
	private String[] columNames = {"Código", "Numero de Vuelo", "Día semana", "Hora salida/llegada", "Origen", "Destino"};
	private Vector columNamesV;
	private Vector rowData;
	private JComboBox<String> comboTipoV;
	private JComboBox<String> comboAno;
	private JComboBox<String> comboDia;
	private JComboBox<String> comboMes;
	private JComboBox<String> comboTipoA;
	private JTextField txtCapacidad;
	private JTextField txtTarifa;
	private JTextField txtImpuesto;
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
		
		JPanel menuServicios = new JPanel();
		tabbedPane.addTab("Men\u00FA servicios", null, menuServicios, null);
		menuServicios.setLayout(null);
		
		JButton btnAgregarVueloEspecfico = new JButton("Agregar vuelo espec\u00EDfico");
		btnAgregarVueloEspecfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnAgregarVueloEspecfico.setBounds(51, 191, 315, 23);
		menuServicios.add(btnAgregarVueloEspecfico);
		
		JButton btnNewButton = new JButton("Ingresar ciudades, aerolineas, vuelos planeados y agentes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarDatos(e);
			}
		});
		btnNewButton.setBounds(51, 157, 315, 23);
		menuServicios.add(btnNewButton);
		
		JButton btnAgregarItinerario = new JButton("Agregar itinerario");
		btnAgregarItinerario.setBounds(51, 225, 315, 23);
		menuServicios.add(btnAgregarItinerario);
		
		JButton btnAgregarTrayectoA = new JButton("Agregar trayecto a un itinerario");
		btnAgregarTrayectoA.setBounds(51, 259, 315, 23);
		menuServicios.add(btnAgregarTrayectoA);
		
		JButton btnMostrarAerolineasVuelos = new JButton("Mostrar aerolineas, vuelos planeados y vuelos especificos");
		btnMostrarAerolineasVuelos.setBounds(455, 157, 315, 23);
		menuServicios.add(btnMostrarAerolineasVuelos);
		
		JButton btnMostrarAgentesItinerarios = new JButton("Mostrar agentes, itinerarios y trayectos");
		btnMostrarAgentesItinerarios.setBounds(455, 191, 315, 23);
		menuServicios.add(btnMostrarAgentesItinerarios);
		
		JButton btnComprarUnItinerario = new JButton("Comprar un itinerario");
		btnComprarUnItinerario.setBounds(455, 225, 315, 23);
		menuServicios.add(btnComprarUnItinerario);
		
		JButton btnGenerarTiqueteElectrnico = new JButton("Generar tiquete electr\u00F3nico");
		btnGenerarTiqueteElectrnico.setBounds(455, 259, 315, 23);
		menuServicios.add(btnGenerarTiqueteElectrnico);
		
		JButton btnSalvarLosDatos = new JButton("Salvar los datos del sistema");
		btnSalvarLosDatos.setBounds(244, 330, 315, 23);
		menuServicios.add(btnSalvarLosDatos);
		
		JButton btnCargarLosDatos = new JButton("Cargar los datos del sistema");
		btnCargarLosDatos.setBounds(244, 364, 315, 23);
		menuServicios.add(btnCargarLosDatos);
		
		JLabel lblServiciosDelSistema = new JLabel("SERVICIOS DEL SISTEMA DE VUELOS");
		lblServiciosDelSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiciosDelSistema.setBounds(10, 11, 814, 35);
		menuServicios.add(lblServiciosDelSistema);
		
		JPanel ingresarDatos = new JPanel();
		tabbedPane.addTab("Ingresar datos", null, ingresarDatos, null);
		ingresarDatos.setLayout(null);
		
		JLabel lblIngresarDatos = new JLabel("Ingresar datos");
		lblIngresarDatos.setBounds(381, 5, 71, 14);
		ingresarDatos.add(lblIngresarDatos);
		
		JButton btnSeleccionarArchivoDe = new JButton("Seleccionar archivo de ciudades");
		btnSeleccionarArchivoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarArchivoCiudades(e);
			}
		});
		btnSeleccionarArchivoDe.setBounds(44, 92, 259, 23);
		ingresarDatos.add(btnSeleccionarArchivoDe);
		
		JButton btnSeleccionarArchivoDe_1 = new JButton("Seleccionar archivo de aerolineas y vuelos planeados");
		btnSeleccionarArchivoDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarArchivoAerolineas(e);
			}
		});
		btnSeleccionarArchivoDe_1.setBounds(44, 126, 321, 23);
		ingresarDatos.add(btnSeleccionarArchivoDe_1);
		
		JButton btnSeleccionarArchivoDe_2 = new JButton("Seleccionar archivo de agentes");
		btnSeleccionarArchivoDe_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarArchivoAgentes(e);
			}
		});
		btnSeleccionarArchivoDe_2.setBounds(44, 160, 321, 23);
		ingresarDatos.add(btnSeleccionarArchivoDe_2);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar.setBounds(20, 391, 89, 23);
		ingresarDatos.add(btnRegresar);
		
		agregarVueloEspecifico = new JPanel();
		tabbedPane.addTab("Agregar vuelo espec\u00EDfico", null, agregarVueloEspecifico, null);
		agregarVueloEspecifico.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aerol\u00EDneas");
		lblNewLabel.setBounds(10, 11, 280, 14);
		agregarVueloEspecifico.add(lblNewLabel);
		
		JButton btnMostrarVuelosPlaneados = new JButton("Mostrar vuelos planeados");
		btnMostrarVuelosPlaneados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarVuelosPlaneados(e);
			}
		});
		btnMostrarVuelosPlaneados.setBounds(226, 36, 198, 23);
		agregarVueloEspecifico.add(btnMostrarVuelosPlaneados);
		
		JLabel lblNewLabel_1 = new JLabel("Datos planeados de la aerol\u00EDnea");
		lblNewLabel_1.setBounds(10, 71, 235, 14);
		agregarVueloEspecifico.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Datos del nuevo vuelo espec\u00EDfico");
		lblNewLabel_2.setBounds(10, 194, 235, 14);
		agregarVueloEspecifico.add(lblNewLabel_2);
		
		JLabel lblTipoDeVuelo = new JLabel("Tipo de vuelo");
		lblTipoDeVuelo.setBounds(10, 219, 140, 14);
		agregarVueloEspecifico.add(lblTipoDeVuelo);
		
		JLabel lblFechaDelVuelo = new JLabel("Fecha del vuelo");
		lblFechaDelVuelo.setBounds(10, 244, 140, 14);
		agregarVueloEspecifico.add(lblFechaDelVuelo);
		
		JLabel lblTipoDeAvin = new JLabel("Tipo de avi\u00F3n");
		lblTipoDeAvin.setBounds(10, 269, 140, 14);
		agregarVueloEspecifico.add(lblTipoDeAvin);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(10, 294, 140, 14);
		agregarVueloEspecifico.add(lblCapacidad);
		
		JLabel lblTarifaBsica = new JLabel("Tarifa b\u00E1sica");
		lblTarifaBsica.setBounds(10, 319, 140, 14);
		agregarVueloEspecifico.add(lblTarifaBsica);
		
		JLabel lblImpuestoSalidaiva = new JLabel("Impuesto salida/IVA");
		lblImpuestoSalidaiva.setBounds(10, 344, 140, 14);
		agregarVueloEspecifico.add(lblImpuestoSalidaiva);
		
		JButton btnRegistrarVueloEspecfico = new JButton("Registrar vuelo espec\u00EDfico");
		btnRegistrarVueloEspecfico.setBounds(10, 368, 198, 23);
		agregarVueloEspecifico.add(btnRegistrarVueloEspecfico);
		
		JButton btnRegresar_1 = new JButton("Regresar");
		btnRegresar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_1.setBounds(266, 368, 89, 23);
		agregarVueloEspecifico.add(btnRegresar_1);
		
		comboAerolineas.setBounds(226, 8, 198, 20);
		agregarNombreAerolineas(comboAerolineas);
		agregarVueloEspecifico.add(comboAerolineas);
		
		scrollPaneaVuelosP = new JScrollPane();
		scrollPaneaVuelosP.setBounds(31, 96, 711, 70);
		agregarVueloEspecifico.add(scrollPaneaVuelosP);
		
		rowData = new Vector();
		columNamesV = new Vector(Arrays.asList(columNames));
		tableVuelosP = new JTable(rowData, columNamesV);
		scrollPaneaVuelosP.setViewportView(tableVuelosP);
		
		comboTipoV = new JComboBox();
		comboTipoV.setBounds(226, 216, 198, 20);
		agregarVueloEspecifico.add(comboTipoV);
		
		comboAno = new JComboBox();
		comboAno.setBounds(226, 241, 28, 20);
		agregarVueloEspecifico.add(comboAno);
		
		comboMes = new JComboBox();
		comboMes.setBounds(306, 241, 28, 20);
		agregarVueloEspecifico.add(comboMes);
		
		comboDia = new JComboBox();
		comboDia.setBounds(396, 241, 28, 20);
		agregarVueloEspecifico.add(comboDia);
		
		comboTipoA = new JComboBox();
		comboTipoA.setBounds(226, 266, 198, 20);
		agregarVueloEspecifico.add(comboTipoA);
		
		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(226, 291, 86, 20);
		agregarVueloEspecifico.add(txtCapacidad);
		txtCapacidad.setColumns(10);
		
		txtTarifa = new JTextField();
		txtTarifa.setBounds(226, 316, 86, 20);
		agregarVueloEspecifico.add(txtTarifa);
		txtTarifa.setColumns(10);
		
		txtImpuesto = new JTextField();
		txtImpuesto.setBounds(226, 341, 86, 20);
		agregarVueloEspecifico.add(txtImpuesto);
		txtImpuesto.setColumns(10);
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
				comboAerolineas.setBounds(226, 8, 198, 20);
				agregarNombreAerolineas(comboAerolineas);
				agregarVueloEspecifico.add(comboAerolineas);
			}else{
				JOptionPane.showConfirmDialog(this, "Error en la lectura de archivos", "Problema", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void SeleccionarArchivoAgentes(ActionEvent e){
		JFileChooser chooser = new JFileChooser("./");
		int retorno = chooser.showSaveDialog(this);
		if(retorno == JFileChooser.APPROVE_OPTION){
			String nombreArchivo = chooser.getSelectedFile().getName();
			boolean LeCiudad = ManejoArchivos.ingresarAgente(nombreArchivo, sistemaVuelo);
			if(LeCiudad){
				JOptionPane.showMessageDialog(this, "Datos leídos con éxito","Informacion", JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showConfirmDialog(this, "Error en la lectura de archivos", "Problema", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void agregarNombreAerolineas(JComboBox<String> comboAerolineas){
		for(Aerolinea aerolinea : sistemaVuelo.getAerolineas()){
			comboAerolineas.addItem(aerolinea.getNombre());
		}
	}
	private void mostrarVuelosPlaneados(ActionEvent e){
		int indiceItem = comboAerolineas.getSelectedIndex();
		String nameAerolinea = comboAerolineas.getItemAt(indiceItem);
		int iAerolinea = sistemaVuelo.buscarAerolinea(nameAerolinea);
		System.out.println(nameAerolinea+" "+iAerolinea);
		if(iAerolinea >= 0){
			Aerolinea aerolinea = sistemaVuelo.getAerolineas().get(iAerolinea);
			for(VueloPlaneado vueloPlaneado : aerolinea.getVuelosPlaneados()){
				Vector fila = new Vector();
				fila.add(vueloPlaneado.getCodigo());
				fila.add(vueloPlaneado.getNumeroVuelo());
				fila.add(vueloPlaneado.getDiaSemana());
				fila.add(vueloPlaneado.getHoraSalida()+"/"+vueloPlaneado.getHoraLlegada());
				fila.add(vueloPlaneado.getOrigen());
				fila.add(vueloPlaneado.getDestino());
				rowData.add(fila);
			}
			tableVuelosP = new JTable(rowData, columNamesV);
			scrollPaneaVuelosP.setViewportView(tableVuelosP);
		}
	}
}
