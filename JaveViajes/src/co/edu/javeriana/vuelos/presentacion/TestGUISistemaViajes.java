package co.edu.javeriana.vuelos.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.ISistemaViajes;
import co.edu.javeriana.vuelos.negocio.Aerolinea;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;
import co.edu.javeriana.vuelos.negocio.VueloEspecifico;
import co.edu.javeriana.vuelos.negocio.VueloEspecificoInternacional;
import co.edu.javeriana.vuelos.negocio.VueloEspecificoNacional;
import co.edu.javeriana.vuelos.negocio.Agente;
import co.edu.javeriana.vuelos.negocio.Itinerario;
import co.edu.javeriana.vuelos.negocio.Trayecto;
import co.edu.javeriana.vuelos.negocio.Silla;

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
import java.time.LocalDate;
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
	private JComboBox comboAno;
	private JComboBox comboDia;
	private JComboBox comboMes;
	private JComboBox<String> comboTipoA;
	private JTextField txtCapacidad;
	private JTextField txtTarifa;
	private JTextField txtImpuesto;
	private JComboBox<String> comboAgentes = new JComboBox<String>();
	private JTextField txtnombreItinerario;
	private JTextField txtID;
	private JTextField txtNombre;
	private JComboBox comboAno_1;
	private JComboBox comboMes_1;
	private JComboBox comboDia_1;
	private JPanel agregarItinerario;
	private JComboBox<String> comboSoloAsistencia;
	private long itinerario = -1;
	private JComboBox<String> comboDestino = new JComboBox<String>();
	private JComboBox<String> comboOrigen = new JComboBox<String>();
	private JTable tableVrequerimientos;
	private JComboBox comboAno_2;
	private JComboBox comboMes_2;
	private JComboBox comboDia_2;
	private JComboBox<String> comboAgentes_1 = new JComboBox<String>();
	private JComboBox<String> comboItinerarios = new JComboBox<String>();
	private JPanel agregarTrayectoItinerario;
	private String[] columNames2 = {"Código", "Aerolinea", "Número vuelo", "Hora salida/llegada", "Origen", "Destino"};
	private Vector columNamesV2;
	private Vector rowData2;
	private JScrollPane scrollPaneTrayectos;
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
		btnAgregarItinerario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnAgregarItinerario.setBounds(51, 225, 315, 23);
		menuServicios.add(btnAgregarItinerario);

		JButton btnAgregarTrayectoA = new JButton("Agregar trayecto a un itinerario");
		btnAgregarTrayectoA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
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
		btnRegistrarVueloEspecfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarVueloEsp(e);
			}
		});
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
		agregarNombreAerolineas();
		agregarVueloEspecifico.add(comboAerolineas);

		scrollPaneaVuelosP = new JScrollPane();
		scrollPaneaVuelosP.setBounds(31, 96, 711, 70);
		agregarVueloEspecifico.add(scrollPaneaVuelosP);

		rowData = new Vector();
		columNamesV = new Vector(Arrays.asList(columNames));
		tableVuelosP = new JTable(rowData, columNamesV);
		scrollPaneaVuelosP.setViewportView(tableVuelosP);

		String[] tipoVuelo = {"Nacional", "Internacional"};
		comboTipoV = new JComboBox(tipoVuelo);
		comboTipoV.setBounds(226, 216, 198, 20);
		agregarVueloEspecifico.add(comboTipoV);


		comboAno = new JComboBox(generarAno());
		comboAno.setBounds(226, 241, 64, 20);
		agregarVueloEspecifico.add(comboAno);

		comboMes = new JComboBox(generarMes());
		comboMes.setBounds(306, 241, 64, 20);
		agregarVueloEspecifico.add(comboMes);

		comboDia = new JComboBox(generarDia());
		comboDia.setBounds(380, 241, 44, 20);
		agregarVueloEspecifico.add(comboDia);

		String[] tipoA = {"Boeing", "Airbus", "Douglas", "Cessna"};
		comboTipoA = new JComboBox(tipoA);
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

		agregarItinerario = new JPanel();
		tabbedPane.addTab("Agregar itinerario", null, agregarItinerario, null);
		agregarItinerario.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Agentes");
		lblNewLabel_3.setBounds(10, 11, 110, 14);
		agregarItinerario.add(lblNewLabel_3);

		comboAgentes.setBounds(202, 8, 202, 20);
		agregarItinerario.add(comboAgentes);

		JLabel lblNombreNuevoItinerario = new JLabel("Nombre nuevo itinerario");
		lblNombreNuevoItinerario.setBounds(10, 36, 123, 14);
		agregarItinerario.add(lblNombreNuevoItinerario);

		txtnombreItinerario = new JTextField();
		txtnombreItinerario.setBounds(202, 33, 148, 20);
		agregarItinerario.add(txtnombreItinerario);
		txtnombreItinerario.setColumns(10);

		JButton btnRegistrarItinerario = new JButton("Registrar itinerario");
		btnRegistrarItinerario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itinerario = registrarItinerario(e);
			}
		});
		btnRegistrarItinerario.setBounds(202, 64, 148, 23);
		agregarItinerario.add(btnRegistrarItinerario);

		JLabel lblDatosDeUn = new JLabel("Datos de un pasajero del itinerario");
		lblDatosDeUn.setBounds(10, 100, 202, 14);
		agregarItinerario.add(lblDatosDeUn);

		JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n");
		lblIdentificacin.setBounds(10, 125, 110, 14);
		agregarItinerario.add(lblIdentificacin);

		txtID = new JTextField();
		txtID.setBounds(202, 125, 148, 20);
		agregarItinerario.add(txtID);
		txtID.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 159, 46, 14);
		agregarItinerario.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(202, 156, 148, 20);
		agregarItinerario.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Fecha nacimiento");
		lblNewLabel_4.setBounds(10, 186, 110, 14);
		agregarItinerario.add(lblNewLabel_4);

		comboAno_1 = new JComboBox(generarAno());
		comboAno_1.setBounds(202, 183, 55, 20);
		agregarItinerario.add(comboAno_1);

		comboMes_1 = new JComboBox(generarMes());
		comboMes_1.setBounds(267, 183, 45, 20);
		agregarItinerario.add(comboMes_1);

		comboDia_1 = new JComboBox(generarDia());
		comboDia_1.setBounds(322, 183, 39, 20);
		agregarItinerario.add(comboDia_1);

		JLabel lblViajaSolo = new JLabel("Viaja solo / requiere asistencia");
		lblViajaSolo.setBounds(10, 211, 160, 14);
		agregarItinerario.add(lblViajaSolo);

		String[] siNo = {"No", "Si"};
		comboSoloAsistencia = new JComboBox(siNo);
		comboSoloAsistencia.setBounds(202, 214, 88, 20);
		agregarItinerario.add(comboSoloAsistencia);

		JButton btnRegistrarPasajero = new JButton("Registrar pasajero");
		btnRegistrarPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(itinerario >= 0){
					registrarPasajero(e, itinerario);
				}
			}
		});
		btnRegistrarPasajero.setBounds(10, 276, 215, 23);
		agregarItinerario.add(btnRegistrarPasajero);

		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(10, 311, 110, 23);
		agregarItinerario.add(btnNewButton_1);

		agregarTrayectoItinerario = new JPanel();
		agregarTrayectoItinerario.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Agragar trayecto a un itinerario", null, agregarTrayectoItinerario, null);
		agregarTrayectoItinerario.setLayout(null);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(10, 11, 125, 14);
		agregarTrayectoItinerario.add(lblOrigen);

		comboOrigen.setBounds(194, 8, 180, 20);
		agregarTrayectoItinerario.add(comboOrigen);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(10, 42, 105, 14);
		agregarTrayectoItinerario.add(lblDestino);

		comboDestino.setBounds(194, 39, 180, 20);
		agregarTrayectoItinerario.add(comboDestino);

		JLabel lblNewLabel_5 = new JLabel("Fecha salida");
		lblNewLabel_5.setBounds(10, 76, 105, 14);
		agregarTrayectoItinerario.add(lblNewLabel_5);

		comboAno_2 = new JComboBox(generarAno());
		comboAno_2.setBounds(194, 70, 62, 20);
		agregarTrayectoItinerario.add(comboAno_2);

		comboMes_2 = new JComboBox(generarMes());
		comboMes_2.setBounds(266, 70, 56, 20);
		agregarTrayectoItinerario.add(comboMes_2);

		comboDia_2 = new JComboBox(generarDia());
		comboDia_2.setBounds(332, 70, 42, 20);
		agregarTrayectoItinerario.add(comboDia_2);

		JButton btnNewButton_2 = new JButton("Mostrar alternativas");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vuelosTrayecto(e);
			}
		});
		btnNewButton_2.setBounds(194, 112, 180, 23);
		agregarTrayectoItinerario.add(btnNewButton_2);

		JLabel lblVuelosQueCumplen = new JLabel("Vuelos que cumplen requerimientos");
		lblVuelosQueCumplen.setBounds(10, 146, 180, 14);
		agregarTrayectoItinerario.add(lblVuelosQueCumplen);

		scrollPaneTrayectos = new JScrollPane();
		scrollPaneTrayectos.setBounds(79, 171, 695, 81);
		agregarTrayectoItinerario.add(scrollPaneTrayectos);
		
		rowData2 = new Vector();
		columNamesV2 = new Vector(Arrays.asList(columNames2));
		tableVrequerimientos = new JTable(rowData2, columNamesV2);
		scrollPaneTrayectos.setViewportView(tableVrequerimientos);

		JLabel lblNewLabel_6 = new JLabel("Seleccione agente");
		lblNewLabel_6.setBounds(10, 278, 180, 14);
		agregarTrayectoItinerario.add(lblNewLabel_6);

		comboAgentes_1.setBounds(194, 275, 180, 20);
		agregarTrayectoItinerario.add(comboAgentes_1);

		JLabel lblSeleccioneItinerario = new JLabel("Seleccione itinerario");
		lblSeleccioneItinerario.setBounds(10, 306, 139, 14);
		agregarTrayectoItinerario.add(lblSeleccioneItinerario);

		comboItinerarios = new JComboBox();
		comboItinerarios.setBounds(194, 303, 180, 20);
		agregarTrayectoItinerario.add(comboItinerarios);

		JButton btnRegistrarTrayecto = new JButton("Registrar trayecto");
		btnRegistrarTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRegistrarTrayecto.setBounds(115, 348, 139, 23);
		agregarTrayectoItinerario.add(btnRegistrarTrayecto);

		JButton btnRegresar_2 = new JButton("Regresar");
		btnRegresar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_2.setBounds(332, 348, 110, 23);
		agregarTrayectoItinerario.add(btnRegresar_2);
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
				agregarNombreCiudades(comboOrigen);
				agregarNombreCiudades(comboDestino);
				agregarTrayectoItinerario.add(comboOrigen);
				agregarTrayectoItinerario.add(comboDestino);
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
				//comboAerolineas.setBounds(226, 8, 198, 20);
				agregarNombreAerolineas();
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
				agregarNombreAgentes();
				agregarItinerario.add(comboAgentes);
			}else{
				JOptionPane.showConfirmDialog(this, "Error en la lectura de archivos", "Problema", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void agregarNombreAerolineas(){
		for(Aerolinea aerolinea : sistemaVuelo.getAerolineas()){
			comboAerolineas.addItem(aerolinea.getNombre());
		}
	}
	private Aerolinea aerolineaEnCombo(){
		int indiceItem = comboAerolineas.getSelectedIndex();
		String nameAerolinea = comboAerolineas.getItemAt(indiceItem);
		int iAerolinea = sistemaVuelo.buscarAerolinea(nameAerolinea);
		if(iAerolinea >= 0){
			Aerolinea aerolinea = sistemaVuelo.getAerolineas().get(iAerolinea);
			return aerolinea;
		}
		else{
			return null;
		}
	}
	private void mostrarVuelosPlaneados(ActionEvent e){
		Aerolinea aerolinea = aerolineaEnCombo();
		if(aerolinea != null){
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
	private void agregarVueloEsp(ActionEvent e){
		if(aerolineaEnCombo() != null){
			int indexFilaSeleccionada = tableVuelosP.getSelectedRow();
			String codVP = String.valueOf(tableVuelosP.getValueAt(indexFilaSeleccionada, 0));
			Aerolinea aerolinea = aerolineaEnCombo();
			long codigoA = aerolinea.getCodigo();
			long codigoVP = Long.parseLong(codVP);
			int capacidad = Integer.parseInt(txtCapacidad.getText());
			long tarifa = Long.parseLong(txtTarifa.getText());
			long valorAdicional = Long.parseLong(txtImpuesto.getText());
			int indiceAno = comboAno.getSelectedIndex();
			int indiceMes = comboMes.getSelectedIndex();
			int indiceDia = comboDia.getSelectedIndex();
			String fech = comboAno.getItemAt(indiceAno)+"-"+comboMes.getItemAt(indiceMes)+"-"+comboDia.getItemAt(indiceDia);
			LocalDate fecha = Utils.conversorFecha(fech);
			String tipoAvion = comboTipoA.getItemAt(comboTipoA.getSelectedIndex());
			String tipo = comboTipoV.getItemAt(comboTipoV.getSelectedIndex());
			long codVE = sistemaVuelo.crearVueloEspecifico(codigoA, codigoVP, fecha, tipoAvion, capacidad, tarifa, valorAdicional, tipo);
			if(codVE >= 0){
				JOptionPane.showMessageDialog(this, codVE);
			}else{
				JOptionPane.showConfirmDialog(this, "Error");
			}
		}
	}
	private String[] generarAno(){
		String[] arreglo = new String[200];
		for(int i = 0; i < 200; i++){
			arreglo[i] = String.valueOf(1900+i);
		}
		return arreglo;
	}
	private String[] generarMes(){
		String[] arreglo = new String[12];
		for(int i = 0; i < 12; i++){	//Se que la manera correcta sería i=1 y así no tengo que hacer i+1, sin embargo en el comboBox deja un espacio vacío al inicio cuando lo hago así
			arreglo[i] = String.valueOf(i+1);
		}
		return arreglo;
	}
	private String[] generarDia(){
		String[] arreglo = new String[32];
		for(int i = 0; i < 31; i++){	//Se que la manera correcta sería i=1 y así no tengo que hacer i+1, sin embargo en el comboBox deja un espacio vacío al inicio cuando lo hago así
			arreglo[i] = String.valueOf(i+1);
		}
		return arreglo;
	}
	private long registrarItinerario(ActionEvent e){
		Agente agente = agenteEnCombo();
		String nombre = txtnombreItinerario.getText();
		long codI = sistemaVuelo.crearItinerario(agente.getCodigo(), nombre);
		if(codI >= 0){
			JOptionPane.showMessageDialog(this, codI);
			return codI;
		}else{
			JOptionPane.showConfirmDialog(this, "Error");
			return -1;
		}
	}
	private void agregarNombreAgentes(){
		for(Agente agente : sistemaVuelo.getAgentes()){
			comboAgentes.addItem(agente.getNombre());
		}
	}
	private void registrarPasajero(ActionEvent e, long codI){
		Agente agente = agenteEnCombo();
		int indiceRequiere = comboSoloAsistencia.getSelectedIndex();
		String requi = comboSoloAsistencia.getItemAt(indiceRequiere);
		int indiceAno = comboAno_1.getSelectedIndex();
		int indiceMes = comboMes_1.getSelectedIndex();
		int indiceDia = comboDia_1.getSelectedIndex();
		String fech = comboAno_1.getItemAt(indiceAno)+"-"+comboMes_1.getItemAt(indiceMes)+"-"+comboDia_1.getItemAt(indiceDia);
		long edad = Utils.obtenerEdad(Utils.conversorFecha(fech));
		boolean tipo = true;
		if(edad < 12){
			tipo = false;
		}
		boolean requiere = false;
		if(requi.equals("Si")){
			requiere = true;
		}
		sistemaVuelo.crearPasajero(agente.getCodigo(), codI, txtID.getText(), txtNombre.getText(), tipo, requiere);
		JOptionPane.showMessageDialog(this, "Registrado");
	}
	private Agente agenteEnCombo(){
		int indiceAgente = comboAgentes.getSelectedIndex();
		String nameAgente = comboAgentes.getItemAt(indiceAgente);
		int iAgente = sistemaVuelo.buscarAgente(nameAgente);
		if(iAgente >= 0){
			Agente agente = sistemaVuelo.getAgentes().get(iAgente);
			return agente;
		}
		else{
			return null;
		}
	}
	private void agregarNombreCiudades(JComboBox<String> comboCiudad){
		for(Ciudad ciudad : sistemaVuelo.getCiudades()){
			comboCiudad.addItem(ciudad.getNombre());
		}
	}
	private void vuelosTrayecto(ActionEvent e){
		int indiceOrigen = comboOrigen.getSelectedIndex();
		int indiceDestino = comboDestino.getSelectedIndex();
		long codOrigen = sistemaVuelo.getCiudades().get(sistemaVuelo.buscarCiudad(comboOrigen.getItemAt(indiceOrigen))).getCodigo();
		long codDest = sistemaVuelo.getCiudades().get(sistemaVuelo.buscarCiudad(comboDestino.getItemAt(indiceDestino))).getCodigo();
		int indiceAno = comboAno_2.getSelectedIndex();
		int indiceMes = comboMes_2.getSelectedIndex();
		int indiceDia = comboDia_2.getSelectedIndex();
		String fech = comboAno_2.getItemAt(indiceAno)+"-"+comboMes_2.getItemAt(indiceMes)+"-"+comboDia_2.getItemAt(indiceDia);
		LocalDate fecha = Utils.conversorFecha(fech);
		if(sistemaVuelo.mostrarVuelosEspecificosPedidos(codOrigen, codDest, fecha)){
			for(Aerolinea aerolineaI : sistemaVuelo.getAerolineas()){
				for(VueloPlaneado vueloPlaneadoI : aerolineaI.getVuelosPlaneadosPedidos()){
					for(VueloEspecifico vueloEspecificoI : vueloPlaneadoI.getVuelosEspecificosPedidos()){
						String tipo;
						long extra;
						Vector fila = new Vector();
						fila.add(vueloEspecificoI.getCodigo());
						fila.add(aerolineaI.getNombre());
						fila.add(vueloPlaneadoI.getNumeroVuelo());
						fila.add(vueloPlaneadoI.getHoraSalida()+"/"+vueloPlaneadoI.getHoraLlegada());
						fila.add(vueloEspecificoI.getCuposLibres());
						fila.add(vueloEspecificoI.getTarifa());
						if(vueloEspecificoI instanceof VueloEspecificoNacional){
							tipo = "nacional";
							extra = ((VueloEspecificoNacional) vueloEspecificoI).getIva();
						}else{
							tipo = "internacional";
							extra = ((VueloEspecificoInternacional) vueloEspecificoI).getImpuestoSalida();
						}
						fila.add(tipo);
						fila.add(extra);
						rowData2.add(fila);
					}
				}
			}
		}
		tableVrequerimientos = new JTable(rowData2, columNamesV2);
		scrollPaneTrayectos.setViewportView(tableVrequerimientos);
	}
}
