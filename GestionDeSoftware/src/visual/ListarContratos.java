package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import logica.Contrato;
import logica.Diseñador;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Indepediente;
import logica.JefeDeProyecto;
import logica.Planificador;
import logica.Programador;
import logica.Trabajador;

import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarContratos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private static Object[] filaLista;
	private JComboBox<String> cbxBuscar;
	private JComboBox<String> cbxCliente;
	private JComboBox<String> cbxEstado;
	private JButton btnBuscar;
	private JButton btnPresentar;
	private JPanel pnlCliente;
	private JPanel pnlEstado;
	private JPanel pnlCodigo;
	private JPanel pnlAux;
	private JFormattedTextField ftxRNC ;
	private	JFormattedTextField ftxCedula;
	private JFormattedTextField ftxCodigo;
	private JList<String> lstTrabajadores;
	private ArrayList<Contrato> auxiliar = new ArrayList<Contrato>();
	private JButton btnNewButton;
	private JButton btnsalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarContratos dialog = new ListarContratos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarContratos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarContratos.class.getResource("/img/listPanel.png")));
		setBounds(100, 100, 952, 466);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Listado de Contratos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 86, 679, 284);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPresentar.setEnabled(true);
				btnNewButton.setEnabled(true);
				btnsalvar.setEnabled(true);
				lstTrabajadores.removeAll();
				
			}
		});
		model = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		String[] nombreColums = {"ID Contrato", "Cliente", "Fecha Inicio", "Fecha Final", "Estado", "Fecha Saldo","Precio Final", "Jefe de Proy."};
		model.setColumnIdentifiers(nombreColums);
		table.setModel(model);
		TableColumnModel modeloColum = table.getColumnModel();
		modeloColum.getColumn(0).setPreferredWidth(75);
		modeloColum.getColumn(1).setPreferredWidth(100);
		modeloColum.getColumn(2).setPreferredWidth(75);
		modeloColum.getColumn(3).setPreferredWidth(75);
		modeloColum.getColumn(4).setPreferredWidth(75);
		modeloColum.getColumn(5).setPreferredWidth(75);
		modeloColum.getColumn(6).setPreferredWidth(75);
		modeloColum.getColumn(7).setPreferredWidth(75);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscador Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel.setBounds(10, 14, 636, 61);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblMostrarContratoPor = new JLabel("Mostrar contrato por:");
		lblMostrarContratoPor.setBounds(10, 25, 135, 14);
		panel.add(lblMostrarContratoPor);
		
		cbxBuscar = new JComboBox<String>();
		cbxBuscar.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(cbxBuscar.getSelectedIndex()==0) {
					pnlAux.setVisible(true);
					pnlCliente.setVisible(false);
					pnlCodigo.setVisible(false);
					pnlEstado.setVisible(false);
					btnBuscar.setEnabled(true);
				}
				if(cbxBuscar.getSelectedIndex()==1) {
					pnlAux.setVisible(false);
					pnlCliente.setVisible(false);
					pnlCodigo.setVisible(false);
					pnlEstado.setVisible(true);
					btnBuscar.setEnabled(true);
				}
				if(cbxBuscar.getSelectedIndex()==2) {
					pnlAux.setVisible(false);
					pnlCliente.setVisible(false);
					pnlCodigo.setVisible(true);
					pnlEstado.setVisible(false);
					btnBuscar.setEnabled(true);
				}
				if(cbxBuscar.getSelectedIndex()==3) {
					pnlAux.setVisible(false);
					pnlCliente.setVisible(true);
					pnlCodigo.setVisible(false);
					pnlEstado.setVisible(false);
					btnBuscar.setEnabled(false);
				}
					
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		cbxBuscar.setBounds(155, 22, 135, 20);
		panel.add(cbxBuscar);
		cbxBuscar.setModel(new DefaultComboBoxModel<>(new String[] {"<Todas>","Estado","Codigo de Contrato","Cliente"}));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxiliar.clear();
				if(cbxBuscar.getSelectedIndex()==0)
					loadTable(0,false,null);
				if(cbxBuscar.getSelectedIndex()==1) {
					if(cbxEstado.getSelectedIndex()==1)
						loadTable(1,false,null);
					if(cbxEstado.getSelectedIndex()==2)
						loadTable(1,true,null);
				}
				if(cbxBuscar.getSelectedIndex()==2)
					loadTable(2,false,ftxCodigo.getText());
				if(cbxBuscar.getSelectedIndex()==3) {
					if(cbxCliente.getSelectedIndex()==1)
						loadTable(3,false,null);
					if(cbxCliente.getSelectedIndex()==2)
						loadTable(3,false,null);
				}
			}
		});
		btnBuscar.setBounds(537, 21, 89, 23);
		panel.add(btnBuscar);
		
		pnlAux = new JPanel();
		pnlAux.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlAux.setBounds(300, 14, 227, 39);
		panel.add(pnlAux);
		
		pnlCliente = new JPanel();
		pnlCliente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlCliente.setBounds(300, 14, 227, 39);
		panel.add(pnlCliente);
		pnlCliente.setVisible(false);
		pnlCliente.setLayout(null);
		
		cbxCliente = new JComboBox<String>();
		cbxCliente.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(cbxCliente.getSelectedIndex()!=0) {
					btnBuscar.setEnabled(true);
					ftxCedula.setEditable(true);
					ftxRNC.setEditable(true);
				}
				if(cbxCliente.getSelectedIndex()==0) {
					btnBuscar.setEnabled(false);
					ftxCedula.setEditable(false);
					ftxRNC.setEditable(false);
				}
				if(cbxCliente.getSelectedIndex()==1) {
					ftxCedula.setVisible(true);
					ftxRNC.setVisible(false);
				}
				if(cbxCliente.getSelectedIndex()==2) {
					ftxCedula.setVisible(false);
					ftxRNC.setVisible(true);
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				btnBuscar.setEnabled(false);
				ftxCedula.setEditable(false);
				ftxRNC.setEditable(false);
			}
		});
		cbxCliente.setModel(new DefaultComboBoxModel<>(new String[] {"<Tipo>","Cedula","RNC"}));
		cbxCliente.setBounds(10, 11, 93, 20);
		pnlCliente.add(cbxCliente);
		
		ftxRNC = new JFormattedTextField(createFormatter("###-#####-#"));
		ftxRNC.setEditable(false);
		ftxRNC.setBounds(113, 8, 104, 23);
		pnlCliente.add(ftxRNC);
		
		ftxCedula = new JFormattedTextField(createFormatter("###-#######-#"));
		ftxCedula.setEditable(false);
		ftxCedula.setBounds(113, 8, 104, 23);
		pnlCliente.add(ftxCedula);
		
		pnlEstado = new JPanel();
		pnlEstado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlEstado.setBounds(300, 14, 227, 39);
		panel.add(pnlEstado);
		pnlEstado.setVisible(false);
		pnlEstado.setLayout(null);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 11, 51, 17);
		pnlEstado.add(lblEstado);
		
		cbxEstado = new JComboBox<String>();
		cbxEstado.setBounds(60, 8, 157, 23);
		cbxEstado.setModel(new DefaultComboBoxModel<>(new String[] {"<Seleccione>","Vigente","Finalizado"}));
		pnlEstado.add(cbxEstado);
		
		pnlCodigo = new JPanel();
		pnlCodigo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlCodigo.setBounds(300, 14, 227, 39);
		panel.add(pnlCodigo);
		pnlCodigo.setVisible(false);
		pnlCodigo.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 11, 50, 17);
		pnlCodigo.add(lblCodigo);
		
		ftxCodigo = new JFormattedTextField(createFormatter("#####-####"));
		ftxCodigo.setBounds(60, 8, 157, 23);
		pnlCodigo.add(ftxCodigo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Empleados Responsables", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(699, 53, 213, 195);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 193, 163);
		panel_1.add(scrollPane_1);
		scrollPane_1.setToolTipText("Empleados Responsables");
		
		lstTrabajadores = new JList<String>();
		limitarSeleccionLista(lstTrabajadores,-1,-1);
		scrollPane_1.setViewportView(lstTrabajadores);
		
		btnPresentar = new JButton("Presentar Trabajadores");
		btnPresentar.setEnabled(false);
		btnPresentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					lstTrabajadores.removeAll();
					cargarLista();
				}
			}
		});
		btnPresentar.setBounds(709, 259, 186, 23);
		contentPanel.add(btnPresentar);
		
		btnNewButton = new JButton("Prorrogar Contrato");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!auxiliar.get(table.getSelectedRow()).isTerminado()) {
					PassDSaldar nuevo = new PassDSaldar(auxiliar.get(table.getSelectedRow()));
					nuevo.setModal(true);
					nuevo.setVisible(true);
					model.setRowCount(0);
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione un contrato no terminado, para prorrogar", "Informacion",JOptionPane.INFORMATION_MESSAGE);
					btnNewButton.setEnabled(false);
				}
			}
		});
		btnNewButton.setBounds(709, 309, 186, 23);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnsalvar = new JButton("Saldar Contrato");
				btnsalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!auxiliar.get(table.getSelectedRow()).isTerminado()) {
							SaldarContrato saldar = new SaldarContrato(auxiliar.get(table.getSelectedRow()));
							saldar.setModal(true);
							saldar.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Ese contrato esta saldado, seleccione otro para saldar", "Informacion",JOptionPane.INFORMATION_MESSAGE);
							btnsalvar.setEnabled(false);
						}
					}
				});
				btnsalvar.setEnabled(false);
				btnsalvar.setIcon(new ImageIcon(ListarContratos.class.getResource("/img/001-technology.png")));
				btnsalvar.setActionCommand("OK");
				buttonPane.add(btnsalvar);
				getRootPane().setDefaultButton(btnsalvar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar?","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
						dispose();
						}
					}
				});
				cancelButton.setIcon(new ImageIcon(ListarContratos.class.getResource("/img/001-delete.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable(int posicion,boolean estado,String codigoContrato) {
		model.setRowCount(0);
		auxiliar.clear();
		filaLista = new Object[model.getColumnCount()];
		for (Contrato contra : EmpresaRps.getInstance().getMiscontratos()) {
			if(posicion == 0) {
				cargar(contra);
				auxiliar.add(contra);
			}
			if(posicion == 1)
				if(contra.isTerminado()==estado){
					cargar(contra);
					auxiliar.add(contra);
				}
			if(posicion == 2)
				if(contra.getCodigoContrato().equalsIgnoreCase(codigoContrato)){
					cargar(contra);
					auxiliar.add(contra);
				}
			if(posicion == 3) {
				if(cbxCliente.getSelectedIndex()==1)
					if(contra.getCliente() instanceof Indepediente)
						if(ftxCedula.getText().equalsIgnoreCase(((Indepediente)contra.getCliente()).getCedula())){
							cargar(contra);
							auxiliar.add(contra);
						}
				if(cbxCliente.getSelectedIndex() == 2)
					if(contra.getCliente() instanceof Empresa)
						if(ftxRNC.getText().equalsIgnoreCase(((Empresa)contra.getCliente()).getRnc())){
							cargar(contra);
							auxiliar.add(contra);
						}
			}
		}
	}

	private void cargar(Contrato contra) {
		filaLista[0] = contra.getCodigoContrato();
		filaLista[1] = contra.getCliente().getNombre();
		filaLista[2] = contra.getFechaInicio().get(Calendar.DAY_OF_MONTH)+"/"+contra.getFechaInicio().get(Calendar.MONTH)+"/"+contra.getFechaInicio().get(Calendar.YEAR);
		filaLista[3] = contra.getFechaFinal().get(Calendar.DAY_OF_MONTH)+"/"+contra.getFechaFinal().get(Calendar.MONTH)+"/"+contra.getFechaFinal().get(Calendar.YEAR);
		if(contra.isTerminado()) {
			filaLista[4] = "Finalizado";
			filaLista[5] = contra.getFechaSaldada().get(Calendar.DAY_OF_MONTH)+"/"+contra.getFechaSaldada().get(Calendar.MONTH)+"/"+contra.getFechaSaldada().get(Calendar.YEAR);
		}
		if(!contra.isTerminado()) {
			filaLista[4] = "Vigente";
			filaLista[5] = "No establecida";
		}
		filaLista[6] = contra.getPreciofinal();
		filaLista[7] = contra.getProyecto().getJefe().getNombre();
		model.addRow(filaLista);
		TableColumnModel modeloColum = table.getColumnModel();
		modeloColum.getColumn(0).setPreferredWidth(75);
		modeloColum.getColumn(1).setPreferredWidth(100);
		modeloColum.getColumn(2).setPreferredWidth(75);
		modeloColum.getColumn(3).setPreferredWidth(75);
		modeloColum.getColumn(4).setPreferredWidth(75);
		modeloColum.getColumn(5).setPreferredWidth(75);
		modeloColum.getColumn(6).setPreferredWidth(75);
		modeloColum.getColumn(7).setPreferredWidth(75);
	}
	
	static MaskFormatter createFormatter(String format) {//validador numeros X un formato dado
		MaskFormatter mask = null;
		try {
            mask = new MaskFormatter(format);
            mask.setPlaceholderCharacter(' ');
        }catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
		return mask;
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
	private void cargarLista() {
		int i=0;
		String[] trabajadores = new String[auxiliar.get(table.getSelectedRow()).getProyecto().getJefe().getMisTrabajadores().size()];
		for (Trabajador worker : auxiliar.get(table.getSelectedRow()).getProyecto().getJefe().getMisTrabajadores()) {
				trabajadores[i] = worker.getCedula()+"("+worker.getNombre()+")"+"/"+tipotrab(worker);
				i++;
			}
		lstTrabajadores.setListData(trabajadores);
	}
	private String tipotrab(Trabajador worker) {
		if(worker instanceof Programador)
			return Programador.class.getSimpleName();
		if(worker instanceof Diseñador)
			return Diseñador.class.getSimpleName();
		if(worker instanceof JefeDeProyecto)
			return JefeDeProyecto.class.getSimpleName();
		if(worker instanceof Planificador)
			return Planificador.class.getSimpleName();
		return null;
	}
}
