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

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private JPanel Grafica1;
	private JPanel GraficaLenguaje;
	private JPanel Grafica3;
	private JPanel Grafica4;
	private JList<String> empleados;
	DefaultCategoryDataset datosLen = new DefaultCategoryDataset();
	private Relog miRelog;
	private String minutos, segundos, horas;
	private Thread hilo;
	private boolean estado;
	private JTextField txtHora;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel");
				/*PARTE DEL FICHERO BINARIO
				 * 
				 * FileInputStream leerFileInputStream = null;
			     ObjectInputStream empresaRpsInputStream = null;
			     
			     leerFileInputStream = new FileInputStream("biblioteca.dat");
			     empresaRpsInputStream = new ObjectInputStream(leerFileInputStream);
			     EmpresaRps.setInstance((EmpresaRps)empresaRpsInputStream.readObject());*/
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("EmpresaRps.S.A.");
		miRelog = new Relog();
		start(miRelog);
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
		mntmCrearCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnClientes.add(mntmCrearCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Lista de Clientes");
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
		panelGrafica1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelGrafica1.setBounds(21, 11, 550, 282);
		panel.add(panelGrafica1);
		panelGrafica1.setLayout(null);
		
		Grafica1 = new JPanel();
		Grafica1.setBounds(10, 11, 530, 260);
		panelGrafica1.add(Grafica1);
		Grafica1.setLayout(new BorderLayout());
				
		GraficaLenguaje = new JPanel();
		GraficaLenguaje.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		Grafica3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Empleados Ineficientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		Grafica3.setBounds(592, 304, 221, 272);
		panel.add(Grafica3);
		Grafica3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 201, 241);
		Grafica3.add(scrollPane);
		
		empleados = new JList<String>();
		scrollPane.setViewportView(empleados);
		empleados.setSelectionInterval(-1, -1);
		limitarSeleccionLista(empleados, -1, -1);
		empleados.setListData(CargarLista());
		
		Grafica4 = new JPanel();
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
		
		txtHora = new JTextField();
		txtHora.setEnabled(false);
		txtHora.setBounds(890, 423, 86, 20);
		panel.add(txtHora);
		txtHora.setColumns(10);
	}

	/*public static actualizarGraficas() {
		
	}*/

	private String[] CargarLista() {
		ArrayList<Trabajador> malosTrabajadores = new ArrayList<Trabajador>();
		CargarMalosTrabajadores(malosTrabajadores);	
		String malosTrabajadore[]= new String[malosTrabajadores.size()];
		if(malosTrabajadores.size()==0)
			malosTrabajadore = new String[1];
		cargarAarray(malosTrabajadores,malosTrabajadore);
		if(malosTrabajadores.size()==0)
			malosTrabajadore[0]= "No hay trabajadores Ineficientes recientemente";
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

	private void graficaGananciaVsPerdidas(JFreeChart grafica) {
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

	private XYDataset dataset() {//creamos hoja de datos se inserta en linea 111 
		XYSeries saldoIngresos = new XYSeries("Ingresos");//datos de ingresos
		XYSeries saldoPerdidas = new XYSeries("Perdidas");//datos de perdidas
		Float saldoArray = null;//auxiliar para guardar flotantes
		LocalDate NOW = LocalDate.now();//164 a 188 son contratos auxiliares con fechas variadas
		ArrayList<Float> total = new ArrayList<Float>();
		cargarArray(total, 0, 10);
		ArrayList<Float> Perdidas = new ArrayList<Float>();
		cargarArray(Perdidas, 0, 10);
		Calendar ejemplo = Calendar.getInstance();
		ejemplo.add(Calendar.MONTH,0 );
		Contrato e = new Contrato("1", null, ejemplo, Calendar.getInstance(), null, (float)5000, (float)0, (float)48.7);
		e.setTerminado(true);
		e.setFechaSaldada(ejemplo);
		Calendar klk = Calendar.getInstance();
		klk.add(Calendar.MONTH, -5);
		Contrato a = new Contrato("3", null, klk, klk, null, (float)60000, (float)0, (float)48.7);
		a.setTerminado(true);
		a.setFechaSaldada(Calendar.getInstance());
		Calendar fecha = Calendar.getInstance();
		Calendar fecha2 = Calendar.getInstance();
		fecha.add(Calendar.MONTH, -5);
		fecha2.add(Calendar.MONTH, -4);
		Contrato alpha = new Contrato("2", null, fecha, fecha, null, (float)60000, (float)0, (float)48.7);
		alpha.setFechaSaldada(fecha2);
		alpha.setTerminado(true);
		EmpresaRps.getInstance().getMiscontratos().add(e);
		EmpresaRps.getInstance().getMiscontratos().add(alpha);
		EmpresaRps.getInstance().getMiscontratos().add(a);
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

	private void cargarMeses(String[] meses) {//metodo que carga los meses basado la fecha actual
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
	
	
	private String carString(int mes) {//retorna el mes basado en un int de 1 a 12
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

	private void cargarArray(ArrayList<Float> total, int unidad, int limite) {// llena un array float de una unidad, hasta su limite maximo
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
	public void start(Relog relo) {
		estado = true;
		hilo = new Thread() {
		@Override
		public void run() {
			do {
				corre(relo);
			}while(estado);
		}
		};
		hilo.start();
	}
	public void corre(Relog relo) {
		do {
			relo.start();
			horas = relo.getHoras();
			minutos = relo.getMinutos();
			segundos = relo.getSegundos();
			String hora="";
			if(Integer.valueOf(horas)==0)
				hora += String.valueOf(00);
			if(Integer.valueOf(horas)<10)
				hora += String.valueOf(0)+String.valueOf(horas);
			if(Integer.valueOf(horas)>=10)
				hora += String.valueOf(horas);
			hora+=":";
			if(Integer.valueOf(minutos)==0)
				hora += String.valueOf(00);
			if(Integer.valueOf(minutos)<10)
				hora += String.valueOf(0)+String.valueOf(minutos);
			if(Integer.valueOf(minutos)>=10)
				hora += String.valueOf(minutos);
			hora+=":";
			if(Integer.valueOf(segundos)==0)
				hora += String.valueOf(00);
			if(Integer.valueOf(segundos)<10)
				hora += String.valueOf(0)+String.valueOf(segundos);
			if(Integer.valueOf(segundos)>=10)
				hora += String.valueOf(segundos);
			txtHora.setText(hora);
		}while(relo.isAlive());
	}
}
