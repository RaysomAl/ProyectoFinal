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
import javax.swing.table.TableColumnModel;
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
import logica.Diseñador;
import logica.EmpresaRps;
import logica.JefeDeProyecto;
import logica.Planificador;
import logica.Programador;
import logica.Trabajador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;



public class ListarTrabajadores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableTrab;
	private static DefaultTableModel tableModelT;
    private static Object[] rowT;
    private static DefaultTableModel tableModelP;
    private static Object[] rowP;
    private JFormattedTextField ftBuscarCedula;
    private ArrayList<Trabajador> B = new ArrayList<>();
    private JScrollPane scrollPaneLista;
    private JComboBox comboBox;
    
    

	/**
	 * Launch the application.
	 *
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
		setBounds(100, 100,  720, 597);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel ListaTrabajadores = new JPanel();
		ListaTrabajadores.setBorder(new TitledBorder(null, "Lista de trabajadores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		ListaTrabajadores.setBounds(12, 82, 691, 440);
		contentPanel.add(ListaTrabajadores);
		ListaTrabajadores.setLayout(null);
		
		JScrollPane scrollPaneLista = new JScrollPane();
		scrollPaneLista.setBounds(10, 21, 671, 408);
		ListaTrabajadores.add(scrollPaneLista);
		
		
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
		tableTrab= new JTable();
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargaTrabajadores();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Jefe De Proyecto", "Planificador", "Programador", "Dise\u00F1ador"}));
		comboBox.setSelectedIndex(0);
		
		tableTrab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			  
			}
		});
		cargaTrabajadores();
		scrollPaneLista.setColumnHeaderView(tableTrab);
		tableTrab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTrab.setModel(tableModelT);
		scrollPaneLista.setViewportView(tableTrab);
		
		String[] columnsHeadersP = {"Nombre", "Tipo de Proyecto", "Lenguaje"};
		tableModelP = new DefaultTableModel(){
		    /**
		     * 
		     */
		    private static final long serialVersionUID = 1L;
		 
		};
		tableModelP.setColumnIdentifiers(columnsHeadersP);
		
		JPanel FiltroTipo = new JPanel();
		FiltroTipo.setBorder(new TitledBorder(null, "Filtro por Tipo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		FiltroTipo.setBounds(12, 11, 299, 60);
		contentPanel.add(FiltroTipo);
		FiltroTipo.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 24, 61, 14);
		FiltroTipo.add(lblFiltro);
		
		
		
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
				String cedula = ftBuscarCedula.getText();
				if(EmpresaRps.getInstance().buscarTrabajador(cedula)!=null){
					cargarDatos(EmpresaRps.getInstance().buscarTrabajador(cedula));
					
				}
				else{
					JOptionPane.showMessageDialog(null, "El cliente no existe");
					
				}
			}

			private void cargarDatos(Trabajador trabajador) {
				
				tableModelT.setRowCount(0);
				String[] columnsHeadersT = {"Cédula", "Nombre", "Apellido","Edad", "Teléfono", "Tipo de trabajador"};
				tableModelT.setColumnIdentifiers(columnsHeadersT);
				rowT=new Object[tableModelT.getColumnCount()];
			
					if(comboBox.getSelectedIndex()==0) {
						rowT[0]=trabajador.getCedula();
						rowT[1]=trabajador.getNombre();
						rowT[2]=trabajador.getApellido();
						rowT[3]=trabajador.getEdad();
						rowT[4]=trabajador.getTelefono();
						if(trabajador instanceof JefeDeProyecto) {
							rowT[5]="Jefe De Proyecto";
						}
						if(trabajador instanceof Planificador) {
							rowT[5]="Planificador";
						}
						if(trabajador instanceof Programador) {
							rowT[5]="Programador";
						}
						if(trabajador instanceof Diseñador) {
							rowT[5]="Diseñador";
						}
						tableModelT.addRow(rowT);
					}
					
					if(comboBox.getSelectedIndex()==1 && trabajador instanceof JefeDeProyecto) {
						rowT[0]=trabajador.getCedula();
						rowT[1]=trabajador.getNombre();
						rowT[2]=trabajador.getApellido();
						rowT[3]=trabajador.getEdad();
						rowT[4]=trabajador.getTelefono();
						rowT[5]="Jefe De Proyecto";
						tableModelT.addRow(rowT);

					}
					if(comboBox.getSelectedIndex()==2 && trabajador instanceof Planificador) {
						rowT[0]=trabajador.getCedula();
						rowT[1]=trabajador.getNombre();
						rowT[2]=trabajador.getApellido();
						rowT[3]=trabajador.getEdad();
						rowT[4]=trabajador.getTelefono();
						rowT[5]="Planificador";
						tableModelT.addRow(rowT);
					}
					if(comboBox.getSelectedIndex()==3 && trabajador instanceof Programador) {
						rowT[0]=trabajador.getCedula();
						rowT[1]=trabajador.getNombre();
						rowT[2]=trabajador.getApellido();
						rowT[3]=trabajador.getEdad();
						rowT[4]=trabajador.getTelefono();
						rowT[5]="Programador";	
						tableModelT.addRow(rowT);
					}
					if(comboBox.getSelectedIndex()==4 && trabajador instanceof Diseñador) {
						rowT[0]=trabajador.getCedula();
						rowT[1]=trabajador.getNombre();
						rowT[2]=trabajador.getApellido();
						rowT[3]=trabajador.getEdad();;
						rowT[4]=trabajador.getTelefono();
						rowT[5]="Diseñador";
						tableModelT.addRow(rowT);
					
					
					
				}
				
				tableTrab.setModel(tableModelT);
				tableTrab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableTrab.getTableHeader().setResizingAllowed(false);
				TableColumnModel columnas1 = tableTrab.getColumnModel();
				columnas1.getColumn(0).setPreferredWidth(111);
				columnas1.getColumn(1).setPreferredWidth(111);
				columnas1.getColumn(2).setPreferredWidth(111);
				columnas1.getColumn(3).setPreferredWidth(111);
				columnas1.getColumn(4).setPreferredWidth(111);
				columnas1.getColumn(5).setPreferredWidth(113);
				
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
				okButton.setIcon(new ImageIcon(ListarTrabajadores.class.getResource("/img/mark.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	  
	
	
	
	public void cargaTrabajadores() {
		tableModelT.setRowCount(0);
		String[] columnsHeadersT = {"Cédula", "Nombre", "Apellido","Edad", "Teléfono", "Tipo de trabajador"};
		tableModelT.setColumnIdentifiers(columnsHeadersT);
		rowT=new Object[tableModelT.getColumnCount()];
		for (Trabajador trabajador : EmpresaRps.getInstance().getMistrabajadores()) {
			if(comboBox.getSelectedIndex()==0) {
				rowT[0]=trabajador.getCedula();
				rowT[1]=trabajador.getNombre();
				rowT[2]=trabajador.getApellido();
				rowT[3]=trabajador.getEdad();
				rowT[4]=trabajador.getTelefono();
				if(trabajador instanceof JefeDeProyecto) {
					rowT[5]="Jefe De Proyecto";
				}
				if(trabajador instanceof Planificador) {
					rowT[5]="Planificador";
				}
				if(trabajador instanceof Programador) {
					rowT[5]="Programador";
				}
				if(trabajador instanceof Diseñador) {
					rowT[5]="Diseñador";
				}
				tableModelT.addRow(rowT);
			}
			
			if(comboBox.getSelectedIndex()==1 && trabajador instanceof JefeDeProyecto) {
				rowT[0]=trabajador.getCedula();
				rowT[1]=trabajador.getNombre();
				rowT[2]=trabajador.getApellido();
				rowT[3]=trabajador.getEdad();
				rowT[4]=trabajador.getTelefono();
				rowT[5]="Jefe De Proyecto";
				tableModelT.addRow(rowT);

			}
			if(comboBox.getSelectedIndex()==2 && trabajador instanceof Planificador) {
				rowT[0]=trabajador.getCedula();
				rowT[1]=trabajador.getNombre();
				rowT[2]=trabajador.getApellido();
				rowT[3]=trabajador.getEdad();
				rowT[4]=trabajador.getTelefono();
				rowT[5]="Planificador";
				tableModelT.addRow(rowT);
			}
			if(comboBox.getSelectedIndex()==3 && trabajador instanceof Programador) {
				rowT[0]=trabajador.getCedula();
				rowT[1]=trabajador.getNombre();
				rowT[2]=trabajador.getApellido();
				rowT[3]=trabajador.getEdad();
				rowT[4]=trabajador.getTelefono();
				rowT[5]="Programador";	
				tableModelT.addRow(rowT);
			}
			if(comboBox.getSelectedIndex()==4 && trabajador instanceof Diseñador) {
				rowT[0]=trabajador.getCedula();
				rowT[1]=trabajador.getNombre();
				rowT[2]=trabajador.getApellido();
				rowT[3]=trabajador.getEdad();;
				rowT[4]=trabajador.getTelefono();
				rowT[5]="Diseñador";
				tableModelT.addRow(rowT);
			}
			
			
		}
		
		tableTrab.setModel(tableModelT);
		tableTrab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableTrab.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnas1 = tableTrab.getColumnModel();
		columnas1.getColumn(0).setPreferredWidth(111);
		columnas1.getColumn(1).setPreferredWidth(111);
		columnas1.getColumn(2).setPreferredWidth(111);
		columnas1.getColumn(3).setPreferredWidth(111);
		columnas1.getColumn(4).setPreferredWidth(111);
		columnas1.getColumn(5).setPreferredWidth(113);
		
	}

	
}
