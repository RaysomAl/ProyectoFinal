package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.Cliente;
import logica.Contrato;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Indepediente;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIndetificador;
	private static JTable tbClientes;
	private static JTable tbContractos;
	private static JButton btnBuscar;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private static DefaultTableModel modeloClientes;
	private static Object[] filaClientes;
	private static int indiceCliente=-1;
	private static DefaultTableModel modeloContratos;
	private static Object[] filaContratos;
	private static JRadioButton rdbtnIndepediente;
	private static JRadioButton rdbtnEmpresa;
	private static EmpresaRps empresaRps;
	private Cliente cliente=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarClientes dialog = new ListarClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarClientes() {
		setTitle("Lista de Clientes");
		setBounds(100, 100, 546, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			});
			panel.setBorder(new TitledBorder(null, "Clientes y Contractos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblIndetificador = new JLabel("Cedula :");
			lblIndetificador.setBounds(10, 22, 46, 14);
			panel.add(lblIndetificador);
			
			txtIndetificador = new JTextField();
			txtIndetificador.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(EmpresaRps.getInstance().getMisclientes().size()>0 && cliente!=null) {
						btnBuscar.setEnabled(true);
					} else {
						btnBuscar.setEnabled(false);
					}
				}
			});
			txtIndetificador.setBounds(66, 19, 86, 20);
			panel.add(txtIndetificador);
			txtIndetificador.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					cargaTablaBuscaClientes(cliente);
					
				}
			});
			btnBuscar.setEnabled(false);
			btnBuscar.setBounds(162, 18, 89, 23);
			panel.add(btnBuscar);
			
			
			
			rdbtnEmpresa = new JRadioButton("Empresa");
			rdbtnIndepediente = new JRadioButton("Indepediente");
			selecionPorDefecto();
			
			
			JScrollPane scrollPaneClientes = new JScrollPane();
			scrollPaneClientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			});
			scrollPaneClientes.setBounds(10, 67, 236, 241);
			panel.add(scrollPaneClientes);
			
			tbClientes = new JTable();
			tbClientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					indiceCliente=tbClientes.getSelectedRow();
					String indentificador = (String) tbClientes.getModel().getValueAt(indiceCliente, 0);
					cliente=EmpresaRps.getInstance().buscarCliente(indentificador);
					if(indiceCliente>-1) {
						btnModificar.setEnabled(true);
						btnEliminar.setEnabled(true);
						cargaTablaContractos(cliente);
					} else {
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
					}

				}
			});
			modeloClientes=new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row,int column) {
					return false;
				}
			};
			cargaTablaClientes();
			
			scrollPaneClientes.setViewportView(tbClientes);
			
			JScrollPane scrollPaneContractos = new JScrollPane();
			scrollPaneContractos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			});
			scrollPaneContractos.setBounds(274, 67, 236, 241);
			panel.add(scrollPaneContractos);
			
			tbContractos = new JTable();
			modeloContratos=new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row,int column) {
					return false;
				}
			};
			cargaTablaContractos(cliente);
			
			scrollPaneContractos.setViewportView(tbContractos);
			

			
			
			rdbtnIndepediente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					rdbtnIndepediente.setSelected(true);
					rdbtnEmpresa.setSelected(false);
					lblIndetificador.setText("Cedula :");
					cargaTablaClientes();
				}
			});
			rdbtnIndepediente.setBounds(10, 43, 109, 23);
			panel.add(rdbtnIndepediente);
			

			rdbtnEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					rdbtnIndepediente.setSelected(false);
					rdbtnEmpresa.setSelected(true);
					lblIndetificador.setText("RNC :");
					cargaTablaClientes();
				}
			});
			rdbtnEmpresa.setBounds(121, 43, 109, 23);
			panel.add(rdbtnEmpresa);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						RegistarCliente modificarCliente=new RegistarCliente( empresaRps,"Modificar Cliente",cliente);
						modificarCliente.setModal(true);
						modificarCliente.setVisible(true);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String indentificador="";
						if(cliente instanceof Indepediente) {
							indentificador=((Indepediente) cliente).getCedula();
						}
						if(cliente instanceof Empresa) {
							indentificador=((Empresa) cliente).getRnc();
							
						}
						EmpresaRps.getInstance().eliminarCliente(indentificador);
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnBuscar.setEnabled(false); 
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	public void selecionPorDefecto() {
		rdbtnIndepediente.setSelected(true);
		rdbtnEmpresa.setSelected(false);
	}
	
	public static void cargaTablaClientes() {
		modeloClientes.setRowCount(0);
		if(rdbtnIndepediente.isSelected()) {
			String[] columnas={"CEDULA","NOMBRE"};
			modeloClientes.setColumnIdentifiers(columnas);
			filaClientes=new Object[modeloClientes.getColumnCount()];
			for (Cliente cliente : EmpresaRps.getInstance().getMisclientes()) {
				if(cliente instanceof Indepediente) {
					filaClientes[0]=((Indepediente) cliente).getCedula();
					filaClientes[1]=((Indepediente) cliente).getNombre()+" "+((Indepediente) cliente).getAmpellido();
				}
				modeloClientes.addRow(filaClientes);
			} 
			
			tbClientes.setModel(modeloClientes);
			tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbClientes.getTableHeader().setResizingAllowed(false);
			TableColumnModel columnas1 = tbClientes.getColumnModel();
			columnas1.getColumn(0).setPreferredWidth(80);
			columnas1.getColumn(1).setPreferredWidth(153);


			
		}
		if(rdbtnEmpresa.isSelected()) {
			String[] columnas={"RNC","NOMBRE"};
			modeloClientes.setColumnIdentifiers(columnas);
			filaClientes=new Object[modeloClientes.getColumnCount()];
			for (Cliente cliente : EmpresaRps.getInstance().getMisclientes()) {
				if(cliente instanceof Empresa) {
					filaClientes[0]=((Empresa) cliente).getRnc();
					filaClientes[1]=((Empresa) cliente).getNombre();
					
					
				}
				modeloClientes.addRow(filaClientes);
				
			}
			tbClientes.setModel(modeloClientes);
			tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbClientes.getTableHeader().setResizingAllowed(false);
			TableColumnModel columnas1 = tbClientes.getColumnModel();
			columnas1.getColumn(0).setPreferredWidth(80);
			columnas1.getColumn(1).setPreferredWidth(153);
		}
		
		
	}
	
	public static void cargaTablaBuscaClientes(Cliente cliente) {

		modeloClientes.setRowCount(0);
		if(cliente instanceof Indepediente) {
			String[] columnas={"CEDULA","NOMBRE"};
			modeloClientes.setColumnIdentifiers(columnas);
			filaClientes=new Object[modeloClientes.getColumnCount()];


					filaClientes[0]=((Indepediente) cliente).getCedula();
					filaClientes[1]=((Indepediente) cliente).getNombre()+" "+((Indepediente) cliente).getAmpellido();
				
				modeloClientes.addRow(filaClientes);
			
			
			tbClientes.setModel(modeloClientes);
			tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbClientes.getTableHeader().setResizingAllowed(false);
			TableColumnModel columnas1 = tbClientes.getColumnModel();
			columnas1.getColumn(0).setPreferredWidth(80);
			columnas1.getColumn(1).setPreferredWidth(153);


			
		}
		if(cliente instanceof Empresa) {
			String[] columnas={"RNC","NOMBRE"};
			modeloClientes.setColumnIdentifiers(columnas);
			filaClientes=new Object[modeloClientes.getColumnCount()];

					filaClientes[0]=((Empresa) cliente).getRnc();
					filaClientes[1]=((Empresa) cliente).getNombre();
					
					
				
				modeloClientes.addRow(filaClientes);
				
			
			tbClientes.setModel(modeloClientes);
			tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbClientes.getTableHeader().setResizingAllowed(false);
			TableColumnModel columnas1 = tbClientes.getColumnModel();
			columnas1.getColumn(0).setPreferredWidth(80);
			columnas1.getColumn(1).setPreferredWidth(153);
		}
		
		
	}
	
	public static void cargaTablaContractos(Cliente cliente) {
		modeloContratos.setRowCount(0);
		String[] columnas={"CODIGO","PROYECTO"};
		modeloContratos.setColumnIdentifiers(columnas);
		filaContratos=new Object[modeloContratos.getColumnCount()];
		if(indiceCliente>-1 && cliente!=null) {
			
			
			for (Contrato contrato : cliente.getMisContratos()) {
				if(contrato.getProyecto().isActivo()) {
					filaContratos[0]=contrato.getCodigoContrato();
					filaContratos[1]=contrato.getProyecto().getNombreproyecto();
				}
				modeloContratos.addRow(filaContratos);
				
			}
		}

		
		tbContractos.setModel(modeloContratos);
		tbContractos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbContractos.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnas1 = tbContractos.getColumnModel();
		columnas1.getColumn(0).setPreferredWidth(80);
		columnas1.getColumn(1).setPreferredWidth(153);
	}

	public static JButton getBtnModificar() {
		return btnModificar;
	}

	public static void setBtnModificar(JButton btnModificar) {
		ListarClientes.btnModificar = btnModificar;
	}

	public static JButton getBtnEliminar() {
		return btnEliminar;
	}

	public static void setBtnEliminar(JButton btnEliminar) {
		ListarClientes.btnEliminar = btnEliminar;
	}

	public static JButton getBtnBuscar() {
		return btnBuscar;
	}

	public static void setBtnBuscar(JButton btnBuscar) {
		ListarClientes.btnBuscar = btnBuscar;
	}
	
	
	
	
}
