package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

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
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class ListarClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	private Cliente c1=new Indepediente("123-1234567-1", "raul", "gonzales", "809", "r12", "dn", "salado", "santiago", "RD");
	private Cliente c2=new Empresa("123-12345-1", "baldon", "b21", "829", "santiago", "RD");
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtRnc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarClientes dialog = new ListarClientes(null);
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
	public ListarClientes(EmpresaRps emp) throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarClientes.class.getResource("/img/listPanel.png")));
		empresaRps=emp;
		
		rdbtnEmpresa = new JRadioButton("Empresa");
		rdbtnIndepediente = new JRadioButton("Indepediente");
		EmpresaRps.getInstance().agregarCliente(c1);
		EmpresaRps.getInstance().agregarCliente(c2);
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
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de clientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblIndetificador = new JLabel("Cedula :");
			lblIndetificador.setBounds(10, 22, 46, 14);
			panel.add(lblIndetificador);
			

			
			btnBuscar = new JButton("");
			btnBuscar.setIcon(new ImageIcon(ListarClientes.class.getResource("/img/003-find.png")));
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String inde="";
					if(rdbtnEmpresa.isSelected()) {
						inde=txtRnc.getText();
					} else {
						inde=txtCedula.getText();
					}
					Cliente cl=EmpresaRps.getInstance().buscarCliente(inde);
					cargaTablaBuscaClientes(cl);
					btnBuscar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					if(cl instanceof Empresa) {
						rdbtnEmpresa.setSelected(true);
						rdbtnIndepediente.setSelected(false);
					} else if(cl instanceof Indepediente) {
						rdbtnEmpresa.setSelected(false);
						rdbtnIndepediente.setSelected(true);
					} else {
						cargaTablaClientes();
						cargaTablaContractos(null);

					}
					
					if(cl==null) {
						JOptionPane.showMessageDialog(null, "El cliente : "+inde+" no existe", "Advertencia",JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			btnBuscar.setEnabled(false);
			if(EmpresaRps.getInstance().getMisclientes().size()>0) {
				btnBuscar.setEnabled(true);
			} else {
				btnBuscar.setEnabled(false);
			}
			btnBuscar.setBounds(174, 18, 28, 23);
			panel.add(btnBuscar);
			
			
			

			selecionPorDefecto();
			
			
			JScrollPane scrollPaneClientes = new JScrollPane();
			scrollPaneClientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					indiceCliente=-1;
					cliente=null;
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnBuscar.setEnabled(false);
				}
			});
			scrollPaneClientes.setBounds(10, 67, 236, 241);
			panel.add(scrollPaneClientes);
			
			tbClientes = new JTable();
			tbClientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					indiceCliente=tbClientes.getSelectedRow();

					if(indiceCliente>-1) {
						
						String indentificador = (String) tbClientes.getModel().getValueAt(indiceCliente, 0);
						cliente=EmpresaRps.getInstance().buscarCliente(indentificador);
						btnModificar.setEnabled(true);
						btnEliminar.setEnabled(true);
						cargaTablaContractos(cliente);
						
					} else {
						
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						cliente=null;
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
					indiceCliente=-1;
					cliente=null;
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnBuscar.setEnabled(false);
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
					btnBuscar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					txtCedula.setText("");
					txtCedula.setVisible(true);
					txtCedula.setSelectionStart(0);
					txtRnc.setText("");
					txtRnc.setVisible(false);
					cargaTablaClientes();
					cargaTablaContractos(null);
				}
			});
			rdbtnIndepediente.setBounds(10, 43, 109, 23);
			panel.add(rdbtnIndepediente);
			

			rdbtnEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					rdbtnIndepediente.setSelected(false);
					rdbtnEmpresa.setSelected(true);
					lblIndetificador.setText("RNC :");
					btnBuscar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					txtCedula.setText("");
					txtCedula.setVisible(false);
					txtRnc.setText("");
					txtRnc.setVisible(true);
					txtRnc.setSelectionStart(0);
					cargaTablaClientes();
					cargaTablaContractos(null);
				}
			});
			rdbtnEmpresa.setBounds(121, 43, 109, 23);
			panel.add(rdbtnEmpresa);
			
			txtCedula = new JFormattedTextField(new MaskFormatter("###-#######-#"));
			txtCedula.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(EmpresaRps.getInstance().getMisclientes().size()>0 && rdbtnIndepediente.isSelected()) {
						btnBuscar.setEnabled(true);
					} else {
						btnBuscar.setEnabled(false);
					}
				}
			});
			txtCedula.setText("");
			txtCedula.setSelectionStart(0);
			txtCedula.setVisible(true);
			txtCedula.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(EmpresaRps.getInstance().getMisclientes().size()>0 && rdbtnIndepediente.isSelected()) {
						btnBuscar.setEnabled(true);
					} else {
						btnBuscar.setEnabled(false);
					}

					txtCedula.setText("");
					txtCedula.setSelectionStart(0);
					
				}
			});
			txtCedula.setBounds(66, 19, 98, 22);
			panel.add(txtCedula);
			
			txtRnc = new JFormattedTextField(new MaskFormatter("###-#####-#"));
			txtRnc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(EmpresaRps.getInstance().getMisclientes().size()>0 && rdbtnEmpresa.isSelected()) {
						btnBuscar.setEnabled(true);
					} else {
						btnBuscar.setEnabled(false);
					}
				}
			});
			txtRnc.setText("");
			txtRnc.setSelectionStart(0);
			txtRnc.setVisible(false);
			txtRnc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(EmpresaRps.getInstance().getMisclientes().size()>0 && rdbtnEmpresa.isSelected()) {
						btnBuscar.setEnabled(true);
					} else {
						btnBuscar.setEnabled(false);
					}

					txtRnc.setText("");
					txtRnc.setSelectionStart(0);
					
				}
			});
			txtRnc.setBounds(66, 19, 98, 20);
			panel.add(txtRnc);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.setIcon(new ImageIcon(ListarClientes.class.getResource("/img/004-interface.png")));
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
				btnEliminar.setIcon(new ImageIcon(ListarClientes.class.getResource("/img/deleted001.png")));
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String ind="";
						if(cliente instanceof Indepediente) {
							ind=((Indepediente) cliente).getCedula();
						} else if(cliente instanceof Empresa){
							ind=((Empresa) cliente).getRnc();
						}
						int opcion = JOptionPane.showConfirmDialog(null, "Desea eliminar a "+ind+" ??","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							
							EmpresaRps.getInstance().eliminarCliente(cliente);
							cargaTablaClientes();
							cargaTablaContractos(null);
						}
						
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
				btnCancelar.setIcon(new ImageIcon(ListarClientes.class.getResource("/img/001-delete.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							dispose();
						}
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
					modeloClientes.addRow(filaClientes);
				}
				
			} 
			
			tbClientes.setModel(modeloClientes);
			tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbClientes.getTableHeader().setResizingAllowed(false);
			TableColumnModel columnas1 = tbClientes.getColumnModel();
			columnas1.getColumn(0).setPreferredWidth(98);
			columnas1.getColumn(1).setPreferredWidth(135);


			
		}
		if(rdbtnEmpresa.isSelected()) {
			String[] columnas={"RNC","NOMBRE"};
			modeloClientes.setColumnIdentifiers(columnas);
			filaClientes=new Object[modeloClientes.getColumnCount()];
			for (Cliente cliente : EmpresaRps.getInstance().getMisclientes()) {
				if(cliente instanceof Empresa) {
					filaClientes[0]=((Empresa) cliente).getRnc();
					filaClientes[1]=((Empresa) cliente).getNombre();
					
					modeloClientes.addRow(filaClientes);
				}
				
				
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
			columnas1.getColumn(0).setPreferredWidth(98);
			columnas1.getColumn(1).setPreferredWidth(135);


			
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
		cargaTablaContractos(cliente);
		
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
		columnas1.getColumn(0).setPreferredWidth(98);
		columnas1.getColumn(1).setPreferredWidth(135);
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
