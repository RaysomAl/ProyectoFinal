package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;



import logica.Cliente;
import logica.Contrato;
import logica.EmpresaRps;
import logica.JefeDeProyecto;
import logica.Planificador;
import logica.Programador;
import logica.Trabajador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;



public class ListarTrabajadores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableTrab;
	private JTable tableProyectos;
	private static DefaultTableModel tableModelT;
    private static Object[] rowT;
    private static DefaultTableModel tableModelP;
    private static Object[] rowP;
    private JFormattedTextField ftBuscarCedula;
    private ArrayList<Trabajador> B = new ArrayList<>();
    private JScrollPane scrollPaneLista;
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarTrabajadores dialog = new ListarTrabajadores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public ListarTrabajadores() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajadores.class.getResource("/img/listPanel.png")));
		setTitle("Lista de Trabajadores");
		setResizable(false);
		setBounds(100, 100,  1050, 597);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel ListaTrabajadores = new JPanel();
		ListaTrabajadores.setBorder(new TitledBorder(null, "Lista de trabajadores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		ListaTrabajadores.setBounds(12, 82, 691, 452);
		contentPanel.add(ListaTrabajadores);
		ListaTrabajadores.setLayout(null);
		
		JScrollPane scrollPaneLista = new JScrollPane();
		scrollPaneLista.setBounds(10, 21, 671, 420);
		ListaTrabajadores.add(scrollPaneLista);
		
		String[] columnsHeadersT = {"Cédula", "Nombre", "Apellido","Cantidad de contratos", "Teléfono", "Tipo de trabajador"};
		tableModelT = new DefaultTableModel(){
		    /**
		     * 
		     */
		    private static final long serialVersionUID = 1L;

		    @Override
		    public boolean isCellEditable(int row, int column) {
			
			return false;
		    }
		};
		tableModelT.setColumnIdentifiers(columnsHeadersT);
		tableTrab= new JTable();
		tableTrab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			    if(tableTrab.getSelectedRow()>=0){
				      int index = tableTrab.getSelectedRow();
				      CargarProyectos(EmpresaRps.getInstance().getMistrabajadores().get(index).getMisContratos());
			    }
			}
		});
		scrollPaneLista.setColumnHeaderView(tableTrab);
		tableTrab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTrab.setModel(tableModelT);
		scrollPaneLista.setViewportView(tableTrab);
		
		
		JPanel proyectosActivos = new JPanel();
		proyectosActivos.setBorder(new TitledBorder(null, "Proyectos activos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		proyectosActivos.setBounds(715, 11, 319, 523);
		contentPanel.add(proyectosActivos);
		proyectosActivos.setLayout(null);
		
		JScrollPane scrollPaneProyectos = new JScrollPane();
		scrollPaneProyectos.setBounds(10, 21, 299, 491);
		proyectosActivos.add(scrollPaneProyectos);
		
		String[] columnsHeadersP = {"Nombre", "Tipo de Proyecto", "Lenguaje"};
		tableModelP = new DefaultTableModel(){
		    /**
		     * 
		     */
		    private static final long serialVersionUID = 1L;

		    @Override
		    public boolean isCellEditable(int row, int column) {
			
			return false;
		    }
		 
		};
		tableModelP.setColumnIdentifiers(columnsHeadersP);
		tableProyectos = new JTable();
		scrollPaneProyectos.setViewportView(tableProyectos);
		scrollPaneProyectos.setColumnHeaderView(tableProyectos);
		tableProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProyectos.setModel(tableModelP);
		scrollPaneProyectos.setViewportView(tableProyectos);
		
		JPanel FiltroTipo = new JPanel();
		FiltroTipo.setBorder(new TitledBorder(null, "Filtro por Tipo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		FiltroTipo.setBounds(12, 11, 299, 60);
		contentPanel.add(FiltroTipo);
		FiltroTipo.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 24, 61, 14);
		FiltroTipo.add(lblFiltro);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(56, 21, 209, 22);
		FiltroTipo.add(comboBox);
		
		JPanel Busquedad = new JPanel();
		Busquedad.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u00FAsqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Busquedad.setBounds(344, 11, 359, 60);
		contentPanel.add(Busquedad);
		Busquedad.setLayout(null);
		
		JLabel lbcedula = new JLabel("C\u00E9dula:");
		lbcedula.setBounds(10, 23, 59, 14);
		Busquedad.add(lbcedula);
		
		MaskFormatter cedulaL = null;
	
		try {
			cedulaL = new MaskFormatter("###-#######-#");
			cedulaL.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        
	    ftBuscarCedula = new JFormattedTextField(cedulaL);
		ftBuscarCedula.setBounds(67, 21, 240, 22);
		Busquedad.add(ftBuscarCedula);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(ListarTrabajadores.class.getResource("/img/003-find.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarTrab();
			}
		});
		btnBuscar.setBounds(314, 19, 35, 22);
		Busquedad.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnEvaluacin = new JButton("Evaluaci\u00F3n");
			btnEvaluacin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(EmpresaRps.getInstance().getMistrabajadores().size()>0) {
						Evaluacion dialog = new Evaluacion(EmpresaRps.getInstance());
						dialog.setModal(true);
						dialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "La empresa no tiene trabajadores", "Advertencia",JOptionPane.WARNING_MESSAGE);
					}

				}
			});
			btnEvaluacin.setIcon(new ImageIcon(ListarTrabajadores.class.getResource("/img/wish-list.png")));
			buttonPane.add(btnEvaluacin);
			{
				JButton okButton = new JButton("Listo");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	   private void CargarProyectos(ArrayList<Contrato> miscontratos) {
	       	tableModelP.setRowCount(0);
		   	DefaultTableCellRenderer cp = new DefaultTableCellRenderer();
		   	cp.setHorizontalAlignment(SwingConstants.CENTER);
		   	tableProyectos.getColumnModel().getColumn(0).setCellRenderer(cp);
		   	tableProyectos.getColumnModel().getColumn(1).setCellRenderer(cp);
		   	tableProyectos.getColumnModel().getColumn(2).setCellRenderer(cp);
		   	rowP = new Object[tableModelP.getColumnCount()];
		   	for (Contrato p : miscontratos) {
		   		if (p.getProyecto().getEstado().equals("En progreso")) {
		   			rowP[0]=p.getProyecto().getNombreproyecto();
		   			rowP[1]=p.getProyecto().getTipo();
		   			rowP[2]=p.getProyecto().getLenguaje();
		   			tableModelP.addRow(rowP);
		   		}
		   	}
	    }
	
	private void cargarTrab(ArrayList<Trabajador> mistrabajadores) {
	   	tableModelT.setRowCount(0);
	   	DefaultTableCellRenderer ct = new DefaultTableCellRenderer();
	   	ct.setHorizontalAlignment(SwingConstants.CENTER);
	   	tableTrab.getColumnModel().getColumn(0).setCellRenderer(ct);
	   	tableTrab.getColumnModel().getColumn(1).setCellRenderer(ct);
	   	tableTrab.getColumnModel().getColumn(2).setCellRenderer(ct);
	   	tableTrab.getColumnModel().getColumn(3).setCellRenderer(ct);
	   	tableTrab.getColumnModel().getColumn(4).setCellRenderer(ct);
	   	tableTrab.getColumnModel().getColumn(5).setCellRenderer(ct);
	   	rowT = new Object[tableModelT.getColumnCount()];
	   	for (Trabajador trab : mistrabajadores) {
	   		rowT[0]=trab.getCedula();
	   		rowT[1]=trab.getNombre();
	   		rowT[2]=trab.getApellido();
	   		rowT[3]=trab.cantidadProyectosActivos();
	   		rowT[4]=trab.getTelefono();
	   	    String aux = null;
	   	    if (trab instanceof JefeDeProyecto)
	   	    	aux = "Jefe de proyecto";
	   	    else if (trab instanceof Planificador)
	   	    	aux = "Planificador";
	   	    else if (trab instanceof Programador)
	   	    	aux = "Programador";
	   	    else
	   	    	aux = "Diseñador";
	   	 rowT[5]=aux;
	   	tableModelT.addRow(rowT);
	   	}
	    }
	
	private void buscarTrab() {
		String cedu = ftBuscarCedula.getText().substring(0, ftBuscarCedula.getCaretPosition());
		ArrayList<Trabajador> mistrabajadores = new ArrayList<>();
		ArrayList<Trabajador> selec = new ArrayList<>();
		for (Trabajador trab: EmpresaRps.getInstance().getMistrabajadores()) {
			System.out.println(trab.getNombre());
			String aux = getId(ftBuscarCedula.getCaretPosition(), trab);
			if (cedu.equals(aux))
				selec.add(trab);
		   } 
		cargarTrab(selec);
	}
	
	/*private ArrayList<Trabajador> noBorrar(){
		ArrayList<Trabajador> Tnoeliminado = new ArrayList<>();
		for (Trabajador tr: EmpresaRps.getInstance().getMistrabajadores()) {
			if (!B.contains(tr))
				Tnoeliminado.add(tr);
		}
		return Tnoeliminado;
	}*/  
	
	private String getId(int num, Trabajador trabajador) {
		String aux = null;
		String aux1 = trabajador.getCedula();
		aux = aux1.substring(0, num);
		return aux;
	}
	
}
