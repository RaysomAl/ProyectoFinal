package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Indepediente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistarCliente extends JDialog {


	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnClienteIndepediente;
	private JRadioButton rdbtnEmpresa;
	private JLabel lblIndetificacion;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JComboBox cbxProvincia;
	private JTextField txtEmail;
	private JButton btnRegistrar;
	private JLabel lblSector;
	private JLabel lblProvincia ;
	private static EmpresaRps empresaRps=null;
	private Cliente modiCliente=null;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPais;
	private JTextField txtCiudad;
	private JTextField txtSector;
	private JFormattedTextField txtTelefono;
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtRnc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistarCliente dialog = new RegistarCliente(null, "Registrar Cliente", null);//Ralddy- mantener el null de la derecha para la principal
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
	public RegistarCliente(EmpresaRps emp,String titulo,Cliente modificar) throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistarCliente.class.getResource("/img/add.cliente48.png")));
		empresaRps=emp;
		modiCliente=modificar;
		setTitle(titulo);
		setBounds(100, 100, 505, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelCliente = new JPanel();
			panelCliente.setBounds(5, 36, 474, 120);
			panelCliente.setBorder(new TitledBorder(null, "Datos de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelCliente);
			panelCliente.setLayout(null);
			
			lblIndetificacion = new JLabel("Cedula :");
			lblIndetificacion.setBounds(10, 27, 46, 14);
			panelCliente.add(lblIndetificacion);
			
			lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(10, 52, 88, 14);
			panelCliente.add(lblNombre);
			
			lblApellido = new JLabel("Apellido :");
			lblApellido.setBounds(220, 52, 69, 14);
			panelCliente.add(lblApellido);
			
			JLabel lblTelefono = new JLabel("Telefono :");
			lblTelefono.setBounds(10, 77, 69, 14);
			panelCliente.add(lblTelefono);
			
			lblEmail = new JLabel("E-mail :");
			lblEmail.setBounds(220, 77, 46, 14);
			panelCliente.add(lblEmail);
			
			txtEmail = new JTextField();
			txtEmail.setBounds(282, 74, 127, 20);
			panelCliente.add(txtEmail);
			txtEmail.setColumns(10);
			
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
					public void keyTyped(KeyEvent e) {
						char nom = e.getKeyChar();
						if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != 'á' && nom != 'é' && nom != 'í' && nom != 'ó' && nom != 'ú'))
							e.consume();
					}
				
			});
			txtNombre.setText("");
			txtNombre.setBounds(75, 49, 127, 20);
			panelCliente.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtApellido = new JTextField();
			txtApellido.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char nom = e.getKeyChar();
					if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != 'á' && nom != 'é' && nom != 'í' && nom != 'ó' && nom != 'ú'))
						e.consume();
				}
			});
			txtApellido.setText("");
			txtApellido.setBounds(282, 49, 127, 20);
			panelCliente.add(txtApellido);
			txtApellido.setColumns(10);
			
			txtTelefono = new JFormattedTextField(new MaskFormatter("###-###-####"));
			txtTelefono.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtTelefono.setText("");
				}
			});
			txtTelefono.setBounds(75, 74, 127, 20);
			txtTelefono.setText("");
			txtTelefono.setSelectionStart(0);
			panelCliente.add(txtTelefono);
			
			txtCedula = new JFormattedTextField(new MaskFormatter("###-#######-#"));
			txtCedula.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtCedula.setText("");
				}
			});
			txtCedula.setBounds(75, 24, 127, 20);
			txtCedula.setText("");
			txtCedula.setSelectionStart(0);
			panelCliente.add(txtCedula);
			
			txtRnc = new JFormattedTextField(new MaskFormatter("###-#####-#"));
			txtRnc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtRnc.setText("");
				}
			});
			txtRnc.setBounds(75, 24, 127, 20);
			txtRnc.setText("");
			txtRnc.setSelectionStart(0);
			panelCliente.add(txtRnc);
		}
		
		JPanel panelUbicacion = new JPanel();
		panelUbicacion.setBorder(new TitledBorder(null, "Datos de la Ubicacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUbicacion.setBounds(5, 158, 474, 92);
		contentPanel.add(panelUbicacion);
		panelUbicacion.setLayout(null);
		
		lblProvincia = new JLabel("Provincia :");
		lblProvincia.setBounds(10, 59, 84, 14);
		panelUbicacion.add(lblProvincia);
		
		JLabel lblCiudad = new JLabel("Ciudad :");
		lblCiudad.setBounds(219, 28, 46, 14);
		panelUbicacion.add(lblCiudad);
		
		lblSector = new JLabel("Sector :");
		lblSector.setBounds(219, 56, 71, 14);
		panelUbicacion.add(lblSector);  
		
		cbxProvincia = new JComboBox();
		cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Destrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Indenpedencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Montecristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
		cbxProvincia.setBounds(75, 56, 127, 20);
		panelUbicacion.add(cbxProvincia);
		
		JLabel lblPais = new JLabel("Pais :");
		lblPais.setBounds(10, 28, 46, 14);
		panelUbicacion.add(lblPais);
		
		txtPais = new JTextField();
		txtPais.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char nom = e.getKeyChar();
				if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != 'á' && nom != 'é' && nom != 'í' && nom != 'ó' && nom != 'ú'))
					e.consume();
			}
		});
		txtPais.setText("");
		txtPais.setBounds(75, 25, 127, 20);
		panelUbicacion.add(txtPais);
		txtPais.setColumns(10);
		
		txtCiudad = new JTextField();
		txtCiudad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char nom = e.getKeyChar();
				if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != 'á' && nom != 'é' && nom != 'í' && nom != 'ó' && nom != 'ú'))
					e.consume();
			}
		});
		txtCiudad.setText("");
		txtCiudad.setBounds(282, 25, 127, 20);
		panelUbicacion.add(txtCiudad);
		txtCiudad.setColumns(10);
		
		txtSector = new JTextField();
		txtSector.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char nom = e.getKeyChar();
				if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != 'á' && nom != 'é' && nom != 'í' && nom != 'ó' && nom != 'ú'))
					e.consume();
			}
		});
		txtSector.setText("");
		txtSector.setBounds(282, 56, 127, 20);
		panelUbicacion.add(txtSector);
		txtSector.setColumns(10);
		
		rdbtnEmpresa = new JRadioButton("Empresa");
		rdbtnClienteIndepediente = new JRadioButton("Cliente Indepediente");
		rdbtnEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnClienteIndepediente.setSelected(false);
				rdbtnEmpresa.setSelected(true);
				lblIndetificacion.setText("RNC :");
				lblApellido.setVisible(false);
				txtApellido.setVisible(false);
				txtNombre.setBounds(75, 49, 334, 20);
				lblEmail.setVisible(true);
				txtEmail.setVisible(true);
				lblProvincia.setVisible(false);
				cbxProvincia.setVisible(false);
				lblSector.setVisible(false);
				txtSector.setVisible(false);
				txtCedula.setVisible(false);
				txtRnc.setVisible(true);
				
				txtRnc.setText("");
				txtNombre.setText("");
				txtEmail.setText("");
				txtTelefono.setText("");
				txtCiudad.setText("");
				txtPais.setText("");
				
				
			}
		});
		
		
		rdbtnClienteIndepediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnClienteIndepediente.setSelected(true);
				rdbtnEmpresa.setSelected(false);
				lblIndetificacion.setText("Cedula :");
				lblApellido.setVisible(true);
				txtApellido.setVisible(true);
				txtNombre.setBounds(75, 49, 127, 20);
				lblEmail.setVisible(false);
				txtEmail.setVisible(false);
				lblProvincia.setVisible(true);
				cbxProvincia.setVisible(true);
				lblSector.setVisible(true);
				txtSector.setVisible(true);
				txtCedula.setVisible(true);
				txtRnc.setVisible(false);
				
				txtCedula.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtTelefono.setText("");
				txtEmail.setText("");
				txtPais.setText("");
				cbxProvincia.setSelectedIndex(0);
				txtCiudad.setText("");
				txtSector.setText("");
				
				
			}
		});
		rdbtnClienteIndepediente.setSelected(true);
		rdbtnClienteIndepediente.setBounds(6, 7, 158, 23);
		contentPanel.add(rdbtnClienteIndepediente);
		
		
		rdbtnEmpresa.setBounds(166, 7, 109, 23);
		contentPanel.add(rdbtnEmpresa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setIcon(new ImageIcon(RegistarCliente.class.getResource("/img/add.cliente.png")));
				if(modiCliente==null) {
					btnRegistrar.setText("Registrar");
				} else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(campoLleno()) {
							
							if(modiCliente==null) {
								if(rdbtnClienteIndepediente.isSelected()) {
									
									String cedula=txtCedula.getText();
									String nombre=txtNombre.getText();
									String ampellido=txtApellido.getText();
									String telefono=txtTelefono.getText();
									String email=txtEmail.getText();
									String pais=txtPais.getText();
									String provincia=cbxProvincia.getSelectedItem().toString();
									String ciudad=txtCiudad.getText();
									String sector=txtSector.getText();
									
									
									Cliente cliente=new Indepediente(cedula, nombre, ampellido, telefono, email, ciudad, sector, provincia, pais);
									boolean mensaje=EmpresaRps.getInstance().agregarCliente(cliente);
									

									
									if(mensaje) {
										JOptionPane.showMessageDialog(null, "operacion exitosa", "informacion",JOptionPane.INFORMATION_MESSAGE);
										txtCedula.setText("");
										txtNombre.setText("");
										txtApellido.setText("");
										txtTelefono.setText("");
										txtEmail.setText("");
										txtPais.setText("");
										cbxProvincia.setSelectedIndex(0);
										txtCiudad.setText("");
										txtSector.setText("");
									} else {
										JOptionPane.showMessageDialog(null, "La cedula : "+txtCedula.getText()+" ya estaba registrada,verifique el campo", "Advertencia",JOptionPane.WARNING_MESSAGE);
									}
									
								}
								
								if(rdbtnEmpresa.isSelected()) {
									String rnc=txtRnc.getText();
									String nombre=txtNombre.getText();
									String email=txtEmail.getText();
									String telefono=txtTelefono.getText();
									String ciudad=txtCiudad.getText();
									String pais=txtPais.getText();
									
									Cliente cliente=new Empresa(rnc, nombre, email, telefono, ciudad, pais);
									boolean mensaje=EmpresaRps.getInstance().agregarCliente(cliente);
									

									
									if(mensaje) {
										JOptionPane.showMessageDialog(null, "operacion exitosa", "informacion",JOptionPane.INFORMATION_MESSAGE);
										txtRnc.setText("");
										txtNombre.setText("");
										txtEmail.setText("");
										txtTelefono.setText("");
										txtCiudad.setText("");
										txtPais.setText("");
										
									} else {
										JOptionPane.showMessageDialog(null, "El RNC : "+txtRnc.getText()+" ya estaba registrad0, verifique el campo", "Advertencia",JOptionPane.WARNING_MESSAGE);
									}
									
									
								} 
							} else {
								
								if(rdbtnClienteIndepediente.isSelected()) {
									

									String nombre=txtNombre.getText();
									String ampellido=txtApellido.getText();
									String telefono=txtTelefono.getText();
									String email=txtEmail.getText();
									String pais=txtPais.getText();
									String provincia=cbxProvincia.getSelectedItem().toString();
									String ciudad=txtCiudad.getText();
									String sector=txtSector.getText();
									modiCliente.setNombre(nombre);
									((Indepediente)modiCliente).setAmpellido(ampellido);
									modiCliente.setTelefono(telefono);
									modiCliente.setEmail(email);
									modiCliente.setPais(pais);
									((Indepediente)modiCliente).setProvincia(provincia);
									((Indepediente)modiCliente).setCiudad(ciudad);
									((Indepediente)modiCliente).setSector(sector);
									
									
									EmpresaRps.getInstance().modificarCliente(modiCliente);
									JOptionPane.showMessageDialog(null, "operacion exitosa", "informacion",JOptionPane.INFORMATION_MESSAGE);
									

									
								}
								
								if(rdbtnEmpresa.isSelected()) {

									String nombre=txtNombre.getText();
									String email=txtEmail.getText();
									String telefono=txtTelefono.getText();
									String ciudad=txtCiudad.getText();
									String pais=txtPais.getText();
									modiCliente.setNombre(nombre);
									modiCliente.setEmail(email);
									modiCliente.setTelefono(telefono);
									modiCliente.setCiudad(ciudad);
									modiCliente.setPais(pais);
									
									EmpresaRps.getInstance().modificarCliente(modiCliente);
									JOptionPane.showMessageDialog(null, "operacion exitosa", "informacion",JOptionPane.INFORMATION_MESSAGE);
									
								} 
								ListarClientes.getBtnEliminar().setEnabled(false);
								ListarClientes.getBtnModificar().setEnabled(false);
								ListarClientes.cargaTablaClientes();
								ListarClientes.cargaTablaContractos(modiCliente);
								ListarClientes.getBtnBuscar().setEnabled(false);

								dispose();
							}
							 
						} else {
							JOptionPane.showMessageDialog(null, "Debe llenar todos los requisitos para poder avanzar", "Advertencia",JOptionPane.WARNING_MESSAGE);
						}

					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setIcon(new ImageIcon(RegistarCliente.class.getResource("/img/cancelar.cliente.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(modiCliente!=null) {
							ListarClientes.getBtnEliminar().setEnabled(false);
							ListarClientes.getBtnModificar().setEnabled(false);
						}

						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??  No sé salvará ningún Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							dispose();
						}
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}	
		cargaCliente();
	}
	
	public void cargaCliente() {//ralddy-metodo para obtener los datos de un cliente y llenan los campos cuando se modifique
		if(modiCliente!=null) {
			rdbtnClienteIndepediente.setEnabled(false);
			rdbtnEmpresa.setEnabled(false);
			
			if (modiCliente instanceof Indepediente) {
				
				rdbtnClienteIndepediente.setSelected(true);
				rdbtnEmpresa.setSelected(false);
				lblIndetificacion.setText("Cedula :");
				lblApellido.setVisible(true);
				txtApellido.setVisible(true);
				txtNombre.setBounds(75, 49, 127, 20);
				lblEmail.setVisible(false);
				txtEmail.setVisible(false);
				lblProvincia.setVisible(true);
				cbxProvincia.setVisible(true);
				lblSector.setVisible(true);
				txtSector.setVisible(true);
				txtCedula.setVisible(true);
				txtCedula.setEnabled(false);
				txtRnc.setVisible(false);
				
				txtCedula.setText(((Indepediente) modiCliente).getCedula());
				txtNombre.setText(modiCliente.getNombre());
				txtApellido.setText(((Indepediente) modiCliente).getAmpellido());
				txtTelefono.setText(modiCliente.getTelefono());
				txtEmail.setText(modiCliente.getEmail());
				txtPais.setText(modiCliente.getPais());
				txtSector.setText(((Indepediente) modiCliente).getSector());
				txtCiudad.setText(modiCliente.getCiudad());
				cbxProvincia.setSelectedItem(((Indepediente) modiCliente).getProvincia());
				
				
			} else if (modiCliente instanceof Empresa) {
				
				rdbtnClienteIndepediente.setSelected(false);
				rdbtnEmpresa.setSelected(true);
				lblIndetificacion.setText("RNC :");
				lblApellido.setVisible(false);
				txtApellido.setVisible(false);
				txtNombre.setBounds(75, 49, 334, 20);
				lblEmail.setVisible(true);
				txtEmail.setVisible(true);
				lblProvincia.setVisible(false);
				cbxProvincia.setVisible(false);
				lblSector.setVisible(false);
				txtSector.setVisible(false);
				txtCedula.setVisible(false);
				txtRnc.setVisible(true);
				txtRnc.setEnabled(false);
				
				txtRnc.setText(((Empresa) modiCliente).getRnc());
				txtNombre.setText(modiCliente.getNombre());
				txtTelefono.setText(modiCliente.getTelefono());
				txtEmail.setText(modiCliente.getEmail());
				txtPais.setText(modiCliente.getPais());
				txtCiudad.setText(modiCliente.getCiudad());


			}
			
		}
	}
	
public boolean campoLleno () {
	boolean l=true;
	if(rdbtnClienteIndepediente.isSelected()) {
		if(txtApellido.getText().equalsIgnoreCase("")||txtCedula.getText().equalsIgnoreCase("")||txtEmail.getText().equalsIgnoreCase("")||txtCiudad.getText().equalsIgnoreCase("")||txtNombre.getText().equalsIgnoreCase("")||txtPais.getText().equalsIgnoreCase("")||txtSector.getText().equalsIgnoreCase("")||txtTelefono.getText().equalsIgnoreCase("")) {
			l=false;
		}
	}
	if(rdbtnEmpresa.isSelected()) {
		if(txtNombre.getText().equalsIgnoreCase("")||txtCiudad.getText().equalsIgnoreCase("")||txtEmail.getText().equalsIgnoreCase("")||txtPais.getText().equalsIgnoreCase("")||txtRnc.getText().equalsIgnoreCase("")||txtTelefono.getText().equalsIgnoreCase("")) {
			l=false;
		}
	}
	return l;
	
}
	
	
}
