package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import logica.Contrato;
import logica.EmpresaRps;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel Grafica1;
	private JPanel Grafica2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setResizable(false);
		dim = super.getToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(dim.width-100, dim.height-100);
		setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		JMenu mnTrabajadores = new JMenu("Trabajadores");
		menuBar.add(mnTrabajadores);
		
		JMenu mnContratos = new JMenu("Contratos");
		menuBar.add(mnContratos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new java.awt.BorderLayout());
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Grafica1 = new JPanel();
		Grafica1.setLayout(new BorderLayout());
		Grafica1.setBounds(10, 0, 572, 304);
		panel.add(Grafica1);
		
		Grafica2 = new JPanel();
		Grafica2.setBounds(582, 0, 572, 304);
		panel.add(Grafica2);
		
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("C", 50);
		data.setValue("c++", 150);
		data.setValue("java", 125);
		XYDataset dataset = dataset();
		JFreeChart grafica = ChartFactory.createXYLineChart("Ganancias vs Pedidas", "Meses", "RD$", dataset
				,PlotOrientation.VERTICAL,true,true,false);
		XYPlot personalizacion = grafica.getXYPlot();
		personalizacion.setBackgroundPaint(Color.gray);
		personalizacion.setDomainGridlinePaint(Color.YELLOW);
		personalizacion.setRangeGridlinePaint(Color.WHITE);
		NumberAxis xAxis = (NumberAxis) personalizacion.getDomainAxis();//continuacion, mejorar el rango
		personalizacion.setDomainAxis(xAxis);
		XYLineAndShapeRenderer lineas = (XYLineAndShapeRenderer) personalizacion.getRenderer();
		lineas.setBaseShapesVisible(true);
		XYItemLabelGenerator label = new StandardXYItemLabelGenerator();
		lineas.setBaseItemLabelGenerator(label);
		lineas.setBaseItemLabelsVisible(true);
		lineas.setBaseLinesVisible(true);
		lineas.setBaseItemLabelsVisible(true);
		ChartPanel panelgrafica = new ChartPanel(grafica);
		panelgrafica.setDomainZoomable(true);
		Grafica1.add(panelgrafica,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 304, 572, 293);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(582, 304, 572, 293);
		panel.add(panel_2);
	}

	private XYDataset dataset() {
		XYSeries saldoIngresos = new XYSeries("Ingresos");
		XYSeries saldoPerdidas = new XYSeries("Perdidas");
		Float saldoArray = null;
		LocalDate NOW = LocalDate.now();
		ArrayList<Float> total = new ArrayList<Float>();
		cargarArray(total, 0, 10);
		ArrayList<Float> Perdidas = new ArrayList<Float>();
		cargarArray(Perdidas, 0, 10);
		Calendar ejemplo = Calendar.getInstance();
		ejemplo.add(Calendar.MONTH, -2);
		Contrato e = new Contrato("", null, ejemplo, Calendar.getInstance(), null, (float)15000, (float)0, (float)48.7);
		EmpresaRps.getInstance().getMiscontratos().add(e);
		for (Contrato aux : EmpresaRps.getInstance().getMiscontratos()) {//recorre los contratos existentes
			if(aux.isTerminado())//si el contrato termino
				if(-(Period.between(NOW, aux.getFechaFinal().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())).getMonths()<10) {//si el contrato es reciente de ultimos 10 mese
					for (int i = 10; i > 0; i--){
						if(-(Period.between(NOW, aux.getFechaFinal().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())).getMonths()==i)
							//se ingresara el saldo del contrato gano y perdido a sus respectivos arrays
							saldoArray = total.get(i);
							total.add(i, saldoArray + aux.montoPagar(true));
							saldoArray = Perdidas.get(i);
							Perdidas.add(i, saldoArray+aux.montoPagar(false));
					}
				}
		}
		for (int i = 0; i > 10; i++) {
			saldoIngresos.add(10-i,total.get(i));
			saldoPerdidas.add(10-1,total.get(i));
		}
		XYSeriesCollection data = new XYSeriesCollection();
		data.addSeries(saldoIngresos);
		data.addSeries(saldoPerdidas);
		return data;
	}

	private void cargarArray(ArrayList<Float> total, int unidad, int limite) {
		for (int i = 0; i < limite; i++) {
			total.add((float) unidad);
		}
	}
}
