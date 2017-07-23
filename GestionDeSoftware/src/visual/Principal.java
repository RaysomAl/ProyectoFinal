package visual;

import java.awt.BorderLayout;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import logica.Cliente;
import logica.Contrato;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Grafica;
import logica.Indepediente;
import logica.Relog;
import logica.Trabajador;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Principal extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private static JPanel Grafica1;
	private JPanel GraficaLenguaje;
	private JPanel Grafica3;
	private JPanel Grafica4;
	private JList<String> empleados;
	DefaultCategoryDataset datosLen = new DefaultCategoryDataset();
	
	private boolean estado;
	private String hora;
    private String minuto;
    private String segundo;
    private String ano;
    private String semana;
    private String dia;
    private String mes;
    private Thread hilo;  
    private String amPm;
    
	private JLabel lbHora;
	private JLabel lbpunto;
	private JLabel lbMinutos;
	private JLabel lbsegundo;
	private JLabel lbsemana;
	private JLabel lbdia;
	private JLabel lbmes;
	private JLabel lbano;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel");
					Principal frame = new Principal();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					FileOutputStream salida = new FileOutputStream("biblioteca.dat");
					ObjectOutputStream escritor = new ObjectOutputStream(salida);
					escritor.writeObject(EmpresaRps.getInstance());
					escritor.close();
				} catch(IOException a1) {
					a1.printStackTrace();;
				}
				
			}
		}); 
		try {
	    	 	FileInputStream leerFileInputStream = null;
	 	    	ObjectInputStream empresaRpsInputStream = null;
	 	    	leerFileInputStream = new FileInputStream("biblioteca.dat");
	 	    	empresaRpsInputStream = new ObjectInputStream(leerFileInputStream);
	 	    	EmpresaRps.setInstance((EmpresaRps)empresaRpsInputStream.readObject());
	 	    	leerFileInputStream.close();
	     } catch (FileNotFoundException e2) {
				JOptionPane.showMessageDialog(null,  "Error en crear el archivo de base datos, nada reciente se salvo", "Error",JOptionPane.ERROR_MESSAGE);
	     } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	     } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	     }
	     
		setTitle("EmpresaRps.S.A.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/evaluation.png")));
		setResizable(false);
		dim = super.getToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(dim.width-100, dim.height-100);
		setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/004-group.png")));
		menuBar.add(mnClientes);
		
		JMenuItem mntmCrearCliente = new JMenuItem("Registrar Cliente");
		mntmCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		mntmCrearCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnClientes.add(mntmCrearCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Lista de Clientes");
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmListarClientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnClientes.add(mntmListarClientes);
		JMenu mnTrabajadores = new JMenu("Trabajadores");
		mnTrabajadores.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTrabajadores.setIcon(new ImageIcon(Principal.class.getResource("/img/trabajador.png")));
		menuBar.add(mnTrabajadores);
		
		JMenuItem mntmCrearTrabajador = new JMenuItem("Registrar trabajador");
		mntmCrearTrabajador.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCrearTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarTrabajador registrarTrabajador = new RegistrarTrabajador();
				registrarTrabajador.setVisible(true);
			}
		});
		mnTrabajadores.add(mntmCrearTrabajador);
		
		JMenuItem mntmListarTrabajadores = new JMenuItem("Lista de trabajadores");
		mntmListarTrabajadores.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmListarTrabajadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarTrabajadores listarTraba;
				try {
					listarTraba = new ListarTrabajadores();
					listarTraba.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnTrabajadores.add(mntmListarTrabajadores);
		
		JMenu mnContratos = new JMenu("Contratos");
		mnContratos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnContratos.setIcon(new ImageIcon(Principal.class.getResource("/img/contract.png")));
		menuBar.add(mnContratos);
		
		JMenuItem mntmCrearContratos = new JMenuItem("Realiza Contrato");
		mntmCrearContratos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCrearContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProyecto nuevoP = new CrearProyecto();
				nuevoP.setModal(true);
				nuevoP.setVisible(true);
			}
		});
		mnContratos.add(mntmCrearContratos);
		
		JMenuItem mntmListarContratos = new JMenuItem("Lista de Contratos");
		mntmListarContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarContratos nuevoLis = new ListarContratos();
				nuevoLis.setModal(true);
				nuevoLis.setVisible(true);
			}
		});
		mntmListarContratos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnContratos.add(mntmListarContratos);
		
		JMenu mnAdministracion = new JMenu("Administracion");
		mnAdministracion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAdministracion.setIcon(new ImageIcon(Principal.class.getResource("/img/admin.png")));
		menuBar.add(mnAdministracion);
		
		JMenuItem mntmControlDeTasa = new JMenuItem("Control de Tasa");
		mntmControlDeTasa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmControlDeTasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EmpresaRps.getIntento()!=0) {
					CambioDeTasa nuevaTasa = new CambioDeTasa();
					nuevaTasa.setModal(true);
					nuevaTasa.setVisible(true);
				}else {
					int opcion = JOptionPane.showConfirmDialog(null, "Para cambiar la tasa necesita primero una contraseña, ¿¿desea crearla??","Advertencia",JOptionPane.WARNING_MESSAGE);
					if(opcion == JOptionPane.OK_OPTION){
						NuevaPass nuevacontra = new NuevaPass();
						nuevacontra.setModal(true);
						nuevacontra.setVisible(true);
					}
				}
			}
		});
		mnAdministracion.add(mntmControlDeTasa);
		
		JMenuItem mntmCambiarcontrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmCambiarcontrasea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCambiarcontrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EmpresaRps.getIntento()==0) {
					NuevaPass nuevacontra = new NuevaPass();
					nuevacontra.setModal(true);
					nuevacontra.setVisible(true);
				}else {
					Contraseña nuevacontra = new Contraseña();
					nuevacontra.setModal(true);
					nuevacontra.setVisible(true);
				}
				
			}
		});
		mnAdministracion.add(mntmCambiarcontrasea);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new java.awt.BorderLayout());
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelGrafica1 = new JPanel();
		panelGrafica1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		panelGrafica1.setBounds(21, 11, 550, 282);
		panel.add(panelGrafica1);
		panelGrafica1.setLayout(null);
		
		Grafica1 = new JPanel();
		Grafica1.setBounds(10, 11, 530, 260);
		panelGrafica1.add(Grafica1);
		Grafica1.setLayout(new BorderLayout());
				
		GraficaLenguaje = new JPanel();
		GraficaLenguaje.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		GraficaLenguaje.setBounds(582, 11, 572, 282);
		panel.add(GraficaLenguaje);
		GraficaLenguaje.setLayout(null);
		
		
		//Java", "C", "C++", "Python", "C#", "Visual Basic. NET", "JavaScript", "PHP", "Perl", "Assembly language"
		
		ArrayList<String> lenguajes = new ArrayList<>();
		lenguajes.add("Java");
		lenguajes.add("C#");
		lenguajes.add("C");
		lenguajes.add("Visual Basic");
		lenguajes.add("C++");
		lenguajes.add("Perl");
		lenguajes.add("Assembly language");
		lenguajes.add("PHP");
		lenguajes.add("JavaScript");
		lenguajes.add("Python");
		Grafica masUsado =EmpresaRps.getInstance().getMasUsado(lenguajes);
		Grafica menosUsado =EmpresaRps.getInstance().getMenosUsado(lenguajes);
		datosLen.addValue(masUsado.getFrec(),"Más Usado",masUsado.getTipo());
		datosLen.addValue(menosUsado.getFrec(),"Menos Usado", menosUsado.getTipo());
		JFreeChart graficaLen = ChartFactory.createBarChart3D("Lenguaje más usado Vs Lenguaje menos usado", "Lenguaje", "Cantidad de Contratos", datosLen, PlotOrientation.VERTICAL, true, true, false);
		
		JPanel panelLenguaje = new JPanel();
		panelLenguaje.setBounds(10, 11, 552, 260);
		GraficaLenguaje.add(panelLenguaje);
		panelLenguaje.setLayout(new BorderLayout(0, 0));
		
		JPanel gLenguaje = new ChartPanel(graficaLen);
		panelLenguaje.add(gLenguaje, BorderLayout.CENTER);
		
		XYDataset dataset = dataset();
		JFreeChart grafica = ChartFactory.createXYLineChart("Ganancias vs Pedidas", "Meses", "RD$", dataset
				,PlotOrientation.VERTICAL,true,true,false);
		graficaGananciaVsPerdidas(grafica);
		grafica.setBackgroundPaint(Color.LIGHT_GRAY);
		ChartPanel panelgrafica = new ChartPanel(grafica);//creamos un panel para la grafica
		panelgrafica.setDomainZoomable(false);//no sera zoomeable por click izquierdo abriendo en eje x
		panelgrafica.setRangeZoomable(false);//no sera zoomeable por click izquierdo abrindo en eje y
		Grafica1.add(panelgrafica,BorderLayout.CENTER);//insertamos la grafica en el panel grafica 
		
		Grafica3 = new JPanel();
		Grafica3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		Grafica3.setBounds(592, 304, 242, 272);
		panel.add(Grafica3);
		Grafica3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 222, 241);
		Grafica3.add(scrollPane);
		
		empleados = new JList<String>();
		scrollPane.setViewportView(empleados);
		empleados.setSelectionInterval(-1, -1);
		limitarSeleccionLista(empleados, -1, -1);
		empleados.setListData(CargarLista());
		
		Grafica4 = new JPanel();
		Grafica4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		Grafica4.setBounds(21, 304, 550, 272);
		CategoryDataset stackDataset = crearStack();
		JFreeChart graficaBarras = crearGrafica(stackDataset);
		ChartPanel panelGrafica4 = new ChartPanel(graficaBarras);
		panel.add(Grafica4);
		Grafica4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(panelGrafica4,BorderLayout.CENTER);
		panel_1.setBounds(10, 11, 530, 250);
		Grafica4.add(panel_1);
		
		JPanel Reloj = new JPanel();
		Reloj.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		Reloj.setBounds(844, 304, 310, 272);
		panel.add(Reloj);
		Reloj.setLayout(null);
		
		lbHora = new JLabel("60");
		lbHora.setFont(new Font("Segoe UI", Font.BOLD, 85));
		lbHora.setBounds(10, 26, 104, 87);
		Reloj.add(lbHora);
		
		lbpunto = new JLabel(":");
		lbpunto.setFont(new Font("Segoe UI", Font.BOLD, 90));
		lbpunto.setBounds(124, 23, 32, 87);
		Reloj.add(lbpunto);
		
		lbMinutos = new JLabel("60");
		lbMinutos.setFont(new Font("Segoe UI", Font.BOLD, 85));
		lbMinutos.setBounds(158, 26, 104, 87);
		Reloj.add(lbMinutos);
		
		lbsemana = new JLabel("Domingo");
		lbsemana.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lbsemana.setBounds(10, 164, 138, 52);
		Reloj.add(lbsemana);
		
		lbdia = new JLabel("20");
		lbdia.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lbdia.setBounds(150, 146, 82, 64);
		Reloj.add(lbdia);
		
		lbmes = new JLabel("Mayo");
		lbmes.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lbmes.setBounds(232, 151, 82, 39);
		Reloj.add(lbmes);
		
		lbano = new JLabel("2017");
		lbano.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lbano.setBounds(232, 192, 74, 38);
		Reloj.add(lbano);
		
		lbsegundo = new JLabel("60");
		lbsegundo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lbsegundo.setBounds(272, 83, 32, 25);
		Reloj.add(lbsegundo);
		
		hilo = new Thread(this);
		hilo.start();
	}

	public static void actualizarGraficas() {
		XYDataset dataset = dataset();
		JFreeChart grafica = ChartFactory.createXYLineChart("Ganancias vs Pedidas", "Meses", "RD$", dataset
				,PlotOrientation.VERTICAL,true,true,false);
		graficaGananciaVsPerdidas(grafica);
		grafica.setBackgroundPaint(Color.WHITE);
		ChartPanel panelgrafica = new ChartPanel(grafica);//creamos un panel para la grafica
		panelgrafica.setDomainZoomable(false);//no sera zoomeable por click izquierdo abriendo en eje x
		panelgrafica.setRangeZoomable(false);//no sera zoomeable por click izquierdo abrindo en eje y
		Grafica1.add(panelgrafica,BorderLayout.CENTER);//insertamos la grafica en el panel grafica 
		
	}

	private String[] CargarLista() {
		ArrayList<Trabajador> malosTrabajadores = new ArrayList<Trabajador>();
		CargarMalosTrabajadores(malosTrabajadores);	
		String malosTrabajadore[]= new String[malosTrabajadores.size()];
		if(malosTrabajadores.size()==0)
			malosTrabajadore = new String[1];
		cargarAarray(malosTrabajadores,malosTrabajadore);
		if(malosTrabajadores.size()==0)
			malosTrabajadore[0]= "No hay trabajadores Ineficientes";
		return malosTrabajadore;
	}

	private void CargarMalosTrabajadores(ArrayList<Trabajador> malosTrabajadores) {
		for (Trabajador trabajador : EmpresaRps.getInstance().getMistrabajadores()) 
			if(trabajador.getEficiencia()<=3)
				malosTrabajadores.add(trabajador);
	}
	
	private void cargarAarray(ArrayList<Trabajador> malosTrabajadores, String[] malosTrabajadore) {
		for (int i = 0; i < malosTrabajadores.size(); i++) 
			malosTrabajadore[i] = malosTrabajadores.get(i).getCedula()+"("+malosTrabajadores.get(i).getNombre()+")";
	}

	private static void graficaGananciaVsPerdidas(JFreeChart grafica) {
		XYPlot personalizacion = grafica.getXYPlot();//crear plot de una grafica, permite interactuar con fisico de grafica
		personalizacion.setBackgroundPaint(Color.WHITE);//COLOR FONDO de grafica
		personalizacion.setDomainGridlinePaint(Color.GRAY);//COLOR LINEAS VERTICALES grafica
		personalizacion.setRangeGridlinePaint(Color.BLACK);//COLOR LINEAS HORIZONTALES grafica
		String[] meses = new String[12];//String que tendra los 12 meses
		cargarMeses(meses);//cargara los meses basado en cual estemos con relog de pc
		SymbolAxis axis = new SymbolAxis("Meses", meses);// crea un axis para eje x con un nombre y string que formaran el eje x
		axis.setTickUnit(new NumberTickUnit(1));//en axis separa de unidades
		axis.setRange(0,meses.length);//inicio de eje x y fin de eje x, todo referido a tamaño
		personalizacion.setDomainAxis(axis);//inserta el axis con todo detalles 
		NumberAxis rango = (NumberAxis) personalizacion.getRangeAxis();//creamos axis eje, este es numerico asi que es mas sencillo
		rango.setRange(0.0,100000);//decimos de donde a donde vamos, ella misma pone intervalos 
		XYLineAndShapeRenderer lineas = (XYLineAndShapeRenderer) personalizacion.getRenderer();//editar las lineas de la graficas
		lineas.setBaseShapesVisible(true);//lineas de punto a punto sera visibles no solo los puntos
		XYItemLabelGenerator label = new StandardXYItemLabelGenerator();//generar labels para los puntos
		lineas.setBaseItemLabelGenerator(label);//insertar los labels en las opciones de las lineas que creamos en linea 147
		lineas.setBaseItemLabelsVisible(true);
		lineas.setBaseLinesVisible(true);
		lineas.setBaseItemLabelsVisible(true);
	}

	private static XYDataset dataset() {//creamos hoja de datos se inserta en linea 111 
		XYSeries saldoIngresos = new XYSeries("Ingresos");//datos de ingresos
		XYSeries saldoPerdidas = new XYSeries("Perdidas");//datos de perdidas
		Float saldoArray = null;//auxiliar para guardar flotantes
		LocalDate NOW = LocalDate.now();//164 a 188 son contratos auxiliares con fechas variadas
		ArrayList<Float> total = new ArrayList<Float>();
		cargarArray(total, 0, 10);
		ArrayList<Float> Perdidas = new ArrayList<Float>();
		cargarArray(Perdidas, 0, 10);
		for (Contrato aux : EmpresaRps.getInstance().getMiscontratos()) {//recorre los contratos existentes
			if(aux.isTerminado()) {//si el contrato termino
				if(-(Period.between(NOW, aux.getFechaSaldada().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())).getMonths()<10) {//si el contrato es reciente de ultimos 10 mese
					for (int i = 10; i >= 0; i--){//solo presentara los contrates de los ultimos 10 meses
						if((Period.between(aux.getFechaSaldada().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),LocalDate.now()).getMonths())==i) {
							//se ingresara el saldo del contrato gano y perdido a sus respectivos arrays
							saldoArray = total.get(i);
							total.add(i, saldoArray + aux.montoPagar(true));
							saldoArray = (float) 0;
							saldoArray = Perdidas.get(i);
							Perdidas.add(i, saldoArray+aux.montoPagar(false));
							saldoArray = (float) 0;
						}
					}
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			saldoIngresos.add(9-i,total.get(i));
			saldoPerdidas.add(9-i,Perdidas.get(i));
		}
		XYSeriesCollection data = new XYSeriesCollection();//colleccion para guardar todo en un solo data
		data.addSeries(saldoIngresos);// ingresamos ambos datas de ingresos y perdidas
		data.addSeries(saldoPerdidas);
		return data;
	}
	
	private JFreeChart crearGrafica(CategoryDataset stackDataset) {
		JFreeChart grafica = ChartFactory.createStackedBarChart
				("Clientes con contratos tardios", "Clientes", "Cantidad Contratos", stackDataset, PlotOrientation.VERTICAL,
						true, true, false);
		
		return grafica;
	}
	private CategoryDataset crearStack() {
		DefaultCategoryDataset resultados = new DefaultCategoryDataset();
		//resultados.addValue(value, rowKey, columnKey);
		ArrayList<Cliente> misDATOS = new ArrayList<Cliente>();
		ArrayList<Integer> aldia = new ArrayList<Integer>();
		ArrayList<Integer> tardio = new ArrayList<Integer>();
		cargarInt(aldia, 0, EmpresaRps.getInstance().getMisclientes().size());
		cargarInt(tardio, 0, EmpresaRps.getInstance().getMisclientes().size());
		int i = 0;
		for (Cliente clien : EmpresaRps.getInstance().getMisclientes()) {
			for (Contrato contr : EmpresaRps.getInstance().getMiscontratos()) {
				if(contr.isTerminado()) {
					if(contr.getCliente() instanceof Empresa)
						if(clien instanceof Empresa)
							if(((Empresa)clien).getRnc().equalsIgnoreCase(((Empresa)contr.getCliente()).getRnc())) { 
								if(ComparaFecha(contr.getFechaFinal(),contr.getFechaSaldada()))
									aldia.set(i, aldia.get(i)+1);
								if(!ComparaFecha(contr.getFechaFinal(),contr.getFechaSaldada()))
									tardio.set(i, tardio.get(i)+1);
							}
					if(contr.getCliente() instanceof Indepediente)
						if(clien instanceof Indepediente)
							if(((Indepediente)clien).getCedula().equalsIgnoreCase(((Indepediente)contr.getCliente()).getCedula())) { 
								if(ComparaFecha(contr.getFechaFinal(),contr.getFechaSaldada()))
									aldia.set(i, aldia.get(i)+1);
								if(!ComparaFecha(contr.getFechaFinal(),contr.getFechaSaldada()))
									tardio.set(i, tardio.get(i)+1);
							}
				}		
			}
			misDATOS.set(i, clien);
			i++;
		}
		Integer[] losMasTarde = {0,0,0,0,0,0,0,0};
		int aux = 0;
		for (int j = 0; j < losMasTarde.length; j++) {
			for (int x = 0; j < tardio.size(); j++) {
				if(tardio.get(j)>aux&&buscarInt(losMasTarde,j))
					aux = j;
			}
			losMasTarde[j]=aux;
			aux = 0;
		}
		for (int j = 0; j < losMasTarde.length; j++) {
			if(losMasTarde[j]!=0) {
				resultados.addValue(aldia.get(j), "Al dia", misDATOS.get(j).getNombre());
				resultados.addValue(tardio.get(j), "Tardio", misDATOS.get(j).getNombre());
			}
		}
		return resultados;
	}
	
	private boolean buscarInt(Integer[] losMasTarde,int j) {
		for (int i = 0; i < losMasTarde.length; i++) {
			if(j == losMasTarde[i])
				return false;
		}
		return true;
	}

	private boolean ComparaFecha(Calendar fechaFinal, Calendar fechaSaldada) {
		Date now = fechaFinal.getTime();
		Date later = fechaSaldada.getTime();
		long duracion  = now.getTime()-later.getTime();
		long diffdedias = TimeUnit.MILLISECONDS.toDays(duracion);
		long diff = duracion - TimeUnit.DAYS.toMillis(diffdedias);
		double diffdeHoras = TimeUnit.MILLISECONDS.toHours(diff);
		float horasAdias = (float) (diffdeHoras/24.0);
		float a = horasAdias + diffdedias;
		a=(float) Math.floor(a);
		int b = (int) a;
		if(b==0)
			return true;
		return false;
	}

	private static void cargarMeses(String[] meses) {//metodo que carga los meses basado la fecha actual
		LocalDate aux = LocalDate.now();
		int mes = aux.getMonthValue();
		if(mes<11)
			mes= mes+2;
		if(mes==11)
			mes = 1;
		if(mes==12)
			mes = 2;
		for (int i = 0; i < 12; i++) {
			meses[i] = carString(mes);
			if(mes==12)
				mes = 1;
			mes++;
		}
	}
	
	
	private static String carString(int mes) {//retorna el mes basado en un int de 1 a 12
			if(mes==1)
				return "Ene.";
			if(mes==2)
				return "Feb.";
			if(mes==3)
				return "Mar.";
			if(mes==4)
				return "Abr.";
			if(mes==5)
				return "May.";
			if(mes==6)
				return "Jun.";
			if(mes==7)
				return "Jul.";
			if(mes==8)
				return "Ago.";
			if(mes==9)
				return "Sept.";
			if(mes==10)
				return "Oct.";
			if(mes==11)
				return "Nov..";
			if(mes==12)
				return "Dic.";
		return null;
	}

	private static void cargarArray(ArrayList<Float> total, int unidad, int limite) {// llena un array float de una unidad, hasta su limite maximo
		for (int i = 0; i < limite; i++) {
			total.add((float) unidad);
		}
	}
	private void cargarInt(ArrayList<Integer> total, int unidad, int limite) {// llena un array float de una unidad, hasta su limite maximo
		for (int i = 0; i < limite; i++) {
			total.add((int) unidad);
		}
	}
	
	private void limitarSeleccionLista(final JList<String> lista,final int maxCounte,final int minCounte) {
		class MySelectionModel extends DefaultListSelectionModel
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private JList<String> list;
		    private int minCount;
		    private int maxCount;

		    private MySelectionModel()
		    {
		        this.list = lista;
		        this.maxCount = maxCounte;
		        this.minCount = minCounte;
		    }

		    @Override
		    public void setSelectionInterval(int index0, int index1)
		    {
		    	if(index0!= minCount)
		        	index0 = minCount;
		        if (index1 - index0 >= maxCount)
		        {
		            index1 = index0 + maxCount - 1;
		        }
		        
		        super.setSelectionInterval(index0, index1);
		    }

		    @Override
		    public void addSelectionInterval(int index0, int index1)
		    {
		        int selectionLength = list.getSelectedIndices().length;
		        if (selectionLength >= maxCount)
		            return;
		        //klk
		        if (index1 - index0 >= maxCount - selectionLength)
		        {
		            index1 = index0 + maxCount - 1 - selectionLength;
		        }
		        if (index1 < index0)
		            return;
		        super.addSelectionInterval(index0, index1);
		    }
		}
		lista.setSelectionModel(new MySelectionModel());
		
	
	}
	
	
	
	public void run() {
		Thread actual = Thread.currentThread();
		while(actual == hilo){
			timempo();
			lbHora.setText(hora);
			lbMinutos.setText(minuto);
			lbsegundo.setText(segundo);
			lbsemana.setText(semana);
			lbdia.setText(dia);
			lbmes.setText(mes);
			lbano.setText(ano);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void timempo() {
		Calendar calendario = new GregorianCalendar();
		Date fecha = new Date();
		
		calendario.setTime(fecha);
		amPm = calendario.get(Calendar.AM_PM) == Calendar.AM?"AM":"PM";
		
		if(amPm.equals("PM")){
			int hour = calendario.get(Calendar.HOUR_OF_DAY);
			hora = hour > 9?""+hour:"0"+hour;
		}else{
			hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); 
        }
		minuto = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
		segundo = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
		
		dia = String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
        int m = calendario.get(Calendar.MONTH);
        if(m == 0)
        	mes = "Enero";
        else if(m == 1)
        	mes = "Febrero";
        else if(m == 2)
        	mes = "Marzo";
        else if(m == 3)
        	mes = "Abril";
        else if(m == 4)
        	mes = "Mayo";
        else if(m == 5)
        	mes = "Junio";
        else if(m == 6)
        	mes = "Julio";
        else if(m == 7)
        	mes = "Agosto";
        else if(m == 8)
        	mes = "Septiembre";
        else if(m == 9)
        	mes = "Octubre";
        else if(m == 10)
        	mes = "Noviembre";
        else if(m == 11)
        	mes = "Diciembre";
        
        ano = String.valueOf(calendario.get(Calendar.YEAR));
        
        int diaSem =  calendario.get(Calendar.DAY_OF_WEEK);
        if(diaSem == 1)
        	semana = "Domingo";
        else if(diaSem == 2)
        	semana = "Lunes";
        else if(diaSem == 3)
        	semana = "Martes";
        else if(diaSem == 4)
        	semana = "Miércoles";
        else if(diaSem == 5)
        	semana = "Jueves";
        else if(diaSem == 6)
        	semana = "Viernes";
        else if(diaSem == 7)
        	semana = "Sábado";
        
	}
	

}
