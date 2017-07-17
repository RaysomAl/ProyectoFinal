package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel Grafica1;
	private JPanel Grafica2;
	private JPanel Grafica3;
	private JPanel Grafica4;
	
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
		super.setSize(dim.width-200, dim.height-200);
		
		
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
		Grafica1.setBounds(0, 0, 212, 115);
		panel.add(Grafica1);
		
		Grafica2 = new JPanel();
		Grafica2.setBounds(212, 0, 205, 115);
		panel.add(Grafica2);
		
		Grafica3 = new JPanel();
		Grafica3.setBounds(0, 115, 212, 115);
		panel.add(Grafica3);
		
		Grafica4 = new JPanel();
		Grafica4.setBounds(212, 115, 205, 115);
		panel.add(Grafica4);
		
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("C", 50);
		data.setValue("c++", 150);
		data.setValue("java", 125);
		JFreeChart grafica = ChartFactory.createPieChart("uso de lenguajes", data,true,true,false);
		ChartPanel panelgrafica = new ChartPanel(grafica);
		panelgrafica.setDomainZoomable(true);
		Grafica1.add(panelgrafica,BorderLayout.CENTER);
	}
}
